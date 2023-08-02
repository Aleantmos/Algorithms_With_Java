import java.util.Arrays;
import java.util.Scanner;

public class _4_RectangleIntersection {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        int[][] matrix = new int[2001][2001];
        for (int i = 0; i < n; i++) {
            int[] coordinates = Arrays.stream(scan.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int minX = coordinates[0] + 1000;
            int maxX = coordinates[1] + 1000;
            int minY = coordinates[3] + 1000;
            int maxY = coordinates[4] + 1000;

            for (int x = minX; x < maxX; x++) {
                for (int y = minY; y < maxY; y++) {
                    matrix[y][x] += 1;
                }
            }
        }
        int result = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] > 1) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
