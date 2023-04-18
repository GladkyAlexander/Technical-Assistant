package ru.greatlarder.technicalassistant.services.work_doc;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.Projector;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExelWorker {

	private final String company;
	FileManager fileManager = new FileManagerImpl();
	HSSFCellStyle styleCell;

	public ExelWorker(List<Equipment> listEquipment, String nameFile, String nameCompany) {
		this.company = nameCompany;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(nameFile);
		HSSFCellStyle style = createStyleForTitle(workbook);

		styleCell = createStyle(workbook);

		int rowNum = 1;

		Row row = sheet.createRow(rowNum);

		row.createCell(1).setCellValue("Серийный номер");
		row.getCell(1).setCellStyle(style);
		sheet.autoSizeColumn(1);

		row.createCell(2).setCellValue("Помещение");
		row.getCell(2).setCellStyle(style);
		sheet.autoSizeColumn(2);

		row.createCell(3).setCellValue("Время наработки");
		row.getCell(3).setCellStyle(style);
		sheet.autoSizeColumn(3);

		for (Equipment equipment : listEquipment) {
			createSheetHeader(sheet, ++rowNum, (Projector) equipment);
		}

	    try (FileOutputStream out = new FileOutputStream(fileManager.folderCompanyDocumentation(company) + "\\" + nameFile)){
		workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void createSheetHeader(HSSFSheet sheet, int rowNum, Projector equipment) {

		Row row = sheet.createRow(rowNum);

		row.createCell(0).setCellValue("");

		row.createCell(1).setCellValue(equipment.getSerialNumber());
		row.getCell(1).setCellStyle(styleCell);
		sheet.autoSizeColumn(1);

		row.createCell(2).setCellValue(equipment.getRoom());
		row.getCell(2).setCellStyle(styleCell);
		sheet.autoSizeColumn(2);

		row.createCell(3).setCellValue(equipment.getTimeWorkLampProjector());
		row.getCell(3).setCellStyle(styleCell);
		sheet.autoSizeColumn(3);
	}

	private HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		font.setFontHeightInPoints((short) 12);

		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THICK);
		style.setBorderLeft(BorderStyle.THICK);
		style.setBorderTop(BorderStyle.THICK);
		style.setBorderBottom(BorderStyle.THICK);

		style.setFont(font);
		return style;
	}

	private HSSFCellStyle createStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		font.setFontHeightInPoints((short) 10);

		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderRight(BorderStyle.THICK);
		style.setBorderLeft(BorderStyle.THICK);
		style.setBorderTop(BorderStyle.THICK);
		style.setBorderBottom(BorderStyle.THICK);

		style.setFont(font);
		return style;
	}

}
