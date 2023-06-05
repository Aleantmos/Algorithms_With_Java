import java.util.Scanner;

public class _1_PermutationWithoutRepetition_Swap {
    /*
    A B C
     */
    public static String[] elements;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        elements = scan.nextLine().split("\\s+");

        permute(0);
    }

    private static void permute(int index) {
        if (index == elements.length) {
            print(elements);
            return;
        }
        permute(index + 1);

        for (int i = index + 1; i < elements.length; i++) {
            swap(elements, index, i);
            permute(index + 1);
            swap(elements, index, i);
        }

    }

    private static void swap(String[] arr, int first, int second) {
        String temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
