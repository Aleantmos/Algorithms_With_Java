import java.util.Arrays;
import java.util.Scanner;

public class _1_RecursiveArraySum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int sum = 0;

        sum = sumNumber(arr, arr.length - 1);

        System.out.println("Iteration sum: " + sum);
    }

    public static int sumNumber(int[] numbers, int index) {

        if (index < 0) {
            return 0;
        }

        return numbers[index] + sumNumber(numbers, index - 1);
    }
}
