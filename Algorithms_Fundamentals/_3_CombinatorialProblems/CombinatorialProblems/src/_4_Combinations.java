import java.util.Scanner;

public class _4_Combinations {

    public static String[] elements;
    public static String[] variations;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        elements = scan.nextLine().split("\\s+");

        int k = Integer.parseInt(scan.nextLine());

        variations = new String[k];


        combinations(0, 0);
    }

    private static void combinations(int index, int start) {

        if (index == variations.length) {
            print(variations);
        } else {
            for (int i = start; i < elements.length; i++) {
                variations[index] = elements[i];
                combinations(index + 1, i + 1);
            }
        }

    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
