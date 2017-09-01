package com.snolf.util.common;

import com.snolf.util.ReflectionUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * <一句话功能简述>
 * @Title: ExcelUtil.java
 * @Description: 根据传入的list数据创建excel文件并下载
 * @author  LiuYu
 * @date 2016年9月22日下午5:47:50
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SuppressWarnings("deprecation")
public class ExcelUtil {
	private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);
	private static CellStyle titleStyle; // 标题行样式
	private static Font titleFont; // 标题行字体
	private static CellStyle dateStyle; // 条件行样式
	private static Font dateFont; // 日期行字体
	private static CellStyle headStyle; // 表头行样式
	private static Font headFont; // 表头行字体
	private static CellStyle contentStyle; // 内容行样式
	private static Font contentFont; // 内容行字体

	
	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: downloadExcel
	 * @Description: 根据数据集合生成excel文件并下载
	 * @author LiuYu
	 * @date 2016年9月22日 下午1:48:55 
	 * 
	 * @param fileName    excel文件名称，工作簿和生成的内容标题都用这个名称
	 * 
	 * @param colNames    需要导出的excel列名数组，为null时导出map中所有的key
	 * 
	 * @param map         列名与集合中实体类属性的对应关系
	 * 
	 * @param searchParam 查询条件参数
	 * 
	 * @param list        需要导出的数据集合
	 * 
	 * @param response
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public static void downloadExcel(String fileName, String[] colNames, LinkedHashMap<String, String> map,
			String searchParam, List<?> list, HttpServletResponse response) throws Exception {

		String[] colContents = null;
		if (colNames == null) {
			colNames = (String[]) map.keySet().toArray(new String[map.keySet().size()]);
			colContents = (String[]) map.values().toArray(new String[map.values().size()]);
		} else {
			colContents = new String[colNames.length];
			for (int i = 0; i < colNames.length; i++) {
				colContents[i] = map.get(colNames[i]);
			}
		}
		log.debug("excel colNames is " + java.util.Arrays.toString(colNames));

		log.debug("excel colContents is " + java.util.Arrays.toString(colContents));
		byte[] in = ExcelUtil.createExcel(fileName, colNames, colContents, searchParam, list);
		// 进行转码，使其支持中文文件名
		String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		bos.write(in);
		bos.flush();
		bos.close();
		log.debug("download excel success,the excel file name is " + fileName);
	}

	/**
	 * 
	 * <一句话功能简述>
	 * @Title: createExcel
	 * @Description: 根据集合数据创建excel文件流
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:48:21 
	 * @param fileName     文件名称
	 * @param colNames     列名数组
	 * @param colContents  对应的数据属性数组
	 * @param searchParam  查询条件
	 * @param list         需要导出的数据集合
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	private static byte[] createExcel(String fileName, String[] colNames, String[] colContents, String searchParam,
			List<?> list) throws Exception {
		HSSFWorkbook wb = null;
		wb = init(wb);

		LinkedHashMap<String, List<?>> map = new LinkedHashMap<String, List<?>>();
		// map.put("账户组访问网站", dataList);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		List<String[]> headNames = new ArrayList<String[]>();
		// 设置Excel文件的列名称
		// 设置工作薄名称
		map.put(fileName, list);
		headNames.add(colNames);
		List<String[]> fieldNames = new ArrayList<String[]>();
		// 设置列内容
		fieldNames.add(colContents);
		ExportSetInfo setInfo = new ExportSetInfo();
		setInfo.setObjsMap(map);
		setInfo.setFieldNames(fieldNames);
		// 设置Excel内容的标题
		setInfo.setTitles(new String[] { fileName });
		// 设置显示的查询条件
		setInfo.setSearch(new String[] { searchParam });
		setInfo.setHeadNames(headNames);
		setInfo.setOut(baos);
		// 将需要导出的数据输出到baos
		ExcelUtil.export2Excel(setInfo, wb);
		return baos.toByteArray();
	}
	
	
	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: export2Excel
	 * @Description:  将Map里的集合对象数据输出Excel数据流
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:49:42 
	 * @param setInfo
	 * @param wb
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @see [类、类#方法、类#成员]
	 */
	private static void export2Excel(ExportSetInfo setInfo, HSSFWorkbook wb)
			throws IOException, IllegalArgumentException, IllegalAccessException {
		wb = init(wb);
		Set<Entry<String, List<?>>> set = setInfo.getObjsMap().entrySet();
		String[] sheetNames = new String[setInfo.getObjsMap().size()];
		int sheetNameNum = 0;
		for (Entry<String, List<?>> entry : set) {
			sheetNames[sheetNameNum] = entry.getKey();
			sheetNameNum++;
		}
		HSSFSheet[] sheets = getSheets(setInfo.getObjsMap().size(), sheetNames, wb);
		int sheetNum = 0;
		for (Entry<String, List<?>> entry : set) {
			// Sheet
			List<?> objs = entry.getValue();
			// 标题行
			createTableTitleRow(setInfo, sheets, sheetNum);
			// 日期行
			createTableDateRow(setInfo, sheets, sheetNum);
			// 表头
			creatTableHeadRow(setInfo, sheets, sheetNum);
			// 表体
			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
			int rowNum = 3;
			for (Object obj : objs) {
				HSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
				contentRow.setHeight((short) 300);
				HSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames().get(sheetNum).length);
				int cellNum = 1; // 去掉一列序号，因此从1开始
				if (fieldNames != null) {
					for (int num = 0; num < fieldNames.length; num++) {
						Object value = ReflectionUtil.invokeGetterMethod(obj, fieldNames[num]);
						cells[cellNum].setCellValue(value == null ? "" : value.toString());
						cellNum++;
					}
				}
				rowNum++;
			}
			 adjustColumnSize(sheets, sheetNum, fieldNames); // 自动调整列宽
			sheetNum++;
		}
		wb.write(setInfo.getOut());
	}

	
	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: init
	 * @Description: 初始化HSSFWorkbook
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:50:00 
	 * @param wb
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private static HSSFWorkbook init(HSSFWorkbook wb) {
		wb = new HSSFWorkbook();

		titleFont = wb.createFont();
		titleStyle = wb.createCellStyle();
		dateStyle = wb.createCellStyle();
		dateFont = wb.createFont();
		headStyle = wb.createCellStyle();
		headFont = wb.createFont();
		contentStyle = wb.createCellStyle();
		contentFont = wb.createFont();

		initTitleCellStyle();
		initTitleFont();
		initDateCellStyle();
		initDateFont();
		initHeadCellStyle();
		initHeadFont();
		initContentCellStyle();
		initContentFont();
		return wb;
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: adjustColumnSize
	 * @Description: 自动调整列宽
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:50:27 
	 * @param sheets
	 * @param sheetNum
	 * @param fieldNames
	 * @see [类、类#方法、类#成员]
	 */
	private static void adjustColumnSize(HSSFSheet[] sheets, int sheetNum, String[] fieldNames) {
		for (int i = 0; i < fieldNames.length + 1; i++) {
			sheets[sheetNum].autoSizeColumn(i, true);
		}
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: createTableTitleRow
	 * @Description: 创建标题行(需合并单元格)
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:50:46 
	 * @param setInfo
	 * @param sheets
	 * @param sheetNum
	 * @see [类、类#方法、类#成员]
	 */
	private static void createTableTitleRow(ExportSetInfo setInfo, HSSFSheet[] sheets, int sheetNum) {
		CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, setInfo.getFieldNames().get(sheetNum).length);
		sheets[sheetNum].addMergedRegion(titleRange);
		HSSFRow titleRow = sheets[sheetNum].createRow(0);
		titleRow.setHeight((short) 800);
		HSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(titleStyle);
		titleCell.setCellValue(setInfo.getTitles()[sheetNum]);
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: createTableDateRow
	 * @Description: 创建条件行(需合并单元格)
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:51:09 
	 * @param setInfo
	 * @param sheets
	 * @param sheetNum
	 * @see [类、类#方法、类#成员]
	 */
	private static void createTableDateRow(ExportSetInfo setInfo, HSSFSheet[] sheets, int sheetNum) {
		CellRangeAddress dateRange = new CellRangeAddress(1, 1, 0, setInfo.getFieldNames().get(sheetNum).length);
		sheets[sheetNum].addMergedRegion(dateRange);
		HSSFRow dateRow = sheets[sheetNum].createRow(1);
		dateRow.setHeight((short) 350);
		HSSFCell dateCell = dateRow.createCell(0);
		dateCell.setCellStyle(dateStyle);
		dateCell.setCellValue(setInfo.getSearch()[sheetNum]);
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: creatTableHeadRow
	 * @Description: 创建表头行(需合并单元格)
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:51:24 
	 * @param setInfo
	 * @param sheets
	 * @param sheetNum
	 * @see [类、类#方法、类#成员]
	 */
	private static void creatTableHeadRow(ExportSetInfo setInfo, HSSFSheet[] sheets, int sheetNum) {
		// 表头
		HSSFRow headRow = sheets[sheetNum].createRow(2);
		headRow.setHeight((short) 350);
		// 序号列
		HSSFCell snCell = headRow.createCell(0);
		snCell.setCellStyle(headStyle);
		snCell.setCellValue("序号");
		// 列头名称
		for (int num = 1, len = setInfo.getHeadNames().get(sheetNum).length; num <= len; num++) {
			HSSFCell headCell = headRow.createCell(num);
			headCell.setCellStyle(headStyle);
			headCell.setCellValue(setInfo.getHeadNames().get(sheetNum)[num - 1]);
		}
	}

	
	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: getSheets
	 * @Description: 创建所有的Sheet
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:51:36 
	 * @param num
	 * @param names
	 * @param wb
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private static HSSFSheet[] getSheets(int num, String[] names, HSSFWorkbook wb) {
		HSSFSheet[] sheets = new HSSFSheet[num];
		for (int i = 0; i < num; i++) {
			sheets[i] = wb.createSheet(names[i]);
		}
		return sheets;
	}


	/**
	 * 
	 * <一句话功能简述>
	 * @Title: getCells
	 * @Description:创建内容行的每一列(附加一列序号)
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:51:53 
	 * @param contentRow
	 * @param num
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private static HSSFCell[] getCells(HSSFRow contentRow, int num) {
		HSSFCell[] cells = new HSSFCell[num + 1];

		for (int i = 0, len = cells.length; i < len; i++) {
			cells[i] = contentRow.createCell(i);
			cells[i].setCellStyle(contentStyle);
		}
		// 设置序号列值，因为出去标题行和日期行，所有-2
		cells[0].setCellValue(contentRow.getRowNum() - 2);

		return cells;
	}


	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: initTitleCellStyle
	 * @Description: 初始化标题行样式
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:52:07 
	 * @see [类、类#方法、类#成员]
	 */
	private static void initTitleCellStyle() {
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.getIndex());
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: initDateCellStyle
	 * @Description: 初始化日期行样式
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:52:18 
	 * @see [类、类#方法、类#成员]
	 */
	private static void initDateCellStyle() {
		dateStyle.setAlignment(CellStyle.ALIGN_LEFT);
		dateStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		dateStyle.setFont(dateFont);
		dateStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.getIndex());
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: initHeadCellStyle
	 * @Description:初始化表头行样式
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:52:31 
	 * @see [类、类#方法、类#成员]
	 */
	private static void initHeadCellStyle() {
		headStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headStyle.setFont(headFont);
		headStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		headStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
		headStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headStyle.setBorderLeft(CellStyle.BORDER_THIN);
		headStyle.setBorderRight(CellStyle.BORDER_THIN);
		headStyle.setTopBorderColor(IndexedColors.BLUE.getIndex());
		headStyle.setBottomBorderColor(IndexedColors.BLUE.getIndex());
		headStyle.setLeftBorderColor(IndexedColors.BLUE.getIndex());
		headStyle.setRightBorderColor(IndexedColors.BLUE.getIndex());
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: initContentCellStyle
	 * @Description:初始化内容行样式
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:52:52 
	 * @see [类、类#方法、类#成员]
	 */
	private static void initContentCellStyle() {
		contentStyle.setAlignment(CellStyle.ALIGN_CENTER);
		contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		contentStyle.setFont(contentFont);
		contentStyle.setBorderTop(CellStyle.BORDER_THIN);
		contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
		contentStyle.setBorderRight(CellStyle.BORDER_THIN);
		contentStyle.setTopBorderColor(IndexedColors.BLUE.getIndex());
		contentStyle.setBottomBorderColor(IndexedColors.BLUE.getIndex());
		contentStyle.setLeftBorderColor(IndexedColors.BLUE.getIndex());
		contentStyle.setRightBorderColor(IndexedColors.BLUE.getIndex());
		contentStyle.setWrapText(true); // 字段换行
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: initTitleFont
	 * @Description:  初始化标题行字体
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:53:06 
	 * @see [类、类#方法、类#成员]
	 */
	private static void initTitleFont() {
		titleFont.setFontName("华文楷体");
		titleFont.setFontHeightInPoints((short) 20);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		titleFont.setCharSet(Font.DEFAULT_CHARSET);
		titleFont.setColor(IndexedColors.BLUE_GREY.getIndex());
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: initDateFont
	 * @Description: 初始化条件行字体
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:53:24 
	 * @see [类、类#方法、类#成员]
	 */
	private static void initDateFont() {
		dateFont.setFontName("宋体");
		dateFont.setFontHeightInPoints((short) 10);
		dateFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		dateFont.setCharSet(Font.DEFAULT_CHARSET);
		dateFont.setColor(IndexedColors.BLUE_GREY.getIndex());
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: initHeadFont
	 * @Description: 初始化表头行字体
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:53:37 
	 * @see [类、类#方法、类#成员]
	 */
	private static void initHeadFont() {
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short) 10);
		headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headFont.setCharSet(Font.DEFAULT_CHARSET);
		headFont.setColor(IndexedColors.BLUE_GREY.getIndex());
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: initContentFont
	 * @Description: 初始化内容行字体
	 * @author LiuYu
	 * @date 2016年9月22日 下午5:53:48 
	 * @see [类、类#方法、类#成员]
	 */
	private static void initContentFont() {
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short) 10);
		contentFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		contentFont.setCharSet(Font.DEFAULT_CHARSET);
		contentFont.setColor(IndexedColors.BLUE_GREY.getIndex());
	}

	
	/**
	 * 
	 * <一句话功能简述>
	 * @Title: ExcelUtil.java
	 * @Description: 封装Excel导出的设置信息
	 * @author  LiuYu
	 * @date 2016年9月22日下午5:54:02
	 * @see  [相关类/方法]
	 * @since  [产品/模块版本]
	 */
	public static class ExportSetInfo {
		private LinkedHashMap<String, List<?>> objsMap;

		private String[] titles;

		private String[] search;

		private List<String[]> headNames;

		private List<String[]> fieldNames;

		private OutputStream out;

		public LinkedHashMap<String, List<?>> getObjsMap() {
			return objsMap;
		}

		
		
		/**
		 * 
		 * <一句话功能简述>
		 * @Title: setObjsMap
		 * @Description:  导出数据
		 * 泛型 String : 代表sheet名称 List : 代表单个sheet里的所有行数据
		 * @author LiuYu
		 * @date 2016年9月22日 下午5:54:20 
		 * @param objsMap
		 * @see [类、类#方法、类#成员]
		 */
		public void setObjsMap(LinkedHashMap<String, List<?>> objsMap) {
			this.objsMap = objsMap;
		}

		public List<String[]> getFieldNames() {
			return fieldNames;
		}

		public String[] getSearch() {
			return search;
		}

		public void setSearch(String[] search) {
			this.search = search;
		}

	
		
		/**
		 * 
		 * <一句话功能简述>
		 * @Title: setFieldNames
		 * @Description: 对应每个sheet里的每行数据的对象的属性名称
		 * @author LiuYu
		 * @date 2016年9月22日 下午5:54:45 
		 * @param fieldNames
		 * @see [类、类#方法、类#成员]
		 */
		public void setFieldNames(List<String[]> fieldNames) {
			this.fieldNames = fieldNames;
		}

		public String[] getTitles() {
			return titles;
		}

		
		
		/**
		 * 
		 * <一句话功能简述>
		 * @Title: setTitles
		 * @Description: 对应每个sheet里的标题，即顶部大字
		 * @author LiuYu
		 * @date 2016年9月22日 下午5:55:40 
		 * @param titles
		 * @see [类、类#方法、类#成员]
		 */
		public void setTitles(String[] titles) {
			this.titles = titles;
		}

		public List<String[]> getHeadNames() {
			return headNames;
		}

		
		
		/**
		 * 
		 * <一句话功能简述>
		 * @Title: setHeadNames
		 * @Description: 对应每个页签的表头的每一列的名称
		 * @author LiuYu
		 * @date 2016年9月22日 下午5:55:52 
		 * @param headNames
		 * @see [类、类#方法、类#成员]
		 */
		public void setHeadNames(List<String[]> headNames) {
			this.headNames = headNames;
		}

		public OutputStream getOut() {
			return out;
		}

		
		/**
		 * 
		 * <一句话功能简述>
		 * @Title: setOut
		 * @Description:   Excel数据将输出到该输出流
		 * @author LiuYu
		 * @date 2016年9月22日 下午5:56:09 
		 * @param out
		 * @see [类、类#方法、类#成员]
		 */
		public void setOut(OutputStream out) {
			this.out = out;
		}
	}
}
