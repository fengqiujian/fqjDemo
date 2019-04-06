package com.enjoyhis.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.base.DBRecord;
import com.enjoyhis.persistence.his.dao.HisPaymentDao;
import com.enjoyhis.persistence.his.dao.HisRegisterDao;
import com.enjoyhis.persistence.his.dao.HisStatementChargeDao;
import com.enjoyhis.persistence.his.dao.ReportDao;
import com.enjoyhis.persistence.his.po.HisPayment;
import com.enjoyhis.persistence.his.po.HisRegister;
import com.enjoyhis.persistence.his.po.HisStatementCharge;
import com.enjoyhis.util.excel.PrintExcelUtil;

@Service("sourceReportService")
public class SourceReportService {

	@Autowired
	HisRegisterDao hisRegisterDao;
	@Autowired
	HisPaymentDao hisPaymentDao;
	@Autowired
	ReportDao reportDao;
	@Autowired
	HisStatementChargeDao hisStatementChargeDao;
	
	
	
	public byte[]  getCardDetailListExcel3(List<Object> list) throws Exception {
		List<Map<String,Object>> resultList = new ArrayList<>();
		for(Object o : list){
			Map<String,Object> map = (Map<String,Object>)o;
			resultList.add(map);
		}
		Workbook wb = PrintExcelUtil.getWeebWork(false);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> header = new ArrayList<String>();
		header.add("序号");
		header.add("时间");
		header.add("病例编号");
		header.add("患者");
		header.add("就诊医生");
		header.add("账单类型");
		header.add("处置单编号");
		header.add("处置单总额");
		header.add("收费单编号");
		header.add("优惠");
		header.add("建设银行");
		header.add("招商银行");
		header.add("通联");
		header.add("拉卡拉");
		header.add("工商银行");
		header.add("北京银行");
		header.add("银联商务");
		header.add("民生银行");
		header.add("浦发银行");
		header.add("银行借记卡");
		header.add("微信");
		header.add("现金");
		header.add("支票");
		header.add("保险");
		header.add("预收款转入");
		header.add("团购");
		header.add("欠费");
		header.add("抹零");
		header.add("约克平台");
		header.add("约克牙医微信");
		header.add("现金折扣");
		header.add("收费单总额");
		header.add("原处置单号");
		header.add("备注");
		
		
		List<Object> dataList = new ArrayList<Object>();
		int index = 1;
		for (Map<String,Object> object : resultList) {
			TreeMap<String, Object> treeMap = new TreeMap<String, Object>();// 此处的数据必须为有序数据，所以使用TreeMap进行封装
			treeMap.put("A", index++);
			treeMap.put("AA",object.get("yczdh")==null?"":object.get("yczdh"));
			treeMap.put("AB",object.get("bz")==null?"":object.get("bz"));
			treeMap.put("AC", object.get("sj"));
			treeMap.put("AD", object.get("blbh"));
			treeMap.put("AE", object.get("hzxm"));
			treeMap.put("AF", object.get("ys"));
			treeMap.put("AG", object.get("lx"));
			treeMap.put("AH", object.get("czdid"));
			treeMap.put("B", object.get("czdze"));
			if(object.get("sfdbh2")!=null&&object.get("sfdbh2")!=""){
				treeMap.put("C", object.get("sfdbh2"));
			}else{
				treeMap.put("C", object.get("sfdbh"));
			}
			treeMap.put("D", object.get("yh")==null?"0.00":object.get("yh"));
			treeMap.put("E",object.get("jsyh")==null?"0.00":object.get("jsyh"));
			treeMap.put("F",object.get("zsyh")==null?"0.00":object.get("zsyh"));
			treeMap.put("G",object.get("tl")==null?"0.00":object.get("tl"));
			treeMap.put("H",object.get("lkl")==null?"0.00":object.get("lkl"));
			treeMap.put("I",object.get("gsyh")==null?"0.00":object.get("gsyh"));
			treeMap.put("J",object.get("bjyh")==null?"0.00":object.get("bjyh"));
			treeMap.put("K",object.get("ylsw")==null?"0.00":object.get("ylsw"));
			treeMap.put("L",object.get("msyh")==null?"0.00":object.get("msyh"));
			treeMap.put("M",object.get("pfyh")==null?"0.00":object.get("pfyh"));
			treeMap.put("N",object.get("yhjjk")==null?"0.00":object.get("yhjjk"));
			treeMap.put("O",object.get("wx")==null?"0.00":object.get("wx"));
			treeMap.put("P",object.get("xj")==null?"0.00":object.get("xj"));
			treeMap.put("Q",object.get("zp")==null?"0.00":object.get("zp"));
			treeMap.put("R",object.get("bx")==null?"0.00":object.get("bx"));
			treeMap.put("S",object.get("yskzr")==null?"0.00":object.get("yskzr"));
			treeMap.put("T",object.get("tg")==null?"0.00":object.get("tg"));
			treeMap.put("U",object.get("qf")==null?"0.00":object.get("qf"));
			treeMap.put("V",object.get("ml")==null?"0.00":object.get("ml"));
			treeMap.put("W",object.get("ykpt")==null?"0.00":object.get("ykpt"));
			treeMap.put("X",object.get("ykyywx")==null?"0.00":object.get("ykyywx"));
			treeMap.put("Y",object.get("hz")==null?"0.00":object.get("hz"));
			treeMap.put("Z",object.get("sfdze")==null?"0.00":object.get("sfdze"));
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
	public byte[]  getCardDetailListExcel2(List<Object> list) throws Exception {
		List<Map<String,String>> resultList = new ArrayList<>();
		for(Object o : list){
			Map<String,String> map = (Map<String,String>)o;
			resultList.add(map);
		}
		Workbook wb = PrintExcelUtil.getWeebWork(false);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> header = new ArrayList<String>();
		header.add("序号");
		header.add("就诊日期");
		header.add("患者ID");
		header.add("患者姓名");
		header.add("医生姓名");
		header.add("处置单号");
		header.add("处置单金额");
		header.add("收费单号");
		
		
		List<Object> dataList = new ArrayList<Object>();
		int index = 1;
		for (Map<String,String> object : resultList) {
			TreeMap<String, Object> treeMap = new TreeMap<String, Object>();// 此处的数据必须为有序数据，所以使用TreeMap进行封装
			treeMap.put("A", index++);
			treeMap.put("B", object.get("zjri"));
			treeMap.put("C", object.get("patId"));
			treeMap.put("D", object.get("hzName"));
			treeMap.put("E", object.get("docName"));
			treeMap.put("F", object.get("statementItemid"));
			treeMap.put("G", object.get("totalAmount"));
			treeMap.put("H", object.get("id"));
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
	public byte[]  getCardDetailListExcel(List<Object> list) throws Exception {
		List<Map<String,String>> resultList = new ArrayList<>();
		for(Object o : list){
			Map<String,String> map = (Map<String,String>)o;
			resultList.add(map);
		}
		
		Workbook wb = PrintExcelUtil.getWeebWork(false);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> header = new ArrayList<String>();
		header.add("序号");
		header.add("就诊日期");
		header.add("来源");
		header.add("患者");
		header.add("初/复诊");
		header.add("手机号");
		header.add("治疗项目");
		header.add("医生");
		header.add("处置单金额");
		header.add("优惠金额");
		header.add("应收金额");
		header.add("欠费金额");
		
		
		List<Object> dataList = new ArrayList<Object>();
		int index = 1;
		for (Map<String,String> object : resultList) {
			TreeMap<String, Object> treeMap = new TreeMap<String, Object>();// 此处的数据必须为有序数据，所以使用TreeMap进行封装
			treeMap.put("A", index++);
			treeMap.put("B", object.get("gh_time"));
			treeMap.put("C", object.get("source"));
			treeMap.put("D", object.get("pat_name"));
			treeMap.put("E", object.get("isnew"));
			treeMap.put("F", object.get("mobile"));
			treeMap.put("G", object.get("service_items"));
			treeMap.put("H", object.get("doc_name"));
			treeMap.put("I", object.get("total_amount"));
			treeMap.put("J", object.get("qk_amount"));
			treeMap.put("K", object.get("pay_amount"));
			treeMap.put("L", object.get("debt_amount"));
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
	public List<Object> selectDateList(String strDate, String endDate, Long docId, Integer isnew) {
		HisRegister hr = new HisRegister();

		String str = " a.status = 4 AND a.statement_itemid = c.id " + " AND a.id = c.reg_id " + " AND c.statement_id = b.id " + " AND TO_DAYS(a.create_time)>=TO_DAYS('" + strDate + "') " + " AND TO_DAYS(a.create_time)<=TO_DAYS('" + endDate + "') ";
		if (docId > 0l) {
			str += " AND a.dentist_id = " + docId;
		}
		if (isnew < 2) {
			str += " AND a.isnew = " + isnew;
		}
		hr.setSqlStr(str);
		return hisRegisterDao.selectDateList(hr);
	}

	public List<Object> getChargeDetailist(String strDate, String endDate, Long docId) {
		DBRecord hr = new DBRecord();

		String str = "AND status = 1 AND flag = 0 " + " AND account_type = 2 " + " AND TO_DAYS(create_time)>=TO_DAYS('" + strDate + "') " + " AND TO_DAYS(create_time)<=TO_DAYS('" + endDate + "') ";
		if (docId > 0l) {
			str += " AND doc_id = " + docId;
		}

		hr.setSqlStr(str);
		return reportDao.getChargeDetailist(hr);
	}

	public List<HisStatementCharge> getHisStatementChargeList(Long sid) {
		HisStatementCharge hsc = new HisStatementCharge();
		hsc.setStatementId(sid);
		return hisStatementChargeDao.selectList(hsc);
	}

	public Map<String, String> getPament() {
		Map<String, String> map = new HashMap<>();
		List<HisPayment> list = hisPaymentDao.selectList(new HisPayment());
		for (HisPayment hp : list) {
			map.put(hp.getTypeCode(), hp.getTypeName());
		}
		return map;
	}

	public List<Object> getWriteOfflist(String strDate, String endDate, Long docId) {
		DBRecord hr = new DBRecord();

		String str = "AND status = 1 AND flag = 0 ";
		str = " AND TO_DAYS(a.create_time)>=TO_DAYS('" + strDate + "') " + " AND TO_DAYS(a.create_time)<=TO_DAYS('" + endDate + "') " + " AND account_type IN (2,12,4) AND parent_id IS NOT NULL ";
		if (docId > 0l) {
			str += " AND a.doc_id = " + docId;
		}

		hr.setSqlStr(str);
		return reportDao.getWriteOfflist(hr);
	}
}
