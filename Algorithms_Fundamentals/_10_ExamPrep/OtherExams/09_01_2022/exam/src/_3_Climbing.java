import java.util.*;

public class _3_Climbing {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        int m = Integer.parseInt(scan.nextLine());

        int[][] graph = new int[n][m];



        for (int i = 0; i < n; i++) {
            graph[i] = Arrays.stream(scan.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[][] helper = new int[n][m];

        helper[n - 1][m - 1] = graph[n - 1][m - 1];

        for (int i = m - 2; i >= 0; i--) {
            helper[n - 1][i] = graph[n - 1][i] + helper[n - 1][i + 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            helper[i][m - 1] = graph[i][m - 1] + helper[i + 1][m - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                helper[i][j] = Math.max(graph[i][j] + helper[i + 1][j],
                        graph[i][j] + helper[i][j + 1]);
            }
        }



        int row = 0;
        int col = 0;
        int total = 0;
        List<Integer> path = new ArrayList<>();

        total += graph[row][col];
        path.add(graph[row][col]);

        while (row < n - 1 || col < m - 1) {

            int down = -1;

            if (row < n - 1) {
                down = helper[row + 1][col];
            }

            int right = -1;

            if (col < m - 1) {
                right = helper[row][col + 1];
            }

            if (down >= right) {
                row++;
            } else {
                col++;
            }

            total += graph[row][col];
            path.add(graph[row][col]);
        }


        System.out.println(total);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(path.size() - 1 - i)).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}
