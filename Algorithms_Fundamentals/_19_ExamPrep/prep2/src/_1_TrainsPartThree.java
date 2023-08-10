import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class _1_TrainsPartThree {

    public static int[][] graph;
    public static int[] prev;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        String[] input = reader.readLine().split("\\s+");

        int source = Integer.parseInt(input[0]);
        int dest = Integer.parseInt(input[1]);

        graph = new int[nodes][nodes];
        prev = new int[nodes];

        while (edges-- > 0) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int capacity = tokens[2];

            graph[from][to] = capacity;
        }

        int maxFlow = 0;

        while (bfs(source, dest)) {

            int minFlow = Integer.MAX_VALUE;

            int node = dest;

            while (node != source) {
                minFlow = Math.min(minFlow, graph[prev[node]][node]);
                node = prev[node];
            }

            maxFlow += minFlow;

            node = dest;

            while (node != source) {
                graph[prev[node]][node] -= minFlow;
                graph[node][prev[node]] += minFlow;
                node = prev[node];
            }
        }


        System.out.println(maxFlow);
    }

    private static boolean bfs(int source, int dest) {
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(prev, -1);

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            /*if (node == dest) {
                return true;
            }*/

            for (int child = 0; child < graph[node].length; child++) {
                if (!visited[child] && graph[node][child] > 0) {
                    queue.offer(child);
                    visited[child] = true;
                    prev[child] = node;
                }
            }

        }
        return visited[dest];
    }
}
