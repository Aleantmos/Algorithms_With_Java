import java.util.*;

public class _1_DistanceBetweenVerticals {

    public static int[][] graph;

    public static Map<Integer, Integer> indexMapper = new HashMap<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nodes = Integer.parseInt(scan.nextLine());
        int pairs = Integer.parseInt(scan.nextLine());

        graph = new int[nodes + 1][];

        for (int i = 0; i <= nodes; i++) {
            String[] edges = scan.nextLine().split(":");

            indexMapper.put(Integer.parseInt(edges[0]), i);

            if (edges.length == 1) {
                graph[i] = new int[0];
            } else {
                graph[i] = Arrays.stream(edges[1].split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        }



        while (pairs-- > 0) {
            int[] relations = Arrays.stream(scan.nextLine().split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = relations[0];
            int dest = relations[1];

            System.out.printf("{%d, %d} -> ", source, dest);

            int[] prev = new int[graph.length];

            Arrays.fill(prev, -1);

            bfs(graph, indexMapper.get(source), indexMapper.get(dest), prev);

            List<Integer> path = new ArrayList<>();

            int parent = prev[indexMapper.get(dest)];

            int pathCount = -1;

            while (parent != -1) {
                pathCount++;
                parent = prev[parent];
            }


            System.out.println(pathCount == -1 ? pathCount : pathCount + 1);
        }
    }

    private static void bfs(int[][] graph, int source, int dest, int[] prev) {

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);

        boolean[] visited = new boolean[graph.length + 1];

        visited[source] = true;

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            if (node == dest) {
                return;
            }
            for (int i = 0; i < graph[node].length; i++) {
                int child = indexMapper.get(graph[node][i]);

                if (!visited[child]) {
                    prev[child] = node;
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }
        prev[source] = -1;
    }
}
