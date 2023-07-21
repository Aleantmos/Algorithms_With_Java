import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _5_Undefined {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nodes = Integer.parseInt(scan.nextLine());
        int edgesCnt = Integer.parseInt(scan.nextLine());

        int[][] graph = new int[nodes + 1][nodes + 1];
        List<int[]> edges = new ArrayList<>();

        for (int i = 0; i < edgesCnt; i++) {
            int[] tokens = Arrays.stream(scan.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[tokens[0]][tokens[1]] = tokens[2];
            edges.add(new int[]{tokens[0], tokens[1]});
        }

        int[] distances = new int[nodes + 1];

        int[] prev = new int[nodes + 1];

        Arrays.fill(prev, -1);

        int source = Integer.parseInt(scan.nextLine());

        distances[source] = 0;

        int dest = Integer.parseInt(scan.nextLine());

        boolean hasNegativeCycle = false;

        for (int i = 0; i < nodes - 1; i++) {
            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];

                if (distances[from] != Integer.MAX_VALUE) {
                    int newDistance = distances[from] + graph[from][to];

                    if (newDistance < distances[to]) {
                        hasNegativeCycle = true;
                        break;
                    }
                }
            }
        }

        if (hasNegativeCycle) {
            System.out.println("Undefined");
        } else {
            List<Integer> path = new ArrayList<>();
            path.add(dest);

            int node = prev[dest];

            while (node != - 1) {
                path.add(node);
                node = prev[node];
            }

            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + " ");
            }
            System.out.println();
            System.out.println(distances[dest]);
        }
    }
}
