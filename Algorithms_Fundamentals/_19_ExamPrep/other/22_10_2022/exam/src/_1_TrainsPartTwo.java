import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1_TrainsPartTwo {
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

        for (int[] ints : graph) {
            Arrays.fill(ints, 0);
        }

        for (int row = 0; row < vertices; row++) {
            int[] edgeData = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = edgeData[0];
            int to = edgeData[1];
            int weight = edgeData[2];

            graph[from][to] = weight;
            graph[to][from] = weight;
        }

        boolean[] visited = new boolean[graph.length];

        int[] prev = new int[graph.length];
        Arrays.fill(prev, -1);

        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[source] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));

        queue.offer(source);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;

            int[] children = graph[parent];

            for (int child = 0; child < children.length; child++) {
                if (children[child] != 0 && !visited[child]) {
                    queue.offer(child);

                    int newDistance = distances[parent] + graph[parent][child];

                    if (newDistance < distances[child]) {
                        distances[child] = newDistance;
                        prev[child] = parent;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        path.add(dest);

        int curr = prev[dest];

        while (curr != -1) {
            path.add(curr);
            curr = prev[curr];
        }

        Collections.reverse(path);

        StringBuilder sb = new StringBuilder();

        for (int el : path) {
            sb.append(el).append(" ");
        }

        System.out.println(sb.toString().trim());
        System.out.println(distances[dest]);
    }
}
