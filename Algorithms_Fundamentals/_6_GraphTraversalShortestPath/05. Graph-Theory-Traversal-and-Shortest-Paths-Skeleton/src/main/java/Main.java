import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String input = scan.nextLine();

            if (input.trim().equals("")) {
                graph.add(new ArrayList<>());
            } else {
                List<Integer> nextNodes = Arrays.stream(input.split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                graph.add(nextNodes);
            }
        }
        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);

        for (Deque<Integer> connectedComponent : connectedComponents) {
            System.out.print("Connected component: ");
            for (Integer integer : connectedComponent) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }

    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {

        boolean[] visited = new boolean[graph.size()];

        List<Deque<Integer>> components = new ArrayList<>();

        for (int start = 0; start < graph.size(); start++) {
            if (!visited[start]) {
                //bfs(start, components, graph, visited);
                dfs(start, components, graph, visited);
            }
        }

        return components;

    }

    private static void bfs(int start, List<Deque<Integer>> components, List<List<Integer>> graph, boolean[] visited) {
        Deque<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            Integer parent = queue.poll();

            components.get(components.size() - 1).offer(parent);

            for (Integer child : graph.get(parent)) {
                if (!visited[child]) {
                    components.add(new ArrayDeque<>());
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }
    }

    private static void dfs(int node, List<Deque<Integer>> components, List<List<Integer>> graph, boolean[] visited) {

        if (!visited[node]) {
            visited[node] = true;
            for (Integer child : graph.get(node)) {
                dfs(child, components, graph, visited);
            }
            components.get(components.size() - 1).offer(node);
        }

    }

/*      TOPOLOGICAL SORTING

    private static Map<String, Integer> getDependenciesCnt(Map<String, List<String>> graph) {

        Map<String, Integer> depCnt = new LinkedHashMap<>();

        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            depCnt.putIfAbsent(node.getKey(), 0);
            for (String child : node.getValue()) {
                depCnt.putIfAbsent(child, 0);
                depCnt.put(child, depCnt.get(child) + 1);
            }
        }
        return depCnt;
    }

    public static List<String> topSort(Map<String, List<String>> graph) {

        Map<String, Integer> dependenciesCnt = getDependenciesCnt(graph);

        List<String> sorted = new ArrayList<>();

        while (!graph.isEmpty()) {
            String key = graph.keySet()
                    .stream()
                    .filter(k -> dependenciesCnt.get(k) == 0)
                    .findFirst()
                    .orElse(null);

            if (key == null) {
                break;
            }
            for (String child : graph.get(key)) {
                dependenciesCnt.put(child, dependenciesCnt.get(child) - 1);
            }

            sorted.add(key);
            graph.remove(key);
        }

        if (!graph.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return sorted;
    }
    */

    /*TOPOLOGICAL SORTING WITH DFS*/

    public static List<String> topSort(Map<String, List<String>> graph) {

        List<String> sorted = new ArrayList<>();

        Set<String> visited = new HashSet<>();
        Set<String> detectCycles = new HashSet<>();

        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            dfsTop(node.getKey(), visited, graph, sorted, detectCycles);
        }

        Collections.reverse(sorted);

        return sorted;
    }


    public static void dfsTop(String key, Set<String> visited, Map<String, List<String>> graph, List<String> sorted, Set<String> detectCycles) {
        if (detectCycles.contains(key)) {
            throw new IllegalArgumentException();
        }

        if (!visited.contains(key)) {
            visited.add(key);
            detectCycles.add(key);

            for (String child : graph.get(key)) {
                dfsTop(child, visited, graph, sorted, detectCycles);
            }
            detectCycles.remove(key);
            sorted.add(key);
        }
    }
}
