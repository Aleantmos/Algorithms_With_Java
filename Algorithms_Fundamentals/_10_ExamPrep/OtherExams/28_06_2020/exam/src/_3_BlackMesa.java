import java.util.*;

public class _3_BlackMesa {
    //not working - redo
    private static List<Integer> visitedNodes = new ArrayList<>();
    private static boolean[] isVisited;
    private static List<Integer> path = new ArrayList<>();
    private static final int cnt = 0;
    private static boolean isFound = false;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<Integer, Set<Integer>> graph = new HashMap<>();

        int nodes = Integer.parseInt(scan.nextLine());

        int edges = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < nodes; i++) {
            graph.put(i + 1, new HashSet<>());
        }

        isVisited = new boolean[nodes + 1];

        for (int i = 0; i < edges; i++) {
            String[] edge = scan.nextLine().split(" ");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);

            graph.get(from).add(to);
        }

        int startNode = Integer.parseInt(scan.nextLine().trim());
        int destination = Integer.parseInt(scan.nextLine().trim());


        dfs(graph, startNode, destination);

        StringBuilder result = new StringBuilder();
        for (Integer curr : path) {
            result.append(curr).append(" ");
        }

        System.out.println(result.toString().trim());

        result = new StringBuilder();

        for (int i = 1; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                result.append(i).append(" ");
            }
        }

        System.out.println(result.toString().trim());

    }

    private static void dfs(Map<Integer, Set<Integer>> graph, int current, int destination) {
        Set<Integer> linkedNodes = graph.get(current);
        isVisited[current] = true;
        if (!isFound) {
            path.add(current);
            if (current == destination) {
                isFound = true;
            }
        }

        for (Integer linkedNode : linkedNodes) {
            if (!isVisited[linkedNode]) {
                visitedNodes.add(current);

                dfs(graph, linkedNode, destination);
            }
        }
    }
}
