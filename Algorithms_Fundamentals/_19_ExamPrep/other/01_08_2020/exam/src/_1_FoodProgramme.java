import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1_FoodProgramme {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        int[] trip = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int source = trip[0];
        int dest = trip[1];

        int[][] graph = new int[nodes][nodes];

        for (int i = 0; i < edges; i++) {
            int[] data = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = data[0];
            int to = data[1];
            int dist = data[2];

            graph[from][to] = dist;
            graph[to][from] = dist;
        }
        boolean[] visited = new boolean[nodes];

        int[] prev = new int[nodes];
        Arrays.fill(prev, -1);

        int[] distances = new int[nodes];

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(
                n -> distances[n]
        ));

        queue.offer(source);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;

            int[] children = graph[parent];

            for (int child = 0; child < children.length; child++) {
                if (children[child] != 0 && !visited[child]) {
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

        StringBuilder sb = new StringBuilder();

        for (int i = path.size() - 1; i >= 0; i--) {
            sb.append(path.get(i)).append(" ");
        }
        sb.append(System.lineSeparator());
        sb.append(distances[dest]);

        System.out.println(sb.toString());

        /*StringBuilder sb = new StringBuilder();
        sb.append(dest).append(" ");

        while (node != -1) {
            sb.append(node).append(" ");
            node = prev[node];
        }

        System.out.println(sb.reverse().toString().trim());

        System.out.println(distances[dest]);*/

    }
}
