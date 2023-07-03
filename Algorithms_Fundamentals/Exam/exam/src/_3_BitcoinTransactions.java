import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class _3_BitcoinTransactions {
    public static int[] trans1;
    public static int[] trans2;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input1 = scan.nextLine();
        String[] parts = input1.split("\\s+");

        trans1 = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            String substring = part.substring(2);

            trans1[i] = Integer.parseInt(substring);
        }

        String input2 = scan.nextLine();
        parts = input2.split("\\s+");

        trans2 = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            String substring = part.substring(2);

            trans2[i] = Integer.parseInt(substring);
        }
        int maxCnt = Math.max(trans1.length + 1, trans2.length + 1);
        int minCnt = Math.max(trans1.length + 1, trans2.length + 1);

        int[][] dp = new int[minCnt][minCnt];
        int[][] prev = new int[Math.max(trans1.length + 1, trans2.length + 1)][2];

        int[] longer;
        int[] shorter;

        if (trans1.length >= trans2.length) {
            longer = trans1;
            shorter = trans2;
        } else {
            longer = trans1;
            shorter = trans2;
        }

        for (int i = 0; i < longer.length; i++) {
            for (int j = 0; j < shorter.length; j++) {
                if (longer[i] == shorter[j]) {
                    dp[i][j] = dp[i][j] + 1;
                }
            }
        }

        Set<Integer> resultSet = new HashSet<>();

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (dp[i][j] == 1) {
                    resultSet.add(longer[i]);
                }
            }

        }

        System.out.print("[");
        StringBuilder result = new StringBuilder();

        for (Integer element : resultSet) {
            result.append("tx").append(element).append(" ");

        }
        System.out.print(result.toString().trim());
        System.out.print("]");

    }
}
