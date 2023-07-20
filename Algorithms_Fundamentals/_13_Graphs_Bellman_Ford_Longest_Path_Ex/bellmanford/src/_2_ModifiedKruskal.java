import java.util.*;
import java.util.stream.Collectors;

public class _2_ModifiedKruskal {
    public static Map<Integer, List<Edge>> graph = new LinkedHashMap<>();

    public static class Edge implements Comparable<Edge> {
        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int budget = Integer.parseInt(scan.nextLine().split("\\s+")[1]);
        int nodes = Integer.parseInt(scan.nextLine().split("\\s+")[1]);
        int edgeCnt = Integer.parseInt(scan.nextLine().split("\\s+")[1]);

        boolean[] used = new boolean[nodes];

        for (int i = 0; i < edgeCnt; i++) {
            String[] tokens = scan.nextLine().split("\\s+");

            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            Edge edge = new Edge(from, to, weight);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(edge);
        }

        int[] parents = new int[nodes];

        Arrays.fill(parents, -1);

        PriorityQueue<Edge> edges = graph.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toCollection(PriorityQueue::new));

        for (int i = 0; i < nodes; i++) {
            parents[i] = i;
        }

        int forestWeight = 0;

        StringBuilder builder = new StringBuilder();

        while (!edges.isEmpty()) {
            Edge minEdge = edges.poll();

            int firstRoot = findRoot(minEdge.from, parents);
            int secondRoot = findRoot(minEdge.to, parents);

            if (firstRoot != secondRoot) {
                builder.append(
                        String.format("(%d %d) -> %d",
                        minEdge.from, minEdge.to, forestWeight));
                forestWeight += minEdge.weight;
                parents[secondRoot] = firstRoot;

                for (int i = 0; i < parents.length; i++) {
                    if (parents[i] == secondRoot)  {
                        parents[i] = firstRoot;
                    }
                }
            }
        }

        System.out.println("Minimum spanning forest weight: " + forestWeight);

        System.out.println(builder.toString().trim());
    }

    private static int findRoot(int node, int[] parents) {
        int root = parents[node];

        while (parents[node] != root) {
            root = parents[root];
        }

        return root;
    }
}
