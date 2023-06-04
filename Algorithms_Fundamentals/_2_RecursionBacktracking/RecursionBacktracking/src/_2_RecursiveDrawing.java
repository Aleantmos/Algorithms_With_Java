import java.util.Scanner;

public class _2_RecursiveDrawing {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        drawFigure(n);

    }

    private static void drawFigure(int n) {

        if (n == 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }

        System.out.println();

        drawFigure(n - 1);

        for (int i = 0; i < n; i++) {
            System.out.println("#");
        }

        System.out.println();
    }
}
