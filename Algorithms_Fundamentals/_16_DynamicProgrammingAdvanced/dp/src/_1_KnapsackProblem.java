import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _1_KnapsackProblem {
    public static List<Integer> weights = new ArrayList<>();
    public static List<Integer> prices = new ArrayList<>();
    public static int[][] dp;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int capacity = Integer.parseInt(scan.nextLine());

        String line = scan.nextLine();

        while (!line.equals("end")) {

            String[] tokens = line.split("\\s+");

            weights.add(Integer.parseInt(tokens[1]));
            prices.add(Integer.parseInt(tokens[2]));


            line = scan.nextLine();
        }

        dp = new int[prices.size() + 1][capacity + 1];

        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        int result = recurrence(0, 0, capacity);

        System.out.println(result);
    }


    public static int recurrence(int valueIndex, int weightIndex, int capacity) {
        if (valueIndex >= prices.size() || weightIndex >= weights.size() || weights.get(weightIndex) > capacity) {
            return 0;
        }

        if (dp[valueIndex][capacity] != -1) {
            return dp[valueIndex][capacity];
        }

        return dp[valueIndex][capacity] = Math.max(recurrence(valueIndex + 1, weightIndex + 1, capacity),
                recurrence(valueIndex + 1, weightIndex + 1, capacity - weights.get(weightIndex)
                        + prices.get(valueIndex)));
    }
}
