import java.util.Arrays;
import java.util.Scanner;

public class _3_BubbleSort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] arr = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        sort(arr);

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    private static void sort(int[] arr) {
        for (int i = 0; i <= arr.length - 2; i++) {
            boolean swapped = false;
            int k = i + 1;
            while (k < arr.length) {
                if (arr[i] > arr[k]) {
                    swap(arr, i, k);
                    swapped = true;
                }
                k++;
            }
            if (!swapped) {
                break;
            }
        }
    }

    private static void swap(int[] arr, int i, int k) {
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }


    /*private static void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int min) {
        int temp = arr[i];
        arr[i] = arr[min];
        arr[min] = temp;
    }*/
}
