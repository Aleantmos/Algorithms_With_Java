import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2_ReaperMan {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int vertices = Integer.parseInt(reader.readLine());

        int[] tripInfo = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int source = tripInfo[0];
        int dest = tripInfo[1];

        int[][] graph = new int[nodes][nodes];

        for (int i = 0; i < vertices; i++) {
            int[] edgeInfo = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = edgeInfo[0];
            int to = edgeInfo[1];
            int distance = edgeInfo[2];

            graph[from][to] = distance;
            graph[to][from] = distance;
        }


        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        int[] prev = new int[graph.length];
        Arrays.fill(prev, -1);

        boolean[] visited = new boolean[graph.length];

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));

        queue.offer(source);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;

            int[] children = graph[parent];

            for (int child = 0; child < children.length; child++) {
                if (children[child] > 0 && !visited[child]) {

                    queue.offer(child);

                    int newDist = distances[parent] + graph[parent][child];

                    if (newDist < distances[child]) {
                        distances[child] = newDist;
                        prev[child] = parent;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        path.add(dest);

        int node = prev[dest];

        while (node != -1) {
            path.add(node);
            node = prev[node];
        }

        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();
        for (Integer element : path) {
            sb.append(element).append(" ");
        }
        System.out.println(sb.toString().trim());

        System.out.println(distances[dest]);

    }
}
