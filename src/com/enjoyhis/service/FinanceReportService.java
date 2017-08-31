package com.enjoyhis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enjoyhis.persistence.his.dao.HisStatementChargeDao;
import com.enjoyhis.persistence.his.po.HisStatementCharge;
import com.enjoyhis.pojo.ReportSumPaymentData;
import com.enjoyhis.pojo.ReportSumPaymentTable;
import com.enjoyhis.util.DateUtils;
import com.enjoyhis.util.excel.PrintExcelUtil;

@Service("financeReportService")
public class FinanceReportService {

	@Autowired
	HisStatementChargeDao hisStatementChargeMapper;

	public List<ReportSumPaymentTable> selectDateList(String strDate, String endDate, Long loginId) {
		HisStatementCharge hsc = new HisStatementCharge();
		if (loginId != null) {
			hsc.setSqlStr(" TO_DAYS(a.create_time)>=TO_DAYS('" + strDate + "') AND TO_DAYS(a.create_time)<=TO_DAYS('" + endDate + "') AND a.statement_id = b.id AND b.doc_id='" + loginId + "'");
		} else {
			hsc.setSqlStr(" TO_DAYS(a.create_time)>=TO_DAYS('" + strDate + "') AND TO_DAYS(a.create_time)<=TO_DAYS('" + endDate + "') AND a.statement_id = b.id ");
		}
		hsc.setSqlSort(" a.create_time asc");
		List<ReportSumPaymentData> lists = hisStatementChargeMapper.selectDateList(hsc);
		ReportSumPaymentData rspds = new ReportSumPaymentData();
		rspds.setTime("over");
		lists.add(rspds);
		List<ReportSumPaymentTable> list = new ArrayList<ReportSumPaymentTable>();
		ReportSumPaymentTable rspt = null;
		String date = null;
		int i = 0;
		for (ReportSumPaymentData rdpd : lists) {
			if (!rdpd.getTime().equals(date)) {
				if (i > 0) {
					list.add(rspt);
				}
				if (rdpd.getTime().equals("over")) {
					break;
				}
				rspt = new ReportSumPaymentTable();
				i++;
				date = rdpd.getTime();
			}
			rspt.setTime(date);
			if (rdpd.getPaymentType().equals("wx")) {
				rspt.setWx(rdpd.getAmount());
			} else if (rdpd.getPaymentType().equals("xj")) {
				rspt.setXj(rdpd.getAmount());
			} else if (rdpd.getPaymentType().equals("zp")) {
				rspt.setZp(rdpd.getAmount());
			} else if (rdpd.getPaymentType().equals("zfb")) {
				rspt.setZfb(rdpd.getAmount());
			} else if (rdpd.getPaymentType().equals("YKPT")) {
				rspt.setYkpt(rdpd.getAmount());
			} else if (rdpd.getPaymentType().equals("YKYYWX")) {
				rspt.setYkyywx(rdpd.getAmount());
			} else if (rdpd.getPaymentType().equals("POS")) {
				if (rdpd.getPaymentSubtype().equals("zsyh")) {
					rspt.setZsyh(rdpd.getAmount());
				} else if (rdpd.getPaymentSubtype().equals("jsyh")) {
					rspt.setJsyh(rdpd.getAmount());
				} else if (rdpd.getPaymentSubtype().equals("zgyh")) {
					rspt.setZgyh(rdpd.getAmount());
				} else if (rdpd.getPaymentSubtype().equals("gsyh")) {
					rspt.setGsyh(rdpd.getAmount());
				} else if (rdpd.getPaymentSubtype().equals("lkl")) {
					rspt.setLkl(rdpd.getAmount());
				} else if (rdpd.getPaymentSubtype().equals("msyh")) {
					rspt.setMsyh(rdpd.getAmount());
				} else if (rdpd.getPaymentSubtype().equals("pfyh")) {
					rspt.setPfyh(rdpd.getAmount());
				} else if (rdpd.getPaymentSubtype().equals("tl")) {
					rspt.setTl(rdpd.getAmount());
				} else if (rdpd.getPaymentSubtype().equals("bjyh")) {
					rspt.setBjyh(rdpd.getAmount());
				} else if (rdpd.getPaymentSubtype().equals("yhjjk")) {
					rspt.setYhjjk(rdpd.getAmount());
				} else if (rdpd.getPaymentSubtype().equals("ylsw")) {
					rspt.setYlsw(rdpd.getAmount());
				} else {
					continue;
				}
			} else {
				continue;
			}
		}
		return list;
	}
	
	
	public byte[] getCardDetailListExcel(String strDate, String endDate, Long loginId) throws Exception {
		List<ReportSumPaymentTable> resultList = selectDateList(strDate, endDate, loginId);

		Workbook wb = PrintExcelUtil.getWeebWork(false);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> header = new ArrayList<String>();
		header.add("序号");
		header.add("收款时间");
		header.add("建设银行");
		header.add("招商银行");
		header.add("工商银行");
		header.add("拉卡拉");
		header.add("民生银行");
		header.add("浦发银行");
		header.add("通联");
		header.add("北京银行");
		header.add("银行借记卡");
		header.add("银联商务");
		header.add("微信");
		header.add("支付宝");
		header.add("约克平台");
		header.add("约克牙医微信");
		header.add("支票");
		header.add("现金");
		header.add("合计");
		

		List<Object> dataList = new ArrayList<Object>();
		int index = 1;
		for (ReportSumPaymentTable object : resultList) {
			TreeMap<String, Object> treeMap = new TreeMap<String, Object>();// 此处的数据必须为有序数据，所以使用TreeMap进行封装
			treeMap.put("A", index++);
			treeMap.put("B", object.getTime());
			treeMap.put("C", object.getJsyh());
			treeMap.put("D", object.getZsyh());
			treeMap.put("E", object.getGsyh());
			treeMap.put("F", object.getLkl());
			treeMap.put("G", object.getMsyh());
			treeMap.put("H", object.getPfyh());
			treeMap.put("I", object.getTl());
			treeMap.put("J", object.getBjyh());
			treeMap.put("K", object.getYhjjk());
			treeMap.put("L", object.getYlsw());
			treeMap.put("M", object.getWx());
			treeMap.put("N", object.getZfb());
			treeMap.put("O", object.getYkpt());
			treeMap.put("P", object.getYkyywx());
			treeMap.put("Q", object.getZp());
			treeMap.put("R", object.getXj());
			treeMap.put("S", object.getHj());
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
}
