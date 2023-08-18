import java.util.*;

public class _3_BlackMesa {
    private static boolean[] isVisited;
    private static List<Integer> path = new ArrayList<>();
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
            String[] edge = scan.nextLine().split("\\s+");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);
            graph.get(from).add(to);
        }

        int source = Integer.parseInt(scan.nextLine().trim());
        int dest = Integer.parseInt(scan.nextLine().trim());

        isVisited[source] = true;

        path.add(source);

        dfs(graph, source, dest);

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

    private static void dfs(Map<Integer, Set<Integer>> graph, int current, int dest) {
        //path.add(current);

        /*if (current == dest) {
            isFound = true;
            return;
        }*/

        Set<Integer> linkedNodes = graph.get(current);

        for (Integer childNode : linkedNodes) {
            if (!isVisited[childNode]) {
                isVisited[childNode] = true;
                if (childNode == dest && !isFound) {
                    path.add(childNode);
                    isFound = true;
                } else if (!isFound){
                    path.add(childNode);
                }
                dfs(graph, childNode, dest);
                if (!isFound && isVisited[childNode]) {
                    path.remove(path.size() - 1);
                }
            }
        }
    }

}
