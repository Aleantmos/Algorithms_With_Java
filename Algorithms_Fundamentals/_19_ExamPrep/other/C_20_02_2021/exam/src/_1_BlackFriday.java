import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1_BlackFriday {
    //PRIM

    public static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int dist;

        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.dist, edge.dist);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCnt = Integer.parseInt(reader.readLine());
        int edgesCnt = Integer.parseInt(reader.readLine());

        Map<Integer, List<Edge>> graph = new HashMap<>();

        int[] prev = new int[nodesCnt];
        for (int i = 0; i < nodesCnt; i++) {
            prev[i] = i;
        }

        Set<Integer> visited = new HashSet<>();

        List<Edge> forest = new ArrayList<>();


        for (int i = 0; i < edgesCnt; i++) {
            int[] data = Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = data[0];
            int to = data[1];
            int dist = data[2];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(new Edge(from, to, dist));

            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(to).add(new Edge(to, from, dist));

        }

        for (Integer node : graph.keySet()) {
            if (!visited.contains(node)) {
                prim(node, forest, visited, graph);
            }
        }


        System.out.println(forest.stream().mapToInt(e -> e.dist).sum());

    }

    private static void prim(Integer start,
                             List<Edge> forest,
                             Set<Integer> visited,
                             Map<Integer, List<Edge>> graph) {

        visited.add(start);

        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.dist));

        edges.addAll(graph.get(start));

        while (!edges.isEmpty()) {
            Edge minEdge = edges.poll();

            int source = minEdge.from;
            int dest = minEdge.to;

            int nonTreeNode = -1;

            if (visited.contains(source) && !visited.contains(dest)) {
                nonTreeNode = dest;
            }

            if (visited.contains(dest) && !visited.contains(source)) {
                nonTreeNode = source;
            }

            if (nonTreeNode != -1) {
                forest.add(minEdge);
                visited.add(nonTreeNode);
                edges.addAll(graph.get(nonTreeNode));
            }
        }

    }

    private static int findRoot(int node, int[] prev) {

        if (node != prev[node]) {
            node = prev[node];
        }
        return node;
    }
}
