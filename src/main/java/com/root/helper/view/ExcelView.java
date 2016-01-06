/* 
 * Copyright (c) 2016, S.F. Express Inc. All rights reserved.
 */
package com.root.helper.view;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

@SuppressWarnings("deprecation")
public class ExcelView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// sheet存在导出文件的机构代码，导入时需要使用此数据项
		String sheetName = (String) model.get("sheetName");
		HSSFSheet sheet = workbook.createSheet(sheetName);

		sheet.setDefaultColumnWidth(12);
		String[] cellsTitle = (String[]) model.get("cellsTitle");
		HSSFCell cell = null;

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);

		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框

		int row = 0; // 开始行
		int column = 0; // 开始列
		int columns = 0; // 合并列数
		// 报表头部
		Map<Integer, String> header = (Map<Integer, String>) model
				.get("header");
		if (header != null) {
			for (Map.Entry<Integer, String> entry : header.entrySet()) {
				columns = entry.getKey().intValue(); // 合并单元格列数
				String cotent = entry.getValue(); // 单元格内容

				cell = getCell(sheet, row, 0);
				cell.setCellType(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle); // 设置单元格样式
				setText(cell, cotent); // 设置第row行0列单元格

				for (int i = 1; i < columns; i++) { // 从第2列开始
					cell = getCell(sheet, row, i);
					cell.setCellStyle(cellStyle); // 设置单元格样式
					setText(cell, ""); // 设置第row行1列到column列单元格
				}
				sheet.addMergedRegion(new CellRangeAddress(row, row, 0,
						columns - 1)); // 合并单元格
			}
			row = 1; // 余下的从第1行开始
		}

		columns = 0;
		String[] columnTitles = null;
		String columnTitle = "";
		// 报表合并标题
		Map<Integer, String[]> titles = (Map<Integer, String[]>) model
				.get("titles");
		if (titles != null) {
			for (String content : cellsTitle) {
				columnTitles = titles.get(column);
				if (columnTitles != null) {
					columns = column + Integer.valueOf(columnTitles[0]) - 1;
					columnTitle = columnTitles[1];
				}
				if (columnTitles != null || (column <= columns && column != 0)) {
					cell = getCell(sheet, row, column);
					cell.setCellType(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle);
					setText(cell, columnTitle);
					sheet.addMergedRegion(new CellRangeAddress(row, row,
							column, columns));

					cell = getCell(sheet, row + 1, column);
					cell.setCellType(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle);
					setText(cell, content);
				} else {
					cell = getCell(sheet, row, column);
					cell.setCellType(HSSFCell.ENCODING_UTF_16);
					cellStyle
							.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置单元格内容上下居中
					cell.setCellStyle(cellStyle);
					setText(cell, content);

					cell = getCell(sheet, row + 1, column);
					cell.setCellStyle(cellStyle);
					setText(cell, "");
					sheet.addMergedRegion(new CellRangeAddress(row, row + 1,
							column, column));
				}
				column += 1;
			}
			row += 2;
		} else {
			for (String content : cellsTitle) {
				cell = getCell(sheet, row, column);
				cell.setCellType(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle);
				setText(cell, content);
				column += 1;
			}
			row += 1;
		}

		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 上边框
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 左边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 右边框

		ArrayList<String[]> excelData = (ArrayList<String[]>) model.get("data");
		if (excelData != null) {
			// 记录行
			for (String[] contents : excelData) {
				column = 0;
				for (String content : contents) {
					cell = getCell(sheet, row, column);
					cell.setCellType(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle);
					setText(cell, content);
					column += 1;
				}
				row += 1;
			}
		}

		Map<String[], List<String[]>> rowData = (Map<String[], List<String[]>>) model
				.get("rowData");
		if (rowData != null && rowData.size() > 0) {
			for (Map.Entry<String[], List<String[]>> entry : rowData.entrySet()) {
				String[] keys = entry.getKey();
				List<String[]> valueList = entry.getValue();
				int size = valueList.size();

				for (int i = 0; i < keys.length; i++) {
					cell = getCell(sheet, row, i);
					cell.setCellType(HSSFCell.ENCODING_UTF_16);
					cellStyle
							.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置单元格内容上下居中
					cell.setCellStyle(cellStyle);
					setText(cell, keys[i]);

					if (valueList.size() > 1) {
						for (int j = 1; j < valueList.size(); j++) {
							cell = getCell(sheet, row + j, i);
							cell.setCellStyle(cellStyle);
							setText(cell, "");
						}
						sheet.addMergedRegion(new CellRangeAddress(row, row
								+ valueList.size() - 1, i, i));
					}
				}

				for (int i = 0; i < valueList.size(); i++) {
					String[] values = valueList.get(i);
					for (int j = 0; j < values.length; j++) {
						cell = getCell(sheet, row + i, keys.length + j);
						cell.setCellType(HSSFCell.ENCODING_UTF_16);
						cell.setCellStyle(cellStyle);
						// setText(cell, values[i]);
						setText(cell, values[j]);
					}
				}
				row += size;
			}
		}

		columns = 0;
		// 报表底部
		Map<Integer, List<String>> footer = (Map<Integer, List<String>>) model
				.get("footer");
		if (footer != null) {
			for (Map.Entry<Integer, List<String>> entry : footer.entrySet()) {
				columns = entry.getKey().intValue(); // 合并单元格列数
				List<String> totals = entry.getValue();

				cell = getCell(sheet, row, 0);
				cell.setCellType(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle);
				setText(cell, "合计：");
				for (int k = 1; k < columns; k++) {
					cell = getCell(sheet, row, k);
					cell.setCellStyle(cellStyle);
					setText(cell, "");
				}
				sheet.addMergedRegion(new CellRangeAddress(row, row, 0,
						columns - 1));

				for (String total : totals) {
					cell = getCell(sheet, row, columns++);
					cell.setCellType(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyle);
					setText(cell, total);
				}

				for (int i = columns; i < cellsTitle.length; i++) {
					cell = getCell(sheet, row, i);
					cell.setCellStyle(cellStyle);
					setText(cell, "");
				}
			}
		}

		String filename = model.get("fileName") + ".xls";// 设置下载时客户端Excel的名称
		filename = ExcelNameEncode.encodeFilename(filename, request);// 处理中文文件名
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		// response.setContentType("application/msexcel;charset=GB2312");
		response.setHeader("Content-disposition", "attachment;filename="
				+ filename);
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
}
