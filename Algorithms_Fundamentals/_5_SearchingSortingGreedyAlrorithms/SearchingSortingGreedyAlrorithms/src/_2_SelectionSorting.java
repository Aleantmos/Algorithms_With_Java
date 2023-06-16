import java.util.Arrays;
import java.util.Scanner;

public class _2_SelectionSorting {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] arr = Arrays.stream(scan.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        sort(arr);

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length ; i++) {
            int min = i;
            for (int j = min + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, i, min);
            }
        }
    }

    private static void swap(int[] arr, int i, int min) {
        int temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
    }
}
