import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final File file = new File("input.txt");

    private static String [] grid;

    public static void main(String [] args) {
        parseFile();
        partA();
        partB();
    }

    static void partA() {
        int [] trees = countCollisions(new int [] [] { { 3, 1 } });

        System.out.printf("The amount of trees that will be hit is %d%n",
                trees [0]);
    }

    static void partB() {
        int [] trees = countCollisions(new int [] [] { { 1, 1 }, { 3, 1 },
            { 5, 1 }, { 7, 1 }, { 1 , 2 } });

        int product = 1;
        for (int i = 0; i < trees.length; i++) {
            product *= trees [i];
        }

        System.out.printf("The product of all trees hit in each slope is %s%n",
                product);
    }

    static int [] countCollisions(int [][] steps) {
        int [] treeCounts = new int [steps.length];
        for (int i = 0; i < steps.length; i++) {
            for (int verticalIndex = 0, horizontalIndex = 0;
                    verticalIndex < grid.length;) {

                if (grid [verticalIndex].charAt(horizontalIndex) == '#') {
                    treeCounts [i]++;
                }

                // Increment the indices
                horizontalIndex = (horizontalIndex + steps [i] [0])
                    % grid [verticalIndex].length();

                verticalIndex += steps [i] [1];
            }
        }
        return treeCounts;
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            ArrayList <String> grid = new ArrayList<>();
            while (input.hasNext()) {
                grid.add(input.next());
            }

            Main.grid = grid.toArray(new String [grid.size()]);
        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
