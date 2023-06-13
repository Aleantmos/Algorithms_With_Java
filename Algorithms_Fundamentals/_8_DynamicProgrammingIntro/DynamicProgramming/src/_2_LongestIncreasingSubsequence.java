import java.util.Arrays;
import java.util.Scanner;

public class _2_LongestIncreasingSubsequence {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int[] sequence = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] length = new int[sequence.length];

        for (int i = 0; i < sequence.length; i++) {
            int curr = sequence[i];
            int bestLength = 1;

            for (int j = i - 1; j >= 0; j-- ) {
                if (sequence[j] < curr && length[j] + 1 > bestLength) {
                    bestLength = length[j] + 1;
                }
            }
            length[i] = bestLength;
        }
        System.out.println(Arrays.stream(length).max().getAsInt());
    }
}
