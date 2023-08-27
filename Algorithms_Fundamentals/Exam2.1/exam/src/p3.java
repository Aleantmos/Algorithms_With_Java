import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodesCnt = Integer.parseInt(reader.readLine());
        int edgesCnt = Integer.parseInt(reader.readLine());

        boolean[][] graph = new boolean[nodesCnt][nodesCnt];
        boolean[][] reversedGraph = new boolean[nodesCnt][nodesCnt];

        for (int i = 0; i < edgesCnt; i++) {
            int[] tokens = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int from = tokens[0];
            int to = tokens[1];

            graph[from][to] = true;
            reversedGraph[to][from] = true;
        }

        boolean[] visited = new boolean[graph.length];

        List<List<Integer>> stronglyConnected = new ArrayList<>();
        List<List<String>> output = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int node = 0; node < graph.length; node++) {
            if (!visited[node]) {
                dfs(node, visited, graph, stack);
            }
        }


        Arrays.fill(visited, false);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (!visited[node]) {
                stronglyConnected.add(new ArrayList<>());
                output.add(new ArrayList<>());
                reversedDfs(node, visited, reversedGraph, stronglyConnected, output, -1);
            }
        }


        Arrays.fill(visited, false);

        int indexMaxSize = -1;
        int maxSize = 0;

        for (int i = 0; i < stronglyConnected.size(); i++) {
            List<Integer> integers = stronglyConnected.get(i);

            if (maxSize < integers.size()) {
                maxSize = integers.size();
                indexMaxSize = i;
            }
        }

        List<Integer> integers = stronglyConnected.get(indexMaxSize);

        for (Integer curr : integers) {
            for (Integer next : integers) {
                if (graph[curr][next]) {
                    System.out.println(curr + " -> " + next);
                }
            }
        }
    }


    private static void reversedDfs(int node,
                                    boolean[] visited,
                                    boolean[][] reversedGraph,
                                    List<List<Integer>> stronglyConnected, List<List<String>> output, int helper) {
        if (!visited[node]) {

            visited[node] = true;

            if (helper != -1) {
                output.get(output.size() - 1).add(helper + " -> " + node);
            }
            stronglyConnected.get(stronglyConnected.size() - 1).add(node);


            for (int i = 0; i < reversedGraph[node].length; i++) {
                if (reversedGraph[node][i]) {
                    reversedDfs(i, visited, reversedGraph, stronglyConnected, output, node);
                }
            }
        }
    }


    private static void dfs(int node, boolean[] visited, boolean[][] graph, Deque<Integer> stack) {
        if (!visited[node]) {

            visited[node] = true;
            for (int child = 0; child < graph[node].length; child++) {
                if (graph[node][child]) {
                    dfs(child, visited, graph, stack);
                }
            }
            stack.push(node);
        }
    }

    private static void dfsOutput(int node, boolean[] visited, boolean[][] graph) {
        if (!visited[node]) {

            visited[node] = true;
            for (int child = 0; child < graph[node].length; child++) {
                if (graph[node][child]) {
                    dfsOutput(child, visited, graph);
                }
            }
        }
    }
}
