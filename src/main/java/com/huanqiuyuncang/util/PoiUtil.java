package com.huanqiuyuncang.util;




import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;

/**
 * 这个类的主要目的是为了实现
 * sheet的拷贝，包括单元格的类型和样式
 */
public class PoiUtil {

    /**
     * Sheet复制
     * @
     * @param toFileName copy得到新模板的名称
     *
     */
    public static void copySheet(HSSFWorkbook wb, String toFileName, HttpServletResponse response, HttpServletRequest request,String isFlag) {
        //行复制
        String fileName = toFileName;
        HSSFSheet fromSheet = null;
        if(isFlag!=null && isFlag.equals("1")){
            fromSheet = wb.getSheetAt(1);
        }else{
            fromSheet = wb.getSheetAt(0);
        }
        HSSFSheet toSheet = wb.createSheet(fileName);
        copyRows(wb, fromSheet, toSheet, fromSheet.getFirstRowNum(), fromSheet.getLastRowNum());
        //隐藏模板的sheet
        wb.setSheetHidden(0, true);
        wb.setSheetHidden(1, true);
    }

    /**S
     * 拷贝Excel行
     * @param wb
     * @param fromsheet
     * @param newsheet
     * @param firstrow
     * @param lastrow
     */
    public static void copyRows(HSSFWorkbook wb, HSSFSheet fromsheet,HSSFSheet newsheet, int firstrow, int lastrow) {
        if ((firstrow == -1) || (lastrow == -1) || lastrow < firstrow) {
            return;
        }
        // 拷贝合并的单元格
        Region region = null;
        for (int i = 0; i < fromsheet.getNumMergedRegions(); i++) {
            region = fromsheet.getMergedRegionAt(i);
            if ((region.getRowFrom() >= firstrow)&& (region.getRowTo() <= lastrow)) {
                newsheet.addMergedRegion(region);
            }
        }

        HSSFRow fromRow = null;
        HSSFRow newRow = null;
        HSSFCell newCell = null;
        HSSFCell fromCell = null;
        // 设置列宽
        for (int i = firstrow; i <= lastrow; i++) {
            fromRow = fromsheet.getRow(i);
            if (fromRow != null) {
                for (int j = fromRow.getLastCellNum(); j >= fromRow.getFirstCellNum(); j--) {
                    int colnum = fromsheet.getColumnWidth((short) j);
                    if (colnum > 100) {
                        newsheet.setColumnWidth((short) j, (short) colnum);
                    }
                    if (colnum == 0) {
                        newsheet.setColumnHidden((short) j, true);
                    } else {
                        newsheet.setColumnHidden((short) j, false);
                    }
                }
                break;
            }
        }
        // 拷贝行并填充数据
        for (int i = 0; i <= lastrow; i++) {
            fromRow = fromsheet.getRow(i);
            if (fromRow == null) {
                continue;
            }
            newRow = newsheet.createRow(i - firstrow);
            newRow.setHeight(fromRow.getHeight());
            for (int j = fromRow.getFirstCellNum(); j < fromRow.getPhysicalNumberOfCells(); j++) {
                fromCell = fromRow.getCell((short) j);
                if (fromCell == null) {
                    continue;
                }
                newCell = newRow.createCell((short) j);
                newCell.setCellStyle(fromCell.getCellStyle());
                int cType = fromCell.getCellType();
                newCell.setCellType(cType);
                switch (cType) {
                    case HSSFCell.CELL_TYPE_STRING:
                        newCell.setCellValue(fromCell.getRichStringCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        newCell.setCellValue(fromCell.getNumericCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA:
                        newCell.setCellFormula(fromCell.getCellFormula());
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        newCell.setCellValue(fromCell.getBooleanCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_ERROR:
                        newCell.setCellValue(fromCell.getErrorCellValue());
                        break;
                    default:
                        newCell.setCellValue(fromCell.getRichStringCellValue());
                        break;
                }
            }
        }
    }

}
