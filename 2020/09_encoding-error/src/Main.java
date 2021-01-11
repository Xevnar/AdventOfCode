import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {
    private static final File file = new File("input.txt");

    private static long [] values;

    public static void main(String [] args) {
        parseFile();
        partA();
        partB();
    }

    static void partA() {
    }

    static void partB() {
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            List<Long> values = new ArrayList<>();
            input.useDelimiter("\n");
            while (input.hasNext()) {
                values.add(input.nextLong());
            }

        Main.values = Arrays.stream(values.toArray(new Long [values.size()]))
            .mapToLong(Long::longValue).toArray();
        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
