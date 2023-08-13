import java.util.*;

public class _2_CryptoExchange {
    public static Map<String, List<String>> graph = new HashMap<>();
    public static Map<String, Boolean> visited = new HashMap<>();
    public static int maxSteps;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int cnt = Integer.parseInt(scan.nextLine());

        String[] input;

        maxSteps = Integer.MAX_VALUE;

        for (int i = 0; i < cnt; i++) {
            input = scan.nextLine().split(" - ");

            String from = input[0];
            String to = input[1];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(from).add(to);
            graph.get(to).add(from);

            visited.put(from, false);
            visited.put(to, false);
        }

        input = scan.nextLine().split(" -> ");

        String source = input[0];
        String dest = input[1];

        int steps = 0;

        dfs(source, dest, steps);

        System.out.println(maxSteps == Integer.MAX_VALUE ? -1 : maxSteps);
    }

    private static void dfs(String parent,
                            String dest,
                            int steps) {

        if (parent.equals(dest)) {
            if (steps < maxSteps) {
                maxSteps = steps;
                return;
            }
        }


        visited.put(parent, true);
        List<String> children = graph.get(parent);

        if (children == null) {
            return;
        }

        for (String child : children) {
            if (!visited.get(child)) {
                dfs(child, dest, steps + 1);
                visited.put(child, false);
            }
        }
    }

}
