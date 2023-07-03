import java.util.*;

public class _3_BlackMesa {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        int k = Integer.parseInt(scan.nextLine());

        int[][] graph = new int[n + 1][n + 1];

        for (int i = 0; i < k; i++) {
            graph[Integer.parseInt(scan.nextLine())][Integer.parseInt(scan.nextLine())] = 1;
        }

        int source = Integer.parseInt(scan.nextLine());
        int dest = Integer.parseInt(scan.nextLine());

        boolean[] visited = new boolean[n + 1];

        ArrayList<Integer> path = new ArrayList<>();

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);

        boolean isNotFound = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;

            if (isNotFound) {
                path.add(node);
                isNotFound = node != dest;
            }

            for (int i = 0; i < graph[node].length; i++) {
                if (graph[node][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }

        StringBuilder out = new StringBuilder();

        for (Integer node : path) {
            out.append(node).append(" ");
        }

        out.append(System.lineSeparator());

        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]) {
                out.append(i).append(" ");
            }
        }
        System.out.println(out.toString().trim());
    }
}
