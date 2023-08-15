import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class _3_Medivac {

    public static class Unit {
        int unit;
        int size;
        int urgency;

        public Unit(int unit, int size, int urgency) {
            this.unit = unit;
            this.size = size;
            this.urgency = urgency;
        }

        public int getSize() {
            return size;
        }

        public int getUnit() {
            return unit;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int capacity = Integer.parseInt(reader.readLine());

        List<Unit> units = new ArrayList<>();

        String line = reader.readLine();

        while (!line.equals("Launch")) {
            int[] props = Arrays.stream(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            units.add(new Unit(props[0], props[1], props[2]));

            line = reader.readLine();
        }

        int[][] dp = new int[units.size() + 1][capacity + 1];
        boolean[][] unitsDelivered = new boolean[units.size() + 1][capacity + 1];

        for (int currentUnit = 1; currentUnit <= units.size(); currentUnit++) {
            Unit unit = units.get(currentUnit - 1);
            for (int currentCapacity = 0; currentCapacity <= capacity; currentCapacity++) {
                int excludedUnit = dp[currentUnit - 1][currentCapacity];
                if (currentCapacity - unit.size < 0) {
                    dp[currentUnit][currentCapacity] = excludedUnit;
                } else {
                    int includedUnit = dp[currentUnit - 1][currentCapacity - unit.size] + unit.urgency;

                    if (excludedUnit > includedUnit) {
                        dp[currentUnit][currentCapacity] = excludedUnit;
                    } else {
                        dp[currentUnit][currentCapacity] = includedUnit;
                        unitsDelivered[currentUnit][currentCapacity] = true;
                    }
                }
            }
        }

        int urgencyAchieved = dp[units.size()][capacity];

        Set<Unit> outputUnits = new TreeSet<>(Comparator.comparingInt(u -> u.unit));

        int lastUnit = units.size();
        while (lastUnit > 0) {
            if (unitsDelivered[lastUnit][capacity]) {
                Unit unit = units.get(lastUnit - 1);
                outputUnits.add(unit);
                capacity -= unit.size;
            }
            lastUnit--;
        }

        System.out.println(outputUnits
                .stream()
                .mapToInt(Unit::getSize)
                .sum());
        System.out.println(urgencyAchieved);

        System.out.println(outputUnits.stream()
                .map(Unit::getUnit)
                .map(String::valueOf)
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
