import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final File file = new File("input.txt");

    private static String [] passports;

    public static void main(String [] args) {
        parseFile();
        partA();
    }

    static void partA() {
        ArrayList<String> validPassports = new ArrayList<>();

        for (int i = 0; i < passports.length; i++) {
            String [] currentPassport = passports [i].split("(\\v|\\h)+");

            if (currentPassport.length < 7) {
                // Invalid passport
                continue;
            }

            if (currentPassport.length < 8) {
                int cidIndex = -1;
                for (int j = 0; j < currentPassport.length; j++) {
                    if (currentPassport [j].startsWith("cid")) {
                        cidIndex = j;
                    }
                }

                if (cidIndex != -1) {
                    continue;
                }
            }

            validPassports.add(passports [i]);
        }

        System.out.printf("The number of valid passports is %d%n",
                validPassports.size());
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
