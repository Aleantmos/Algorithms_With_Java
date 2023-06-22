import java.util.*;

public class _3_Guards {

    //finish

    private static final Set<Integer> visitedNodes = new HashSet<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        int nodes = Integer.parseInt(scan.nextLine());
        int edgeCount = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < nodes; i++) {
            graph.put(i + 1, new HashSet<>());
        }

        for (int i = 0; i < edgeCount; i++) {
            String[] edge = scan.nextLine().split(" ");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);

            graph.get(from).add(to);
        }

        int startNode = Integer.parseInt(scan.nextLine());

        dfs(graph, startNode);
    }

    private static void dfs(Map<Integer, Set<Integer>> graph, int currentNode) {
        if (visitedNodes.contains(currentNode)) {
            return;
        }
        Set<Integer> connectedNodes = graph.get(currentNode);

        visitedNodes.add(currentNode);

        for (Integer connectedNode : connectedNodes) {
            dfs(graph, connectedNode);
        }
    }
}
