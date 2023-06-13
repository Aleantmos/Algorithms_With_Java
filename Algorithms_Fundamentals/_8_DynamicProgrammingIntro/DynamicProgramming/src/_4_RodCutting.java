import java.util.Arrays;
import java.util.Scanner;

public class _4_RodCutting {
    public static int[] bestPrices;
    public static int[] prices;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        prices = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int length = Integer.parseInt(scan.nextLine());

        bestPrices = new int[length + 1];

        int maxProfit = cutRope(length);

        System.out.println(maxProfit);
    }

    private static int cutRope(int length) {
        if (length == 0) {
            return 0;
        }

        if (bestPrices[length] != 0) {
            return bestPrices[length];
        }

        int currentBest = bestPrices[length];

        for (int i = 1; i < length; i++) {
            int localMax = Math.max(currentBest, prices[i] + cutRope(length - i));

            if (localMax > currentBest) {
                currentBest = localMax;
                bestPrices[length] = currentBest;
            }
        }

        return bestPrices[length];
    }
}
