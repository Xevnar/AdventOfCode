import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final File file = new File("input.txt");

    private static String [] passports;

    public static void main(String [] args) {
        parseFile();
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            ArrayList <String> passports = new ArrayList<>();
            input.useDelimiter("\n\n");
            while (input.hasNext()) {
                passports.add(input.next());
            }

            Main.passports = passports.toArray(new String [passports.size()]);
        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
