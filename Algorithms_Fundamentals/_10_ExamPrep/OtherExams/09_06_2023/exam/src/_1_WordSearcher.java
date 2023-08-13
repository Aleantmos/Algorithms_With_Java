import java.util.*;

public class _1_WordSearcher {

    //chatGPT
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int rows = Integer.parseInt(scan.nextLine());
        int cols = Integer.parseInt(scan.nextLine());

        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = scan.nextLine().split("");
        }

        String[] words = scan.nextLine().split("\\s+");
        
        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (searchWord(matrix, word)) {
                result.add(word);
            }
        }

        for (String element : result) {
            System.out.println(element);
        }
    }

    private static boolean searchWord(String[][] matrix, String word) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col].equals(word.substring(0, 1))) {
                    boolean[][] visited = new boolean[rows][cols];
                    if (dfs(row, col, matrix, word, 0, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean dfs(int row, int col, String[][] matrix, String word, int index, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }

        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length) {
            return false;
        }

        if (!matrix[row][col].equals(word.substring(index, index + 1))) {
            return false;
        }
        visited[row][col] = true;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // Skip the current cell
                }
                if (dfs(row + i, col + j, matrix, word, index + 1, visited)) {
                    return true;
                }
            }
        }

        visited[row][col] = false; // Unmark cell

        return false;
    }

    /*

        me -> debug it...

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int rows = Integer.parseInt(scan.nextLine());
        int cols = Integer.parseInt(scan.nextLine());

        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = scan.nextLine().split("");
        }

        String[] words = scan.nextLine().split("\\s+");

        List<String> result = new ArrayList<>();

        boolean flag = false;

        for (String word : words) {
            String[] letters = word.split("");
            int letterCnt = 0;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    String curr = matrix[row][col];

                    if (curr.equals(letters[letterCnt])) {
                        int startRow = Math.max(0, row - 1);
                        int startCol = Math.max(0, col - 1);

                        int endRow = Math.min(rows, row + 1);
                        int endCol = Math.min(cols, col + 1);
                        letterCnt += 1;
                        boolean found = false;
                        flag = dfs(startRow, endRow, startCol, endCol, letters, letterCnt, matrix, found);
                    }

                    if (flag) {
                        result.add(word);
                        break;
                    }

                }
                if (flag) {
                    flag = false;
                    break;
                }
            }
        }

        for (String element : result) {
            System.out.println(element);
        }

    }

    private static boolean dfs(int startRow,
                               int endRow,
                               int startCol,
                               int endCol,
                               String[] letters,
                               int letterCnt,
                               String[][] matrix,
                               boolean found) {
        String curr = letters[letterCnt];

        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                if (matrix[row][col].equals(curr)) {
                    if (letterCnt + 1 != letters.length) {
                        startRow = Math.max(0, row - 1);
                        startCol = Math.max(0, col - 1);

                        endRow = Math.min(matrix.length - 1, row + 1);
                        endCol = Math.min(matrix[row].length - 1, col + 1);

                        letterCnt += 1;
                        boolean result = dfs(startRow, endRow, startCol, endCol, letters, letterCnt, matrix, found);
                        return result;
                    } else {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                break;
            }
        }
        return found;
    }

    */

}
