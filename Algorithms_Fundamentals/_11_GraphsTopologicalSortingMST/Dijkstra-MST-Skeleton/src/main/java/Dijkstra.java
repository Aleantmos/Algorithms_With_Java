import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        int[] distances = new int[graph.length];

        int[] prev = new int[graph.length];
        Arrays.fill(prev, -1);

        boolean[] visited = new boolean[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        distances[sourceNode] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));

        queue.offer(sourceNode);

        while (!queue.isEmpty()) {
            int parent = queue.poll();
            visited[parent] = true;

            int[] children = Arrays.stream(graph[parent])
                    .filter(child -> child != 0)
                    .toArray();
            for (int child = 0; child < children.length; child++) {
                if (children[child] != 0 && !visited[child]) {
                    queue.offer(child);

                    int newDistance = distances[parent] + graph[parent][child];

                    if (newDistance < distances[child]) {
                        distances[child] = newDistance;
                        prev[child] = parent;
                    }
                }
            }
        }
        List<Integer> path = new ArrayList<>();

        path.add(destinationNode);

        int n = prev[sourceNode];

        while (n != -1) {
            path.add(n);
            n = prev[n];
        }

        Collections.reverse(path);

        return path.size() == 1 ? null : path;
    }
}
