import java.util.*;
import java.util.stream.Collectors;

public class _1_Bellman_Ford_ShortestPaths {

    public static int[][] graph;
    public static int[] distance;
    public static int[] prev;
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

        bellmanFord(source);

        List<Integer> path = new ArrayList<>();

        path.add(dest);
        int node = prev[dest];

        while (node != -1) {
            path.add(node);
            node = prev[node];
        }

        Collections.reverse(path);

        System.out.println(path.stream().map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    private static void bellmanFord(int sourceNode) {

        distance = new int[graph.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        prev = new int[graph.length];
        Arrays.fill(prev, -1);

        distance[sourceNode] = 0;

        for (int i = 1; i < graph.length - 1; i++) {
            for (int r = 1; r < graph.length; r++) {
                for (int c = 1; c < graph[r].length; c++) {
                    int weight = graph[r][c];
                    if (weight != 0) {
                        int source = r;
                        int dest= c;

                        if (distance[r] != Integer.MAX_VALUE) {
                            int newValue = distance[source] + weight;
                            if (newValue < distance[dest]) {
                                distance[dest] = newValue;
                                prev[dest] = source;
                            }
                        }
                    }
                }
            }
        }
    }
}
