package chopchop.model.ingredient.exceptions;

/**
 * Signals that the operation will result in duplicate Ingredients (Persons are considered duplicates if they have
 * the same identity).
 */
public class DuplicateIngredientException extends RuntimeException {
    public DuplicateIngredientException() {
        super("Operation would result in duplicate ingredients");
    }
}
