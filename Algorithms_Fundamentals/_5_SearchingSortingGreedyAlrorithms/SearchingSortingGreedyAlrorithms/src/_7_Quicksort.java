import java.util.Arrays;
import java.util.Scanner;

public class _7_Quicksort {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] arr = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();


        sort(arr, 0, arr.length - 1);

        for (int element : arr) {
            System.out.print(element + " ");
        }
    }

    private static void sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = arr[high];

        int leftPointer = low;
        int rightPointer = high;

        while (leftPointer < rightPointer) {

            while (arr[leftPointer] <= pivot && leftPointer < rightPointer ) {
                leftPointer++;
            }

            while (arr[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }

            swap(arr, leftPointer, rightPointer);
        }
        swap(arr, leftPointer, high);
        sort(arr, low, leftPointer - 1);
        sort(arr, leftPointer + 1, high);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
}


// 1 8 3 9 4 5 7