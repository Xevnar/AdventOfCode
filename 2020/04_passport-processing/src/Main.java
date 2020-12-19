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
        partB();
    }

    static void partA() {
        String [] completePassports = getCompletePassports();

        System.out.printf("The number of complete passports is %d%n",
                completePassports.length);
    }

    static void partB() {
        String [] completePassports = getCompletePassports();

        ArrayList<String> validPassports = new ArrayList<>();
        passport_validating:
        for (int i = 0; i < completePassports.length; i++) {
            String [] currentPassport = completePassports [i].split("(\\v|\\h)+");

            for (int j = 0; j < currentPassport.length; j++) {
                String [] info = currentPassport [j].split(":");
                String regex;
                switch (info [0]) {
                    case "byr":
                        regex = "(19[2-9]\\d|200[0-2])";
                        break;
                    case "iyr":
                        regex = "20(1\\d|20)";
                        break;
                    case "eyr":
                        regex = "20(2\\d|30)";
                        break;
                    case "hgt":
                        regex = "(1([5-8]\\d|9[0-3])cm|(59|6\\d|7[0-6])in)";
                        break;
                    case "hcl":
                        regex = "#[0-9a-f]{6}";
                        break;
                    case "ecl":
                        regex = "(amb|blu|brn|gry|grn|hzl|oth)";
                        break;
                    case "pid":
                        regex = "\\d{9}";
                        break;
                    case "cid":
                        regex =".*";
                        break;
                    default:
                        regex = "\\w\\b\\w"; // unmatchable regex
                        break;
                }

                if (! info [1].matches(regex)) {
                    // Invalid Passport
                    continue passport_validating;
                }
            }

            validPassports.add(completePassports [i]);
        }
        System.out.printf("The amount of valid passports is %d%n",
                validPassports.size());
    }

    static String [] getCompletePassports() {
        ArrayList<String> completePassports = new ArrayList<>();

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

            completePassports.add(passports [i]);
        }
        return completePassports.toArray(new String [completePassports.size()]);
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
