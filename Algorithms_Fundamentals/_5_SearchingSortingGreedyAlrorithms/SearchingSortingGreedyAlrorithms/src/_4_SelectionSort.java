import java.util.Arrays;
import java.util.Scanner;

public class _4_SelectionSort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] arr = Arrays.stream(scan.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        selectionSort(arr);

        for (int element : arr) {
            System.out.print(element + " ");
        }
    }


    private static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
               if (arr[j] < arr[min]) {
                   min = j;
               }
            }
            if (arr[i] > arr[min]) {
                swap(arr, i, min);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
