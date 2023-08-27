import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class p2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCnt = Integer.parseInt(reader.readLine());
        int edgesCnt = Integer.parseInt(reader.readLine());

        int[][] graph = new int[nodesCnt][nodesCnt];

        for (int i = 0; i < edgesCnt; i++) {

            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int data = tokens[2];

            graph[from][to] = data;
            graph[to][from] = data;
        }

        int[] corrupt = Arrays.stream(reader.readLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        int source = Integer.parseInt(reader.readLine());
        int dest = Integer.parseInt(reader.readLine());

        int[] parents = new int[graph.length];
        Arrays.fill(parents, -1);

        int maxFlow = 0;

        while (bfs(source, dest, graph, parents, corrupt)) {
            int node = dest;
            int flow = Integer.MAX_VALUE;

            while (node != 0) {
                flow = Math.min(flow, graph[parents[node]][node]);
                node = parents[node];
            }
            maxFlow += flow;

            node = dest;

            while (node != 0) {
                graph[parents[node]][node] -= flow;
                graph[node][parents[node]] += flow;
                node = parents[node];
            }
        }

        System.out.println(maxFlow);
    }

    private static boolean bfs(int source, int dest, int[][] graph, int[] parents, int[] corrupt) {
        Deque<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[graph.length];

        for (int node : corrupt) {
            visited[node] = true;
        }

        Arrays.fill(parents, -1);

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int child = 0; child < graph.length; child++) {
                if (graph[node][child] != 0 && !visited[child]) {
                    visited[child] = true;
                    parents[child] = node;
                    queue.offer(child);
                }
            }
        }

        return visited[dest];
    }
}
