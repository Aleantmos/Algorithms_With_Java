import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2_Box {

    public static class Box {
        int width;
        int depth;
        int height;

        public Box(int width, int depth, int height) {
            this.width = width;
            this.depth = depth;
            this.height = height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int boxesCnt = Integer.parseInt(reader.readLine());

        List<Box> boxes = new ArrayList<>();

        for (int i = 0; i < boxesCnt; i++) {
            int[] data = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            boxes.add(new Box(data[0], data[1], data[2]));
        }

        int[] prev = new int[boxesCnt];
        Arrays.fill(prev, -1);
        int[] length = new int[boxesCnt];
        Arrays.fill(length, 1);

        int bestLength = 0;
        int bestIndex = 0;

        for (int i = 1; i < boxesCnt; i++) {
            length[i] = 1;

            Box currBox = boxes.get(i);

            for (int j = i - 1; j >= 0; j--) {
                Box prevBox = boxes.get(j);

                if (prevBox.width < currBox.width &&
                    prevBox.depth < currBox.depth &&
                    prevBox.height < currBox.height &&
                    length[j] + 1 > length[i]) {
                    length[i] = length[j] + 1;
                    prev[i] = j;
                }
            }
            if (length[i] > bestLength) {
                bestLength = length[i];
                bestIndex = i;
            }
        }

        Deque<Box> boxStack = new ArrayDeque<>();

        while (bestIndex != -1) {
            boxStack.push(boxes.get(bestIndex));
            bestIndex = prev[bestIndex];
        }

        for (Box box : boxStack) {
            System.out.printf("%d %d %d%n", box.width, box.depth, box.height);
        }
    }
}
