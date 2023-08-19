import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1_VampireLabyrinth {
    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(scan.readLine());
        int edges = Integer.parseInt(scan.readLine());

        int[] trip = Arrays.stream(scan.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int source = trip[0];
        int dest = trip[1];

        int[][] graph = new int[nodes][nodes];
        int[] distances = new int[nodes];

        for (int i = 0; i < edges; i++) {
            int[] data = Arrays.stream(scan.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = data[0];
            int to = data[1];
            int vampCnt = data[2];
            graph[from][to] = vampCnt;
            graph[to][from] = vampCnt;
        }

        int[] prev = new int[nodes];
        Arrays.fill(prev, -1);

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        boolean[] visited = new boolean[graph.length];

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));

        queue.offer(source);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;
            int[] children = graph[parent];
            for (int child = 0; child < children.length; child++) {
                if (children[child] != 0 && !visited[child]) {
                    queue.offer(child);

                    int newCnt = distances[parent] + graph[parent][child];
                    if (newCnt < distances[child]) {
                        distances[child] = newCnt;
                        prev[child] = parent;
                    }
                }
            }

        }

        List<Integer> path = new ArrayList<>();

        path.add(dest);

        int node = prev[dest];

        StringBuilder sb = new StringBuilder();
        sb.append(dest).append(" ");

        while (node != -1) {
            sb.append(node).append(" ");
            node = prev[node];
        }

        System.out.println(sb.reverse().toString().trim());

        System.out.println(distances[dest]);
    }
}
