import java.util.*;

public class _4_ShortestPathInGraph {

    public static boolean[] visited;
    public static int[] prev;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nodes = Integer.parseInt(scan.nextLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < nodes + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int edges = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < edges; i++) {
            int[] paths = Arrays.stream(scan.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph.get(paths[0]).add(paths[1]);
        }

        int source = Integer.parseInt(scan.nextLine());
        int dest = Integer.parseInt(scan.nextLine());

        visited = new boolean[nodes + 1];
        prev = new int[nodes + 1];

        Arrays.fill(prev, -1);

        bfs(graph, source, dest);

        List<Integer> path = new ArrayList<>();

        path.add(dest);

        int prevNode = prev[dest];;

        while (prevNode != -1) {
            path.add(prevNode);
            prevNode = prev[prevNode];
        }

        System.out.println("Shortest path length is: " + (path.size() - 1));

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
    }

    private static void bfs(List<List<Integer>> graph, int source, int dest) {

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == dest) {
                return;
            }
            for (int child : graph.get(node)) {
                if (!visited[child]) {
                    visited[child] = true;
                    prev[child] = node;
                    queue.offer(child);
                }

            }
        }
    }
}
