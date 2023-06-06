public class _2_Sorting {
    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};

        sort(arr);

        for(int i : arr) {
            System.out.println(i + " ");
        }
    }

    public static void sort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
