package PCOS;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import excelUtils.ReadFromExcel;


public class EliminateAndToAddRecipies {

	public static void main(String[] args) throws IOException {
		/*WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");*/
		WebDriver driver = new ChromeDriver();
		driver.get("https://tarladalal.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]")).click();
		driver.findElement(By.id("ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht335")).click();
		
		int paginationSize = driver.findElements(By.cssSelector("#pagination>a")).size();
		
		List<String> eliRecID=new ArrayList<String>();
		List<String> eliRecName=new ArrayList<String>();
		List<String> eliPreparationTime=new ArrayList<String>();
		List<String> eliCookTime=new ArrayList<String>();
		List<String> eliIngradients=new ArrayList<String>();
		List<String> eliPreparationMethod=new ArrayList<String>();
		List<String> eliNutritionValue = new ArrayList<String>();
		List<String> eliRecipeUrl=new ArrayList<String>();
	//ToAdd	
		List<String> addRecID=new ArrayList<String>();
		List<String> addRecName=new ArrayList<String>();
		List<String> addPreparationTime=new ArrayList<String>();
		List<String> addCookTime=new ArrayList<String>();
		List<String> addIngradients=new ArrayList<String>();
		List<String> addPreparationMethod=new ArrayList<String>();
		List<String> addNutritionValue = new ArrayList<String>();
		List<String> addRecipeUrl=new ArrayList<String>();
	//Eliminate
		//String[] ingradientsToEliminate = { "brinjal","ragi", "bajra", "jeera", "Cakes", "Pastries", "White bread","Fried food","Oats", "Pizza", "Burger", "Carbonated beverages", "Sugary foods (sweets, icecreams) and beverages (soda, juices)", "Red meat", "Processed meat", "Dairy", "Soy products", "Gluten", "Pasta", "White rice", "Doughnuts", "Fries", "Coffee", "Seed oils- vegetable oil, soybean oil, canola oil, rapeseed oil, sunflower oil, safflower oil","chilli"};
		ReadFromExcel object= new ReadFromExcel();
		List<String> ingradientsToEliminate = object.getData("C:/Testing/Saritha/Team_14-Scrape_Hunt-RecipeScrappingHackathon/src/test/java/Resources/PCOSExcelOutput/IngredientsDataInput.xlsx", "ToEliminate");
		List<String> fruitsVeggiesToAdd = object.getData("C:\\Testing\\Saritha\\RecepeScrapping\\src\\test\\resources\\IngredientsDataInput.xlsx", "ToAdd");
		
		
		System.out.println("from excel eliminate , size - "+ingradientsToEliminate.size());
		System.out.println("from excel add, size - "+fruitsVeggiesToAdd.size());

		//String[] ingradientsToAdd = { "High fiber fruits", "vegetables"};
		
		try {
		for(int k = 1; k <= paginationSize; k++)
			//for(int k=0;k<2;k++)

		{
			WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10));
			String paginationSelector ="#pagination>a:nth-child("+k+")";
			w.until(ExpectedConditions.elementToBeClickable(By.cssSelector(paginationSelector)));
			driver.findElement(By.cssSelector(paginationSelector)).click();
			List<WebElement> recipeCards = driver.findElements(By.xpath("//div[@class='rcc_rcpno']/span"));
			//for(int j=0;j<recipeCards.size();j++)
			for(int j=0;j<10;j++)
			{
				List<WebElement> recipeName= driver.findElements(By.xpath("//span[@class='rcc_recipename']"));

			List<WebElement> recCards = driver.findElements(By.xpath("//div[@class='rcc_rcpno']/span"));
			System.out.println(recCards.size() +" Loop-"+j);
			GetterSetter_PCOS obj = new GetterSetter_PCOS();
			String RecipeId = recCards.get(j).getText();
			
			String[] id1 = RecipeId.split("\n");
			String id2 = id1[0];
			String[] id3 = id2.split("#");
			String id4 = id3[1];
			obj.setRecipeID(id4);
			System.out.println("RecipeId = " + obj.getRecipeID());
			//recID.add(obj.getRecipeID());			
			obj.setRecipeName(recipeName.get(j).getText());						
			String RecipeName = obj.getRecipeName(); 
			System.out.println(RecipeName +" Loop - "+j);
			//recName.add(RecipeName);
			
			w.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='rcc_recipename']")));
			recipeName.get(j).click();
			
			String prepTime = driver.findElement(By.xpath("//p/time[@itemprop = 'prepTime']")).getText();
			System.out.println("PreparationTime = " + prepTime);
			//preparationTime.add(prepTime);
			
			String cookTime = driver.findElement(By.xpath("//p/time[@itemprop = 'cookTime']")).getText();
			System.out.println("cookingTime = " + cookTime);
			//CookTime.add(cookTime);
			
			String prepMethod = driver.findElement(By.id("ctl00_cntrightpanel_pnlRcpMethod")).getText();
			System.out.println("preparationMethod = " + prepMethod);
			//preparationMethod.add(prepMethod);
			
			String nutritionalValue = driver.findElement(By.id("rcpnutrients")).getText();
			System.out.println("NutritionalValue = " + nutritionalValue);
			
			System.out.println("Recipe Url = " + driver.getCurrentUrl());
			//recipeUrl.add(driver.getCurrentUrl());
			
			//List eliminateList = Arrays.asList(ingradientsToEliminate);
			String ingredientList = driver.findElement(By.id("rcpinglist")).getText();
			String[] ingradientsArray=ingredientList.split(" ");
			System.out.println("ingredientList = " + ingredientList);
			System.out.println(ingradientsToEliminate.size());
			boolean eliminateRec = false;
			boolean addRec = false;
	//Elimination Part		
			for(int x=0;x<ingradientsToEliminate.size();x++){
				for(int y=0; y<ingradientsArray.length; y++) {
					
		
			if(ingradientsToEliminate.get(x).equals(ingradientsArray[y])) {
				System.out.println("Match Found for eliminate" +ingradientsToEliminate.get(x));
				eliminateRec = true;
				break;
			} } }
			
			if (eliminateRec == false) {
				eliRecID.add(obj.getRecipeID());	
				eliRecName.add(RecipeName);
				eliPreparationTime.add(prepTime);
				eliCookTime.add(cookTime);
				eliIngradients.add(ingredientList);
				eliPreparationMethod.add(prepMethod);
				eliNutritionValue.add(nutritionalValue);
				eliRecipeUrl.add(driver.getCurrentUrl());
			}
	//ToAdd part
			for(int p=0;p<fruitsVeggiesToAdd.size();p++){
				
				for(int q=0; q<ingradientsArray.length; q++) {
									
			if(fruitsVeggiesToAdd.get(p).equals(ingradientsArray[q])) {
				System.out.println("Match Found for Add " +fruitsVeggiesToAdd.get(p));
				addRec = true;
				break;
			}
				}
			}
			
			if (addRec == true) {
				addRecID.add(obj.getRecipeID());	
				addRecName.add(RecipeName);
				addPreparationTime.add(prepTime);
				addCookTime.add(cookTime);
				addIngradients.add(ingredientList);
				addPreparationMethod.add(prepMethod);
				addNutritionValue.add(nutritionalValue);
				addRecipeUrl.add(driver.getCurrentUrl());
			}
			
			driver.navigate().back();
			}
		}
		} catch (Exception e) {
			System.out.println("Error"+e.getMessage());
		} finally {
			
			addRecipeToExcel("RecipeScrapping_PCOS.xlsx", "PCOS", eliRecID, eliRecName, eliPreparationTime, eliCookTime, eliIngradients, eliPreparationMethod, eliNutritionValue, eliRecipeUrl);
			addRecipeToExcel("RecipeToAdd.xlsx", "FruVegToAdd", addRecID, addRecName, addPreparationTime, addCookTime, addIngradients, addPreparationMethod, addNutritionValue, addRecipeUrl);
					
		driver.close();
		}
	}
		
	
public static void addRecipeToExcel(String file, String sheetName,List<String> recId, List<String> recName, List<String> preptnTime, List<String> cookingTime, List<String> ingradients, List<String> preptnMethod, List<String> nutriVal, List<String> recURL ) throws IOException
{
	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\PCOSExcelOutput\\"+file;
	System.out.println(sheetName);
	File xlFile = new File(filePath);
	XSSFWorkbook workBook = new XSSFWorkbook();
	
	XSSFSheet workSheet = workBook.createSheet(sheetName);	
		
	Row rowRecId= workSheet.createRow(0);
	Row rowRecName= workSheet.createRow(1);
	Row rowPrepTime = workSheet.createRow(2);
	Row rowCookTime = workSheet.createRow(3);
	Row rowIngradientsList = workSheet.createRow(4);
	Row rowPrepMethod = workSheet.createRow(5);
	Row rownutritionalValue = workSheet.createRow(6);
	Row rowRecipeURL = workSheet.createRow(7);
	
	int arrSize = recId.size();
	for (int m = 0; m < arrSize; m++) {
		workSheet.setColumnWidth(m, 10000);
		workSheet.setDefaultRowHeight((short) 500);
						
		if(m==0){
			Cell cellHeader1= rowRecId.createCell(m);
			Cell cellHeader2= rowRecName.createCell(m);
			Cell cellHeader3 = rowCookTime.createCell(m);
			Cell cellHeader4 = rowIngradientsList.createCell(m);
			Cell cellHeader5 = rowPrepMethod.createCell(m);
			
			Cell cellHeader6 = rowRecipeURL.createCell(m);
			Cell cellHeader7 = rowPrepTime.createCell(m);
			Cell cellHeader8 =rownutritionalValue.createCell(m);
			
			cellHeader1.setCellValue("Recipe ID");
			cellHeader2.setCellValue("Recipe Name");
			cellHeader3.setCellValue("Cooking Time");
			cellHeader4.setCellValue("Ingradients List");
			cellHeader5.setCellValue("Preparation Method");
			cellHeader6.setCellValue("Recipe URL");
			cellHeader7.setCellValue("Preparation Time");
			cellHeader8.setCellValue("Nutritional Value");
		}
		
			Cell cellId= rowRecId.createCell(m+1);
			Cell cellName= rowRecName.createCell(m+1);
			Cell cookTime = rowCookTime.createCell(m+1);
			Cell ingradientList = rowIngradientsList.createCell(m+1);
			
			Cell prepMethod = rowPrepMethod.createCell(m+1);
			Cell recipeNameUrl = rowRecipeURL.createCell(m+1);
			Cell prepTime = rowPrepTime.createCell(m+1);
			Cell nutriValue = rownutritionalValue.createCell(m+1);
			cellId.setCellValue(recId.get(m));
			cellName.setCellValue(recName.get(m));
			cookTime.setCellValue(cookingTime.get(m));
			ingradientList.setCellValue(ingradients.get(m));
			prepMethod.setCellValue(preptnMethod.get(m));
			recipeNameUrl.setCellValue(recURL.get(m));
			prepTime.setCellValue(preptnTime.get(m));	
			nutriValue.setCellValue(nutriVal.get(m));
			
	}
	FileOutputStream outstream = new FileOutputStream(filePath);
	workBook.write(outstream);	
	workBook.close();
}
	

}
