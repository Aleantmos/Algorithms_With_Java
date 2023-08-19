import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _2_Paths {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nodes = Integer.parseInt(scan.nextLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            String input = scan.nextLine();
            if (!input.isEmpty()) {
                int[] dest = Arrays.stream(input.split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();


                graph.add(new ArrayList<>());
                for (int el : dest) {
                    graph.get(i).add(el);
                }
            }
        }

        int dest = graph.size();
        boolean[] visited = new boolean[graph.size() + 1];
        int[] prev = new int[graph.size() + 1];

        for (int i = 0; i < graph.size(); i++) {
            int source = i;
            Arrays.fill(prev, -1);
            dfs(graph, source, dest, visited, prev);
        }
    }

    private static void printPath(int dest, int[] prev) {
        StringBuilder sb = new StringBuilder();
        int node = dest;
        while (node != -1) {
            sb.append(node).append(" ");
            node = prev[node];
        }

        System.out.println(sb.reverse().substring(1, sb.length()).toString());
    }

    private static void dfs(List<List<Integer>> graph, int source, int dest, boolean[] visited, int[] prev) {
        if (source == dest) {
            printPath(dest, prev);
            return;
        }
        visited[source] = true;
        for (Integer child : graph.get(source)) {
            if (!visited[child]) {
                visited[child] = true;
                prev[child] = source;
                dfs(graph, child, dest, visited, prev);
                visited[child] = false;
                prev[child] = -1;
            }
        }

    }
}
