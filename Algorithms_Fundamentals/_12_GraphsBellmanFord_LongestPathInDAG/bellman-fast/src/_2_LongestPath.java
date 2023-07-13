import java.util.*;
import java.util.stream.Collectors;

public class _2_LongestPath {
    public static int[][] graph;
    public static int[] distance;
    public static int[] prev;
    public static boolean[] visited;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nodes = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());

        graph = new int[nodes + 1][nodes + 1];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scan.nextLine().split("\s++"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = tokens[0];
            int dest = tokens[1];
            int weight = tokens[2];

            graph[source][dest] = weight;
        }

        int source = Integer.parseInt(scan.nextLine());
        int dest = Integer.parseInt(scan.nextLine());

        distance = new int[graph.length];

        Arrays.fill(distance, Integer.MIN_VALUE);

        distance[source] = 0;
        visited = new boolean[graph.length];

        ArrayDeque<Integer> sorted = new ArrayDeque<>();

        for (int i = 1; i < graph.length; i++) {
            topologicalSort(i, sorted);
        }

        while (!sorted.isEmpty()) {
            int node = sorted.pop();

            for (int i = 1; i < graph[node].length; i++) {
                int weight = graph[node].length;
                if (weight != 0) {
                    if (distance[node] + weight > distance[i]) {
                        distance[i] = distance[node] + weight;
                    }
                }
            }
        }

        System.out.println(distance[dest]);
    }

    private static void topologicalSort(int node, ArrayDeque<Integer> sorted) {

        if (visited[node]) {
            return;
        }

        visited[node] = true;
        for (int i = 1; i < graph[node].length; i++) {
            if(graph[node][i] != 0) {
                topologicalSort(i, sorted);
            }
        }
        sorted.push(node);
    }
}