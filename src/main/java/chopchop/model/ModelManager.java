package chopchop.model;

import static java.util.Objects.requireNonNull;
import static chopchop.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.logging.Logger;

import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.IngredientBook;
import chopchop.model.ingredient.ReadOnlyIngredientBook;
import chopchop.model.recipe.ReadOnlyRecipeBook;
import chopchop.model.recipe.Recipe;
import chopchop.model.recipe.RecipeBook;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import chopchop.commons.core.GuiSettings;
import chopchop.commons.core.LogsCenter;

/**
 * Represents the in-memory model of the recipe book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final RecipeBook recipeBook;
    private final IngredientBook ingredientBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Recipe> filteredRecipes;
    private final FilteredList<Ingredient> filteredIngredients;


    /**
     * Initializes a ModelManager with the given recipeBook and userPrefs.
     */
    public ModelManager(ReadOnlyRecipeBook recipeBook, ReadOnlyIngredientBook ingredientBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(recipeBook, ingredientBook, userPrefs);


        logger.fine("Initializing with recipe book: " + recipeBook + " and user prefs " + userPrefs);
        this.userPrefs = new UserPrefs(userPrefs);
        this.recipeBook = new RecipeBook(recipeBook);
        filteredRecipes = new FilteredList<Recipe>(this.recipeBook.getFoodEntryList());
        filteredIngredients = new FilteredList<FoodEntry>(this.ingredientBook.getFoodEntryList());

        logger.fine("Initializing with ingredient book: " + recipeBook + " and user prefs " + userPrefs);
        this.ingredientBook = new IngredientBook(ingredientBook);


    }

    public ModelManager() {
        this(new RecipeBook(), new IngredientBook(), new UserPrefs());
    }


//
//    /**
//     * Initializes a ModelManager with the given ingredientBook and userPrefs.
//     */
//    public ModelManager(ReadOnlyFoodEntryBook ingredientBook, ReadOnlyUserPrefs userPrefs) {
//        super();
//        requireAllNonNull(ingredientBook, userPrefs);
//
//        logger.fine("Initializing with address book: " + ingredientBook + " and user prefs " + userPrefs);
//
//        this.ingredientBook = new IngredientBook(ingredientBook);
//        this.userPrefs = new UserPrefs(userPrefs);
//        filteredIngredients = new FilteredList<FoodEntry>(this.ingredientBook.getFoodEntryList());
//    }
//
//    public ModelManager() {
//        this(new IngredientBook(), new UserPrefs());
//    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getRecipeBookFilePath() {
        return userPrefs.getRecipeBookFilePath();
    }

    @Override
    public void setRecipeBookFilePath(Path recipeBookFilePath) {
        requireNonNull(recipeBookFilePath);
        userPrefs.setRecipeBookFilePath(recipeBookFilePath);

    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path ingredientBookFilePath) {
        requireNonNull(ingredientBookFilePath);
        userPrefs.setAddressBookFilePath(ingredientBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override

    public void setRecipeBook(ReadOnlyRecipeBook recipeBook) {
        this.recipeBook.resetData(recipeBook);
    }

    @Override
    public ReadOnlyRecipeBook getRecipeBook() {
        return recipeBook;
    }

    @Override
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return recipeBook.hasRecipe(recipe);
    }

    @Override
    public void deleteRecipe(Recipe target) {
        recipeBook.removeRecipe(target);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        recipeBook.addRecipe(recipe);
        updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
    }

    @Override
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireAllNonNull(target, editedRecipe);

        recipeBook.setRecipe(target, editedRecipe);
    }

    //=========== Filtered Recipe List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Recipe} backed by the internal list of
     * {@code versionedRecipeBook}
     */
    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return filteredRecipes;
    }

    @Override
    public void updateFilteredRecipeList(Predicate<Recipe> predicate) {
        requireNonNull(predicate);
        filteredRecipes.setPredicate(predicate);
    }

    public void setAddressBook(ReadOnlyFoodEntryBook ingredientBook) {
        this.ingredientBook.resetData(ingredientBook);
    }

    @Override
    public ReadOnlyFoodEntryBook getIngredientBook() {
        return ingredientBook;
    }

    @Override
    public boolean hasIngredient(Ingredient person) {
        requireNonNull(person);
        return ingredientBook.hasIngredient(person);
    }

    @Override
    public void deleteIngredient(Ingredient target) {
        ingredientBook.removeIngredient(target);
    }

    @Override
    public void addIngredient(Ingredient ind) {
        ingredientBook.addIngredient(ind);
        updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
    }

    @Override
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireAllNonNull(target, editedIngredient);

        ingredientBook.setIngredient(target, editedIngredient);
    }

    //=========== Filtered Ingredient List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Ingredient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<FoodEntry> getFilteredIngredientList() {
        return filteredIngredients;
    }

    @Override
    public void updateFilteredIngredientList(Predicate<FoodEntry> predicate) {
        requireNonNull(predicate);
        filteredIngredients.setPredicate(predicate);

    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;

        if (book instanceof RecipeBook) {
            return recipeBook.equals(other.recipeBook)
                    && userPrefs.equals(other.userPrefs)
                    && filteredRecipes.equals(other.filteredRecipes);
        } else if (book instanceof IngredientBook) {
            return ingredientBook.equals(other.ingredientBook)
                    && userPrefs.equals(other.userPrefs)
                    && filteredIngredients.equals(other.filteredIngredients);
        } else {
            return false;
        }





    }

}
