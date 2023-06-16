public class _3_BubbleSort {
    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 6, 2, 1};

        sort(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
