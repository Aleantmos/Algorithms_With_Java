import java.util.Arrays;
import java.util.Scanner;

public class _2_Stairs {
    private static long[] memory;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int stairs = Integer.parseInt(scan.nextLine());


        if (stairs == 0) {
            System.out.println(0);
            return;
        }
        memory = new long[stairs + 1];

        Arrays.fill(memory, -1);

        System.out.println(climbStares(stairs));
    }

    private static long climbStares(int stairs) {
        if (memory[stairs] != 0) {
            return memory[stairs];
        }

        if (stairs == 1) {
            return 1;
        }

        if (stairs == 2) {
            return 2;
        }

        long result = climbStares(stairs - 1) + climbStares(stairs - 2);

        memory[stairs] = result;

        return result;
    }
}
