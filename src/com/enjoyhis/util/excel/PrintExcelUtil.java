package com.enjoyhis.util.excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@SuppressWarnings("deprecation")
public class PrintExcelUtil {

	/**
	 * 制定存储数据的Map的键值,用于获取表头,和数据
	 */
	public static final String HEADERINFO = "headInfo";
	public static final String DATAINFON = "dataInfo";

	/**
	 * 获取WorkBook对象 2015年11月13日 16:04:10
	 */
	public static Workbook getWeebWork(boolean type) throws IOException {
		Workbook workbook = null;
		if (type) {
			workbook = new HSSFWorkbook();// 创建 Excel 2003 工作簿对象
		} else {
			workbook = new XSSFWorkbook();// 创建 Excel 2007 工作簿对象
		}
		workbook.createSheet();
		return workbook;
	}

	/**
	 * 获取WorkBook对象
	 * 
	 * @param type
	 * @param sheetName(sheet页名称)
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWeebWork(boolean type, String sheetName) throws IOException {
		Workbook workbook = null;
		if (type) {

			workbook = new HSSFWorkbook();// 创建 Excel 2003 工作簿对象
		} else {
			workbook = new XSSFWorkbook();// 创建 Excel 2007 工作簿对象
		}
		Sheet sheet = workbook.createSheet(sheetName);
		sheet.setForceFormulaRecalculation(true);
		return workbook;
	}

	/**
	 * 获取sheet对象
	 */
	public static Sheet getSheet(Workbook wb) {
		return wb.getSheetAt(0);
	}

	/**
	 * 获取单元格
	 */
	public static Cell getCell(Row row, String column) {
		int c = string2Int(column);
		return row.getCell(c);
	}

	/**
	 * 获取最后一行row对象 2015年11月16日 14:17:23
	 */
	public static Row getlastRow(Workbook wb, String value, List<String> headList) {

		CellStyle style = getCellStyle(wb, false);

		Sheet sheet = getSheet(wb);
		int lastRowNum = sheet.getLastRowNum();
		Row row = sheet.createRow(lastRowNum + 1);
		for (int i = 0; i < headList.size(); i++) {
			Cell createCell = row.createCell(i);
			createCell.setCellValue(value);
			createCell.setCellStyle(style);
		}
		return row;
	}

	/***
	 * 将数据信息保存到workBook中
	 */
	@SuppressWarnings("unchecked")
	public static Workbook saveExcelData(Workbook wb, Map<String, Object> map) {

		List<String> headList = (List<String>) map.get(PrintExcelUtil.HEADERINFO);
		List<TreeMap<String, Object>> dataList = (List<TreeMap<String, Object>>) map.get(PrintExcelUtil.DATAINFON);

		setHeader(wb, headList);
		setDataContext(wb, dataList);
		Sheet sheet = getSheet(wb);
		sheet.setForceFormulaRecalculation(true);
		return wb;
	}

	/***
	 * 导出Excel,将封装数据信息的workBook写入到二进制流中 2015年11月13日 16:06:25
	 */
	public static byte[] writeExcel(Workbook wb) throws Exception {

		// 使用流导出数据
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}
		return os.toByteArray();
	}

	/**
	 * 设置表头 ,为表头添加数据字段 2015年11月13日 16:12:44
	 */
	public static void setHeader(Workbook wb, List<String> headList) {
		Sheet sheet = getSheet(wb);
		CellStyle style = getCellStyle(wb, true);
		Row row = sheet.createRow(0);
		for (int i = 0; i < headList.size(); i++) {
			Cell headCell = row.createCell(i);
			headCell.setCellType(Cell.CELL_TYPE_STRING);
			headCell.setCellStyle(style);
			headCell.setCellValue(headList.get(i));
		}
	}

	/**
	 * 设置表格内容 ,将数据内容填充到表格中 2015年11月13日 16:16:42
	 */

	public static void setDataContext(Workbook wb, List<TreeMap<String, Object>> dataList) {

		Sheet sheet = wb.getSheetAt(0);
		for (int i = 0; i < dataList.size(); i++) {
			Row rowdata = sheet.createRow(i + 1);// 创建数据行
			TreeMap<String, Object> mapdata = dataList.get(i);
			Iterator<?> it = mapdata.keySet().iterator();
			int j = 0;
			while (it.hasNext()) {
				// 从map中读取数据
				Object object = mapdata.get(it.next());
				Cell celldata = rowdata.createCell(j);
				setCellValue(wb, celldata, object);
				j++;
			}
		}
	}

	/**
	 * 设置单元格的值 ,在填充表格数据时调用,将数据信息填充到单元格中 2015年11月13日 16:22:54
	 */
	public static void setCellValue(Workbook workbook, Cell cell, Object value) {

		CellStyle style = getCellStyle(workbook, false);
		// 处理null值
		if (value == null) {
			value = "";
		}

		// 判断值的类型后进行强制类型转换
		String textValue = null;
		if (value instanceof Integer) {
			Integer intValue = (Integer) value;
			cell.setCellValue(intValue);
		} else if (value instanceof Double || value instanceof Float || value instanceof BigDecimal) {
			style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
			Double doubleValue = Double.parseDouble(value + "");
			cell.setCellValue(doubleValue);
		} else if (value instanceof Date) {
			DataFormat format = workbook.createDataFormat();
			style.setDataFormat(format.getFormat("yyyy-MM-dd hh:mm:ss"));
			Date date = (Date) value;
			cell.setCellValue(date);
		} else {
			// 其它数据类型都当做字符串简单处理
			textValue = value.toString();
			XSSFRichTextString richString = new XSSFRichTextString(textValue);
			cell.setCellValue(richString);
		}
		// 设置单元格样式
		cell.setCellStyle(style);
	}

	/**
	 * 定义两种字体和样式,分别用于表头,和数据内容 获取表格的设置样式,true 为表头样式,false为数据样式 2015年11月13日 16:30:20
	 */
	public static CellStyle getCellStyle(Workbook workbook, boolean select) {

		// 用于表头的样式,字体
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 生成一个字体(用于单元格)
		Font font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		// 把字体应用到当前的样式
		style.setFont(font);

		// 填充数据的样式,字体
		CellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		// style2.setWrapText(true);
		// 生成另一个字体
		Font font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontHeightInPoints((short) 11);
		font2.setFontName("宋体");
		// 把字体应用到当前的样式
		font.setColor(HSSFColor.BLACK.index);
		style2.setFont(font2);

		return select ? style : style2;
	}

	/**
	 * 设置excel 单元格的公式,自动得到运算结果
	 */
	public static void setFormula(String formula, Cell cell, String colName, Integer beginRowNum, Integer endRowNum) {
		String beginstr = colName + beginRowNum;
		String endStr = colName + endRowNum;
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		if ("sum".equalsIgnoreCase(formula)) {
			cell.setCellFormula("SUM(" + beginstr + ":" + endStr + ")");
		} else {
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(formula);
		}
	}

	/**
	 * 设置excel 单元格的公式,自动得到运算结果
	 * 
	 * @param wb(Workbook)
	 * @param formula(函数名例如（SUM）*此方法只能算SUM)
	 * @param row(行数（起始行为0）)
	 * @param col(列名（A、B……）)
	 * @param beginRowNum(函数开始的单元格 例如A1)
	 * @param endRowNum(函数结束的单元格 例如A3)
	 */

	public static void setFormula(Workbook wb, String formula, Integer row, String col, String beginRowNum, String endRowNum) {
		Sheet sheet = getSheet(wb);
		Cell cell = sheet.getRow(row).getCell(string2Int(col));
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		if ("sum".equalsIgnoreCase(formula)) {
			cell.setCellFormula("SUM(" + beginRowNum + ":" + endRowNum + ")");
		} else {
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(formula);
		}
	}

	/**
	 * 设置列的对齐方式,left ,center,right
	 * 
	 */
	public static void setColumnAlign(Workbook wb, String align, String... colNum) {

		Sheet sheet = getSheet(wb);
		for (int i = 0; i < colNum.length; i++) {
			for (int j = 1; j <= sheet.getLastRowNum(); j++) {
				Cell cell = sheet.getRow(j).getCell(string2Int(colNum[i]));
				CellStyle style = cell.getCellStyle();
				/*
				 * if (cell.getCellType()==Cell.CELL_TYPE_STRING) { XSSFFont font3 = (XSSFFont)
				 * wb.createFont(); font3.setColor(HSSFColor.RED.index); style.setFont(font3); }
				 */
				switch (align) {
				case "left":
					style.setAlignment(CellStyle.ALIGN_LEFT);
					break;
				case "center":
					style.setAlignment(CellStyle.ALIGN_CENTER);
					break;
				case "right":
					style.setAlignment(CellStyle.ALIGN_RIGHT);
					break;
				default:
					style.setAlignment(CellStyle.ALIGN_LEFT);
				}
				cell.setCellStyle(style);
			}
		}
	}

	/**
	 * 获得Cell
	 * 
	 * @param row（从0开始）
	 * @param col（从0开始）
	 */
	public static Cell getCell(Workbook wb, Integer row, Integer col) {
		Sheet sheet = getSheet(wb);
		return sheet.getRow(row).getCell(col);
	}

	/**
	 * 设置列宽,根据列的名称和列宽值设置列宽 2015年11月13日 16:47:32
	 */
	public static void setColumnwidth(Workbook wb, Integer value, String... colNum) {

		Sheet sheet = getSheet(wb);
		for (int i = 0; i < colNum.length; i++) {
			sheet.setColumnWidth(string2Int(colNum[i]), value);
		}
	}

	/**
	 * 辅助方法,将列宽的名称,转换为指定的列值
	 */
	public static int string2Int(String str) {

		char ch = str.toLowerCase().charAt(0);
		int colnum = ch - 97;
		return colnum;
	}

	public static void main(String[] args) throws IOException {
		Workbook wb = getWeebWork(false);
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> header = new ArrayList<String>();
		header.add("序号");
		header.add("一级分类");
		header.add("二级分类");

		List<Object> dataList = new ArrayList<Object>();

		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();// 此处的数据必须为有序数据，所以使用TreeMap进行封装
		treeMap.put("A", 11);
		treeMap.put("B", "weq");
		treeMap.put("C", "ogfdgf");
		TreeMap<String, Object> treeMap1 = new TreeMap<String, Object>();// 此处的数据必须为有序数据，所以使用TreeMap进行封装
		treeMap1.put("A", 22);
		treeMap1.put("B", "www");
		treeMap1.put("C", "bai");

		dataList.add(treeMap);
		dataList.add(treeMap1);

		map.put(PrintExcelUtil.HEADERINFO, header);
		map.put(PrintExcelUtil.DATAINFON, dataList);

		printExcel(wb, map);
		System.out.println("---成功---");
	}

	@SuppressWarnings("unchecked")
	public static void printExcel(Workbook wb, Map<String, Object> map) throws IOException {

		List<String> headList = (List<String>) map.get(ExcelUtil.HEADERINFO);
		List<TreeMap<String, Object>> dataList = (List<TreeMap<String, Object>>) map.get(ExcelUtil.DATAINFON);

		// 设置默认列宽
		// sheet.setDefaultColumnWidth(3766);
		setHeader(wb, headList);
		setDataContext(wb, dataList);
		setColumnAlign(wb, "right", "A");
		Row row = getlastRow(wb, "--", headList);
		Cell cell = row.getCell(0);
		setFormula("总计", cell, "A", 0, 1);
		// setColumnAlign(wb, "left" ,"A");
		setColumnAlign(wb, "left", "B");
		FileOutputStream outputStream = new FileOutputStream(new File("d:\\xxx.xlsx"));
		wb.write(outputStream);
		outputStream.close();
	}

	public static String int2Alphabet(int num) {
		if (num > -1 && num < 26) {
			char ch = (char) (num + 65);
			return ch + "";
		}
		return "";
	}

	/**
	 * 通用的导出数据到Excel表功能
	 * 
	 * @param attachmentName:附件名称
	 * @param listRecord:数据，如果需要序号和汇总行，需要提前用dbUtilsService.getSummaryRow4Json和dbUtilsService.
	 * createRowNum4Json生成
	 * @param fields:·号分隔的数据字段列名
	 * @param header:·号分隔的列标题
	 * @param align:·号分隔的列对齐方式
	 * @param columnWidth:·号分隔的列宽度，auto表示自动列宽
	 * @return
	 */
	public static ResponseEntity<byte[]> export2Excel(String attachmentName, JSONArray listRecord, String fields, String header, String align, String columnWidth) throws Exception {
		// 获取wb
		Workbook wb = getWeebWork(false);
		// 设置表头字段
		List<String> headerList = Arrays.asList(header.split("`"));
		setHeader(wb, headerList);

		// 设置数据字段
		String[] filedArr = fields.split("`");
		String[] types = new String[filedArr.length];
		for (int j = 0; j < filedArr.length; j++) {
			types[j] = "string";
			boolean getType = false;

			/*
			 * //是否日期 for(int i = 0; i < listRecord.size(); i++) { JSONObject object =
			 * listRecord.getJSONObject(i); Object o = object.get(filedArr[j]); if(o == null) {
			 * continue; } String temp = object.getString(filedArr[j]);
			 * if(NumberUtils.isNumber(temp) == false) { getType = true; } } if(getType) {
			 * continue; }
			 */

			// 是否字符串
			for (int i = 0; i < listRecord.size(); i++) {
				JSONObject object = listRecord.getJSONObject(i);
				Object o = object.get(filedArr[j]);
				if (o == null) {
					continue;
				}
				String temp = object.getString(filedArr[j]);
				if (NumberUtils.isNumber(temp) == false) {
					getType = true;
				}
			}
			if (getType) {
				continue;
			}

			// 是否整数
			for (int i = 0; i < listRecord.size(); i++) {
				JSONObject object = listRecord.getJSONObject(i);
				Object o = object.get(filedArr[j]);
				if (o == null) {
					continue;
				}
				String temp = object.getString(filedArr[j]);
				if (temp.contains(".")) {
					types[j] = "decimal";
					getType = true;
				}
			}
			if (getType) {
				continue;
			}
			types[j] = "integer";
		}

		List<TreeMap<String, Object>> dataList = new ArrayList<TreeMap<String, Object>>();
		for (int i = 0; i < listRecord.size(); i++) {
			JSONObject row = listRecord.getJSONObject(i);
			// 保存Excel中一行数据
			TreeMap<String, Object> treeMap = new TreeMap<String, Object>();// 此处的数据必须为有序数据，所以使用TreeMap进行封装
			// 根据字段名称,回去字段值填充单元格
			for (int j = 0; j < filedArr.length; j++) {

				if (row.containsKey(filedArr[j]) == false) {
					treeMap.put(int2Alphabet(j), null);
					continue;
				}
				if (types[j].equals("string")) {
					treeMap.put(int2Alphabet(j), row.getString(filedArr[j]));
				} else if (types[j].equals("integer")) {
					treeMap.put(int2Alphabet(j), row.getLong(filedArr[j]));
				} else if (types[j].equals("decimal")) {
					treeMap.put(int2Alphabet(j), Double.parseDouble(row.getString(filedArr[j]) + ""));
				}
			}
			dataList.add(treeMap);
		}
		setDataContext(wb, dataList);

		// 设置对齐格式
		if (align != null) {
			String[] alignArr = align.split("`");
			for (int i = 0; i < alignArr.length; i++) {
				setColumnAlign(wb, alignArr[i], int2Alphabet(i));
			}
		}
		// 设置列宽
		if (columnWidth != null) {
			String[] colmnWidthArr = columnWidth.split("`");
			for (int i = 0; i < colmnWidthArr.length; i++) {
				int width = Integer.parseInt(colmnWidthArr[i]);
				if (width == 0) {
					continue;
				}
				setColumnwidth(wb, width, int2Alphabet(i));
			}
		}
		byte[] bytes = null;
		bytes = writeExcel(wb);

		// 导出excel
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", new String(attachmentName.getBytes("gb2312"), "iso-8859-1"));

		// 只需修改 文件名称和创建service方法
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}

	/**
	 * 本地测试使用,
	 */
	public static void export2Excel2(String attachmentName, JSONArray listRecord, String fields, String header, String align, String columnWidth) throws Exception {
		// 获取wb
		Workbook wb = getWeebWork(false);
		// 设置表头字段
		List<String> headerList = Arrays.asList(header.split("`"));
		setHeader(wb, headerList);
		// 设置数据字段
		// List<TreeMap<String,Object>> mapListJson = (List)listRecord;
		String[] filedArr = fields.split("`");
		String[] types = new String[filedArr.length];
		for (int j = 0; j < filedArr.length; j++) {
			types[j] = "string";
			boolean getType = false;

			/*
			 * //是否日期 for(int i = 0; i < listRecord.size(); i++) { JSONObject object =
			 * listRecord.getJSONObject(i); Object o = object.get(filedArr[j]); if(o == null) {
			 * continue; } String temp = object.getString(filedArr[j]);
			 * if(NumberUtils.isNumber(temp) == false) { getType = true; } } if(getType) {
			 * continue; }
			 */

			// 是否字符串
			for (int i = 0; i < listRecord.size(); i++) {
				JSONObject object = listRecord.getJSONObject(i);
				Object o = object.get(filedArr[j]);
				if (o == null) {
					continue;
				}
				String temp = object.getString(filedArr[j]);
				if (NumberUtils.isNumber(temp) == false) {
					getType = true;
				}
			}
			if (getType) {
				continue;
			}

			// 是否整数
			for (int i = 0; i < listRecord.size(); i++) {
				JSONObject object = listRecord.getJSONObject(i);
				Object o = object.get(filedArr[j]);
				if (o == null) {
					continue;
				}
				String temp = object.getString(filedArr[j]);
				if (temp.contains(".")) {
					types[j] = "decimal";
					getType = true;
				}
			}
			if (getType) {
				continue;
			}
			types[j] = "integer";
		}

		List<TreeMap<String, Object>> dataList = new ArrayList<TreeMap<String, Object>>();
		for (int i = 0; i < listRecord.size(); i++) {
			JSONObject row = listRecord.getJSONObject(i);
			// 保存Excel中一行数据
			TreeMap<String, Object> treeMap = new TreeMap<String, Object>();// 此处的数据必须为有序数据，所以使用TreeMap进行封装
			// 根据字段名称,回去字段值填充单元格
			for (int j = 0; j < filedArr.length; j++) {
				if (types[j].equals("string")) {
					treeMap.put(int2Alphabet(j), row.getString(filedArr[j]));
				} else if (types[j].equals("integer")) {
					treeMap.put(int2Alphabet(j), row.getLong(filedArr[j]));
				} else if (types[j].equals("decimal")) {
					treeMap.put(int2Alphabet(j), Double.parseDouble(row.getString(filedArr[j]) + ""));
				}
			}
			dataList.add(treeMap);
		}
		setDataContext(wb, dataList);

		// 设置对齐格式
		if (align != null) {
			String[] alignArr = align.split("`");
			for (int i = 0; i < alignArr.length; i++) {
				setColumnAlign(wb, alignArr[i], int2Alphabet(i));
			}
		}
		// 设置列宽
		if (columnWidth != null) {
			String[] colmnWidthArr = columnWidth.split("`");
			for (int i = 0; i < colmnWidthArr.length; i++) {
				int width = Integer.parseInt(colmnWidthArr[i]);
				if (width == 0) {
					continue;
				}
				setColumnwidth(wb, width, int2Alphabet(i));
			}
		}

		FileOutputStream outputStream = new FileOutputStream(new File("d:\\" + attachmentName));
		wb.write(outputStream);
		outputStream.close();
	}

}
