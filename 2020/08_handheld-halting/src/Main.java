import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class Main {
    private static final File file = new File("input.txt");

    private static String [] bootCode;

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
            List <String> bootCode = new LinkedList<>();
            input.useDelimiter("\n");
            while (input.hasNext()) {
                bootCode.add(input.next());
            }

            Main.bootCode = bootCode.toArray(new String [bootCode.size()]);
        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
