import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] prices = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int length = Integer.parseInt(reader.readLine());

        int[] bestPrices = new int[length + 1];

        int maxProfit = getMaxProfit(length, bestPrices, prices);

        System.out.println(maxProfit);
    }

    private static int getMaxProfit(int length, int[] bestPrices, int[] prices) {

        if (length == 0) {
            return 0;
        }

        if (bestPrices[length] != 0) {
            return bestPrices[length];
        }

        int currBest = bestPrices[length];

        for (int i = 1; i <= length; i++) {
            int localMax = Math.max(currBest, prices[i - 1] + getMaxProfit(length - i, bestPrices, prices));

            if (localMax > currBest) {
                currBest = localMax;
                bestPrices[length] = currBest;
            }
        }
        return bestPrices[length];
    }
}
