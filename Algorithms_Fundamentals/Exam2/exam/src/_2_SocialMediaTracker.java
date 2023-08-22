import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2_SocialMediaTracker {

    // not my solution but awesome nonetheless
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int edges = Integer.parseInt(reader.readLine());
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        Map<String, int[]> distAndHops = new HashMap<>();

        for (int i = 0; i < edges; i++) {
            String[] tokens = reader.readLine().split("\\s+");

            String from = tokens[0];
            String to = tokens[1];
            int influence = Integer.parseInt(tokens[2]);


            graph.putIfAbsent(from, new HashMap<>());
            graph.get(from).put(to, influence);

            distAndHops.put(from, new int[]{Integer.MAX_VALUE, 0});
            distAndHops.put(to, new int[]{Integer.MAX_VALUE, 0});
        }

        String source = reader.readLine();
        String dest = reader.readLine();

        distAndHops.get(source)[0] = 0;

        Set<String> visited = new HashSet<>();

        ArrayDeque<String> stackOfNodes = new ArrayDeque<>();
        for (String node : graph.keySet()) {
            topSorts(node, stackOfNodes, visited, graph);
        }

        while (!stackOfNodes.isEmpty()) {
            String parent = stackOfNodes.poll();
            Map<String, Integer> children = graph.get(parent);

            if (children != null) {
                for (String child : children.keySet()) {
                    int influence = children.get(child);

                    int newInf = distAndHops.get(parent)[0] + influence;
                    int currInf = distAndHops.get(child)[0];

                    int newHops = distAndHops.get(parent)[1] + 1;
                    int currHops = distAndHops.get(child)[1];

                    if (newInf > currInf || (newInf == currInf && newHops < currHops)) {
                        distAndHops.get(child)[0] = newInf;
                        distAndHops.get(child)[1] = newHops;
                    }
                }
            }
        }
        System.out.printf("(%d, %d)%n", distAndHops.get(dest)[0],
                distAndHops.get(dest)[1]);
    }

    private static void topSorts(String node,
                                 ArrayDeque<String> stackOfNodes,
                                 Set<String> visited, Map<String, Map<String, Integer>> graph) {

        if (visited.contains(node)) {
            return;
        }

        visited.add(node);

        Map<String, Integer> edges = graph.get(node);

        if (edges != null) {

            for (String child : edges.keySet()) {
                topSorts(child, stackOfNodes, visited, graph);
            }

            stackOfNodes.push(node);
        }
    }
}





    /*public static class Edge {
        String to;
        int dist;

        public Edge(String to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    public static int maxHops = Integer.MAX_VALUE;
    public static int maxDest = 0;

    public static int currHops = 0;
    public static int currDest = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int edgesCnt = Integer.parseInt(reader.readLine());

        Map<String, List<Edge>> edges = new HashMap<>();

        Map<String, Boolean> visited = new HashMap<>();

        for (int i = 0; i < edgesCnt; i++) {

            String[] tokens = reader.readLine().split(" ");

            String from = tokens[0];
            String to = tokens[1];
            int dist = Integer.parseInt(tokens[2]);

            edges.putIfAbsent(from, new ArrayList<>());
            edges.get(from).add(new Edge(to, dist));

            //edges.putIfAbsent(to, new ArrayList<>());
            //edges.get(to).add(new Edge(from, dist));

            visited.put(from, false);
            visited.put(to, false);
        }

        String source = reader.readLine();
        String dest = reader.readLine();

        bfs(edges, source, dest, visited);

        System.out.println("(" + maxDest + ", " + maxHops + ")");

}

    private static void bfs(Map<String, List<Edge>> edges, String node, String dest, Map<String, Boolean> visited) {
        if (node.equals(dest)) {
            if (currDest > maxDest) {
                maxHops = currHops;
                maxDest = currDest;

                currHops = 0;
                currDest = 0;
            } else if (currDest == maxDest && currHops < maxHops) {
                maxHops = currHops;
                maxDest = currDest;

                currHops = 0;
                currDest = 0;
            }
            return;
        }

        if (!visited.get(node)) {
            visited.put(node, true);
            List<Edge> children = edges.get(node);
            for (int i = 0; i < children.size(); i++) {
                String child = children.get(i).to;
                int dist = children.get(i).dist;
                currHops++;
                currDest += dist;
                bfs(edges, child, dest, visited);
                visited.put(child, false);
            }
        }

    }*/

