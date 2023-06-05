import java.util.Scanner;

public class _1_PermutationsWithoutRepetitions {
    public static String[] elements = {"A", "B", "C"};
    public static String[] permutes = new String[elements.length];
    public static boolean[] used = new boolean[elements.length];

    public static void main(String[] args) {
        permute(0);
    }

    private static void permute(int index) {
        if (index == elements.length) {
            print();
            return;
        }

        for (int i = 0; i < elements.length; i++) {
            if (!used[i]) {
                used[i] = true;
                permutes[index] = elements[i];
                permute(index + 1);
                used[i] = false;
            }
        }
    }

    private static void print() {
        System.out.println(String.join(" ", permutes));
    }
}
