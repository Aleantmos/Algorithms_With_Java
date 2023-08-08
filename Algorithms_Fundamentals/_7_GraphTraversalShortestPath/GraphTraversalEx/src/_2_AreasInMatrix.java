import com.sun.tools.javac.Main;

import java.util.*;

public class _2_AreasInMatrix {

    static class Edge {
        private int row;
        private int col;

        public Edge(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        char[][] graph = new char[n][];
        boolean[][] visited = new boolean[n][];

        for (int i = 0; i < n; i++) {
            graph[i] = scan.nextLine().toCharArray();
            visited[i] = new boolean[graph[i].length];
        }


        Map<Character, Integer> areas = new HashMap<>();

        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {
                if (!visited[row][col]) {
                    char currSymbol = graph[row][col];
                    areas.putIfAbsent(currSymbol, 0);
                    areas.put(currSymbol, areas.get(currSymbol) + 1);
                    bfs(row, col, graph, visited, areas, graph[row][col]);
                }
            }
        }

        int total = 0;

        for (Character key : areas.keySet()) {
            total += areas.get(key);
        }
        System.out.println("Areas: " + total);

        for (Character character : areas.keySet()) {
            System.out.printf("Letter '%s' -> %d%n", character, areas.get(character));
        }

    }

    private static void bfs(int row, int col, char[][] graph, boolean[][] visited, Map<Character, Integer> areas, char currSymbol) {
        Deque<Edge> queue = new ArrayDeque<>();

        Edge edge = new Edge(row, col);

        queue.offer(edge);

        while (!queue.isEmpty()) {
            Edge node = queue.poll();

            visited[node.row][node.col] = true;

            if (isInBound(node.row + 1, node.col, graph) && !visited[node.row + 1][node.col] && graph[node.row + 1][node.col] == currSymbol) {
                Edge child = new Edge(node.row + 1, node.col);

                queue.offer(child);
            }
            if (isInBound(node.row, node.col + 1, graph) && !visited[node.row][node.col + 1] && graph[node.row][node.col + 1] == currSymbol) {
                Edge child = new Edge(node.row, node.col + 1);

                queue.offer(child);
            }
            if (isInBound(node.row - 1, node.col, graph) && !visited[node.row - 1][node.col] && graph[node.row - 1][node.col] == currSymbol) {
                Edge child = new Edge(node.row - 1, node.col);

                queue.offer(child);
            }
            if (isInBound(node.row, node.col - 1, graph) && !visited[node.row][node.col - 1] && graph[node.row][node.col - 1] == currSymbol) {
                Edge child = new Edge(node.row , node.col - 1);

                queue.offer(child);
            }
        }
    }

    private static boolean isInBound(int row, int col, char[][] graph) {
        return row >= 0 && row < graph.length && col >= 0 && col < graph[row].length;
    }

    /*public static class Edge {
        int[] source;
        int[] destination;

        Edge(int sRow, int sCol, int dRow, int dCol) {
            this.source = new int[]{sRow, sCol};
            this.destination = new int[]{dRow, dCol};
        }
    }


    public static List<Edge> graph = new ArrayList<>();
    public static char[][] matrix;
    public static boolean[][] visited;

    public static boolean[] visitedNode;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int rows = Integer.parseInt(scan.nextLine());

        matrix = new char[rows][];
        visited = new boolean[rows][];

        for (int row = 0; row < rows; row++) {
            matrix[row] = scan.nextLine().toCharArray();
            visited[row] = new boolean[matrix[row].length];
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (!visited[i][j]) {
                    dfs(i, j, matrix[i][j]);
                }
            }
        }

        visitedNode = new boolean[graph.size()];

        Map<Character, Integer> areas = new TreeMap<>();
        for (int i = 0; i < graph.size(); i++) {
            if(!visitedNode[i]) {
                Edge edge = graph.get(i);
                char key = _2_AreasInMatrix.matrix[edge.source[0]][edge.source[1]];
                areas.putIfAbsent(key, 0);
                areas.put(key, areas.get(key) + 1);
                bfs(i);
            }
        }
    }

    private static void bfs(int source) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        visitedNode[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            Edge edge = graph.get(node);
            if (edge.destination != null) {
                visitedNode[node + 1] = true;
                queue.offer(node + 1);
            }
        }
    }

    private static void dfs(int row, int col, char areaSymbol) {
        if (isOutOfBounds(row, col) || visited[row][col] || matrix[row][col] != areaSymbol) {
            return;
        }

        visited[row][col] = true;

        if (inBounds(row, col + 1) && !visited[row][col + 1] || matrix[row][col + 1] == areaSymbol) {
            graph.add(new Edge(row, col, row, col + 1));
            dfs(row, col + 1, areaSymbol);
        }
        if (inBounds(row, col - 1) && !visited[row][col - 1] || matrix[row][col - 1] == areaSymbol) {
            graph.add(new Edge(row, col, row, col - 1));
            dfs(row, col - 1, areaSymbol);
        }
        if (inBounds(row + 1, col) && !visited[row + 1][col] || matrix[row + 1][col] == areaSymbol) {
            graph.add(new Edge(row, col, row + 1, col));
            dfs(row + 1, col, areaSymbol);
        }
        if (inBounds(row - 1, col) && !visited[row - 1][col] || matrix[row - 1][col] == areaSymbol) {
            graph.add(new Edge(row, col, row - 1, col + 1));
            dfs(row - 1, col, areaSymbol);
        }
    }

    private static boolean inBounds(int row, int col) {
        return !isOutOfBounds(row, col);
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || matrix[row].length >= col;
    }*/
}
