package hypertension;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//import utilities.ExcelReader;

import java.util.List;

public class Hypertension {
	public static WebDriver driver;
	

	
	public void southIndianRecipe() throws InterruptedException, IOException  {
			
		
			System.setProperty("webdriver.chrom.driver", "/Recipe1/src/test/resources/drivers/chromedriver.exe");
			
			ChromeOptions co = new ChromeOptions();
			
			co.addArguments("--remote-allow-origins=*");
			
			driver  = new ChromeDriver(co);
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			
			driver.get("https://www.tarladalal.com");
			
			
           driver.findElement(By.xpath("//div[text()='RECIPES']")).click();
           driver.findElement(By.xpath("//a[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht167']")).click();
           List<WebElement> page = driver.findElements(By.xpath("//div[@style='text-align:right;padding-bottom:15px;']/a"));
           int pagesize = page.size();
           int column = 1;
           for (int j = 1; j <= pagesize; j++) {
        	   driver.findElement(By.xpath("//div[@style='text-align:right;padding-bottom:15px;']/a["+j+"]")).click();
        	   
           
           List<WebElement> cards = driver.findElements(By.xpath("//div[@class='recipelist']/article/div[3]/span/a"));
           int cardsize = cards.size();
           System.out.println("number of cards=" + cardsize );
           for (int i = 1; i <= cardsize; i++) {
       		System.out.println(i);
       		String RecipeName =  driver.findElement(By.xpath("//div[@class='recipelist']/article["+i+"]/div[3]/span/a")).getText();
       		System.out.println("RecipeNames=" + RecipeName );
       		
       		String ID = driver.findElement(By.xpath("//div[@class='recipelist']/article["+i+"]/div[2]/span")).getText();
       		String[] id1 = ID.split("\n");
			String id2 = id1[0];
			String[] id3 = id2.split("#");
			String id4 = id3[1];
			ID = id4;
			
       		System.out.println("ID=" + ID );
       	    driver.findElement(By.xpath("//div[@class='recipelist']/article["+i+"]/div[3]/span/a")).click();
       	    String PreTime = driver.findElement(By.xpath("//p/time[@itemprop='prepTime']")).getText();
       	    //System.out.println("PreTime=" + PreTime );
       	    String cookingTime = driver.findElement(By.xpath("//p/time[@itemprop='cookTime']")).getText();
       	    //System.out.println("cookingTime=" + cookingTime );
       	    String IngreList = driver.findElement(By.xpath("//div[@id='rcpinglist']")).getText();System.out.println("cookingTime=" + cookingTime );
       	    System.out.println("IngreList=" + IngreList );
       	    String PrepMethod = driver.findElement(By.xpath("//div[@id='ctl00_cntrightpanel_pnlRcpMethod']")).getText();
       	    //System.out.println("PrepMethod=" + PrepMethod );
       	    String NutriValue = driver.findElement(By.id("rcpnutrients")).getText();System.out.println("cookingTime=" + cookingTime );
       	    //System.out.println("NutriValue=" + NutriValue );
       	    String URL = driver.getCurrentUrl();
       	    
       	    String[][] ExcludeCode= HypertensionExcelReader.getData("Sheet1");
		       	 int Exsize=ExcludeCode.length;
		       	System.out.println("Exsize=" + Exsize );
		       	boolean isElimIngredExists=false;
		       	for(int k=0;k<Exsize;k++)
				{
		       		//System.out.println("Exsize to string=" + ExcludeCode[k][0] );
		       		
		       		int t = IngreList.toLowerCase().indexOf(ExcludeCode[k][0].toLowerCase());
		       		if (t!=-1){
		       		System.out.println("Elim Matched " +ExcludeCode[k][0]);
		       		isElimIngredExists=true;
					break;
		       		}
		       		//else {
		       		//	System.out.println("Not match");
		       		//}
				}
		       	
	    	    String[][] AddCode= HypertensionExcelReader.getData("Sheet2");
		       	 int Addsize=AddCode.length;
		       	System.out.println("Exsize=" + Exsize );
		       	boolean isAddIngredientExists=false;
		       	for(int k=0;k<Addsize;k++)
				{
		       		//System.out.println("Exsize to string=" + AddCode[k][0] );
		       		
		       		int t = IngreList.toLowerCase().indexOf(AddCode[k][0].toLowerCase());
		       		if (t!=-1){
		       		System.out.println("Add Matched" + AddCode[k][0]);
		       		isAddIngredientExists=true;
					break;
		       		}
		       		//else {
		       		//	System.out.println("Not match");
		       		//}
				}
		       	
		       	if ((!isElimIngredExists) && (isAddIngredientExists)) {
		       		System.out.println("Added to excel "+RecipeName);
		       	HypertensionExcelWriter excelWriter = new HypertensionExcelWriter();
				excelWriter.WriteData("Sheet1", 0, column++, ID, RecipeName, IngreList, PreTime, cookingTime, PrepMethod,
						NutriValue, URL);
		       	}
		    driver.navigate().back();   	
          }
           }	
	}
	public static void main(String[] args) throws InterruptedException, IOException
	{
		
		Hypertension southIndian = new Hypertension ();
		
		southIndian.southIndianRecipe();
		
}
}
