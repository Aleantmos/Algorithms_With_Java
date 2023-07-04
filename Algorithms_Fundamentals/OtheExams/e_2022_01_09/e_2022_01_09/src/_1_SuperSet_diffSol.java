import java.util.*;
import java.util.stream.Collectors;

public class _1_SuperSet_diffSol {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Integer> input = Arrays.stream(scan.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        List<List<Integer>> subset = new ArrayList<>();

        findSubset(subset, input, new ArrayList<>(), 0);

        Collections.sort(subset, (o1, o2) -> {
            int n = Math.min(o1.size(), o2.size());

            // sort based on size
            return o1.size() - o2.size();
        });

        for (int i = 0; i < subset.size(); i++) {
            for (int j = 0; j < subset.get(i).size(); j++) {
                System.out.print(subset.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private static void findSubset(List<List<Integer>> subset, List<Integer> nums, ArrayList<Integer> output, int index) {
        if (index == nums.size()) {
            subset.add(output);
            return;
        }

        findSubset(subset, nums, new ArrayList<>(output), index + 1);

        output.add(nums.get(index));
        findSubset(subset, nums, new ArrayList<>(output), index + 1);
    }

}
