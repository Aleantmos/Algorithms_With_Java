import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1_TourDeSofia {

    public static Map<Integer, List<Integer>> graph = new HashMap<>();
    public static boolean[] visited;
    public static int[] dist;
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());
        int source = Integer.parseInt(reader.readLine());

        visited = new boolean[nodes];
        dist = new int[nodes];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph.putIfAbsent(tokens[0], new ArrayList<>());
            graph.get(tokens[0]).add(tokens[1]);
        }

        bfs(source);

        if (dist[source] != 0) {
            System.out.println(dist[source]);
        } else {
            int cnt = 0;
            for (boolean node : visited) {
                if (node) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }

    private static void bfs(int source) {

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);
        visited[source] = true;
        dist[source] = 0;

        while (!queue.isEmpty()) {
            int parent = queue.poll();

            List<Integer> children = graph.get(parent);

            if (children != null) {
                for (int i = 0; i < children.size(); i++) {
                    int child = children.get(i);

                    if (!visited[child]) {
                        visited[child] = true;
                        queue.offer(child);
                        dist[child] = dist[parent] + 1;
                    } else if (dist[child] == 0) {
                        dist[child] = dist[parent] + 1;
                        return;
                    }
                }
            }
        }
    }
}
