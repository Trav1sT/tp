package chopchop.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import chopchop.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path recipeBookFilePath = Paths.get("data" , "addressbook.json");
    private Path ingredientBookFilePath = Paths.get("data" , "addressbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setRecipeBookFilePath(newUserPrefs.getRecipeBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public Path getAddressBookFilePath() {
        return null;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }


    public Path getRecipeBookFilePath() {
        return recipeBookFilePath;
    }

    public void setRecipeBookFilePath(Path recipeBookFilePath) {
        requireNonNull(recipeBookFilePath);
        this.recipeBookFilePath = recipeBookFilePath;
    }

    public void setIngredientBookFilePath(Path indBookFilePath) {
        requireNonNull(indBookFilePath);
        this.ingredientBookFilePath = indBookFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && recipeBookFilePath.equals(o.recipeBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, recipeBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + recipeBookFilePath);

        return sb.toString();
    }

}
