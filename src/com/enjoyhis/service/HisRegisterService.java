package com.enjoyhis.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caucho.hessian.client.HessianRuntimeException;
import com.enjoyhis.persistence.his.dao.HisPatientDao;
import com.enjoyhis.persistence.his.dao.HisRegisterDao;
import com.enjoyhis.persistence.his.po.HisEmployee;
import com.enjoyhis.persistence.his.po.HisPatient;
import com.enjoyhis.persistence.his.po.HisRegister;
import com.enjoyhis.pojo.BookingDoctor;
import com.enjoyhis.pojo.BookingInfo;
import com.enjoyhis.pojo.BookingPatient;
import com.enjoyhis.pojo.BookingPlan;
import com.enjoyhis.pojo.HisPatientPojo;
import com.enjoyhis.pojo.Register;
import com.enjoyhis.pojo.ReportSumPaymentTable;
import com.enjoyhis.pojo.WeekDay;
import com.enjoyhis.rmiclient.HesPatientService;
import com.enjoyhis.rmiclient.HesRegisterService;
import com.enjoyhis.util.BeanCopyUtil;
import com.enjoyhis.util.DateUtils;
import com.enjoyhis.util.HessianFactoryUtil;
import com.enjoyhis.util.HisEnum;
import com.enjoyhis.util.HisMqEnum;
import com.enjoyhis.util.LogUtils;
import com.enjoyhis.util.excel.PrintExcelUtil;

/**
 * 挂号、预约service
 * 
 * @author tianfei
 *
 */
@Service("hisRegisterService")
public class HisRegisterService {

	@Autowired
	private HisRegisterDao hisRegisterDao;
	@Autowired
	private HisPatientDao hisPatientDao;

	@Autowired
	private HisEmployeeService hisEmployeeService;// 医生service

	@Autowired
	private HisPatientService hisPatientService;// 患者service
	@Autowired
	private SysConfigService sysConfigService;// 院区service
	@Autowired
	private SysSeqService sysSeqService;
	@Autowired
	private HisRegisterCallService hisRegisterCallService;// 叫号service

	@Autowired
	private HisMqService hisMqService;

	private Logger log = LogUtils.CLIENT_TRACE;

	private HesRegisterService hesRegisterService = (HesRegisterService) HessianFactoryUtil.getHessianObj(HesRegisterService.class);
	private HesPatientService hesPatientService = (HesPatientService) HessianFactoryUtil.getHessianObj(HesPatientService.class);

	/**
	 * 保存挂号信息
	 * 
	 * @param patient
	 * @param register
	 * @param userId
	 * @param birthday
	 * @return
	 */
	@Transactional
	public HisPatient save(HisPatient patient, HisRegister register, Long userId, String birthday) {
		Date now = DateUtils.getDate();
		Integer isnew = 0;// 0旧，1新
		Map<String, String> sysConfig = sysConfigService.getSysConfig();
		Integer unitCode = Integer.parseInt(sysConfig.get("local_unit"));

		// 判断患者信息是否存在，如果不存在，需要在患者表里添加数据
		if (null == patient.getId()) {
			HisPatient hispatient = null;
			patient.setBirthday(DateUtils.str2Date(birthday, DateUtils.date_sdf));
			patient.setNewlyDate(now);
			hispatient = hisPatientService.save(2, patient);
			patient.setId(hispatient.getId());
			patient.setPatNo(hispatient.getPatNo());
			try {
				hesPatientService.insertPatient2jt(patient);
			} catch (HessianRuntimeException e) {
				log.error("hession runtime 异常");
				hisMqService.mqMsgToJt(hispatient.getId(), HisMqEnum.his_patient);
			}
			isnew = 1;
		} else {
			if (patient.getPatNo().startsWith("TEMP")) {
				isnew = 1;
				Long tempno = sysSeqService.getTableSeq(unitCode, "his_patient_no");
				String patientNo = tempno.toString();
				if (unitCode > 1000) {
					patientNo = patientNo.substring(0, patientNo.length() - unitCode.toString().length());
				} else if (unitCode >= 100) {
					patientNo = patientNo.substring(0, patientNo.length() - 1 - unitCode.toString().length());
				} else if (unitCode >= 10) {
					patientNo = patientNo.substring(0, patientNo.length() - 2 - unitCode.toString().length());
				} else {
					patientNo = patientNo.substring(0, patientNo.length() - 3 - unitCode.toString().length());
				}
				if (patientNo.length() > 5) {
					patient.setPatNo("10" + unitCode + DateUtils.date2Str(new Date(), DateUtils.yyyyMMdd).substring(2, 8) + patientNo.substring(patientNo.length() - 5, patientNo.length()));
				} else {
					patient.setPatNo("10" + unitCode + DateUtils.date2Str(new Date(), DateUtils.yyyyMMdd).substring(2, 8) + patientNo);
				}
				patient.setNewlyDate(now);
			}
			patient.setModifyTime(now);
			try {
				hesPatientService.insertPatient2jt(patient);
				hisPatientService.updateByPrimaryKeySelective(patient);
			} catch (HessianRuntimeException e) {
				log.error("hession runtime 异常");
				hisMqService.mqMsgToJt(patient.getId(), HisMqEnum.his_patient);
			}
		}

		Long registerId = sysSeqService.getTableSeq(unitCode, "his_register");
		if (null != registerId) {
			register.setId(registerId);
		}
		if (null != unitCode) {
			register.setUnitCode(unitCode);
		}
		if (null != userId) {
			register.setOperator(userId);
		}
		register.setPatId(patient.getId());
		register.setStatus(HisEnum.REGISTER.key);
		register.setIsAppoint(0);
		register.setIsnew(isnew);
		register.setCreateTime(now);
		register.setModifyTime(now);
		register.setGhTime(now);
		int insertSelective = hisRegisterDao.insertSelective(register);
		insertSelective = insertSelective / insertSelective; // 如果插入失败，这里会报错回滚
		try {
			hesRegisterService.insertRegister2jt(register);
		} catch (HessianRuntimeException e) {
			log.error("hession掉服务器接口错误");
			hisMqService.mqMsgToJt(register.getId(), HisMqEnum.his_register);
		}

		// 叫号系统添加数据
		try {
			hisRegisterCallService.save(registerId, now);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return patient;
	}

	/**
	 * 根据挂号单或预约单id查询数据
	 * 
	 * @param id
	 * @return
	 */
	public Register findById(Long id) {
		HisRegister hisRegister = hisRegisterDao.selectByPrimaryKey(id);
		return getRegisterInfo(hisRegister);
	}

	/**
	 * 获取患者信息
	 * 
	 * @param hisRegister
	 * @return
	 */
	private Register getRegisterInfo(HisRegister hisRegister) {
		Register register = new Register();
		try {
			BeanCopyUtil.copyProperties(hisRegister, register);
			if (null != register.getBeginTime() && null != register.getEndTime()) {
				register.setBookingDate(DateUtils.date_sdf.format(register.getBeginTime()));
				register.setBegin(DateUtils.formatShortTime(register.getBeginTime()));
				register.setEnd(DateUtils.formatShortTime(register.getEndTime()));
			}
			if (null != register.getDentistId()) {
				HisEmployee maindoc = hisEmployeeService.findById(register.getDentistId());
				register.setMaindocName(maindoc.getEmployeeName());
			}
			if (null != register.getPatId()) {
				HisPatient patient = hisPatientService.findById(register.getPatId());
				if (null == patient) {
					patient = hesPatientService.findById(register.getPatId());
				}
				register.setPatient(patient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return register;
	}

	/**
	 * 修改挂号操作
	 * 
	 * @param id
	 * @param source
	 * @param deptCode
	 * @param dentistId
	 * @param serviceItems
	 * @param isNew
	 * @return
	 */
	@Transactional
	public boolean update(HisRegister register) {
		// 把以前所有的register表里此患者的isnew 都设置为0
		if (register.getIsnew() != null && register.getIsnew() == 1 && register.getStatus() == HisEnum.BOOKING.key) {
		} else if (register.getIsnew() != null && register.getIsnew() == 1 && register.getStatus() != HisEnum.BOOKING.key) {
			HisRegister hisRegister2 = new HisRegister();
			hisRegister2.setPatId(register.getPatId());
			List<HisRegister> selectRegisterList = hisRegisterDao.selectRegisterList(hisRegister2);
			for (int i = 0; i < selectRegisterList.size(); i++) {
				HisRegister hisRegister3 = selectRegisterList.get(i);
				hisRegister3.setIsnew(0);
				hisRegisterDao.updateByPrimaryKeySelective(hisRegister3);
			}
			register.setIsnew(0);
		}
		register.setModifyTime(new Date());
		register.setGhTime(new Date());
		register.setStatus(HisEnum.REGISTER.key);
		hisRegisterDao.updateByPrimaryKeySelective(register);
		try {
			hesRegisterService.insertRegister2jt(register);
		} catch (HessianRuntimeException e) {
			hisMqService.mqMsgToJt(register.getId(), HisMqEnum.his_register);
			log.error("hession掉服务器接口错误");
		}
		return true;
	}

	/**
	 * 修改预约（患者信息不能修改，可修改vip类型）
	 * 
	 * @param id
	 * @param dentistId
	 * @param bookingDate
	 * @param begin
	 * @param end
	 * @param serviceItems
	 * @param remark
	 * @return
	 */
	@Transactional
	public boolean updateBooking(Long id, Long patId, String patName, String patNo, String userSex, String mobile, Integer age, String birthday, Long dentistId, String bookingDate, String begin, String end, String serviceItems, String remark, String allergicHis, Integer isAppoint, Integer status, Long userId, String source, String type, int affirm) {
		HisRegister hisRegister = new HisRegister();
		hisRegister.setId(id);
		hisRegister.setModifyTime(new Date());
		hisRegister.setDentistId(dentistId);

		hisRegister.setServiceItems(serviceItems);
		hisRegister.setStatus(HisEnum.BOOKING.key);

		Date beginTime = null;
		Date endTime = null;
		try {
			String date = DateUtils.formatDate(bookingDate);
			beginTime = DateUtils.parseDate(date + " " + begin + ":00", "yyyy-MM-dd HH:mm:ss");
			endTime = DateUtils.parseDate(date + " " + end + ":00", "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Integer appoLen = Integer.parseInt(((Long) (endTime.getTime() - beginTime.getTime())).toString()) / 1000 / 60;
		hisRegister.setBeginTime(beginTime);
		hisRegister.setEndTime(endTime);
		hisRegister.setAppoLen(appoLen);
		hisRegister.setAffirm(affirm);
		hisRegister.setRemark(remark);

		HisPatientPojo hispatient = hisPatientService.findById(patId);
		hispatient.setType(type);
		hisPatientDao.updateByPrimaryKey(hispatient);

		hisRegisterDao.updateByPrimaryKeySelective(hisRegister);
		try {
			hesRegisterService.insertRegister2jt(hisRegister);
		} catch (HessianRuntimeException e) {
			log.error("hession掉服务器接口错误");
			hisMqService.mqMsgToJt(hisRegister.getId(), HisMqEnum.his_register);
		}
		return true;
	}

	public List<HisPatientPojo> findPatient(String patName, String mobile, Long maindocId, Integer pageNumber, Integer pageSize) {
		HisPatient record = new HisPatient();
		record.setPatName(patName);
		record.setMobile(mobile);
		record.setMaindocId(maindocId);
		record.setSqlSort("id desc");
		record.setLimitCount(pageSize);
		Long limitStart = (long) (pageSize * (pageNumber - 1));
		record.setLimitStart(limitStart);
		return hisPatientService.findPatientList(record);
	}

	public Integer findPatientCount(String patName, String mobile, Long maindocId) {
		HisPatient record = new HisPatient();
		record.setPatName(patName);
		record.setMobile(mobile);
		record.setMaindocId(maindocId);
		return hisPatientService.findPatientCount(record);
	}

	/**
	 * 查询今日就诊列表
	 * 
	 * @param dentistId
	 * @param dateTime
	 * @param name
	 * @param mobile
	 * @param type
	 * @param pageNumber
	 * @param pageSize
	 * @param userType
	 * @return
	 */
	public List<Object> findRegister(Long dentistId, Date dateTime, String name, String mobile, Integer type, Integer pageNumber, Integer pageSize, Integer userType, String sortName, String sortOrder) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dateTime", null == dateTime ? new Date() : dateTime);
		map.put("dentistId", dentistId);
		map.put("name", name);
		map.put("mobile", mobile);
		map.put("type", type);
		map.put("sortName", sortName);
		map.put("sortOrder", sortOrder);
		List<Object> jtList = hisRegisterDao.findRegister(map);
		// if (userType == 1) {
		// try {
		// jtList = hesRegisterService.findRegister(map);
		// } catch (HessianRuntimeException e) {
		// log.error("hession掉服务器接口错误");
		// }
		// }
		// 云上没有再查本地
		// if (null == jtList || jtList.size() == 0) {
		// jtList = hisRegisterDao.findRegister(map);
		// }
		return jtList;
	}

	public int selectCount(Long dentistId, Date dateTime, String name, String mobile, Integer type, Integer userType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dateTime", null == dateTime ? new Date() : dateTime);
		map.put("dentistId", dentistId);
		map.put("name", name);
		map.put("mobile", mobile);
		map.put("type", type);
		return hisRegisterDao.selectRegisterCount(map);
	}

	/**
	 * 保存预约
	 */
	@Transactional
	public HisPatient saveBooking(Long id, Long patId, String patName, String patNo, String userSex, String mobile, Integer age, String birthday, Long dentistId, String bookingDate, String begin, String end, String serviceItems, String remark, String allergicHis, Integer isAppoint, Integer status, Long userId, String source, String type, int affirm, String introducer, String unitCodeS) {
		Date now = DateUtils.getDate();
		Integer isnew = 0;// 0旧患者，1新患者
		Map<String, String> sysConfig = sysConfigService.getSysConfig();
		String localUnit = sysConfig.get("local_unit");
		if (StringUtils.isEmpty(unitCodeS)) {
			unitCodeS = localUnit;
		}
		Integer unitCode = Integer.valueOf(unitCodeS);
		Integer localUnitCode = Integer.valueOf(localUnit);

		// 判断患者信息是否存在
		HisPatient hispatient = null;
		if (null == patId) {// 如果不存在，需要在患者表里添加数据
			HisPatient patient = new HisPatient(null, patName, null, unitCode, now, now, mobile, patNo, userSex, age, DateUtils.str2Date(birthday, DateUtils.date_sdf), dentistId, null, null, null, null, null, remark, source, null, null, allergicHis, null, null, null, null, null, introducer, null, null, null, null, type);
			hispatient = hisPatientService.save(1, patient);
			patId = hispatient.getId();
			// 集团添加或更新患者
			try {
				hesPatientService.insertPatient2jt(hispatient);
			} catch (HessianRuntimeException e1) {
				log.error("hession掉服务器接口错误");
				hisMqService.mqMsgToJt(hispatient.getId(), HisMqEnum.his_patient);
			}
			isnew = 1;// 新
		} else {
			try {
				hispatient = hesPatientService.findById(patId);
			} catch (HessianRuntimeException e) {
				log.error("hession掉服务器接口错误");
			}
			HisPatient fyHisPatient = hisPatientDao.selectByPrimaryKey(patId);
			if (hispatient == null) {
				hispatient = fyHisPatient;
			}
			if (hispatient != null) {
				hispatient.setLastappointDate(now);// 最近预约
				hispatient.setModifyTime(now); // 修改时间
				hispatient.setType(type);// vip患者类型
				hispatient.setIntroducer(introducer); // 预约人
			}
			// 本地添加或更新患者
			if (fyHisPatient == null && hispatient != null) {
				hisPatientDao.insertSelective(hispatient);
			} else {
				hisPatientDao.updateByPrimaryKeySelective(hispatient);
			}
			// 集团添加或更新患者
			try {
				hesPatientService.insertPatient2jt(hispatient);
			} catch (HessianRuntimeException e1) {
				log.error("hession掉服务器接口错误");
				hisMqService.mqMsgToJt(hispatient.getId(), HisMqEnum.his_patient);
			}
		}
		// 新建一条预约数据
		Long registerId = sysSeqService.getTableSeq(localUnitCode, "his_register");
		HisRegister record = new HisRegister();
		record.setId(registerId);
		record.setIntroducer(introducer);
		record.setPatId(patId);
		record.setDentistId(dentistId);
		record.setUnitCode(unitCode);
		record.setServiceItems(serviceItems);
		record.setOperator(userId);
		record.setStatus(HisEnum.BOOKING.key);
		record.setIsAppoint(1);
		record.setRemark(remark);
		record.setAffirm(affirm);
		if (hispatient.getPatNo().startsWith("TEMP")) {
			isnew = 1;
		} else {
			isnew = 0;
		}
		record.setIsnew(isnew);
		record.setCreateTime(now);
		record.setModifyTime(now);
		Date beginTime = null;
		Date endTime = null;
		try {
			String date = DateUtils.formatDate(bookingDate);
			beginTime = DateUtils.parseDate(date + " " + begin + ":00", "yyyy-MM-dd HH:mm:ss");
			endTime = DateUtils.parseDate(date + " " + end + ":00", "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Integer appoLen = Integer.parseInt(((Long) (endTime.getTime() - beginTime.getTime())).toString()) / 1000 / 60;
		record.setBeginTime(beginTime);
		record.setEndTime(endTime);
		record.setAppoLen(appoLen);
		record.setSource(source);
		int insertValue = 0;

		if (Integer.parseInt(localUnit) == unitCode) {
			insertValue = hisRegisterDao.insertSelective(record);
			insertValue = insertValue / insertValue; // 如果插入失败，则报错回滚
		}
		try {
			hesRegisterService.insertRegister2jt(record);
		} catch (HessianRuntimeException e) {
			log.error("hession掉服务器接口错误");
			hisMqService.mqMsgToJt(record.getId(), HisMqEnum.his_register);
		}
		return hispatient;
	}

	/**
	 * 修改挂号单状态
	 * 
	 * @param registerId
	 * @param statementItemid
	 * @param status
	 * @param modifyTime
	 */
	public void updateStatus(Long registerId, Long statementItemid, Integer status, Date modifyTime) {
		HisRegister record = new HisRegister();
		record.setId(registerId);
		record.setStatementItemid(statementItemid);
		record.setStatus(status);
		record.setModifyTime(modifyTime);
		hisRegisterDao.updateByPrimaryKeySelective(record);
	}

	/**
	 * 退号操作
	 * 
	 * @author tianfei
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean exitRegister(Long id, String remark) {
		HisRegister record = new HisRegister();
		record.setId(id);
		record.setRemark(remark);
		record.setStatus(HisEnum.EXIT.key);
		record.setModifyTime(new Date());
		hisRegisterDao.updateByPrimaryKeySelective(record);
		try {
			hesRegisterService.insertRegister2jt(record);
		} catch (HessianRuntimeException e) {
			log.error("hessian同步方法exitRegister()，退号失败。");
			hisMqService.mqMsgToJt(id, HisMqEnum.his_register);
		}
		return true;
	}

	@Transactional
	public boolean exitBooking(Long id, String remark) {
		HisRegister record = new HisRegister();
		record.setId(id);
		record.setRemark(remark);
		record.setStatus(HisEnum.CANCEL_BOOKING.key);
		record.setModifyTime(new Date());
		hisRegisterDao.updateByPrimaryKeySelective(record);
		try {
			hesRegisterService.insertRegister2jt(record);
		} catch (HessianRuntimeException e) {
			log.error("hession掉服务器接口错误");
			hisMqService.mqMsgToJt(record.getId(), HisMqEnum.his_register);
		}
		return true;
	}

	public List<Register> query4List(HisRegister record) {
		List<HisRegister> selectList = hisRegisterDao.selectList(record);
		List<Register> list = new ArrayList<Register>();
		if (null != selectList && selectList.size() > 0) {
			for (HisRegister one : selectList) {
				list.add(getRegisterInfo(one));
			}
		}
		return list;
	}

	// 护士
	public BookingPlan bookingPlanData(Date bookingDate, String doctorstr, boolean isBookingDocs) {
		BookingPlan bookingPlan = new BookingPlan();
		List<BookingDoctor> doctors = new ArrayList<BookingDoctor>();
		List<BookingPatient> patients = new ArrayList<BookingPatient>();

		// 查询需要显示的医生列表
		List<HisEmployee> employeeList = new ArrayList<HisEmployee>();
		if (isBookingDocs) {// 只查看预约的医生
			employeeList = findBookingDocsByDate(bookingDate);
		} else {
			String[] arr = doctorstr.split(",");
			for (int i = 0; i < arr.length; i++) {
				HisEmployee doctor = hisEmployeeService.getHisEmployeeOne(Long.parseLong(arr[i]));
				if (null != doctor) {
					employeeList.add(doctor);
				}
			}
		}

		for (HisEmployee one : employeeList) {
			BookingDoctor doc = new BookingDoctor();
			doc.setId(one.getId());
			doc.setDocName(one.getEmployeeName());
			doctors.add(doc);
			HisRegister record2 = new HisRegister();
			record2.setStatus(HisEnum.BOOKING.key);
			record2.setBeginTime(bookingDate);
			record2.setDentistId(one.getId());
			List<HisRegister> registerList = hisRegisterDao.selectList(record2);
			List<List<BookingPatient>> obj = getBookingPatientByDoc(registerList, one.getId());
			for (List<BookingPatient> list : obj) {
				patients.addAll(list);
			}
		}
		bookingPlan.setDoctors(doctors);
		bookingPlan.setPatients(patients);
		return bookingPlan;
	}

	// 医生
	public Map<String, Object> doctorPlanData(Date bookingDate, Long doctorId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<BookingPatient> patients = new ArrayList<BookingPatient>();

		// 查询未来一周时间
		List<WeekDay> weekDays = DateUtils.getWeekDay(bookingDate);

		for (WeekDay one : weekDays) {
			HisRegister record2 = new HisRegister();
			record2.setStatus(HisEnum.BOOKING.key);
			record2.setBeginTime(DateUtils.str2Date(one.getDate(), DateUtils.date_sdf));
			record2.setDentistId(doctorId);
			List<HisRegister> registerList = hisRegisterDao.selectList(record2);
			List<List<BookingPatient>> obj = getBookingPatientByDoc(registerList, doctorId);
			for (List<BookingPatient> list : obj) {
				patients.addAll(list);
			}
		}
		map.put("weekDays", weekDays);
		map.put("patients", patients);
		return map;
	}

	/**
	 * 根据开始时间、结束时间、医生ID来获取所有预约
	 * 
	 * @param wujiaxing
	 */
	public List<BookingInfo> doctorPlanData(Date beginTime, Date endTime, String[] doctorId) {
		HisRegister hisRegister = new HisRegister();
		// hisRegister.setStatus(HisEnum.BOOKING.key);
		hisRegister.setIsAppoint(1);
		hisRegister.setBeginTime(beginTime);
		hisRegister.setEndTime(endTime);
		hisRegister.setSqlStr(" and status!=6");
		List<BookingInfo> bookingInfoList = new ArrayList<>();
		if (doctorId == null) {
			List<HisRegister> registerList = hisRegisterDao.selectRegisterList(hisRegister);
			for (int i = 0; i < registerList.size(); i++) {
				HisRegister register = registerList.get(i);
				BookingInfo bookingInfo = new BookingInfo();
				bookingInfo.setId(register.getId());
				bookingInfo.setDocId(Long.valueOf(register.getDentistId()));
				bookingInfo.setDate(DateUtils.date2Str(register.getBeginTime(), DateUtils.date_sdf));
				bookingInfo.setBeginTime(DateUtils.date2Str(register.getBeginTime(), DateUtils.short_time_sdf));
				bookingInfo.setEndTime(DateUtils.date2Str(register.getEndTime(), DateUtils.short_time_sdf));
				bookingInfo.setPatientName(register.getPatName());
				bookingInfo.setServiceItems(register.getServiceItems());
				bookingInfo.setAffirm(register.getAffirm());
				bookingInfo.setPatientType(register.getPatType());
				bookingInfo.setStatus(register.getStatus());
				bookingInfoList.add(bookingInfo);
			}
		} else {
			for (int i = 0; i < doctorId.length; i++) { // 每一个医生的预约
				hisRegister.setDentistId(Long.valueOf(doctorId[i]));
				List<HisRegister> registerList = hisRegisterDao.selectRegisterList(hisRegister);
				for (int j = 0; j < registerList.size(); j++) {
					HisRegister register = registerList.get(j);
					BookingInfo bookingInfo = new BookingInfo();
					bookingInfo.setId(register.getId());
					bookingInfo.setDocId(Long.valueOf(doctorId[i]));
					bookingInfo.setDate(DateUtils.date2Str(register.getBeginTime(), DateUtils.date_sdf));
					bookingInfo.setBeginTime(DateUtils.date2Str(register.getBeginTime(), DateUtils.short_time_sdf));
					bookingInfo.setEndTime(DateUtils.date2Str(register.getEndTime(), DateUtils.short_time_sdf));
					bookingInfo.setPatientName(register.getPatName());
					bookingInfo.setServiceItems(register.getServiceItems());
					bookingInfo.setAffirm(register.getAffirm());
					bookingInfo.setPatientType(register.getPatType());
					bookingInfo.setStatus(register.getStatus());
					bookingInfoList.add(bookingInfo);
				}
			}
		}

		return bookingInfoList;
	}

	private List<List<BookingPatient>> getBookingPatientByDoc(List<HisRegister> registerList, Long docId) {
		List<List<BookingPatient>> list = new ArrayList<List<BookingPatient>>();
		int trNum = 1;
		for (int i = 1; i <= 2; i++) { // 2列
			List<BookingPatient> temp = new ArrayList<BookingPatient>();
			for (int j = 1; j <= 56; j++) { // 56行
				BookingPatient one = new BookingPatient();
				one.setId(docId + "-" + i + "-" + j);
				one.setColumn(docId + "-" + i + "-");
				one.setDocId(docId);
				one.setGroup(0);
				one.setSortNum(0);
				one.setNum(trNum);
				one.setX(i);
				one.setY(j);
				one.setColor(false);
				one.setServiceItems("");
				int h = (j - 1) / 4 + 7;
				int m = (j - 1) % 4 * 15;
				one.setBegin(h + ":" + (m == 0 ? "00" : m));
				temp.add(one);
				trNum++;
				if (trNum > 56) {
					trNum = 1;
				}
			}
			list.add(temp);
		}
		if (null == registerList || registerList.size() == 0) {
			return list;
		}
		int group = 1;
		for (HisRegister one : registerList) {// 遍历每一条预约信息
			Map<String, Object> map = getBookingDetail(one);
			for (int i = 0; i < list.size(); i++) {
				List<BookingPatient> tempList = list.get(i);
				Integer beginNum = (Integer) map.get("beginNum");
				Integer endNum = (Integer) map.get("endNum");

				boolean flag = false;// 判断需要操作的格子有没有颜色，只有都满足条件，没有颜色才可以进行添加颜色操作
				for (int j = beginNum; j < endNum; j++) {// 判断两个时间段之间格子有没有被占有，如果占有，直接跳出本循环（也就是跳出本列，默认为四列，去下一列找空格子）
					if (tempList.get(j).isColor()) {
						flag = true;
						break;
					}
				}

				if (flag)// 如果格子中有颜色，说明不能进行添加颜色操作，直接进入下次循环
					continue;
				else {// 添加颜色操作
					boolean first = true;// 是否是组中的第一个（只给第一个添加备注）
					int sortNum = 1;
					for (int j = beginNum; j < endNum; j++) {
						tempList.get(j).setColor(true);
						tempList.get(j).setServiceItems(one.getServiceItems());
						tempList.get(j).setFirst(first);
						// tempList.get(j).setTdcolor(tdcolor);
						tempList.get(j).setGroup(group);
						tempList.get(j).setSortNum(sortNum);
						tempList.get(j).setBeginNum(beginNum);
						tempList.get(j).setEndNum(endNum);
						sortNum++;
						first = false;
					}
					group++;
					break;
				}
			}
		}

		return list;
	}

	/**
	 * 根据预约信息，获取开始时间和结束时间编号（eg:07:00=1, 07:15=2）,和时长
	 * 
	 * @param one
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private Map<String, Object> getBookingDetail(HisRegister one) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date beginTime = one.getBeginTime();
		Date endTime = one.getEndTime();
		int beginNum = (beginTime.getHours() - 7) * 4 + (beginTime.getMinutes() / 15);
		int endNum = (endTime.getHours() - 7) * 4 + (endTime.getMinutes() / 15);
		int appolen = one.getAppoLen() / 15;
		String begin = beginTime.getHours() + ":" + beginTime.getMinutes();
		map.put("beginNum", beginNum);
		map.put("endNum", endNum);
		map.put("appolen", appolen);
		map.put("begin", begin);
		return map;
	}

	/**
	 * 修改今日工作（护士站）短信或电话回访操作
	 * 
	 * @param registerId
	 * @param type 1:电话回访 2:短信回访
	 */
	@Transactional
	public void updateMsgOrCall(Long registerId, Integer type) {
		HisRegister record = new HisRegister();
		record.setId(registerId);
		if (type == 1)// 电话回访
			record.setOutCall("1");
		else
			record.setMsgId(1L);
		hisRegisterDao.updateByPrimaryKeySelective(record);
	}

	/**
	 * 预约未到报表数据（报表）
	 * 
	 * @author tianfei
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List<Object> nonarrivalReportData(String beginDate, String endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date begin = DateUtils.str2Date(beginDate, DateUtils.date_sdf);
		Date end = DateUtils.str2Date(endDate, DateUtils.date_sdf);
		map.put("beginDate", begin);
		map.put("endDate", end);
		return hisRegisterDao.nonarrivalReportData(map);
	}

	public byte[] getCardDetailListExcel(List<Object> list) throws Exception {
		List<Map<String, String>> resultList = new ArrayList<>();
		for (Object o : list) {
			Map<String, String> map = (Map<String, String>) o;
			resultList.add(map);
		}

		Workbook wb = PrintExcelUtil.getWeebWork(false);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> header = new ArrayList<String>();
		header.add("序号");
		header.add("就诊日期");
		header.add("患者总数");
		header.add("挂号总数");
		header.add("初诊挂号人数");
		header.add("复诊挂号人数");
		header.add("预约人数");
		header.add("预约未到");

		List<Object> dataList = new ArrayList<Object>();
		int index = 1;
		for (Map<String, String> object : resultList) {
			TreeMap<String, Object> treeMap = new TreeMap<String, Object>();// 此处的数据必须为有序数据，所以使用TreeMap进行封装
			treeMap.put("A", index++);
			treeMap.put("B", object.get("registerDate"));
			treeMap.put("C", object.get("patientNum"));
			treeMap.put("D", object.get("registerNum"));
			treeMap.put("E", object.get("newRegisterNum"));
			treeMap.put("F", object.get("oldRegisterNum"));
			treeMap.put("G", object.get("bookingNum"));
			treeMap.put("H", object.get("nonArrival"));
			dataList.add(treeMap);
		}

		map.put(PrintExcelUtil.HEADERINFO, header);
		map.put(PrintExcelUtil.DATAINFON, dataList);
		wb = PrintExcelUtil.saveExcelData(wb, map);
		// PrintExcelUtil.setColumnAlign(wb,
		// "right","B","F","G","H","I","J","O","P");
		// PrintExcelUtil.setColumnAlign(wb, "left", "C");
		// PrintExcelUtil.setColumnwidth(wb, 4000, "B", "C");
		byte[] bs = PrintExcelUtil.writeExcel(wb);
		return bs;
	}

	public byte[] getCardDetailListExcel2(List<Object> list) throws Exception {
		List<Map<String, String>> resultList = new ArrayList<>();
		for (Object o : list) {
			Map<String, String> map = (Map<String, String>) o;
			resultList.add(map);
		}

		Workbook wb = PrintExcelUtil.getWeebWork(false);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> header = new ArrayList<String>();
		header.add("序号");
		header.add("患者ID");
		header.add("病历编号");
		header.add("患者姓名");
		header.add("手机号");
		header.add("预约日期");
		header.add("时间");
		header.add("需时");
		header.add("预约事项");
		header.add("预约备注");
		header.add("医师");
		header.add("最后就诊日");
		header.add("操作人");

		List<Object> dataList = new ArrayList<Object>();
		int index = 1;
		for (Map<String, String> object : resultList) {
			TreeMap<String, Object> treeMap = new TreeMap<String, Object>();// 此处的数据必须为有序数据，所以使用TreeMap进行封装
			treeMap.put("A", index++);
			treeMap.put("B", object.get("patId"));
			treeMap.put("C", object.get("patNo"));
			treeMap.put("D", object.get("patName"));
			treeMap.put("E", object.get("mobile"));
			treeMap.put("F", object.get("beginDate"));
			treeMap.put("G", object.get("beginTime"));
			treeMap.put("H", object.get("appoLen"));
			treeMap.put("I", object.get("serviceItems"));
			treeMap.put("J", object.get("remark"));
			treeMap.put("K", object.get("doctorName"));
			treeMap.put("L", object.get("lasthospDate"));
			treeMap.put("M", object.get("operator"));
			dataList.add(treeMap);
		}

		map.put(PrintExcelUtil.HEADERINFO, header);
		map.put(PrintExcelUtil.DATAINFON, dataList);
		wb = PrintExcelUtil.saveExcelData(wb, map);
		// PrintExcelUtil.setColumnAlign(wb,
		// "right","B","F","G","H","I","J","O","P");
		// PrintExcelUtil.setColumnAlign(wb, "left", "C");
		// PrintExcelUtil.setColumnwidth(wb, 4000, "B", "C");
		byte[] bs = PrintExcelUtil.writeExcel(wb);
		return bs;
	}

	public List<Object> patientReportData(String beginDate, String endDate, Long doctorId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date begin = DateUtils.str2Date(beginDate, DateUtils.date_sdf);
		Date end = DateUtils.str2Date(endDate, DateUtils.date_sdf);
		map.put("beginDate", begin);
		map.put("endDate", end);
		map.put("dentistId", doctorId);
		return hisRegisterDao.patientReportData(map);
	}

	/**
	 * 根据日期查看被预约的医生集合
	 * 
	 * @param bookingDate
	 * @return
	 */
	public List<HisEmployee> findBookingDocsByDate(Date bookingDate) {
		List<HisEmployee> employeeList = new ArrayList<HisEmployee>();
		Long[] ids = hisRegisterDao.findBookingDocsByDate(bookingDate);
		for (Long id : ids) {
			HisEmployee doctor = hisEmployeeService.getHisEmployeeOne(id);
			if (null != doctor)
				employeeList.add(doctor);
		}
		return employeeList;
	}

	public List<HisRegister> doctorPlanByTime(Date currentTime, String doctorId) {
		Map<String, Object> map = new HashMap<>();
		map.put("currentTime", currentTime);
		map.put("doctorId", doctorId);
		List<HisRegister> registerList = hisRegisterDao.selectRegisterList(map);
		return registerList;
	}
}
