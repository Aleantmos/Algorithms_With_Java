import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class _3_Time {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] arr1 = Arrays.stream(scan.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] arr2 = Arrays.stream(scan.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] dp = new int[arr1.length + 1][arr2.length + 1];

        for (int i = 1; i <= arr1.length; i++) {
            for (int j = 1; j <= arr2.length; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        Deque<Integer> result = new ArrayDeque<>();

        int r = arr1.length;
        int c = arr2.length;

        while (r > 0 && c > 0) {
            if(arr1[r - 1] == arr2[c - 1]) {
                result.push(arr1[r - 1]);
                r--;
                c--;
            } else if (r > 0 && c == 0) {
                r--;
            } else if (r == 0 && c > 0) {
                c--;
            } else if (dp[r - 1][c] > dp[r][c - 1]) {
                r--;
            } else {
                c--;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int el : result) {
            sb.append(el).append(" ");
        }
        System.out.println(sb.toString().trim());
        System.out.println(dp[arr1.length][arr2.length]);
    }
}
