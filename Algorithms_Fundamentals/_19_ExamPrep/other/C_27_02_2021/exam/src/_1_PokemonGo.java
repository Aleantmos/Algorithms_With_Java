import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class _1_PokemonGo {
    public static class Street {
        String name;
        int fuelNeeded;
        int pokemonCnt;

        public Street(String name, int fuelNeeded, int pokemonCnt) {
            this.name = name;
            this.fuelNeeded = fuelNeeded;
            this.pokemonCnt = pokemonCnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int capacity = Integer.parseInt(reader.readLine());

        List<Street> streets = new ArrayList<>();

        String line = reader.readLine();

        while (!line.equals("End")) {
            String[] data = line.split(", ");
            String streetName = data[0];
            int pokemonCnt = Integer.parseInt(data[1]);
            int fuelNeeded = Integer.parseInt(data[2]);

            streets.add(new Street(streetName, fuelNeeded, pokemonCnt));

            line = reader.readLine();
        }

        int[][] dp = new int[streets.size() + 1][capacity + 1];
        boolean[][] visited = new boolean[streets.size() + 1][capacity + 1];

        for (int i = 1; i <= streets.size(); i++) {
            int strFuelNeeded = streets.get(i - 1).fuelNeeded;
            int pokemonCnt = streets.get(i - 1).pokemonCnt;

            for (int j = 0; j <= capacity; j++) {
                int excluded = dp[i - 1][j];

                if (j - strFuelNeeded < 0) {
                    dp[i][j] = excluded;
                } else {
                    int included = dp[i - 1][j - strFuelNeeded] + pokemonCnt;

                    if (excluded > included) {
                        dp[i][j] = excluded;
                    } else {
                        dp[i][j] = included;
                        visited[i][j] = true;
                    }
                }
            }
        }


        int lastStr = streets.size();

        Set<String> result = new TreeSet<>();
        int totalPokCnt = 0;

        while (lastStr > 0) {
            if (visited[lastStr][capacity]) {
                Street currStr = streets.get(lastStr - 1);
                result.add(currStr.name);
                capacity -= currStr.fuelNeeded;
                totalPokCnt += currStr.pokemonCnt;
            }
            lastStr--;
        }
        if (!result.isEmpty()) {
            System.out.println(result.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" -> ")));
        }
        System.out.println("Total Pokemon caught -> " + totalPokCnt);
        System.out.println("Fuel Left -> " + capacity);
    }
}
