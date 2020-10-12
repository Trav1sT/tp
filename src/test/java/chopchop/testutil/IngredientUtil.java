package chopchop.testutil;


import static chopchop.logic.parser.CliSyntax.ARG_EXPIRY;
import static chopchop.logic.parser.CliSyntax.ARG_QUANTITY;

import chopchop.logic.commands.AddIngredientCommand;
import chopchop.model.ingredient.Ingredient;

/**
 * A utility class for Ingredient.
 */
public class IngredientUtil {

    /**
     * Returns an add command string for adding the {@code ingredient}.
     */
    public static String getAddCommand(Ingredient ind) {
        return AddIngredientCommand.COMMAND_WORD + " " + getIngredientDetails(ind);
    }

    /**
     * Returns the part of command string for the given {@code ingredient}'s details.
     */
    public static String getIngredientDetails(Ingredient ind) {
        StringBuilder sb = new StringBuilder();
        sb.append(ind.getName().fullName + " ");
        sb.append(ARG_QUANTITY + " " + ind.getQuantity().toString() + " ");
        sb.append(ARG_EXPIRY + " " +  ind.getExpiryDate().toString() + " ");
        return sb.toString();
    }
}
