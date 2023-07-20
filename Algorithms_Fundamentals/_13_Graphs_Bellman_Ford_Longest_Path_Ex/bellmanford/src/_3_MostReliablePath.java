import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class _3_MostReliablePath {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int nodes = Integer.parseInt(scan.nextLine().split("\\s+")[1]);

        String[] path = scan.nextLine().split("\\s+");

        int edges = Integer.parseInt(scan.nextLine().split("\\s+")[1]);

        int source = Integer.parseInt(path[1]);
        int dest = Integer.parseInt(path[3]);

        int[][] graph = new int[nodes][nodes];

        for (int i = 0; i < edges; i++) {
            int[] tokens = Arrays.stream(scan.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[tokens[0]][tokens[1]] = tokens[2];
            graph[tokens[1]][tokens[0]] = tokens[2];
        }

        double[] distances = new double[nodes];

        boolean[] visited = new boolean[nodes];

        distances[source] = 1.00;

        PriorityQueue<Integer> queue = new PriorityQueue<>(
                (f, s) -> Double.compare(distances[s], distances[f]));

        queue.offer(source);

        while (!queue.isEmpty()) {
            Integer minNode = queue.poll();

            visited[minNode] = true;

            for (int i = 0; i < graph[minNode].length; i++) {
                int weight = graph[minNode][i];

                if (weight != 0 && !visited[i]) {
                    double newDistance = distances[minNode] * (weight) /100.00;
                    if (newDistance > distances[i]) {
                        distances[i] = newDistance;
                    }
                    queue.offer(i);
                }
            }
        }
        System.out.println(distances[dest]);
    }
}
