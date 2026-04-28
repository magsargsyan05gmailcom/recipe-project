import java.util.Set;

public class Utils {

    public static double jaccard(Set<String> a, Set<String> b) {

        int intersection = 0;

        for (String x : a) {
            if (b.contains(x)) {
                intersection++;
            }
        }

        int union = a.size() + b.size() - intersection;

        if (union == 0) return 0;

        return (double) intersection / union;
    }
}