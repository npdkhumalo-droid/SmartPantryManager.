import com.smartpantrymanager.Models.packagpackage;

smartpantrymanager.Models;

/** One ingredient line of a recipe, e.g. "eggs", 3, "pcs". */
public class recipeIngredient {
    public final String name;
    public final double quantity;
    public final String unit;
    packagpackage com;

    public recipeIngredient(String name, double quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
}

