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
        int inputLength = arr.length;

        if (inputLength < 2) {
            return;
        }
        int midIndex = inputLength / 2;

        int[] left = new int[midIndex];
        int[] right = new int[inputLength - midIndex];

        for (int i = 0; i < midIndex; i++) {
            left[i] = arr[i];
        }

        for (int i = midIndex; i < inputLength; i++) {
            right[i - midIndex] = arr[i];
        }

        sort(left);
        sort(right);

        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int leftSize = left.length;
        int rightSize = right.length;

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftSize && j < rightSize) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else  {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            arr[k] = left[j];
            j++;
            k++;
        }



    }
}



    /*private static void sort(int[] arr) {
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
    }*/

