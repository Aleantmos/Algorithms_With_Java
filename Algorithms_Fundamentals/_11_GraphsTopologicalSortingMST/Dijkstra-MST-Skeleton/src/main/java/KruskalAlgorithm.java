import java.util.*;

public class KruskalAlgorithm {

    public static Set<Integer> visited = new HashSet<>();
    public static Map<Integer, List<Edge>> edgesByNode = new HashMap<>();

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        List<Edge> forest = new ArrayList<>();

        for (Edge edge : edges) {
            edgesByNode.putIfAbsent(edge.getStartNode(), new ArrayList<>());
            edgesByNode.get(edge.getStartNode()).add(edge);

            edgesByNode.putIfAbsent(edge.getEndNode(), new ArrayList<>());
            edgesByNode.get(edge.getEndNode()).add(edge);
        }

        for (int node : edgesByNode.keySet()) {
            if (!visited.contains(node)) {
                prim(node, forest);
            }
        }

        forest.sort(Comparator.comparingInt(Edge::getWeight));

        return forest;
    }

    private static void prim(int start, List<Edge> forest) {
        visited.add(start);

        PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        edges.addAll(edgesByNode.get(start));

        while (!edges.isEmpty()) {
            Edge minEdge = edges.poll();

            int sourceNode = minEdge.getStartNode();
            int destinationNode = minEdge.getStartNode();

            int nonTreeNode = -1;

            if (visited.contains(sourceNode) && !visited.contains(destinationNode)) {
                nonTreeNode = destinationNode;
            }

            if (visited.contains(destinationNode) && !visited.contains(sourceNode)) {
                nonTreeNode = sourceNode;
            }

            if (nonTreeNode != -1) {
                forest.add(minEdge);
                visited.add(nonTreeNode);
                edges.addAll(edgesByNode.get(nonTreeNode));
            }
        }
    }




    /*

            -----------------KRUSKAL-----------------



    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {

        Collections.sort(edges);

        List<Edge> forest = new ArrayList<>();

        int[] parents = new int[numberOfVertices];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }








        while (!edges.isEmpty()) {
            Edge edge = edges.remove(0);

            int source = edge.getStartNode();
            int dest = edge.getEndNode();

            int firstRoot = findRoot(source, parents);
            int secondRoot = findRoot(dest, parents);

            if (firstRoot != secondRoot) {
                forest.add(edge);
                parents[firstRoot] = secondRoot;
            }
        }
        return forest;
    }

    public static int findRoot(int node, int[] parents) {

        while (parents[node] != node) {
            node = parents[node];
        }
        return node;
    }*/
}
