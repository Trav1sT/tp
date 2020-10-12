package chopchop.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;
import chopchop.model.ingredient.ReadOnlyIngredientBook;
import chopchop.model.recipe.ReadOnlyRecipeBook;
import chopchop.commons.core.LogsCenter;
import chopchop.commons.exceptions.DataConversionException;
import chopchop.model.ReadOnlyUserPrefs;
import chopchop.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private IngredientBookStorage ingredientBookStorage;
    private RecipeBookStorage recipeBookStorage;
    private UserPrefsStorage userPrefsStorage;


    /**
     * Constructs {@code StorageManagers}
     */
    public StorageManager(IngredientBookStorage ingredientBookStorage, RecipeBookStorage recipeBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.ingredientBookStorage = ingredientBookStorage;
        this.recipeBookStorage = recipeBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    @Override
    public Path getIngredientBookFilePath() {
        return ingredientBookStorage.getIngredientBookFilePath();
    }

    @Override
    public Optional<ReadOnlyRecipeBook> readRecipeBook()
        throws DataConversionException, IOException {
        return readRecipeBook(recipeBookStorage.getRecipeBookFilePath());
    }

    @Override
    public Optional<ReadOnlyRecipeBook> readRecipeBook(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return recipeBookStorage.readRecipeBook(filePath);
    }

    @Override
    public Path getRecipeBookFilePath() {
        return null;
    }

    @Override
    public Optional<ReadOnlyIngredientBook> readIngredientBook()
        throws DataConversionException, IOException {
        return readIngredientBook(ingredientBookStorage.getIngredientBookFilePath());
    }

    /**
     * @param filePath
     * @see #getIngredientBookFilePath()
     */
    @Override
    public Optional<ReadOnlyIngredientBook> readIngredientBook(Path filePath)
        throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return ingredientBookStorage.readIngredientBook(filePath);
    }

    @Override
    public void saveIngredientBook(ReadOnlyIngredientBook addressBook) throws IOException {
        saveIngredientBook(addressBook, ingredientBookStorage.getIngredientBookFilePath());
    }

    /**
     * @param ingredientBook
     * @param filePath
     * @see #saveIngredientBook(ReadOnlyIngredientBook)
     */
    @Override
    public void saveIngredientBook(ReadOnlyIngredientBook ingredientBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        ingredientBookStorage.saveIngredientBook(ingredientBook, filePath);
    }

    @Override
    public void saveRecipeBook(ReadOnlyRecipeBook recipeBook) throws IOException {
        saveRecipeBook(recipeBook, recipeBookStorage.getRecipeBookFilePath());
    }

    /**
     * @param recipeBook
     * @param filePath
     * @see #saveRecipeBook(ReadOnlyRecipeBook)
     */
    @Override
    public void saveRecipeBook(ReadOnlyRecipeBook recipeBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        recipeBookStorage.saveRecipeBook(recipeBook, filePath);
    }

}