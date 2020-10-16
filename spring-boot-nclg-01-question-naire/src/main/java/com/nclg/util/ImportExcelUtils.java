package com.nclg.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：<br>
 * </>
 *
 * @author 周志通
 * @version 1.0.0
 * @date 2020/6/29 14:32
 **/
public class ImportExcelUtils {
    /**
     * 2003- 版本的excel
     */
    public final static String EXCEL_2003_L = ".xls" ;
    /**
     *  2007+ 版本的excel
     */
    public final static String EXCEL_2007_U = ".xlsx" ;

    /**
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象
     * @param in 文件输入流
     * @param fileName 文件名
     * @return {@link List}
     * @throws Exception 文件输入的异常
     */
    public  List<List<String>> getBankListByExcel(InputStream in,String fileName) throws Exception{
        List<List<String>> list ;
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet ;
        Row row ;
        Cell cell ;
        list = new ArrayList<>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}

            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){continue;}
                //遍历所有的列
                List<String> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(this.getCellValue(cell));
                }
                list.add(li);
            }
        }
        return list;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr 文件的输入流
     * @param fileName 文件名
     * @return {@link Workbook}
     * @throws Exception 抛出异常
     */
    public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb ;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(EXCEL_2003_L.equals(fileType)){
            //2003-
            wb = new HSSFWorkbook(inStr);
        }else if(EXCEL_2007_U.equals(fileType)){
            //2007+
            wb = new XSSFWorkbook(inStr);
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     * @param cell 列
     * @return {@link Object}
     */
    public  String getCellValue(Cell cell){
        Object value = null;
        //格式化number String字符
        DecimalFormat df = new DecimalFormat("0");
        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        //格式化数字
        DecimalFormat df2 = new DecimalFormat("0.00");
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else{
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value.toString() ;
    }



}
