import java.util.Arrays;
import java.util.Scanner;

public class _1_AlphaDecay {
    public static int[] output;
    public static boolean[] visited;
    public static int[] nums;
    public static int k;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        nums = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        k = Integer.parseInt(scan.nextLine());

        visited = new boolean[nums.length];

        output = new int[k];


        getComb(0);
    }

    private static void getComb(int index) {
        if (index >= k) {
            print();
            return;
        }

        for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    output[index] = nums[i];
                    getComb(index + 1);
                    visited[i] = false;
                }
        }
    }

    private static void print() {
        StringBuilder builder = new StringBuilder();

        for (int curr : output) {
            builder.append(curr).append(" ");
        }
        System.out.println(String.join(" ", builder.toString()));
    }
}
