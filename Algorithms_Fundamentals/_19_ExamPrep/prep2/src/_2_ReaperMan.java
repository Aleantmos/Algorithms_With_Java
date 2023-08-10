import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class _2_ReaperMan {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        int[] input = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int source = input[0];
        int dest = input[1];

        int[][] graph = new int[nodes][nodes];
        int[] distances = new int[nodes];
        int[] parents = new int[nodes];
        boolean[] visited = new boolean[nodes];

        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);

        distances[source] = 0;


        while (edges-- > 0) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];
            int dist = tokens[2];

            graph[from][to] = dist;
            graph[to][from] = dist;

        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(e -> distances[e]));

        queue.offer(source);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;

            for (int child = 0; child < graph[parent].length; child++) {
                if (!visited[child] && graph[parent][child] != 0) {
                    queue.offer(child);

                    int newDist = distances[parent] + graph[parent][child];

                    if (newDist < distances[child]) {
                        distances[child] = newDist;
                        parents[child] = parent;
                    }
                }
            }
        }

        ArrayDeque<Integer> path = new ArrayDeque<>();
        path.push(dest);
        int prev = parents[dest];

        while (prev != -1) {
            path.push(prev);
            prev = parents[prev];
        }

        System.out.println(path.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));

        System.out.println(distances[dest]);
    }
}
