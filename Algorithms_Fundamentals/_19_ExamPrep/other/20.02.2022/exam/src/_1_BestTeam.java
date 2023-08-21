import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class _1_BestTeam {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(reader.readLine().split("\\s+"))
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

        int index = -1;
        int[] prev = new int[arr.length + 1];

        if (globalMaxLengthLIS > globalMaxLengthSIS) {
            index = globalMaxIndexLIS;
            prev = Arrays.copyOf(prevL, prevL.length);
        } else {
            index = globalMaxIndexSIS;
            prev = Arrays.copyOf(prevS, prevS.length);
        }

        StringBuilder result = new StringBuilder();

        List<Integer> path = new ArrayList<>();

        /*while (index != -1) {
            result.append(arr[index]).append(" ");
            //System.out.print(arr[index] + " ");
            path.add(arr[index]);
            index = prev[index];
        }

        Collections.reverse(path);

        for (Integer integer : path) {
            System.out.print(integer + " ");
        }*/

        List<Integer> bestTeam;

        if (globalMaxIndexLIS == globalMaxIndexSIS && globalMaxIndexSIS == -1) {
            bestTeam = new ArrayList<>();
            bestTeam.add(arr[0]);
        } else if (globalMaxLengthLIS > globalMaxLengthSIS) {
            bestTeam = getBestTeam(globalMaxLengthLIS, prevL, arr);
        } else {
            bestTeam = getBestTeam(globalMaxIndexSIS, prevS, arr);
        }

        System.out.println(bestTeam.stream().map(String::valueOf).collect(Collectors.joining(" ")));

    }

    private static List<Integer> getBestTeam(int index, int[] prev, int[] arr) {
        List<Integer> bestTeam = new ArrayList<>();
        while (index != -1) {
            bestTeam.add(0, arr[index]);
            index = prev[index];
        }
        return bestTeam;
    }
}
