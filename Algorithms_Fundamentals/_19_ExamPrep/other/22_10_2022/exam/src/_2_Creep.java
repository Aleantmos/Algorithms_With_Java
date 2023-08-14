import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2_Creep {

    //todo - must optimize
    public static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int dist;

        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }


        public boolean equals(Edge edge) {
            return this.to == edge.to && this.from == edge.from;
        }


        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.dist, e.dist);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int vertices = Integer.parseInt(reader.readLine());

        List<Edge> edges = new ArrayList<>();



        for (int i = 0; i < vertices; i++) {
            int[] edgeData = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = edgeData[0];
            int to = edgeData[1];
            int distance = edgeData[2];

            Edge edgeToAdd = new Edge(from, to, distance);

            boolean isSet = false;
            for (Edge edge : edges) {
                if (edgeToAdd.equals(edge)) {
                    edge.setDist(distance);
                    isSet = true;
                }
            }
            if (!isSet) {
                edges.add(edgeToAdd);
            }
        }

        List<Edge> forest = new ArrayList<>();

        Collections.sort(edges);

        int[] parent = new int[vertices];
        for (int i = 0; i < nodes; i++) {
            parent[i] = i;
        }

        int totalDist = 0;

        while (!edges.isEmpty()) {
            Edge edge = edges.remove(0);

            int source = edge.from;
            int dest = edge.to;

            int firstRoot = findRoot(source, parent);
            int secondRoot = findRoot(source, parent);

            if (firstRoot != secondRoot) {
                forest.add(edge);
                parent[firstRoot] = dest;
                totalDist += edge.dist;
            }
        }

        for (Edge edge : forest) {
            System.out.printf("%d %d%n", edge.from, edge.to);
        }
        System.out.println(totalDist);
    }

    private static int findRoot(int node, int[] parent) {

        while (parent[node] != node) {
            node = parent[node];
        }
        return node;
    }
}
