import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _2_LongestIncreasingSubsequence {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int[] sequence = { 3, 14, 5, 12, 15, 7, 8, 9, 11, 10, 1};

        int[] length = new int[sequence.length];
        int[] prev = new int[sequence.length];

        Arrays.fill(prev, -1);

        int maxLength = 0;
        int maxIndex = 0;

        for (int i = 0; i < sequence.length; i++) {
            int current = sequence[i];
            int bestLength = 1;
            int bestIndex = - 1;

            for (int j = i - 1; j >= 0; j--) {
                if (sequence[j] < current && length[j] + 1 > bestLength) {
                    bestLength = length[j] + 1;
                    bestIndex = j;
                }
            }

            prev[i] = bestIndex;
            length[i] = bestLength;

            if (maxLength <= bestLength) {
                maxLength = bestLength;
                maxIndex = i;
            }
        }

        List<Integer> LIS = new ArrayList<>();


        int index = maxIndex;

        while (index != -1) {
            LIS.add(sequence[maxIndex]);
            index = prev[maxIndex];
        }

        for (int i = LIS.size() - 1; i >= 0; i--) {
            System.out.println(LIS.get(i) + " ");
        }
    }
}
