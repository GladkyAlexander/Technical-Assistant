package ru.greatlarder.technicalassistant.services.work_doc;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class ExelZip {
    private final String company;
    FileManager fileManager = new FileManagerImpl();
    HSSFCellStyle styleCell;

    public ExelZip(List<Equipment> listEquipment, String nameFile, String nameCompany) {
        this.company = nameCompany;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(nameFile);
        HSSFCellStyle style = createStyleForTitle(workbook);

        styleCell = createStyle(workbook);

        int rowNum = 1;

        Row row = sheet.createRow(rowNum);
        row.createCell(1).setCellValue("ID");
        row.getCell(1).setCellStyle(style);
        sheet.autoSizeColumn(1);

        row.createCell(2).setCellValue("Оборудование");
        row.getCell(2).setCellStyle(style);
        sheet.autoSizeColumn(2);

        row.createCell(3).setCellValue("Производитель");
        row.getCell(3).setCellStyle(style);
        sheet.autoSizeColumn(3);

        row.createCell(4).setCellValue("Серийный номер");
        row.getCell(4).setCellStyle(style);
        sheet.autoSizeColumn(4);

        row.createCell(5).setCellValue("Дата начала эксплуатации");
        row.getCell(5).setCellStyle(style);
        sheet.autoSizeColumn(5);

        for (Equipment equipment : listEquipment) {
            createSheetHeader(sheet, ++rowNum, equipment);
        }

        try (FileOutputStream out = new FileOutputStream(fileManager.folderCompanyDocumentation(company) + "\\" + nameFile)){
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createSheetHeader(HSSFSheet sheet, int rowNum, Equipment equipment) {

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("");

        row.createCell(1).setCellValue(equipment.getId());
        row.getCell(1).setCellStyle(styleCell);
        sheet.autoSizeColumn(1);

        row.createCell(2).setCellValue(equipment.getName());
        row.getCell(2).setCellStyle(styleCell);
        sheet.autoSizeColumn(2);

        row.createCell(3).setCellValue(equipment.getManufacturer());
        row.getCell(3).setCellStyle(styleCell);
        sheet.autoSizeColumn(3);

        row.createCell(4).setCellValue(equipment.getSerialNumber());
        row.getCell(4).setCellStyle(styleCell);
        sheet.autoSizeColumn(4);
        
        row.createCell(5).setCellValue(String.valueOf(equipment.getDateWork()));
        row.getCell(5).setCellStyle(styleCell);
        sheet.autoSizeColumn(5);
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
