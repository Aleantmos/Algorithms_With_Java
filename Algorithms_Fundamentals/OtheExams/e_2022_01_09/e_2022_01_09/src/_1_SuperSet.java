import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _1_SuperSet {
    public static int[] input;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        input = Arrays.stream(scan.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();


        List<List<Integer>> subsets = new ArrayList<>();

        List<Integer> currSubset = new ArrayList<>();

        rec(input, 0, currSubset, subsets);

        for (List<Integer> subset : subsets) {
            for (Integer integer : subset) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    private static void rec(int[] nums, int start, List<Integer> currSubset, List<List<Integer>> subsets) {
        subsets.add(new ArrayList<>(currSubset));
        for (int i = start; i < nums.length; i++) {
            currSubset.add(nums[i]);
            rec(nums, i + 1, currSubset, subsets);
            currSubset.remove(currSubset.size() - 1);
        }
    }

}
