import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class _3_TravelOptimizer {
    public static class Edge {
        int to;
        int dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    public static int limitCnt = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int edgesCnt = Integer.parseInt(reader.readLine());

        Map<Integer, List<Edge>> graph = new HashMap<>();

        for (int i = 0; i < edgesCnt; i++) {
            int[] data = Arrays.stream(reader.readLine().split(", "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = data[0];
            int to = data[1];
            int dist = data[2];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());

            graph.get(from).add(new Edge(to, dist));
            graph.get(to).add(new Edge(from, dist));
        }

        int source = Integer.parseInt(reader.readLine());
        int dest = Integer.parseInt(reader.readLine());
        int limit = Integer.parseInt(reader.readLine());

        boolean[] visited = new boolean[graph.keySet().size()];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (Integer node : graph.keySet()) {
            topSort(node, stack, graph, visited);
        }

        int[] distances = new int[graph.keySet().size()];
        Arrays.fill(distances, Integer.MAX_VALUE);

        int[] prev = new int[graph.keySet().size()];
        Arrays.fill(prev, -1);

        distances[source] = 0;

        int[] stops = new int[graph.keySet().size()];

        boolean isSource = false;

        while (!stack.isEmpty()) {
            Integer parent = stack.pop();

            if (parent == source) {
                isSource = true;
            }

            List<Edge> edges = graph.get(parent);
            for (Edge edge : edges) {
                int currDist = edge.dist;
                int child = edge.to;

                if (distances[parent] != Integer.MAX_VALUE
                        && ((stops[parent] < limit) || (stops[parent] == limit && child == dest))
                        && (distances[parent] + currDist < distances[child])) {

                    distances[child] = distances[parent] + currDist;
                    prev[child] = parent;

                    if (isSource && parent != dest) {
                        stops[child] = stops[parent]++;
                    }

                }
            }
        }


        List<Integer> path = new ArrayList<>();
        int node = dest;

        boolean isFound = false;

        while (node != -1) {
            path.add(node);
            if (node == source) {
                isFound = true;
            }
            node = prev[node];
        }

        if (isFound) {
            Collections.reverse(path);
            String result = path.stream()
                    .map(m -> String.valueOf(m))
                    .collect(Collectors.joining(" "));
            System.out.println(result);
        } else {
            System.out.println("There is no such path.");
        }
    }

    private static void topSort(Integer node,
                                ArrayDeque<Integer> stack,
                                Map<Integer, List<Edge>> graph,
                                boolean[] visited) {

        if (visited[node]) {
            return;
        }

        visited[node] = true;

        for (Edge edge : graph.get(node)) {
            topSort(edge.to, stack, graph, visited);
        }

        stack.push(node);
    }
}
