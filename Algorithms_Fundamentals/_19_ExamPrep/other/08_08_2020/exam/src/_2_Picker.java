import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2_Picker {
    public static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.weight, edge.weight);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCnt = Integer.parseInt(reader.readLine());
        int edgesCnt = Integer.parseInt(reader.readLine());

        //Set<Integer> visited = new HashSet<>();
        //List<Edge> edges = new ArrayList<>();

        int[][] graph = new int[nodesCnt][nodesCnt];

        for (int i = 0; i < edgesCnt; i++) {
            int[] data = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            graph[data[0]][data[1]] = data[2];
        }

        int cost = 0;

        int[] parents = new int[nodesCnt];

        for (int i = 0; i < nodesCnt; i++) {
            parents[i] = i;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                Comparator.comparingInt(n -> graph[n[0]][n[1]]));


        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {
                if (graph[row][col] != 0) {
                    queue.offer(new int[] {row, col});
                }
            }
        }

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {
            int[] minEdge = queue.poll();

            int from = minEdge[0];
            int to = minEdge[1];
            int weight = graph[from][to];

            int firstRoot = findRoot(from, parents);
            int secondRoot = findRoot(to, parents);;

            if (firstRoot != secondRoot) {
                parents[secondRoot] = firstRoot;
                result.append(from).append(" ").append(to).append(System.lineSeparator());
                cost += weight;
            }
        }

        result.append(cost);

        System.out.println(result.toString());
        /*for (int i = 0; i < edgesCnt; i++) {
            int[] data = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = data[0];
            int to = data[1];
            int weight = data[2];

            edges.add(new Edge(from, to, weight));
            edges.add(new Edge(to, from, weight));
        }

        Collections.sort(edges);

        List<Edge> forest = new ArrayList<>();
        int[] parents = new int[nodesCnt];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        while (!edges.isEmpty()) {
            Edge edge = edges.remove(0);

            int source = edge.from;
            int dest = edge.to;

            int firstRoot = findRoot(source, parents);
            int secondRoot = findRoot(dest, parents);

            if (firstRoot != secondRoot) {
                forest.add(edge);
                parents[firstRoot] = secondRoot;
            }
        }
        int totalWeight = 0;

        for (Edge edge : forest) {
            System.out.printf("%d %d%n", edge.from, edge.to);
            totalWeight += edge.weight;
        }
        System.out.println(totalWeight);*/
    }

    private static int findRoot(int node, int[] parents) {
        while (parents[node] != node) {
            node = parents[node];
        }
        return node;
    }
}
