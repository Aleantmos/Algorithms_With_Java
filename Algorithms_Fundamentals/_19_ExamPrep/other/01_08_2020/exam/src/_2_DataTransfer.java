import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class _2_DataTransfer {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        int[][] graph = new int[nodes][nodes];

        int[] trip = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int source = trip[0];
        int dest = trip[1];

        for (int i = 0; i < edges; i++) {
            int[] data = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = data[0];
            int to = data[1];
            int flow = data[2];

            graph[from][to] = flow;
        }

        //int[][] copy = graph;

        int[] parents = new int[graph.length];

        Arrays.fill(parents, -1);

        int maxFlow = 0;

        while (bfs(graph, parents, source, dest)) {
            int node = dest;
            int flow = Integer.MAX_VALUE;

            while (node != source) {
                flow = Math.min(flow, graph[parents[node]][node]);
                node = parents[node];
            }

            maxFlow += flow;

            node = dest;

            while (node != source) {
                graph[parents[node]][node] -= flow;
                graph[node][parents[node]] -= flow;
                node = parents[node];
            }
        }
        System.out.println(maxFlow);
    }

    private static boolean bfs(int[][] graph, int[] parents, int source, int dest) {
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.length];

        Arrays.fill(parents, -1);

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int parent = queue.poll();

            for (int child = 0; child < graph.length; child++) {
                if (graph[parent][child] > 0 && !visited[child]) {
                    visited[child] = true;
                    parents[child] = parent;
                    queue.offer(child);
                }
            }
        }
        return visited[dest];
    }
}
