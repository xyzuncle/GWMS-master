package com.huanqiuyuncang.util;

import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.huanqiuyuncang.util.PageData;
import com.huanqiuyuncang.util.Tools;
/**
* 导入到EXCEL
* 类名称：ObjectExcelView.java
* @author
* @version 1.0
 */
public class ObjectExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		String filename = Tools.date2Str(date, "yyyyMMddHHmmss");
		HSSFSheet sheet;
		HSSFCell cell;
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
		sheet = workbook.createSheet("sheet1");
		
		List<String> titles = (List<String>) model.get("titles");
		int len = titles.size();
		HSSFCellStyle headerStyle = workbook.createCellStyle(); //标题样式
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = workbook.createFont();	//标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short)11);
		headerStyle.setFont(headerFont);
		short width = 20,height=25*20;
		sheet.setDefaultColumnWidth(width);
		for(int i=0; i<len; i++){ //设置标题
			String title = titles.get(i);
			cell = getCell(sheet, 0, i);
			cell.setCellStyle(headerStyle);
			setText(cell,title);
		}
		sheet.getRow(0).setHeight(height);
		
		HSSFCellStyle contentStyle = workbook.createCellStyle(); //内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		List<PageData> varList = (List<PageData>) model.get("varList");
		int varCount = varList.size();
		for(int i=0; i<varCount; i++){
			PageData vpd = varList.get(i);
			for(int j=0;j<len;j++){
				String varstr = vpd.getString("var"+(j+1)) != null ? vpd.getString("var"+(j+1)) : "";
				cell = getCell(sheet, i+1, j);
				cell.setCellStyle(contentStyle);
				setText(cell,varstr);
			}
			
		}
		
	}


	/**
	 * 分拣单
	 */
	public void buildExcleFJ(HttpServletResponse response,HttpServletRequest request){

        File file = new File(ObjectExcelView.class.getResource("/templet/fj.xls").getFile());
        try {

            String fileName="分拣单";
            String downloadFileName="";//被浏览器下载看到的名称

            FileInputStream fileIn = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
            HSSFSheet sheet = workbook.getSheetAt(0);
            //获取第一行
            HSSFRow row0 = sheet.getRow(0);
            String time = DateUtil.format(new Date(),"yyyy-MM-dd");
            //即使合并单元格了，也是冲单元格的开始赋值
            row0.getCell(9).setCellValue(time);

            //获取第二行
            HSSFRow row1 = sheet.getRow(1);
            row1.getCell(2).setCellValue("我是单号");
            row1.getCell(4).setCellValue("我是名字");
            row1.getCell(10).setCellValue("我是日期");
            row1.getCell(13).setCellValue("打印人：我是打印人");

            //获取第三行
            HSSFRow row2 = sheet.getRow(2);
            row2.getCell(2).setCellValue("我是仓库号");
            row2.getCell(4).setCellValue("我是仓库");
            row2.getCell(10).setCellValue("我是操作员");
            row2.getCell(13).setCellValue("打印日期："+time);

            //开始循环遍历数据

            int rowIndex=4;
            for (int i = 0; i < 2; i++) {
                HSSFRow row = sheet.getRow(rowIndex);
                row.getCell(0).setCellValue(1);
                row.getCell(1).setCellValue("我是订单号");
                row.getCell(3).setCellValue("我是收件人");
                row.getCell(6).setCellValue("我是货号");
                row.getCell(7).setCellValue("我是商品名称");
                row.getCell(11).setCellValue("我是条码");
                row.getCell(14).setCellValue("我是数量");
                rowIndex++;
            }

            //针对firefox和其他浏览器的解决方法
            String agent = request.getHeader("USER-AGENT");
            if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)
            {
                downloadFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
            }
            else
            {
                downloadFileName =  java.net.URLEncoder.encode(fileName, "UTF-8");
            }

            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFileName + ".xls");
            response.setContentType("application/octet-stream;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());

            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

	/**
	 * 汇总
	 */
	public void buildExcleHZ(){

	}

    public static void main(String[] args) {
    }

}
