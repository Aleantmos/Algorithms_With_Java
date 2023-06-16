import java.util.Arrays;
import java.util.Scanner;

public class _1_BinarySearch {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] arr = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        Arrays.sort(arr);

        int n = Integer.parseInt(scan.nextLine());


        System.out.println(getBinarySearch(arr, n));
    }

    private static int getBinarySearch(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int current = arr[mid];

            if (key < current) {
                end = mid - 1;

            } else if (key > current) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
