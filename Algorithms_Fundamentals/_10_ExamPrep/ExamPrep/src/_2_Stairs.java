import java.util.Arrays;
import java.util.Scanner;

public class _2_Stairs {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int steps = Integer.parseInt(scan.nextLine());


        int[] memory = new int [steps + 1];

        Arrays.fill(memory, 0);

        memory[1] = 1;
        memory[2] = 2;

        System.out.println(climbSteps(steps, memory));

    }

    private static int climbSteps(int steps, int[] memory) {
        if (memory[steps] != 0) {
            return memory[steps];
        }
        if (steps == 1) {
            return 1;
        }

        if (steps == 2) {
            return 2;
        }

        int result = climbSteps(steps - 1, memory) + climbSteps(steps - 2, memory);

        memory[steps] = result;

        return result;
    }
}
