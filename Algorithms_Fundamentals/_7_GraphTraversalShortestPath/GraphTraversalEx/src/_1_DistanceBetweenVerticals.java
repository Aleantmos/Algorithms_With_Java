import java.util.*;
import java.util.stream.Collectors;

public class _1_DistanceBetweenVerticals {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nodes = Integer.parseInt(scan.nextLine());
        int pairs = Integer.parseInt(scan.nextLine());

        //int[][] oldGraph = new int[nodes + 1][];

        Map<Integer, List<Integer>> graph = new HashMap<>();


        for (int i = 0; i < nodes; i++) {
            String[] data = scan.nextLine().split(":");

            int from = Integer.parseInt(data[0]);

            if (data.length != 1) {
                List<Integer> destList = Arrays.stream(data[1].split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                graph.put(from, destList);
            } else {
                graph.put(from, new ArrayList<>());
            }
        }

        while (pairs-- > 0) {
            int[] pairInfo = Arrays.stream(scan.nextLine().split("-"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = pairInfo[0];
            int dest = pairInfo[1];

            Map<Integer, Boolean> visited = new HashMap<>();
            Map<Integer, Integer> prev = new HashMap<>();

            for (Integer node : graph.keySet()) {
                visited.put(node, false);
                prev.put(node, -1);
            }

            bfs(graph, visited, source, dest, prev);

            int parent = prev.get(dest);

            int length = 0;

            while (parent != -1) {
                length++;
                parent = prev.get(parent);
            }

            System.out.printf("{%d, %d} -> %d%n",
                    source, dest, length == 0 ? -1 : length);
        }
    }

    private static void bfs(Map<Integer, List<Integer>> graph,
                            Map<Integer, Boolean> visited,
                            int source, int dest, Map<Integer, Integer> prev) {

        Deque<Integer> queue = new ArrayDeque<>();

        queue.add(source);

        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            visited.put(node, true);
            if (node == dest) {
                break;
            }

            for (Integer child : graph.get(node)) {
                if (!visited.get(child)) {
                    visited.put(child, true);
                    prev.put(child, node);
                    queue.add(child);
                }
            }
        }
    }



    /*public static int[][] graph;

    public static Map<Integer, Integer> indexMapper = new HashMap<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nodes = Integer.parseInt(scan.nextLine());
        int pairs = Integer.parseInt(scan.nextLine());

        graph = new int[nodes + 1][];

        for (int i = 1; i <= nodes; i++) {
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

            System.out.printf("{%d, %d} -> %d%n",
                    source, dest, pathCount == -1 ? pathCount : pathCount + 1);

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
    }*/
}
