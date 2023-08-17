import java.util.*;

public class _1_BestTeam {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] arr = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] lis = new int[arr.length];
        Arrays.fill(lis, 1);

        int[] prevL = new int[arr.length + 1];
        Arrays.fill(prevL, -1);

        int[] sis = new int[arr.length];
        Arrays.fill(sis, 1);

        int[] prevS = new int[arr.length + 1];
        Arrays.fill(prevS, -1);

        int globalMaxLengthLIS = 1;
        int globalMaxIndexLIS = -1;

        int globalMaxLengthSIS = 1;
        int globalMaxIndexSIS = -1;

        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                    prevL[i] = j;

                    if (lis[i] > globalMaxLengthLIS) {
                        globalMaxLengthLIS = lis[i];
                        globalMaxIndexLIS = i;
                    }
                }

                if (arr[i] < arr[j] && sis[i] < sis[j] + 1) {
                   sis[i] = sis[j] + 1;
                   prevS[i] = j;
                   if (sis[i] > globalMaxLengthSIS) {
                       globalMaxLengthSIS = sis[i];
                       globalMaxIndexSIS = i;
                   }
                }
            }
        }
        int[] prev;
        int index;
        if (globalMaxLengthLIS > globalMaxLengthSIS) {
            prev = Arrays.copyOf(prevL, prevL.length);
            index = globalMaxIndexLIS;
        } else {
            prev = Arrays.copyOf(prevS, prevS.length);
            index = globalMaxIndexSIS;
        }

        List<Integer> path = new ArrayList<>();

        while (index != -1) {
            path.add(arr[index]);
            index = prev[index];
        }

        if (!path.isEmpty()) {
            Collections.reverse(path);

            StringBuilder sb = new StringBuilder();

            for (Integer el : path) {
                sb.append(el).append(" ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}
