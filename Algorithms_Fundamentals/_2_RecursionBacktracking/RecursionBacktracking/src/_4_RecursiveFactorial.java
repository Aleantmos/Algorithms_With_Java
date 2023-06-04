import java.util.Scanner;

public class _4_RecursiveFactorial {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

d        long result = calculateFactorial(n);

        System.out.println(result);

    }

    private static long calculateFactorial(int n) {
        if (n == 1) {
            return 1;
        }

        return n * calculateFactorial(n - 1);
    }
}
