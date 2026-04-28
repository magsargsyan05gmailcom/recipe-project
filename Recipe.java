import java.util.Set;

public class Recipe {

    String name;
    Set<String> ingredients;
    Set<String> tags;

    public Recipe(String name, Set<String> ingredients, Set<String> tags) {
        this.name = name;
        this.ingredients = ingredients;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return name + " -> " + ingredients;
    }
}