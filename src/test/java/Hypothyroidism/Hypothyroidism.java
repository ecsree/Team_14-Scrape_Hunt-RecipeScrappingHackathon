package Hypothyroidism;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



import io.github.bonigarcia.wdm.WebDriverManager;


public class Hypothyroidism {
	

public static void main(String[] args) throws InterruptedException, IOException {
	
	
	String path=System.getProperty("user.dir");
	System.setProperty("webdriver.edge.driver", path+"/src/test/resources/Drivers/chromedriver.exe");
	 ChromeOptions options = new ChromeOptions();
	    options.addArguments("--remote-allow-origins=*");
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver=new ChromeDriver(options);
	
	driver.get("http://www.tarladalal.com");
	
	
	driver.manage().window().maximize();
	Thread.sleep(1000);
	
	WebElement recipies = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
	
	recipies.click();
	
	
	Thread.sleep(1000);
	
	Thread.sleep(2000);
	
	
	
	WebElement hypothyroidismrecipes = driver.findElement(By.xpath("//a[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht226']"));

	hypothyroidismrecipes.click();
	
	
	
	String xlpath="C:\\Users\\akash\\eclipse-workspace\\RecipeScrapping\\RecipeScrapping\\src\\test\\resources\\Recipe_ScrappedHypothyroididm.xlsx";
//	XLUtility xlutil=new XLUtility(xlpath);
//	
	String eliminatedpath="C:\\Users\\akash\\eclipse-workspace\\RecipeScrapping\\RecipeScrapping\\src\\test\\resources\\HypothyroidismItems.xlsx";
	 FileInputStream file = new FileInputStream(
             new File(eliminatedpath));
	 XSSFWorkbook workbookread = new XSSFWorkbook(file);
	 XSSFSheet sheetread = workbookread.getSheetAt(0);
//	 Iterator<Sheet> sheetIterator = workbookread.sheetIterator();
//	 while (sheetIterator.hasNext()) {
//	        Sheet sheet = sheetIterator.next();
//	        System.out.println("=> " + sheet.getSheetName());
//	    }
	 Iterator<Row> rowIterator = sheetread.iterator();
	 List<String> eliminated = new ArrayList<String>();
	 while (rowIterator.hasNext()) {
         Row row = rowIterator.next();
         Iterator<Cell> cellIterator = row.cellIterator();
         while(cellIterator.hasNext()) {
        	 Cell cell = cellIterator.next();
        	 eliminated.add(cell.getStringCellValue());
        	 //System.out.println("Print eliminated"+eliminated);
         }
	 
     }
	 sheetread= workbookread.getSheetAt(1);
	 Iterator<Row> rowIterator1 = sheetread.iterator();
	 List<String> toadd = new ArrayList<String>();
	 while (rowIterator1.hasNext()) {
         Row row = rowIterator1.next();
         Iterator<Cell> cellIterator1 = row.cellIterator();
         while(cellIterator1.hasNext()) {
        	 Cell cell = cellIterator1.next();
        	toadd.add(cell.getStringCellValue());
        	 //System.out.println("Print add"+toadd);
         }
	 
     }
	 file.close();
	 
	 
	 XSSFWorkbook workbook = new XSSFWorkbook();
	 XSSFSheet sheet = workbook.createSheet("Sheet1");

	 XSSFRow  header = sheet.createRow(0);
      header.createCell(0).setCellValue("Recipe ID");
      header.createCell(1).setCellValue("Recipe Name");
      header.createCell(2).setCellValue("Preparation Time");
      header.createCell(3).setCellValue("Cooking Time");
      header.createCell(4).setCellValue("Ingredients");
      header.createCell(5).setCellValue("Method");
      header.createCell(6).setCellValue("Nutrient Values");
      header.createCell(7).setCellValue("Recipe URL");
    
     FileOutputStream outputStream = new FileOutputStream(xlpath);
	//Write headers in excel sheet
//	xlutil.setCellData("Sheet1", 0, 0, "Recipe ID");
//	xlutil.setCellData("Sheet1", 0, 1, "Recipe Name");
//	xlutil.setCellData("Sheet1", 0, 2, "Preaption Time");
//	xlutil.setCellData("Sheet1", 0, 3, "Cooking Time");
//	xlutil.setCellData("Sheet1", 0, 4, "Ingredients");
	
	List<String> recID=new ArrayList<String>();
	List<String> recName=new ArrayList<String>();
	List<String> PrepTime=new ArrayList<String>();
	List<String> CookTime=new ArrayList<String>();
	List<String> Ingredient=new ArrayList<String>();
	List<String>Method=new ArrayList<String>();
	List<String>NutrientValues=new ArrayList<String>();
	List<String>recURL=new ArrayList<String>();
	
	Thread.sleep(2000);
	int	sizePagination = driver.findElements(By.xpath("//*[@id=\"pagination\"]/a")).size();
	System.out.println("No of Page" +sizePagination);
	
 
    //List<WebElement> noofrecipes=driver.findElements(By.xpath("//div[2]//article[@class='rcc_recipecard']"));
    //System.out.println(noofrecipes);
	
	
	String tempRecName = "";
	String tempRecId = "";
	String tempPrepTime = "";
	String tempCookTime = "";
	String tempIngred = "";
	String tempMethod = "";
	String tempNutrient = "";
	String tempUrl = "";
	int count=0;
	//driver.findElement(By.xpath("//*[@id=\"pagination\"]/a[2]")).click();
	for(int i=1;i<=sizePagination;i++)  //this is for Pagination
	{
		//System.out.println("Page - "+i);
		driver.findElement(By.xpath("//*[@id=\"pagination\"]/a["+i+"]")).click();
		String pgno=driver.findElement(By.xpath("//*[@id=\"pagination\"]/a["+i+"]")).getText();
		System.out.println("Page:"+pgno);
		int noofrecipes=driver.findElements(By.xpath("//div[2]//article[@class='rcc_recipecard']")).size();
		//System.out.println("No of Recipe" +noofrecipes);
		System.out.println("No of Recipe in each page:" +noofrecipes);
		for(int j=1;j<=noofrecipes;j++)   //this is for recipes card
		{
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement recipeID = driver.findElement(By.xpath("//article["+j+"]/div[2]/span"));
						
			System.out.println(recipeID.getText());
			tempRecId = driver.findElement(By.xpath("//article["+j+"]/div[2]/span")).getText();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement recipeName= driver.findElement(By.xpath("//article["+j+"]/div[3]/span[1]/a"));
			
			System.out.println(recipeName.getText());
			tempRecName = driver.findElement(By.xpath("//article["+j+"]/div[3]/span[1]/a")).getText();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			System.out.println(recID.size());
			System.out.println(recName.size());
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			/*
			 * WebElement
			 * nutval=driver.findElement(By.xpath("(//span[@class='unitcalamt'])["+j+"]"));
			 * WebElement nutunit=driver.findElement(By.
			 * xpath("(//span[@class='calunittxt'][normalize-space() = 'caloriesperserving'])["
			 * +j+"]")); String val=nutval.getText(); String unit=nutunit.getText();
			 * String[] L = unit.split("\n"); String f=String.join(" ",L);
			 */
//			WebElement Nutval=driver.findElement(By.xpath("(//div[@class='rcplstcal'])["+j+"]"));
//	       // System.out.println("Nutrient Values: "+Nutval.getText());
//	        String s=Nutval.getText();
//	        String[] L = s.split("\n");
//	        String f=String.join(" ",L);
//	        System.out.println("Nutrient Values: "+f);
//	        NutrientValues.add(driver.findElement(By.xpath("(//div[@class='rcplstcal'])["+j+"]")).getText());
//			
			recipeName.click();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement preaptime=driver.findElement(By.xpath("//time[@itemprop='prepTime']"));
			System.out.println(preaptime.getText());
			tempPrepTime = driver.findElement(By.xpath("//time[@itemprop='prepTime']")).getText();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement cooktime=driver.findElement(By.xpath("//time[@itemprop='cookTime']"));
			System.out.println(cooktime.getText());
			tempCookTime = driver.findElement(By.xpath("//time[@itemprop='cookTime']")).getText();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement ingredient=driver.findElement(By.xpath("//*[@id=\"rcpinglist\"]"));
			System.out.println(ingredient.getText());
			tempIngred = driver.findElement(By.xpath("//*[@id=\"rcpinglist\"]")).getText();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement method=driver.findElement(By.xpath("//div[@id='recipe_small_steps']"));
			System.out.println(method.getText());
			tempMethod = driver.findElement(By.xpath("//div[@id='recipe_small_steps']")).getText();
			
			try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			String nutritionalValue = driver.findElement(By.id("rcpnutrients")).getText();
			System.out.println("NutritionalValue = " + nutritionalValue);
			tempNutrient = nutritionalValue;
			}catch(Exception e){
				tempNutrient = "N/A";
				System.out.println("Nutrient Values is not available");
			}
			System.out.println("Recipe Category: Vegetarian");
			System.out.println("Targetted morbid conditions: Hypothyroidism");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			System.out.println("Recipe URL: "+driver.getCurrentUrl());
			tempUrl = driver.getCurrentUrl();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			boolean elm_f = false;
			for(String elm:eliminated) {
				if(StringUtils.containsIgnoreCase(tempIngred,elm)) {
					elm_f = true;
					break;
				}
			}
			boolean add = false; 
				for(String adlist:toadd) {
					if(StringUtils.containsIgnoreCase(tempIngred,adlist)) {
						add = true;
						break;
					}
				}
				
				
			if(!elm_f && add) {
				recID.add(tempRecId);
				recName.add(tempRecName);
				PrepTime.add(tempPrepTime);
				CookTime.add(tempCookTime);
				NutrientValues.add(tempNutrient);
				Ingredient.add(tempIngred);
				Method.add(tempMethod);
				recURL.add(tempUrl);	
			}
			else {
				count++;
			}
			driver.navigate().back();

		}
			}
	System.out.println("We are not adding - "+count);
	XSSFRow rowwrite;
	for(int rowInd=0;rowInd < recID.size();rowInd++) {
		rowwrite = sheet.createRow(rowInd+1);
		Cell cell1 = rowwrite.createCell(0);
		cell1.setCellValue(recID.get(rowInd));
		Cell cell2 = rowwrite.createCell(1);
		cell2.setCellValue(recName.get(rowInd));
		Cell cell3 = rowwrite.createCell(2);
		cell3.setCellValue(PrepTime.get(rowInd));
		Cell cell4 = rowwrite.createCell(3);
		cell4.setCellValue(CookTime.get(rowInd));
		Cell cell5 = rowwrite.createCell(4);
		cell5.setCellValue(Ingredient.get(rowInd));
		Cell cell6 = rowwrite.createCell(5);
		cell6.setCellValue(Method.get(rowInd));
		Cell cell7 = rowwrite.createCell(6);
		cell7.setCellValue(NutrientValues.get(rowInd));
		Cell cell8 = rowwrite.createCell(7);
		cell8.setCellValue(recURL.get(rowInd));
	}
	workbook.write(outputStream);
    outputStream.close();
	
	
}

}

