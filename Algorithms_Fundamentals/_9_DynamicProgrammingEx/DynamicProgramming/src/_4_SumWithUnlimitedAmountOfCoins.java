import java.util.Arrays;
import java.util.Scanner;

public class _4_SumWithUnlimitedAmountOfCoins {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] coins = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int targetSum = Integer.parseInt(scan.nextLine());

        int[] dp = new int[targetSum  + 1];

        dp[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= targetSum; j++) {
                 dp[j] += dp[j - coins[i]] ;
            }
        }

        System.out.println(dp[targetSum]);
    }
}
