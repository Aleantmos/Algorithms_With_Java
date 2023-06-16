import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class _5_SortingWithCounting {
    public static int[] count;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
/*
        int[] arr = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
*/
        int[] arr ={13, 5, 2, 2, 5};

        Integer max = Collections.max(Arrays.asList(13, 5, 2, 2, 5));

        count = new int[max + 1];

        sort(arr);

        for (int index = 0; index < count.length; index++) {
            if (count[index] != 0) {
                for (int i = 0; i < count[index]; i++) {
                    System.out.print(index + " ");
                }
            }
        }
    }

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
    }
}
