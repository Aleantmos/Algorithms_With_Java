import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class _3_Code {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr1 = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] arr2 = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] dp = new int[arr1.length + 1][arr2.length + 1];

        for (int row = 1; row <= arr1.length; row++) {
            for (int col = 1; col <= arr2.length; col++) {
                if (arr1[row - 1] == arr2[col - 1]) {
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                } else {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                }
            }
        }

        int row = dp.length - 1, col = dp[row].length - 1;

        Deque<Integer> result = new ArrayDeque<>();

        while (row > 0 && col > 0) {
            if (arr1[row - 1] == arr2[col - 1]) {
                result.push(arr1[row - 1]);
                row--;
                col--;
            } else if (dp[row - 1][col] >= dp[row][col - 1]) {
                row--;
            } else {
                col--;
            }
        }




        StringBuilder sb = new StringBuilder();

        while (!result.isEmpty()) {
            sb.append(result.pop()).append(" ");
        }

        System.out.println(sb.toString().trim());

        System.out.println(dp[arr1.length][arr2.length]);

    }
}
