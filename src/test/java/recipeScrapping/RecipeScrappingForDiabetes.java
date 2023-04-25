package recipeScrapping;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import ExcelWritingReading.ReadEliminatedIngredientForDiabetesFromExcel;
import ExcelWritingReading.ReadToAddListForDiabetes;
import ExcelWritingReading.WriteScrappedDiabeticRecipesToExcel;
import ExcelWritingReading.WriteToAddDiabeticRecipeToExcel;

public class RecipeScrappingForDiabetes {

    @Test
	@SuppressWarnings("deprecation")
	public void Recipe() throws InterruptedException, IOException {

		GetSetForRecipeScrapping hRecipe = new GetSetForRecipeScrapping();
		WriteScrappedDiabeticRecipesToExcel data = new WriteScrappedDiabeticRecipesToExcel();
		WriteToAddDiabeticRecipeToExcel toAddRecipe = new WriteToAddDiabeticRecipeToExcel();
		String RecipeName=null;
		List<WebElement> recipeCards=null;
		List<WebElement> page=null;
		List<WebElement> recipeIds=null;
		List<WebElement> recipeNames =null;
		List<WebElement> goInsideRecipe = null;
		String ingredientList = null;
		String preparationTime =null;
		String cookingTime=null;
		String preparationMethod = null;
		WebElement goTo =null;
		ReadEliminatedIngredientForDiabetesFromExcel dataEliminate = new  ReadEliminatedIngredientForDiabetesFromExcel();
		ArrayList <String> el = dataEliminate.readEliminatedIngredients();
		ReadToAddListForDiabetes toAdd = new ReadToAddListForDiabetes(); 
		ArrayList <String> add = toAdd.ToAddIngredientList();


		//WebDriver driver = new ChromeDriver();
		WebDriver driver = new EdgeDriver();
		driver.get("https://www.tarladalal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.partialLinkText("RECIPES")).click();

		//Thread.sleep(500);
		driver.findElement(By.partialLinkText("Diabetic recipes")).click();
		int rowIndex =0;
		int columnIndex=0;
		int addColumnindex=0;

			//pagination
			page=driver.findElements(By.xpath("//a[@class='respglink']"));
			int numberOfPages = 23;
			//numberOfPages = page.size();
			for(int i=1;i<numberOfPages;i++) {
				driver.navigate().to("https://www.tarladalal.com/recipes-for-indian-diabetic-recipes-370?pageindex="+i);


				//To Get RecipeCards
				recipeCards = driver.findElements(By.xpath("//article[@class='rcc_recipecard']"));
				int NumberOfrecipes = recipeCards.size();
				System.out.println("Page # " + i + ", NumberOfrecipes = " + NumberOfrecipes);

				for (int h = 0; h < NumberOfrecipes; h++) {
					//WebElement recipeDetails = recipeCards.get(h);
					driver.navigate().to("https://www.tarladalal.com/recipes-for-indian-diabetic-recipes-370?pageindex="+i);
					System.out.println("Processing Recipe " + h + " out of " + NumberOfrecipes + " Recipes from Page # " + i + " out of " + numberOfPages + " Pages..");
								

					try
					{					//To get recipe id
						recipeIds = driver.findElements(By.xpath("//div[@class='rcc_rcpno']/span"));	
						//System.out.println(recipeIds);
						String id = recipeIds.get(h).getText();
						String[] id1 = id.split("\n");
						String id2 = id1[0];
						String[] id3 = id2.split("#");
						String id4 = id3[1];
						hRecipe.setRecipeId(id4);
					}
					catch(Exception e)
					{	
						System.out.println("Error while getting receipe id");
					}
					//System.out.println("RecipeId = " + hRecipe.getRecipeId());
					String RecipeId = hRecipe.getRecipeId();


					//To get recipe name
					recipeNames = driver.findElements(By.xpath("//span[@class='rcc_recipename']"));
					hRecipe.setRecipeName(recipeNames.get(h).getText());
					//System.out.println("RecipeName = " + hRecipe.getRecipeName());
					RecipeName = hRecipe.getRecipeName();


					//To get recipeDetails
					goInsideRecipe = driver.findElements(By.xpath("//span[@class='rcc_recipename']"));
					goTo = hRecipe.setGoToRecipe(goInsideRecipe.get(h));
					goTo.click();



					//To Get ingredientList
					ingredientList = driver.findElement(By.id("rcpinglist")).getText();
					//System.out.println("IngredientList = " + ingredientList);	


					preparationTime=null;
					//To Get Preparation Time
					try {
						preparationTime = driver.findElement(By.xpath("//p/time[@itemprop = 'prepTime']")).getText();
						//System.out.println("PreparationTime = " + preparationTime);
					}
					catch(Exception e){
						preparationTime = "Preparation time not found";
					}
					cookingTime = null;
					try {
						//To Get Cooking Time
						cookingTime = driver.findElement(By.xpath("//p/time[@itemprop = 'cookTime']")).getText();
						//System.out.println("CookingTime = " + cookingTime);
					}
					catch(Exception e) {
						cookingTime = "cooking time not found";
					}

					//To Get Preparation Method
					preparationMethod = driver.findElement(By.id("ctl00_cntrightpanel_pnlRcpMethod")).getText();
					//System.out.println("PreparationMethod = " + preparationMethod);


					//To Get Nutrient Value
					String nutritionalValue=null;
					try {
						nutritionalValue = driver.findElement(By.id("rcpnutrients")).getText();
						//System.out.println("NutritionalValue = " + nutritionalValue);

					}
					catch(Exception e){
						nutritionalValue= "No nutritional value Present";

					}
					//To get Recipe Url
					String recipeUrl=null;
					try {
					 recipeUrl =  driver.getCurrentUrl();
					}
					catch(Exception e) {
						System.out.println("Error Found");
					}


					//To Read data from excel

					boolean isEliminatedIngredientExists=false;
					for(int k=0; k<el.size();k++) {
						if(ingredientList.indexOf(el.get(k))!=-1) {
							isEliminatedIngredientExists=true;
							break;
						}
					}
					if(!isEliminatedIngredientExists) {
						++columnIndex;
						rowIndex=0;
						data.writeExcelSheet(++rowIndex,columnIndex,RecipeId,RecipeName,ingredientList,preparationTime,cookingTime,preparationMethod,nutritionalValue,recipeUrl);
						
//						boolean isToAdd=false;
//						for(int a=0; a<add.size();a++) {
//							if(ingredientList.indexOf(add.get(a))!=-1) {
//								isToAdd=true;
//								break;
//							}
//					}
						
//						if(isToAdd) {
//							++addColumnindex;
//							rowIndex=0;
//							toAddRecipe.writeExcelSheet(++rowIndex,addColumnindex,RecipeId,RecipeName,ingredientList,preparationTime,cookingTime,preparationMethod,nutritionalValue,recipeUrl);
//						}

					try {
						driver.navigate().back();
					}
					catch(Exception e) {
						System.out.println("Loading Error");
					}
					recipeIds=null;
					recipeNames=null;
					goInsideRecipe=null;
					ingredientList=null;
					preparationTime=null;
					cookingTime=null;
					preparationMethod=null;
					goTo=null;
				}
				recipeCards=null;

			}
						
			//driver.quit();
		}       
			page=null;

    }


	public static void main(String[]arg) throws InterruptedException, IOException {
		RecipeScrappingForDiabetes obj = new RecipeScrappingForDiabetes();
		obj.Recipe();
		System.out.println();
		System.out.println("All Receipes are Successfully Processed...");
		System.out.println();
		
	}

}



