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
    }

    static void partA() {
        int treeCount = 0;

        for (int verticalIndex = 0, horizontalIndex = 0;
                verticalIndex < grid.length;) {

            if (grid [verticalIndex].charAt(horizontalIndex) == '#') {
                treeCount++;
            }

            // Increment the indices
            horizontalIndex = (horizontalIndex + 3)
                % grid [verticalIndex++].length();
        }

        System.out.printf("The amount of trees that will be hit is %d%n",
                treeCount);
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
