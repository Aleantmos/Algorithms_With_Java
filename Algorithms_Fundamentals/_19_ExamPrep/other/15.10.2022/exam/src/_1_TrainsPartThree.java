import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class _1_TrainsPartThree {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int vertices = Integer.parseInt(reader.readLine());

        int[] directions = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int source = directions[0];
        int dest = directions[1];

        int[][] graph = new int[nodes][nodes];

        for (int edge = 0; edge < vertices; edge++) {
            int[] edgeInfo = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = edgeInfo[0];
            int to = edgeInfo[1];
            int capacity = edgeInfo[2];

            graph[from][to] = capacity;
        }

        int[] parents = new int[nodes];
        Arrays.fill(parents, -1);

        int maxFlow = 0;

        while (bfs(source, dest, graph, parents)) {
            int node = dest;
            int minFlow = Integer.MAX_VALUE;

            while (node != source) {
                minFlow = Math.min(minFlow, graph[parents[node]][node]);
                node = parents[node];
            }

            maxFlow += minFlow;

            node = dest;
            while (node != source) {
                graph[parents[node]][node] -= minFlow;
                graph[node][parents[node]] += minFlow;
                node = parents[node];
            }
        }
        System.out.println(maxFlow);
    }

    private static boolean bfs(int source, int dest, int[][] graph, int[] parents) {

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        boolean[] visited = new boolean[graph.length];
        visited[source] = true;
        while (!queue.isEmpty()) {
            int parent = queue.poll();
            for (int child = 0; child < graph.length; child++) {
                if (graph[parent][child] != 0 && !visited[child]) {
                    queue.offer(child);
                    visited[child] = true;
                    parents[child] = parent;
                }
            }
        }
        return visited[dest];
    }

}
