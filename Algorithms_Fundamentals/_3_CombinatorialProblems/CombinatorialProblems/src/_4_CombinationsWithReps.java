import java.util.Scanner;

public class _4_CombinationsWithReps {
    public static String[] elements;
    public static String[] combinations;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        elements = scan.nextLine().split("\\s+");

        int k = Integer.parseInt(scan.nextLine());

        combinations = new String[k];

        getCombinations(0, 0);
    }

    private static void getCombinations(int index, int start) {
        if (index == combinations.length) {
            print(combinations);
            return;
        }

        for (int i = start; i < elements.length; i++) {
            combinations[index] = elements[i];
            getCombinations(index + 1, i);
        }
    }

    private static void print(String[] combinations) {
        System.out.println(String.join(" ", combinations));
    }
}
