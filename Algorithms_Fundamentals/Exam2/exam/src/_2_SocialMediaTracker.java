import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2_SocialMediaTracker {
    public static class Edge {
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

    }
}
