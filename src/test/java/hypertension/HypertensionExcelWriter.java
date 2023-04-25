package hypertension;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HypertensionExcelWriter {
	public XSSFRow row;
	public XSSFCell cell;
	
	public  void   WriteData (String sheetname, int rownum, int column, String ID, String RecipeName, 
			String IngreList, String PreTime, String cookingTime, String PrepMethod, 
			String NutriValue, String URL) throws IOException
	{
		
		String projectDir=System.getProperty("user.dir");
		String path=projectDir+"/src/test/resources/Data/HypertensionRecipeList.xlsx";
		File file =    new File(path);
		
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(inputStream);
		
		
		XSSFSheet sheet=wb.getSheet(sheetname);
		
		row =sheet.getRow(rownum+1);
		cell  = row.createCell(column);
		cell.setCellValue(ID);
		
		row =sheet.getRow(rownum+2);
		cell  = row.createCell(column);
		cell.setCellValue(RecipeName);
		
		row =sheet.getRow(rownum+3);
		cell  = row.createCell(column);
		cell.setCellValue(IngreList);
		
		row =sheet.getRow(rownum+4);
		cell  = row.createCell(column);
		cell.setCellValue(PreTime);
		
		row =sheet.getRow(rownum+5);
		cell  = row.createCell(column);
		cell.setCellValue(cookingTime);
		
		row =sheet.getRow(rownum+6);
		cell  = row.createCell(column);
		cell.setCellValue(PrepMethod);
		
		row =sheet.getRow(rownum+7);
		cell  = row.createCell(column);
		cell.setCellValue(NutriValue);
		
		row =sheet.getRow(rownum+8);
		cell  = row.createCell(column);
		cell.setCellValue(URL);
		
		FileOutputStream outputStream = new FileOutputStream(path);
        wb.write(outputStream);
        wb.close();
        inputStream.close();
        outputStream.close();
		
	}

}
