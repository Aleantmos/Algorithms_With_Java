import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class _1_TrainsPartTwo {
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
            int dist = data[2];

            graph[from][to] = dist;
            graph[to][from] = dist;
        }

        int[] prev = new int[nodes];
        Arrays.fill(prev, -1);

        int[] distances = new int[nodes];
        Arrays.fill(distances, Integer.MAX_VALUE);

        boolean[] visited = new boolean[nodes];

        PriorityQueue<Integer> queue = new PriorityQueue<>
                (Comparator.comparingInt(node -> distances[node]));

        distances[source] = 0;

        queue.offer(source);

        while (!queue.isEmpty()) {
            Integer parent = queue.poll();
            visited[parent] = true;

            int[] children = graph[parent];
            for (int i = 0; i < children.length; i++) {
                if (children[i] != 0 && !visited[i]) {
                    queue.offer(i);

                    int newDist = graph[parent][i] + distances[parent];

                    if (newDist < distances[i]) {
                        distances[i] = newDist;
                        prev[i] = parent;
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();

        int node = dest;

        while (node != -1) {
            path.add(node);
            node = prev[node];
        }

        Collections.reverse(path);
        System.out.println(
                path.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" ")));
        System.out.println(distances[dest]);
    }
}
