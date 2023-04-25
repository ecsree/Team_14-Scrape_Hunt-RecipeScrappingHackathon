package ExcelWritingReading;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadAndWriteFoodAllergyConditions {
	
	public ArrayList<String> readEliminatedIngredients() throws IOException  {
		ArrayList<String> a = new ArrayList<String>();
	   	String path = System.getProperty("user.dir")+"/src/test/java/Resources/Eliminated Ingredients for Diabetes.xlsx";
	   	File Excelfile = new File(path);
    	    	   	    			
			FileInputStream Fis = new FileInputStream(Excelfile);
			XSSFWorkbook workbook = new XSSFWorkbook(Fis);
			XSSFSheet sheet = workbook.getSheet("peanut allergy");
			Iterator<Row> row = sheet.rowIterator();
			
			while(row.hasNext()) {
				Row currRow = row.next();
				
				Iterator<Cell> cell = currRow.cellIterator();
				while(cell.hasNext()) {
					Cell currCell = cell.next();
					a.add(currCell.getStringCellValue());
				}
				
			}
			return a;

}
	
	public ArrayList<String> readDiabeticRecipes() throws IOException  {
		ArrayList<String> a = new ArrayList<String>();
	   	String path = System.getProperty("user.dir")+"/src/test/java/Resources/ScrapedRecipesForDiabetes.xlsx";
	   	File Excelfile = new File(path);
    	    	   	    			
			FileInputStream Fis = new FileInputStream(Excelfile);
			XSSFWorkbook workbook = new XSSFWorkbook(Fis);
//			XSSFSheet sheet = workbook.getSheet("Sheet1");
//			row = sheet.ro;
//			
//			while(row.hasNext()) {
//				Row currRow = row.next();
//				
//				Iterator<Cell> cell = currRow.cellIterator();
//				while(cell.hasNext()) {
//					Cell currCell = cell.next();
//					a.add(currCell.getStringCellValue());
//				}
//				
//			}
			return a;

}

}
