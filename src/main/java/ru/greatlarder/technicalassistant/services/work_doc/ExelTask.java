package ru.greatlarder.technicalassistant.services.work_doc;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExelTask {
    private final String company;
    FileManager fileManager = new FileManagerImpl();
    HSSFCellStyle styleCell;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public ExelTask(List<Affairs> listTasks, String nameFile, String nameCompany) {
        this.company = nameCompany;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(nameFile);

        HSSFCellStyle style = createStyleForTitle(workbook);

        styleCell = createStyle(workbook);

        int rowNum = 1;

        Row row = sheet.createRow(rowNum);
        row.createCell(1).setCellValue("Дата");
        row.getCell(1).setCellStyle(style);
        sheet.autoSizeColumn(1);

        row.createCell(2).setCellValue("Время");
        row.getCell(2).setCellStyle(style);
        sheet.autoSizeColumn(2);

        row.createCell(3).setCellValue("Помещение");
        row.getCell(3).setCellStyle(style);
        sheet.autoSizeColumn(3);

        row.createCell(4).setCellValue("Заказчик");
        row.getCell(4).setCellStyle(style);
        sheet.autoSizeColumn(4);

        row.createCell(5).setCellValue("Просьба");
        row.getCell(5).setCellStyle(style);
        sheet.autoSizeColumn(5);

        for (Affairs task : listTasks) {
            createSheetHeader(sheet, ++rowNum, task);
        }

        try (FileOutputStream out = new FileOutputStream(fileManager.folderCompanyDocumentation(company) + "\\" + nameFile)){
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSheetHeader(HSSFSheet sheet, int rowNum, Affairs task) {

        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue("");

        row.createCell(1).setCellValue(task.getDateOfCreation().format(formatter));
        row.getCell(1).setCellStyle(styleCell);
        sheet.autoSizeColumn(1);

        row.createCell(2).setCellValue(task.getTimeOfCreation().toString());
        row.getCell(2).setCellStyle(styleCell);
        sheet.autoSizeColumn(2);

        row.createCell(3).setCellValue(task.getRoom());
        row.getCell(3).setCellStyle(styleCell);
        sheet.autoSizeColumn(3);

        row.createCell(4).setCellValue(task.getCreator());
        row.getCell(4).setCellStyle(styleCell);
        sheet.autoSizeColumn(4);

        row.createCell(5).setCellValue(task.getTextTask());
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
        style.setBorderRight(BorderStyle.DOUBLE);
        style.setBorderLeft(BorderStyle.DOUBLE);
        style.setBorderTop(BorderStyle.DOUBLE);
        style.setBorderBottom(BorderStyle.DOUBLE);

        style.setFont(font);
        return style;
    }
}
