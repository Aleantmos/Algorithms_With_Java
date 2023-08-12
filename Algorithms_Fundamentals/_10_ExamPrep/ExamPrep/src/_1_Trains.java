import java.util.Arrays;
import java.util.Scanner;

public class _1_Trains {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double[] arrivals = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .sorted()
                .toArray();

        double[] departures = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .sorted()
                .toArray();

        int platformsCnt = 0;
        int maxPlatforms = 0;


        for (int i = 0, j = 0; i < arrivals.length; i++) {
            if (arrivals[i] < departures[j]) {
                platformsCnt++;

                if (platformsCnt > maxPlatforms) {
                    maxPlatforms = platformsCnt;
                }
            } else {
                j++;
            }
        }

        System.out.println(maxPlatforms);

    }
}
