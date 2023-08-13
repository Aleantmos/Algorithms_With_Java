import java.util.*;

public class _3_ContaminatedPath {
    //40/100 deadlock between two contaminated points unchecked
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int cnt = Integer.parseInt(scan.nextLine());

        int[][] matrix = new int[cnt][];
        for (int i = 0; i < cnt; i++) {
            matrix[i] = Arrays.stream(scan.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[] prev = new int[matrix.length + 1];
        Arrays.fill(prev, -1);

        String[] contaminationInfo = scan.nextLine().split(" ");

        int[][] contaminationPoints = new int[contaminationInfo.length][2];

        for (int i = 0; i < contaminationInfo.length; i++) {
            String[] pointsAsString = contaminationInfo[i].split(",");

            int[] points = Arrays.stream(pointsAsString)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            contaminationPoints[i][0] = points[0];
            contaminationPoints[i][1] = points[1];
        }

        int[][] helper = new int[cnt][cnt];

        helper[0][0] = matrix[0][0];
        for (int row = 1; row < matrix.length; row++) {
            helper[row][0] = helper[row - 1][0] + matrix[row][0];
        }

        for (int col = 1; col < matrix.length; col++) {
            helper[0][col] = helper[0][col - 1] + matrix[0][col];
        }

        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix.length; col++) {
                if (checkPoints(contaminationPoints, row, col)) {
                    helper[row][col] = 0;
                } else {
                    helper[row][col] = Math.max(helper[row - 1][col] + matrix[row][col],
                            helper[row][col - 1] + matrix[row][col]);

                }
            }
        }

        int row = matrix.length - 1, col = matrix.length - 1;
        List<String> result = new ArrayList<>();

        printOutput(helper.length - 1, helper.length - 1, result);

        while (row > 0 || col > 0) {

            int up = -1;

            if (row > 0) {
                up = helper[row - 1][col];
            }

            int left = -1;

            if (col > 0) {
                left = helper[row][col - 1];
            }

            if (up > left) {
                row--;
            } else {
                col--;
            }

            printOutput(row, col, result);

            /*    helper[row][col] = Math.max(helper[row - 1][col] + matrix[row][col],
                        helper[row][col - 1] + matrix[row][col]);
            */
        }

        System.out.println("Max total fertility: " + helper[helper.length - 1][helper.length - 1]);

        Collections.reverse(result);


        StringBuilder sb = new StringBuilder();
        for (String element : result) {
            sb.append(element).append(" ");
        }

        System.out.println("[" + sb.toString().trim() + "]");
    }

    private static void printOutput(int row, int col, List<String> result) {
        result.add(String.format("(%d, %d)", row, col));
    }



    private static boolean checkPoints(int[][] contaminationPoints, int row, int col) {
        for (int[] contaminationPoint : contaminationPoints) {
            if (row == contaminationPoint[0] && col == contaminationPoint[1]) {
                return true;
            }
        }
        return false;
    }
}
