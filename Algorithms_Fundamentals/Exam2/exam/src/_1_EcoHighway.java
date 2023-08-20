import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _1_EcoHighway {

    public static class Edge implements Comparable<Edge>{
        String from;
        String to;
        int cost;

        public Edge(String from, String to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return Integer.compare(this.cost, edge.cost);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int edgesCnt = Integer.parseInt(reader.readLine());

        List<Edge> edges = new ArrayList<>();

        Map<String, String> prev = new HashMap<>();

        for (int i = 0; i < edgesCnt; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            String from = tokens[0];
            String to = tokens[1];
            int cost = Integer.parseInt(tokens[2]);

            if (tokens.length == 4) {
                int ecoCost = Integer.parseInt(tokens[3]);
                cost += ecoCost;
            }
            edges.add(new Edge(from, to, cost));
            edges.add(new Edge(to, from, cost));

            prev.putIfAbsent(from, from);
            prev.putIfAbsent(to, to);
        }


        List<Edge> forest = new ArrayList<>();

        Collections.sort(edges);

        int totalCost = 0;

        while (!edges.isEmpty()) {
            Edge edge = edges.remove(0);

            String source = edge.from;
            String dest = edge.to;

            String firstRoot = findRoot(source, prev);
            String secondRoot = findRoot(dest, prev);

            if (!firstRoot.equals(secondRoot)) {
                forest.add(edge);
                prev.put(firstRoot, secondRoot);
                totalCost += edge.cost;
            }
        }

        System.out.println("Total cost of building highways: " + totalCost);

    }

    private static String findRoot(String node, Map<String, String> prev) {

        while (!prev.get(node).equals(node)) {
            node = prev.get(node);
        }
        return node;
    }
}
