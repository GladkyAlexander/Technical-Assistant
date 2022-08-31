package ru.greatlarder.technicalassistant.services.work_doc;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ReadExel {

    public  String parse(String fileName) {

        StringBuilder result = new StringBuilder();
        InputStream inputStream;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert workBook != null;
        Sheet sheet = workBook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                CellType cellType = cell.getCellType();
                switch (cellType) {
                    case STRING -> result.append(cell.getStringCellValue()).append("=");
                    case NUMERIC -> result.append("[").append(cell.getNumericCellValue()).append("]");
                    case BOOLEAN -> result.append("[").append(cell.getNumericCellValue()).append("]");
                    default -> result.append("|");
                }
            }
            result.append("\n");
        }

        return result.toString();
    }

}
