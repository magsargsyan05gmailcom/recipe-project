import java.util.*;
import java.nio.file.*;
import org.json.*;

public class Main {

    public static void main(String[] args) throws Exception {

        KnowledgeGraph graph = new KnowledgeGraph();

        // 🔥 LOAD JSON
        String content = new String(Files.readAllBytes(Paths.get("data/data.json")));
        JSONObject obj = new JSONObject(content);

        JSONArray arr = obj.getJSONArray("recipes");

        for (int i = 0; i < arr.length(); i++) {
            JSONObject r = arr.getJSONObject(i);

            String name = r.getString("name");

            Set<String> ingredients = new HashSet<>();
            JSONArray ing = r.getJSONArray("ingredients");
            for (int j = 0; j < ing.length(); j++) {
                ingredients.add(ing.getString(j));
            }

            Set<String> tags = new HashSet<>();
            JSONArray tg = r.getJSONArray("tags");
            for (int j = 0; j < tg.length(); j++) {
                tags.add(tg.getString(j));
            }

            graph.addRecipe(name, ingredients, tags);
        }

        // sample substitute
        graph.addSubstitute("cheese", "tofu");

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print(">> ");
            String input = scanner.nextLine();

            if (input.startsWith("what-can-i-cook")) {

                String[] parts = input.split(" ");
                Set<String> userIngredients =
                        new HashSet<>(Arrays.asList(parts).subList(1, parts.length));

                graph.whatCanICook(userIngredients);
            }

            else if (input.startsWith("similar-to")) {
                String recipe = input.split(" ")[1];
                graph.similarTo(recipe);
            }

            else if (input.startsWith("filter")) {
                String tag = input.split(" ")[1];
                graph.filterByTag(tag);
            }

            else if (input.startsWith("substitutes-for")) {
                String ing = input.split(" ")[1];
                System.out.println(graph.substitutes.getOrDefault(ing, new HashSet<>()));
            }

            else if (input.startsWith("add-recipe")) {

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Ingredients (space separated): ");
                Set<String> ing = new HashSet<>(Arrays.asList(scanner.nextLine().split(" ")));

                System.out.print("Tags (space separated): ");
                Set<String> tags = new HashSet<>(Arrays.asList(scanner.nextLine().split(" ")));

                graph.addRecipe(name, ing, tags);
                System.out.println("Added!");
            }

            else if (input.equals("ingredient-graph")) {

                for (Recipe r : graph.getRecipes().values()) {
                    System.out.println(r.name + " -> " + r.ingredients);
                }
            }

            else if (input.equals("exit")) {
                break;
            }

            else {
                System.out.println("Unknown command");
            }
        }
    }
}