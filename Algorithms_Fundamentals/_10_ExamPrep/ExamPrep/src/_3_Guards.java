import java.util.*;

public class _3_Guards {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nodes = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());

        Map<Integer, List<Integer>> graph = new HashMap<>();
        boolean[] visited;

        for (int i = 0; i < edges; i++) {
            int[] input = Arrays.stream(scan.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = input[0];
            int to = input[1];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);

        }

        int source = Integer.parseInt(scan.nextLine());

        List<Integer> disconnected = new ArrayList<>();


        for (int dest = 1; dest <= nodes; dest++) {
            visited = new boolean[nodes + 1];
            bfs(source, dest, graph, visited, disconnected);
            if (!visited[dest]) {
                disconnected.add(dest);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Integer element : disconnected) {
            sb.append(element).append(System.lineSeparator());
        }
        System.out.println(sb.toString().trim());
    }

    private static void bfs(int parent, int dest,
                            Map<Integer, List<Integer>> graph,
                            boolean[] visited,
                            List<Integer> disconnected) {
        if (parent == dest) {
            visited[dest] = true;
            return;
        }

        visited[parent] = true;

        List<Integer> children = graph.get(parent);

        if (children == null) {
            return;
        }

        for (Integer child : children) {
            if (!visited[child]) {
                visited[child] = true;
                bfs(child, dest, graph, visited, disconnected);
            }
        }
    }
}
