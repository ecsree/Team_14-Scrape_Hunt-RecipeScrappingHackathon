package recipeScrapping;
import org.openqa.selenium.WebElement;

public class GetSetForRecipeScrapping {
	private String RecipeName;
	private String RecipeId;
	private WebElement goToRecipe;
	

	
	public String getRecipeName() {
		return RecipeName;
	}
	public void setRecipeName(String recipeName) {
		this.RecipeName = recipeName;
	}
	public String getRecipeId() {
		return RecipeId;
	}
	public String setRecipeId(String recipeId) {
		this.RecipeId = recipeId;
		return recipeId;
	}
	public Object getGoToRecipe() {
		return goToRecipe;
	}
	public WebElement setGoToRecipe(WebElement goToRecipe) {
		this.goToRecipe = goToRecipe;
		return goToRecipe;
		
	}

	
}
