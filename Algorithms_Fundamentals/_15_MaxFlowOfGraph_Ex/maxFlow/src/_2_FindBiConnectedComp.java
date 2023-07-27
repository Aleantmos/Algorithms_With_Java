import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _2_FindBiConnectedComp {

    public static int[][] graph;
    public static int[] depths;
    public static int[] lowpoints;
    public static int[] parents;
    public static int[] reachableCnt;

    public static List<List<Integer>> subGraphs = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nodes = Integer.parseInt(scan.nextLine().split("\\s+")[1]);
        int edges = Integer.parseInt(scan.nextLine().split("\\s+")[1]);

        graph = new int[nodes][nodes];
        depths = new int[nodes];
        lowpoints = new int[nodes];
        parents = new int[nodes];
        reachableCnt = new int[nodes];

        Arrays.fill(parents, -1);

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scan.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int source = tokens[0];
            int dest = tokens[1];

            graph[source][reachableCnt[source]++] = dest;
            graph[dest][reachableCnt[dest]++] = source;
        }

        findArticulationPoints(0, 1, new ArrayList<>());
    }

    private static void findArticulationPoints(int node, int depth, List<Integer> subSet) {
        depths[node] = depth;
        lowpoints[node] = depth;

        for (int i = 0; i < reachableCnt[node]; i++) {
            int child = graph[node][i];

            if (depths[child] == 0) {
                parents[child] = node;
                List<Integer> components = new ArrayList<>();
                findArticulationPoints(child, depth + 1, components);
                if (lowpoints[child] >= depth || parents[child] == -1) {
                    components.add(node);
                    subGraphs.add(components);
                } else {
                    subSet.addAll(components);
                }
                lowpoints[node] = Math.min(lowpoints[node], lowpoints[child]);
            } else if (child != parents[node]) {
                lowpoints[node] = Math.min(lowpoints[node], depths[child]);
            }
        }
        subSet.add(node);
    }
}
