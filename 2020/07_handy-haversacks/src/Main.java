import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final File file = new File("input.txt");

    private static String [] rules;

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
            ArrayList <String> rules = new ArrayList<>();
            input.useDelimiter("\n\n");
            while (input.hasNext()) {
                rules.add(input.next());
            }

            Main.rules = rules.toArray(new String [rules.size()]);
        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
