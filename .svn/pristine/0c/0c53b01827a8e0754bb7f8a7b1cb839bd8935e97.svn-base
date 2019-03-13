package com.sys.common.filema;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil
{
	private static Logger log = Logger.getLogger(ExcelUtil.class);

	//默认单元格内容为数字时格�? 
	private static DecimalFormat df = new DecimalFormat("");

	// 默认单元格格式化日期字符�?  
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 格式化数�? 
	private static DecimalFormat nf = new DecimalFormat("0.00");

	/**
	 * 读取excel类
	 * 
	 * @param file
	 * @param heedRow
	 *            跳过省略的每一个sheet的头部行数
	 * @return
	 */
	public static ArrayList<ArrayList<Object>> readExcel(File file, int heedRow)
	{
		if(file == null)
		{
			return null;
		}
		if(file.getName().endsWith("xlsx"))
		{
			//处理ecxel2007  
			return readExcel2007(file, heedRow);
		}
		else
		{
			//处理ecxel2003  
			return readExcel2003(file, heedRow);
		}
	}

	/*
	 * @return 将返回结果存储在ArrayList内，存储结构与二维数组类�?
	 * lists.get(0).get(0)表示获取Excel�?�?列单元格
	 */
	public static ArrayList<ArrayList<Object>> readExcel2003(File file, int heedRow)
	{
		try
		{
			ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
			ArrayList<Object> colList;
			FileInputStream filestream = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(filestream);
			int sheetnum = wb.getNumberOfSheets();
			for (int s = 0; s < sheetnum; s++)
			{
				HSSFSheet sheet = wb.getSheetAt(s);
				HSSFRow row;
				HSSFCell cell;
				Object value;
				for (int i = sheet.getFirstRowNum() + heedRow, rowCount = heedRow; rowCount < sheet
						.getPhysicalNumberOfRows(); i++)
				{
					row = sheet.getRow(i);
					colList = new ArrayList<Object>();
					if(row == null)
					{
						//当读取行为空�? 
						if(i != sheet.getPhysicalNumberOfRows())
						{//判断是否是最后一�? 
							rowList.add(colList);
						}
						continue;
					}
					else
					{
						rowCount++;
					}
					for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++)
					{
						cell = row.getCell(j);
						if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
						{
							//当该单元格为�? 
							if(j != row.getLastCellNum())
							{//判断是否是该行中�?���?��单元�? 
								colList.add("");
							}
							continue;
						}
						switch (cell.getCellType())
						{
							case XSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								if(cell.getCellStyle().getDataFormatString().indexOf("%") != -1)
								{
									double value1 = cell.getNumericCellValue();
									value = BigDecimal.valueOf(value1).multiply(BigDecimal.valueOf(100.00))
											.setScale(2, BigDecimal.ROUND_HALF_UP)
											+ "%";
								}
								else
								{
									cell.setCellType(XSSFCell.CELL_TYPE_STRING);
									/*
									 * if ("@".equals(cell.getCellStyle().
									 * getDataFormatString())) { value =
									 * df.format
									 * (cell.getNumericCellValue()).toString();
									 * 
									 * } else if
									 * ("General".equals(cell.getCellStyle()
									 * .getDataFormatString())) { value =
									 * df.format
									 * (cell.getNumericCellValue()).toString();
									 * }else{ value =
									 * sdf.format(HSSFDateUtil.getJavaDate(cell
									 * .getNumericCellValue())); }
									 */
									if("DateUtil".equals(cell.getCellStyle().getDataFormatString()))
									{
										value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
									}
									else
									{
										value = cell.toString();
									}
								}
								break;
							case XSSFCell.CELL_TYPE_BOOLEAN:
								value = Boolean.valueOf(cell.getBooleanCellValue());
								break;
							case XSSFCell.CELL_TYPE_BLANK:
								value = "";
								break;
							default:
								value = cell.toString();
						}// end switch  
						colList.add(value);
					}//end for j  
					rowList.add(colList);
				}//end for i  
			}
			filestream.close();
			return rowList;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public static ArrayList<ArrayList<Object>> readExcel2007(File file, int heedRow)
	{
		try
		{
			ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
			ArrayList<Object> colList;
			FileInputStream filestream = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(filestream);
			int sheetnum = wb.getNumberOfSheets();
			for (int s = 0; s < sheetnum; s++)
			{
				XSSFSheet sheet = wb.getSheetAt(s);
				log.info("sheetPhysicalNumberOfRows:" + sheet.getPhysicalNumberOfRows());
				XSSFRow row;
				XSSFCell cell;
				Object value;
				for (int i = sheet.getFirstRowNum() + heedRow, rowCount = heedRow; rowCount < sheet
						.getPhysicalNumberOfRows(); i++)
				{
					row = sheet.getRow(i);
					colList = new ArrayList<Object>();
					//System.out.println("row:"+row+"--cell:"+row.getPhysicalNumberOfCells());
					if(row == null)
					{
						//当读取行为空 
						if(i != sheet.getPhysicalNumberOfRows())
						{//判断是否是最后
							rowList.add(colList);
						}
						rowCount++;
						continue;
					}else if(row.getPhysicalNumberOfCells()==0){
						rowCount++;
						continue;
					}
					else
					{
						rowCount++;
					}
					for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++)
					{
						cell = row.getCell(j);
						if(cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
						{
							//当该单元格为
							if(j != row.getLastCellNum())
							{//判断是否是该行中�?���?��单元�? 
								colList.add("");
							}
							continue;
						}
						switch (cell.getCellType())
						{
							case XSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								if(cell.getCellStyle().getDataFormatString().indexOf("%") != -1)
								{
									double value1 = cell.getNumericCellValue();
									value = BigDecimal.valueOf(value1).multiply(BigDecimal.valueOf(100.00))
											.setScale(2, BigDecimal.ROUND_HALF_UP)
											+ "%";
								}
								else
								{
									cell.setCellType(XSSFCell.CELL_TYPE_STRING);
									/*
									 * if ("@".equals(cell.getCellStyle().
									 * getDataFormatString())) { value =
									 * df.format
									 * (cell.getNumericCellValue()).toString();
									 * 
									 * } else if
									 * ("General".equals(cell.getCellStyle()
									 * .getDataFormatString())) { value =
									 * df.format
									 * (cell.getNumericCellValue()).toString();
									 * }else{ value =
									 * sdf.format(HSSFDateUtil.getJavaDate(cell
									 * .getNumericCellValue())); }
									 */
									if("DateUtil".equals(cell.getCellStyle().getDataFormatString()))
									{
										value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
									}
									else
									{
										value = cell.toString();
									}
								}
								break;
							case XSSFCell.CELL_TYPE_BOOLEAN:
								value = Boolean.valueOf(cell.getBooleanCellValue());
								break;
							case XSSFCell.CELL_TYPE_BLANK:
								value = "";
								break;
							default:
								value = cell.toString();
						}// end switch  
						colList.add(value);
					}//end for j  
					rowList.add(colList);
				}//end for i  
			}

			filestream.close();
			return rowList;
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}

	}

	public static void writeExcel(ArrayList<ArrayList<Object>> result, String path) throws IOException
	{
		if(result == null)
		{
			return;
		}
		// XSSFWorkbook wb = new XSSFWorkbook();
		SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook();
		// XSSFSheet sheet = wb.createSheet("sheet1"); 
		SXSSFSheet sheet = sxssfWorkbook.createSheet("sheet1");
		for (int i = 0; i < result.size(); i++)
		{
			SXSSFRow row = sheet.createRow(i);
			if(result.get(i) != null)
			{
				for (int j = 0; j < result.get(i).size(); j++)
				{
					SXSSFCell cell = row.createCell(j);
					cell.setCellValue(result.get(i).get(j) == null ? "" : result.get(i).get(j).toString());
				}
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try
		{
			sxssfWorkbook.write(os);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		File file = new File(path);//Excel文件生成后存储的位
		if(!file.exists()) file.createNewFile();
		OutputStream fos = null;
		try
		{
			fos = new FileOutputStream(file);
			fos.write(content);
			os.close();
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @Title: exportExcelSheets
	 * @Description: 导出Excel多sheet的方法
	 * @param result
	 *            （表头,表格数据,对应sheet名称）
	 * @param path
	 *            （文件保存路径）
	 * @throws Exception
	 */
	public static void exportExcelSheets(Map<String, ArrayList<ArrayList<Object>>> result, String path)
		throws Exception
	{
		SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook();
		for (String key : result.keySet())
		{
			SXSSFSheet sheet = sxssfWorkbook.createSheet(key);
			sheet.setDefaultColumnWidth((short) 30);
			// 生成一个样式  
			CellStyle style = sxssfWorkbook.createCellStyle();
			// 设置这些样式   
			style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			style.setBorderRight(XSSFCellStyle.BORDER_THIN);
			style.setBorderTop(XSSFCellStyle.BORDER_THIN);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			// 生成一个字体  
			Font font = sxssfWorkbook.createFont();
			font.setFontHeightInPoints((short) 12);
			// 把字体应用到当前的样式  
			style.setFont(font);
			// 指定当单元格内容显示不下时自动换行  
			style.setWrapText(true);
			// 产生表格标题行  
			//SXSSFRow row = sheet.createRow(0);  
			/*
			 * for (int i = 0; i < headers.length; i++) { SXSSFCell cell =
			 * row.createCell((short) i);
			 * 
			 * cell.setCellStyle(style); XSSFRichTextString text = new
			 * XSSFRichTextString(headers[i]);
			 * cell.setCellValue(text.toString()); }
			 */
			// 遍历集合数据，产生数据行
			ArrayList<ArrayList<Object>> keylist = result.get(key);
			if(keylist != null)
			{
				for (int i = 0; i < keylist.size(); i++)
				{
					SXSSFRow datarow = sheet.createRow(i);
					for (int j = 0; j < keylist.get(i).size(); j++)
					{
						if(i == 0)
						{
							sheet.setColumnWidth(j, (short) (18 * 256));
						}
						SXSSFCell cell = datarow.createCell(j);
						cell.setCellValue(keylist.get(i).get(j) == null ? "" : keylist.get(i).get(j).toString());
						cell.setCellStyle(style);
					}
				}
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try
		{
			sxssfWorkbook.write(os);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		File file = new File(path);//Excel文件生成后存储的位
		if(!file.exists()) file.createNewFile();
		OutputStream fos = null;
		try
		{
			fos = new FileOutputStream(file);
			fos.write(content);
			os.close();
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 写入excel
	 * @param mergedArr
	 * @param columnlengthArr
	 * @param filePath
	 * @param month
	 * @param datalist
	 * @param sheetname
	 * @throws IOException
	 */
	public static void writeJSBBExcel(String[] mergedArr, int[] columnlengthArr, String filePath, String month,
			List<List<ArrayList<Object>>> datalist,String[] sheetname) throws IOException
	{
		// 创建新的Excel 工作簿
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		for(int pos=0;pos<mergedArr.length;pos++){
			List<ArrayList<Object>> list = datalist.get(pos);
			int columnlength=columnlengthArr[pos];
			String mergedRegion=mergedArr[pos];
			// 设置样式
			Font f = workbook.createFont();
			f.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			f.setFontHeightInPoints((short) 20);
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(f);
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

			Font fleft = workbook.createFont();
			fleft.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
			fleft.setFontHeightInPoints((short) 12);
			CellStyle cellLeftStyle = workbook.createCellStyle();
			cellLeftStyle.setFont(fleft);
			cellLeftStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellLeftStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);

			CellStyle cs = workbook.createCellStyle();
			Font fcs = workbook.createFont();
			fcs.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
			fcs.setFontHeightInPoints((short) 12);
			cs.setFont(fcs);
			cs.setAlignment(CellStyle.ALIGN_CENTER);
			cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cs.setWrapText(true);
			// 第一行
			// 在索引0的位置创建行（最顶端的行）
			SXSSFSheet sheet = workbook.createSheet(sheetname[pos]);
			SXSSFRow row = sheet.createRow(0);
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, columnlength - 1);
			sheet.addMergedRegion(cellRangeAddress);
			// 在索引0的位置创建单元格（左上端）
			SXSSFCell cell = row.createCell(0);
			// 定义单元格为字符串类型
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellStyle(cellStyle);
			// 在单元格中输入一些内容
			cell.setCellValue("第三方支付结算表");

			// 第二行
			row = sheet.createRow(1);
			cellRangeAddress = new CellRangeAddress(1, 1, 0, columnlength - 1);
			sheet.addMergedRegion(cellRangeAddress);
			cell = row.createCell(0);
			cell.setCellStyle(cellLeftStyle);
			cell.setCellValue("结算周期：" + month);

			// 处理表头，第三行开始
			String[] mergedRegions = mergedRegion.split(";");
			//创建一个虚拟表头，并使用false标识这个单元格没有被占用
			List<Map<Integer, Boolean>> headerLists = new ArrayList<Map<Integer, Boolean>>();
			for (int i = 0; i <= mergedRegions.length + 1; i++)
			{
				Map<Integer, Boolean> headerMap = new HashMap<Integer, Boolean>();
				for (int j = 0; j < columnlength; j++)
				{
					headerMap.put(j, false);
					if(i == 0)
					{
						sheet.setColumnWidth(j, (short) (12 * 256));
					}
				}
				headerLists.add(headerMap);
			}
			for (int i = 0; i < mergedRegions.length; i++)
			{
				String mergedRegionss = mergedRegions[i];
				int x = 2 + i;
				int y = 2 + i;
				int m = 0;
				int n = 0;
				row = sheet.createRow(2 + i);
				row.setHeight((short) (3 * 256));
				String[] _mergedRegionss = mergedRegionss.split(":");
				for (int j = 0; j < _mergedRegionss.length; j++)
				{
					String mergedRegionsss = _mergedRegionss[j];
					String[] _mergedRegionsss = mergedRegionsss.split(",");
					//获取最小行中未被占用的单元格
					List<Integer> cellNum = new ArrayList<Integer>();
					for (int mm = 0; mm < headerLists.size(); mm++)
					{
						Map<Integer, Boolean> headerMap = headerLists.get(mm);
						for (Integer key : headerLists.get(mm).keySet())
						{
							if(!headerMap.get(key))
							{
								cellNum.add(key);
							}
						}
						if(cellNum.size() > 0)
						{
							break;
						}
					}
					Collections.sort(cellNum);
					m = cellNum.get(0);

					int _y = y + (Integer.parseInt(_mergedRegionsss[1])) - 1;
					if(Integer.parseInt(_mergedRegionsss[1]) == 0)
					{
						_y = y;
					}

					n = m + (Integer.parseInt(_mergedRegionsss[2])) - 1;
					if(Integer.parseInt(_mergedRegionsss[2]) == 0)
					{
						n = m;
					}

					// String cra = x + ", " + _y + ", " + m + ", " + n;
					for (int t = x - 2; t <= _y - 2; t++)
					{
						for (int k = m; k <= n; k++)
						{
							headerLists.get(t).put(k, true);
						}
					}
					if(x != _y || m != n)
					{
						cellRangeAddress = new CellRangeAddress(x, _y, m, n);
						sheet.addMergedRegion(cellRangeAddress);
					}
					cell = row.createCell(m);
					cell.setCellStyle(cs);
					cell.setCellValue(_mergedRegionsss[0]);
					m = n + 1;
				}
			}
			for (int i = 5; i < list.size() + 5; i++)
			{
				SXSSFRow row1 = sheet.createRow(i);
				row1.setHeight((short) (70 * 20));
				if(list.get(i - 5) != null)
				{
					for (int j = 0; j < list.get(i - 5).size(); j++)
					{
						SXSSFCell cell1 = row1.createCell(j);
						cell1.setCellStyle(cs);
						cell1.setCellValue(list.get(i - 5).get(j) == null ? "" : list.get(i - 5).get(j).toString());
					}
				}
			}
		}
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try
		{
			workbook.write(os);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		File file = new File(filePath);//Excel文件生成后存储的位
		if(!file.exists()) file.createNewFile();
		OutputStream fos = null;
		try
		{
			fos = new FileOutputStream(file);
			fos.write(content);
			os.close();
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @Description: 导出jsbb，复杂表头
	 * @param mergedRegion
	 *            表头样式字符串(前台页面获取，合并的行数列数)
	 * @param columnlength
	 *            元组列数
	 * @param filePath
	 * @param month
	 *            月份(几月的结算报表)
	 * @param list
	 *            数据集
	 * @throws IOException
	 */
	public static void writePCJSBBExcel(String mergedRegion, int columnlength, String filePath, String month,
			List<ArrayList<Object>> list,String sheetname) throws IOException
	{
		// 创建新的Excel 工作簿
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		// 设置样式
		Font f = workbook.createFont();
		f.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		f.setFontHeightInPoints((short) 20);
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(f);
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		Font fleft = workbook.createFont();
		fleft.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		fleft.setFontName("宋体");
		fleft.setFontHeightInPoints((short) 14);
		CellStyle cellLeftStyle = workbook.createCellStyle();
		cellLeftStyle.setFont(fleft);
		cellLeftStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellLeftStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);

		CellStyle cscente = workbook.createCellStyle();
		Font fcscente = workbook.createFont();
		fcscente.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		fcscente.setFontName("宋体");
		fcscente.setFontHeightInPoints((short) 14);
		cscente.setFont(fcscente);
		cscente.setAlignment(CellStyle.ALIGN_CENTER);
		cscente.setVerticalAlignment(CellStyle.ALIGN_LEFT);
		cscente.setWrapText(true);
		
		CellStyle cscenter = workbook.createCellStyle();
		Font fcscenter = workbook.createFont();
		fcscenter.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		fcscenter.setFontName("宋体");
		fcscenter.setFontHeightInPoints((short) 14);
		cscenter.setFont(fcscenter);
		cscenter.setAlignment(CellStyle.ALIGN_CENTER);
		cscenter.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cscenter.setWrapText(true);
		
		CellStyle cs = workbook.createCellStyle();
		Font fcs = workbook.createFont();
		fcs.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		fcs.setFontName("宋体");
		fcs.setFontHeightInPoints((short) 10);
		cs.setFont(fcs);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setWrapText(true);
		// 第一行
		// 在索引0的位置创建行（最顶端的行）
		SXSSFSheet sheet = workbook.createSheet(sheetname);
		SXSSFRow row = sheet.createRow(0);
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 28);
		sheet.addMergedRegion(cellRangeAddress);
		// 在索引0的位置创建单元格（左上端）
		SXSSFCell cell = row.createCell(0);
		// 定义单元格为字符串类型
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell.setCellStyle(cellStyle);
		// 在单元格中输入一些内容
		cell.setCellValue("智慧家庭运营中心第三方支付分省结算明细表");

		// 第二行
		row = sheet.createRow(1);
		cellRangeAddress = new CellRangeAddress(1, 1, 0, 9);
		sheet.addMergedRegion(cellRangeAddress);
		cell = row.createCell(0);
		cell.setCellStyle(cellLeftStyle);
		month=month==null?"全部":month;
		cell.setCellValue("结算周期：" + month);
		cellRangeAddress = new CellRangeAddress(1, 1, 10, 28);
		sheet.addMergedRegion(cellRangeAddress);
		cell = row.createCell(10);
		cell.setCellStyle(cellLeftStyle);
		cell.setCellValue("金额单位：元");
		// 第三行
				row = sheet.createRow(2);
				
				cellRangeAddress = new CellRangeAddress(2, 4, 0, 0);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(0);
				cell.setCellStyle(cscenter);
				cell.setCellValue("序号");
				
				cellRangeAddress = new CellRangeAddress(2, 4, 1, 1);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(1);
				cell.setCellStyle(cscenter);
				cell.setCellValue("业务类型");
				
				cellRangeAddress = new CellRangeAddress(2, 3, 2, 9);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(2);
				cell.setCellStyle(cscenter);
				cell.setCellValue("分省结算信息");
				
				cellRangeAddress = new CellRangeAddress(2, 2, 10, 18);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(10);
				cell.setCellStyle(cscenter);
				cell.setCellValue("平台收入相关数据");
				
				cellRangeAddress = new CellRangeAddress(2, 2, 19, 24);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(19);
				cell.setCellStyle(cscenter);
				cell.setCellValue("实收及结算金额");
				
				cellRangeAddress = new CellRangeAddress(2, 3, 25, 28);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(25);
				cell.setCellStyle(cscenter);
				cell.setCellValue("业务收款合计");
				// 第四行
				row = sheet.createRow(3);
				
				cellRangeAddress = new CellRangeAddress(3, 3, 10, 12);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(10);
				cell.setCellStyle(cscente);
				cell.setCellValue("微信");
				
				cellRangeAddress = new CellRangeAddress(3, 3, 13, 15);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(13);
				cell.setCellStyle(cscente);
				cell.setCellValue("支付宝");
				
				cellRangeAddress = new CellRangeAddress(3, 3, 16, 18);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(16);
				cell.setCellStyle(cscente);
				cell.setCellValue("翼支付");
				
				cellRangeAddress = new CellRangeAddress(3, 3, 19, 20);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(19);
				cell.setCellStyle(cscente);
				cell.setCellValue("微信");
				
				cellRangeAddress = new CellRangeAddress(3, 3, 21, 22);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(21);
				cell.setCellStyle(cscente);
				cell.setCellValue("支付宝");
				
				cellRangeAddress = new CellRangeAddress(3, 3, 23, 24);
				sheet.addMergedRegion(cellRangeAddress);
				cell = row.createCell(23);
				cell.setCellStyle(cscente);
				cell.setCellValue("翼支付");
				// 第五行
				row = sheet.createRow(4);

				cell = row.createCell(2);
				cell.setCellStyle(cs);
				cell.setCellValue("省份");

				cell = row.createCell(3);
				cell.setCellStyle(cs);
				cell.setCellValue("视听/非视听");

				cell = row.createCell(4);
				cell.setCellStyle(cs);
				cell.setCellValue("省公司名称");

				cell = row.createCell(5);
				cell.setCellStyle(cs);
				cell.setCellValue("开户行");

				cell = row.createCell(6);
				cell.setCellStyle(cs);
				cell.setCellValue("开户名");

				cell = row.createCell(7);
				cell.setCellStyle(cs);
				cell.setCellValue("银行账户");

				cell = row.createCell(8);
				cell.setCellStyle(cs);
				cell.setCellValue("坏账率");

				cell = row.createCell(9);
				cell.setCellStyle(cs);
				cell.setCellValue("结算比例");

				cell = row.createCell(10);
				cell.setCellStyle(cs);
				cell.setCellValue("应收总金额（订单金额）");

				cell = row.createCell(11);
				cell.setCellStyle(cs);
				cell.setCellValue("退费");

				cell = row.createCell(12);
				cell.setCellStyle(cs);
				cell.setCellValue("手续费");

				cell = row.createCell(13);
				cell.setCellStyle(cs);
				cell.setCellValue("应收总金额（订单金额）");

				cell = row.createCell(14);
				cell.setCellStyle(cs);
				cell.setCellValue("退费");

				cell = row.createCell(15);
				cell.setCellStyle(cs);
				cell.setCellValue("手续费");

				cell = row.createCell(16);
				cell.setCellStyle(cs);
				cell.setCellValue("应收总金额（订单金额）");

				cell = row.createCell(17);
				cell.setCellStyle(cs);
				cell.setCellValue("退费");

				cell = row.createCell(18);
				cell.setCellStyle(cs);
				cell.setCellValue("手续费");

				cell = row.createCell(19);
				cell.setCellStyle(cs);
				cell.setCellValue("实收总金额");

				cell = row.createCell(20);
				cell.setCellStyle(cs);
				cell.setCellValue("结算金额（价税合计）");

				cell = row.createCell(21);
				cell.setCellStyle(cs);
				cell.setCellValue("实收总金额");

				cell = row.createCell(22);
				cell.setCellStyle(cs);
				cell.setCellValue("结算金额（价税合计）");
				
				cell = row.createCell(23);
				cell.setCellStyle(cs);
				cell.setCellValue("实收总金额");

				cell = row.createCell(24);
				cell.setCellStyle(cs);
				cell.setCellValue("结算金额（价税合计）");
				
				cell = row.createCell(25);
				cell.setCellStyle(cs);
				cell.setCellValue("实收总金额（订单金额）");

				cell = row.createCell(26);
				cell.setCellStyle(cs);
				cell.setCellValue("结算金额（价税合计）");
				
				cell = row.createCell(27);
				cell.setCellStyle(cs);
				cell.setCellValue("结算金额（价款）");
				
				cell = row.createCell(28);
				cell.setCellStyle(cs);
				cell.setCellValue("税款");
				
		for (int i = 5; i < list.size() + 5; i++)
		{
			SXSSFRow row1 = sheet.createRow(i);
			row1.setHeight((short) (70 * 20));
			if(list.get(i - 5) != null)
			{
				for (int j = 0; j < list.get(i - 5).size(); j++)
				{
					SXSSFCell cell1 = row1.createCell(j);
					cell1.setCellStyle(cs);
					cell1.setCellValue(list.get(i - 5).get(j) == null ? "" : list.get(i - 5).get(j).toString());
				}
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try
		{
			workbook.write(os);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		File file = new File(filePath);//Excel文件生成后存储的位
		if(!file.exists()) file.createNewFile();
		OutputStream fos = null;
		try
		{
			fos = new FileOutputStream(file);
			fos.write(content);
			os.close();
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 导出pcjsbb，复杂表头
	 * @param mergedRegion
	 *            表头样式字符串(前台页面获取，合并的行数列数)
	 * @param columnlength
	 *            元组列数
	 * @param filePath
	 * @param month
	 *            月份(几月的结算报表)
	 * @param list
	 *            数据集
	 * @throws IOException
	 */
	public static void writeJSBBExcel(String mergedRegion, int columnlength, String filePath, String month,
			List<ArrayList<Object>> list,String sheetname) throws IOException
	{
		// 创建新的Excel 工作簿
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		// 设置样式
		Font f = workbook.createFont();
		f.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		f.setFontHeightInPoints((short) 20);
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(f);
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		Font fleft = workbook.createFont();
		fleft.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		fleft.setFontHeightInPoints((short) 12);
		CellStyle cellLeftStyle = workbook.createCellStyle();
		cellLeftStyle.setFont(fleft);
		cellLeftStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellLeftStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);

		CellStyle cs = workbook.createCellStyle();
		Font fcs = workbook.createFont();
		fcs.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		fcs.setFontHeightInPoints((short) 12);
		cs.setFont(fcs);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setWrapText(true);
		// 第一行
		// 在索引0的位置创建行（最顶端的行）
		SXSSFSheet sheet = workbook.createSheet(sheetname);
		SXSSFRow row = sheet.createRow(0);
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, columnlength - 1);
		sheet.addMergedRegion(cellRangeAddress);
		// 在索引0的位置创建单元格（左上端）
		SXSSFCell cell = row.createCell(0);
		// 定义单元格为字符串类型
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell.setCellStyle(cellStyle);
		// 在单元格中输入一些内容
		cell.setCellValue("第三方支付结算表");

		// 第二行
		row = sheet.createRow(1);
		cellRangeAddress = new CellRangeAddress(1, 1, 0, columnlength - 1);
		sheet.addMergedRegion(cellRangeAddress);
		cell = row.createCell(0);
		cell.setCellStyle(cellLeftStyle);
		cell.setCellValue("结算周期：" + month);

		// 处理表头，第三行开始
		String[] mergedRegions = mergedRegion.split(";");
		//创建一个虚拟表头，并使用false标识这个单元格没有被占用
		List<Map<Integer, Boolean>> headerLists = new ArrayList<Map<Integer, Boolean>>();
		for (int i = 0; i <= mergedRegions.length + 1; i++)
		{
			Map<Integer, Boolean> headerMap = new HashMap<Integer, Boolean>();
			for (int j = 0; j < columnlength; j++)
			{
				headerMap.put(j, false);
				if(i == 0)
				{
					sheet.setColumnWidth(j, (short) (12 * 256));
				}
			}
			headerLists.add(headerMap);
		}
		for (int i = 0; i < mergedRegions.length; i++)
		{
			String mergedRegionss = mergedRegions[i];
			int x = 2 + i;
			int y = 2 + i;
			int m = 0;
			int n = 0;
			row = sheet.createRow(2 + i);
			row.setHeight((short) (3 * 256));
			String[] _mergedRegionss = mergedRegionss.split(":");
			for (int j = 0; j < _mergedRegionss.length; j++)
			{
				String mergedRegionsss = _mergedRegionss[j];
				String[] _mergedRegionsss = mergedRegionsss.split(",");
				//获取最小行中未被占用的单元格
				List<Integer> cellNum = new ArrayList<Integer>();
				for (int mm = 0; mm < headerLists.size(); mm++)
				{
					Map<Integer, Boolean> headerMap = headerLists.get(mm);
					for (Integer key : headerLists.get(mm).keySet())
					{
						if(!headerMap.get(key))
						{
							cellNum.add(key);
						}
					}
					if(cellNum.size() > 0)
					{
						break;
					}
				}
				Collections.sort(cellNum);
				m = cellNum.get(0);

				int _y = y + (Integer.parseInt(_mergedRegionsss[1])) - 1;
				if(Integer.parseInt(_mergedRegionsss[1]) == 0)
				{
					_y = y;
				}

				n = m + (Integer.parseInt(_mergedRegionsss[2])) - 1;
				if(Integer.parseInt(_mergedRegionsss[2]) == 0)
				{
					n = m;
				}

				// String cra = x + ", " + _y + ", " + m + ", " + n;
				for (int t = x - 2; t <= _y - 2; t++)
				{
					for (int k = m; k <= n; k++)
					{
						headerLists.get(t).put(k, true);
					}
				}
				if(x != _y || m != n)
				{
					cellRangeAddress = new CellRangeAddress(x, _y, m, n);
					sheet.addMergedRegion(cellRangeAddress);
				}
				cell = row.createCell(m);
				cell.setCellStyle(cs);
				cell.setCellValue(_mergedRegionsss[0]);
				m = n + 1;
			}
		}
		for (int i = 5; i < list.size() + 5; i++)
		{
			SXSSFRow row1 = sheet.createRow(i);
			row1.setHeight((short) (70 * 20));
			if(list.get(i - 5) != null)
			{
				for (int j = 0; j < list.get(i - 5).size(); j++)
				{
					SXSSFCell cell1 = row1.createCell(j);
					cell1.setCellStyle(cs);
					cell1.setCellValue(list.get(i - 5).get(j) == null ? "" : list.get(i - 5).get(j).toString());
				}
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try
		{
			workbook.write(os);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		File file = new File(filePath);//Excel文件生成后存储的位
		if(!file.exists()) file.createNewFile();
		OutputStream fos = null;
		try
		{
			fos = new FileOutputStream(file);
			fos.write(content);
			os.close();
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static List<String[]> readCSV(File file, String flag)
	{
		List<String[]> list = new ArrayList<String[]>();
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
			// FileReader filereader=new FileReader(file);

			// BufferedReader reader = new BufferedReader(filereader);
			String line = null;
			String[] str = null;

			//微信明细数据    第一行是标题 ，最后两行 是总结  提取数据需要过滤
			if("wx".equals(flag))
			{
				while ((line = reader.readLine()) != null)
				{
					str = line.replaceAll("`", "").replaceAll("\"", "").split(",");
					list.add(str);
				}
			}
			//支付宝明细数据  前四行 和最后四行是总结  第5行是 标题  提取数据时需要过滤
			if("zfb".equals(flag))
			{
				while ((line = reader.readLine()) != null)
				{
					str = line.replaceAll("\"", "").split(",");
					list.add(str);
				}
			}
			if("yzf".equals(flag))
			{
				while ((line = reader.readLine()) != null)
				{
					str = line.replaceAll("\"", "").split(",");
					list.add(str);
				}
			}
			reader.close();
			//filereader.close();
			return list;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static DecimalFormat getDf()
	{
		return df;
	}

	public static void setDf(DecimalFormat df)
	{
		ExcelUtil.df = df;
	}

	public static SimpleDateFormat getSdf()
	{
		return sdf;
	}

	public static void setSdf(SimpleDateFormat sdf)
	{
		ExcelUtil.sdf = sdf;
	}

	public static DecimalFormat getNf()
	{
		return nf;
	}

	public static void setNf(DecimalFormat nf)
	{
		ExcelUtil.nf = nf;
	}
}
