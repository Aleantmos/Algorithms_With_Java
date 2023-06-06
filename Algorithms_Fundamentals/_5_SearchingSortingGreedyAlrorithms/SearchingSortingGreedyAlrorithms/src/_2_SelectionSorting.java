public class _2_SelectionSorting {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};

        sort(arr);

        for(int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void sort(int[] arr) {

        for (int index = 0; index < arr.length; index++) {
            int min = index;
            for (int curr = index + 1; curr < arr.length; curr++) {
                if (arr[curr] < arr[min]) {
                    min = curr;
                }
            }
            swap(arr, index, min);
        }
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
