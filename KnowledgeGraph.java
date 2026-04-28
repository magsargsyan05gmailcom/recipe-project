import java.util.*;

public class KnowledgeGraph {

    Map<String, Recipe> recipes = new HashMap<>();

    // ingredient -> recipes (hash index)
    Map<String, Set<String>> ingredientIndex = new HashMap<>();

    // ingredient substitutes
    Map<String, Set<String>> substitutes = new HashMap<>();

    public void addRecipe(String name, Set<String> ingredients, Set<String> tags) {
        Recipe r = new Recipe(name, ingredients, tags);
        recipes.put(name, r);

        // build hash index
        for (String ing : ingredients) {
            ingredientIndex.putIfAbsent(ing, new HashSet<>());
            ingredientIndex.get(ing).add(name);
        }
    }

    // add substitute
    public void addSubstitute(String a, String b) {
        substitutes.putIfAbsent(a, new HashSet<>());
        substitutes.get(a).add(b);
    }

    // BFS style cook search
    public void whatCanICook(Set<String> userIngredients) {

        Queue<String> queue = new LinkedList<>(userIngredients);
        Set<String> visited = new HashSet<>(userIngredients);

        // BFS over substitutes
        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (substitutes.containsKey(current)) {
                for (String next : substitutes.get(current)) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.add(next);
                    }
                }
            }
        }

        boolean found = false;

        for (Recipe r : recipes.values()) {
            Set<String> missing = new HashSet<>(r.ingredients);
            missing.removeAll(visited);

            if (missing.size() == 0) {
                System.out.println("You can cook: " + r.name);
                found = true;
            } else if (missing.size() == 1) {
                System.out.println("Almost: " + r.name + " missing " + missing);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No recipes found.");
        }
    }

    // similarity
    public void similarTo(String recipeName) {
        if (!recipes.containsKey(recipeName)) {
            System.out.println("Recipe not found");
            return;
        }

        Set<String> base = recipes.get(recipeName).ingredients;

        for (Recipe r : recipes.values()) {
            if (!r.name.equals(recipeName)) {
                double score = Utils.jaccard(base, r.ingredients);
                System.out.println(r.name + " -> " + score);
            }
        }
    }

    // filter by tag
    public void filterByTag(String tag) {
        for (Recipe r : recipes.values()) {
            if (r.tags.contains(tag)) {
                System.out.println(r.name);
            }
        }
    }

    public Map<String, Recipe> getRecipes() {
        return recipes;
    }
}