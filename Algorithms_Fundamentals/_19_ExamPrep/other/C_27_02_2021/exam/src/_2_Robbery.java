import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _2_Robbery {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

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

        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        boolean[] visited = new boolean[graph.length];

        int[] prev = new int[graph.length];
        Arrays.fill(prev, -1);

        String[] cameras = reader.readLine().split("\\s+");
        for (String camera : cameras) {
            String[] tokens = camera.split("");
            if (tokens[1].equals("w")) {
                int index = Integer.parseInt(tokens[0]);
                visited[index] = true;
            }
        }

        int source = Integer.parseInt(reader.readLine());
        int dest = Integer.parseInt(reader.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));

        distances[source] = 0;
        queue.offer(source);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;

            int[] children = graph[parent];

            for (int i = 0; i < children.length; i++) {
                if (children[i] != 0 && !visited[i]) {
                    queue.offer(i);

                    int newDistance = distances[parent] + graph[parent][i];

                    if (newDistance < distances[i]) {
                        distances[i] = newDistance;
                        prev[i] = parent;
                    }
                }
            }
        }

        System.out.println(distances[dest]);
    }
}
