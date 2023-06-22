import java.util.Arrays;
import java.util.Scanner;

public class _1_AlphaDecay {
    private static String[] values;
    private static boolean[] used;
    private static String[] comboContainer;
    private static int k;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        values = scan.nextLine().split("\\s+");

        k = Integer.parseInt(scan.nextLine());

        comboContainer = new String[k];

        used = new boolean[values.length];

        combinations(0);

    }

    private static void combinations(int index) {
        if (index >= k) {
            System.out.println(String.join(" ", comboContainer));
            //print(comboContainer);
        } else {
            for (int i = 0; i < values.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    comboContainer[index] = values[i];
                    combinations(index + 1);
                    used[i] = false;
                }
            }
        }
    }

    private static void print(int[] comboContainer) {

        for (int i = 0; i < comboContainer.length; i++) {
            System.out.print(comboContainer[i] + " ");
        }
        System.out.println();
    }

}
