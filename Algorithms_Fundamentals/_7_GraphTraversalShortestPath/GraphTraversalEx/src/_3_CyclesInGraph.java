import java.util.*;

public class _3_CyclesInGraph {

    public static Map<String, List<String>> graph = new HashMap<>();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String line = scan.nextLine();

        String source = null;

        while (!line.equals("")) {

            String[] tokens = line.split("-");

            if (source == null) {
                source = tokens[0];
            }

            graph.putIfAbsent(tokens[0], new ArrayList<>());

            graph.get(tokens[0]).add(tokens[1]);

            line = scan.nextLine();
        }

        Set<String> visited = new HashSet<>();
        Set<String> cycles = new HashSet<>();

        try {
            dfs(source, visited, cycles);
            System.out.println("Acyclic: Yes");
        } catch (IllegalStateException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void dfs(String source, Set<String> visited, Set<String> cycles) {
        if (cycles.contains(source)) {
            throw new IllegalStateException("Acyclic: No");
        }

        if (visited.contains(source)) {
            return;
        }


        cycles.add(source);
        visited.add(source);

        List<String> children = graph.get(source);

        if (children == null) {
            return;
        }

        for (String child : children) {
            if (!visited.contains(child)) {
                dfs(child, visited, cycles);
            }
        }
        cycles.remove(source);
    }
}
