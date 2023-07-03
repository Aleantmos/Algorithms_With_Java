import java.util.*;

public class _2_Chainalysis {
    public static int cnt;

    public static Map<String, List<String>> graph = new HashMap<>();
    public static Map<String, Integer> cyclesVisited = new HashMap<>();
    public static Set<String> visited = new HashSet<>();
    public static Set<String> cycles = new HashSet<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Integer transCnt = Integer.parseInt(scan.nextLine());

        String start = null;

        for (int i = 0; i < transCnt; i++) {
            String[] input = scan.nextLine().split("\\s+");

            String from = input[0];
            if (start == null) {
                start = from;
            }

            String to = input[1];

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
        }

        cnt = 0;


        for (String curr : graph.keySet()) {
            if (!visited.contains(curr)) {
                cyclesVisited.putIfAbsent(curr, 0);
                dfs(curr);
            }
        }

        System.out.println(cnt);

    }

    private static void dfs(String source) {
        if (visited.contains(source) || cycles.contains(source)) {
            return;
        }

        visited.add(source);
        cycles.add(source);

        List<String> children = graph.get(source);

        if (children == null) {
            cnt++;
        } else {
            for (String child : children) {
                if ((cycles.contains(child) && cyclesVisited.get(child) == 0)) {
                    cyclesVisited.put(child, cyclesVisited.get(child) + 1);
                    cnt++;
                }

                if (!visited.contains(child) && child != null) {
                    dfs(child);
                }
            }
        }


    }
}
