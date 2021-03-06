package chopchop.model.ingredient;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;
import java.util.Objects;
import chopchop.model.FoodEntry;
import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.Quantity;
import chopchop.model.attributes.Name;

/**
 * Represents an Ingredient in the recipe manager.
 */
public class Ingredient extends FoodEntry {

    // Identity fields
    private final ExpiryDate expiryDate;

    // Data fields
    private final Quantity quantity;

    /**
     * Every field must be present and not null.
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Ingredient(Name name, Quantity quantity, ExpiryDate expiryDate) {
        super(name);
        requireAllNonNull(quantity, expiryDate);
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }


    /**
     * Returns true if both ingredients of the same name.
     *
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Ingredient)) {
            return false;
        }

        Ingredient otherInd = (Ingredient) other;

        return otherInd.getName().equals(getName())
            && otherInd.getExpiryDate().equals(getExpiryDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.name, quantity, expiryDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
            .append(" Quantity: ")
            .append(getQuantity())
            .append(" Expiry Date: ")
            .append(getExpiryDate());
        return builder.toString();
    }

}
