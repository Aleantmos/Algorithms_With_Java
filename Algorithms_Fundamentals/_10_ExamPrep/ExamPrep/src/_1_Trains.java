import java.util.Arrays;
import java.util.Scanner;

public class _1_Trains {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double[] arrivals = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();

        double[] departures = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();
        

        Arrays.sort(departures);

        int platforms = 0;
        int maxPlatforms = 0;

        for (int i = 0, j = 0; i < arrivals.length; i++) {
            if (arrivals[i] < departures[j]) {
                platforms++;
                i++;

                if (platforms > maxPlatforms) {
                    maxPlatforms = platforms;
                }

            } else {
                platforms--;
                j++;
            }
        }

    }
}
