import java.util.*;
public class _2_NuclearWaste {
    //not my solution - redo again...
    public static int[] prices;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        prices = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rounds = Integer.parseInt(scan.nextLine());

        int[] dp = new int[rounds + 1];
        int[] prev = new int[rounds + 1];

        for (int i = 1; i <= rounds; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                if (j > prices.length) {
                    break;
                }
                int newValue = dp[i - j] + prices[j - 1];

                if (newValue < dp[i]) {
                    dp[i] = newValue;
                    prev[i] = j;
                }
            }
        }
        System.out.println(dp[rounds]);
    }
}
