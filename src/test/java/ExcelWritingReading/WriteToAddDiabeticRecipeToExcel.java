package ExcelWritingReading;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToAddDiabeticRecipeToExcel {
	
    public void writeExcelSheet(int rowIndex,int addColumnindex,String RecipeId,String RecipeName, String ingredientList, String preparationTime, String cookingTime,String preparationMethod,String nutritionalValue,String recipeUrl) throws IOException, InterruptedException {
    	
    	String path = System.getProperty("user.dir")+"/src/test/java/Resources/ScrapedRecipesWithToAddIngredientForDiabetes.xlsx";
    	File excelFile = new File(path); 
    	XSSFWorkbook workbook=null;
    	XSSFSheet worksheet=null;
    	FileInputStream Fis=null;
    	
    	if(!excelFile.exists()) {
    		
    		excelFile.createNewFile();
    		Fis = new FileInputStream(excelFile);
    	workbook = new XSSFWorkbook();
    	worksheet = workbook.createSheet("Sheet1");
    	}
    	    	
    	else {
    	Fis = new FileInputStream(excelFile);	
        workbook = new XSSFWorkbook(Fis);
    	worksheet = workbook.getSheet("Sheet1");
    	}
    	
    	if(worksheet==null) {
    	worksheet = workbook.createSheet("Sheet1");
    	}
    		Row row = worksheet.getRow(rowIndex);
    		if(row==null) {
    			row= worksheet.createRow(rowIndex);
    		}
    		Cell cell = row.createCell(addColumnindex);
    		cell.setCellValue(RecipeId);
    		
    		row = worksheet.getRow(++rowIndex);
    		if(row==null) {
    			row= worksheet.createRow(rowIndex);
    		}
    		cell = row.createCell(addColumnindex);
    		cell.setCellValue(RecipeName);
    		
    		row = worksheet.getRow(++rowIndex);
    		if(row==null) {
    			row= worksheet.createRow(rowIndex);
    		}
    		cell = row.createCell(addColumnindex);
    		cell.setCellValue(ingredientList);
    		
    		row = worksheet.getRow(++rowIndex);
    		if(row==null) {
    			row= worksheet.createRow(rowIndex);
    		}
    		cell = row.createCell(addColumnindex);
    		cell.setCellValue(preparationTime);
    		
    		row = worksheet.getRow(++rowIndex);
    		if(row==null) {
    			row= worksheet.createRow(rowIndex);
    		}
    		cell = row.createCell(addColumnindex);
    		cell.setCellValue(cookingTime);
    		
    		row = worksheet.getRow(++rowIndex);
    		if(row==null) {
    			row= worksheet.createRow(rowIndex);
    		}
    		cell = row.createCell(addColumnindex);
    		cell.setCellValue(preparationMethod);
    		
    		row = worksheet.getRow(++rowIndex);
    		if(row==null) {
    			row= worksheet.createRow(rowIndex);
    		}
    		cell = row.createCell(addColumnindex);
    		cell.setCellValue(nutritionalValue);
    		
    		row = worksheet.getRow(++rowIndex);
    		if(row==null) {
    			row= worksheet.createRow(rowIndex);
    		}
    		cell = row.createCell(addColumnindex);
    		cell.setCellValue(recipeUrl);
    		
    		Fis.close(); 		  		   	  		
    		     	        	
        	FileOutputStream Fos = null;
    	try {
			 Fos = new FileOutputStream(excelFile);
			 workbook.write(Fos);
			 workbook.close();
			 
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
    	finally {
    		Fos.close();
    	}
	}
    

}

