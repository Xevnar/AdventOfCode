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
        System.out.printf("Accumulator value before looping is %d%n",
                boot(bootCode.clone()));
    }

    static int boot(String [] bootCode) {
        int accumulator = 0;
        for (int i = 0; i < bootCode.length;) {
            if (bootCode [i].equals("")) {
                break;
            }

            switch (bootCode [i].substring(0, 3)) {
                case "nop":
                    bootCode [i++] = "";
                    break;
                case "acc":
                    accumulator += Integer.parseInt(bootCode [i].substring(4));
                    bootCode [i++] = "";
                    break;
                case "jmp":
                    int newIndex = Integer.parseInt(bootCode [i].substring(4));
                    bootCode [i] = "";
                    i += newIndex;
                    break;
                default:
                    break;
            }
        }
        return accumulator;
    }

    static void partB() {
        repairBootCode();
        System.out.printf("Accumulator value after fix is %d%n",
                boot(bootCode.clone()));
    }

    static void repairBootCode() {
        String [] localBootCode = bootCode.clone();

        for (int i = 0; i < localBootCode.length;) {
            boolean testOutcome = false;
            if (!localBootCode [i].startsWith("acc")) {
                String [] temp = localBootCode.clone();

                String fix = (temp [i].startsWith("nop") ? "jmp" : "nop")
                    + temp [i].substring(3);
                temp [i] = fix;

                if (testFix(temp, i)) {
                    bootCode [i] = fix;
                    return;
                }
            }

            switch (localBootCode [i].substring(0, 3)) {
                case "jmp":
                    int newIndex = Integer.parseInt(localBootCode [i].substring(4));
                    localBootCode [i] = "";
                    i += newIndex;
                    break;
                default:
                    localBootCode [i++] = "";
                    break;
            }
        }
    }

    static boolean testFix(String [] localBootCode, int startIndex) {
        for (int i = startIndex; i < localBootCode.length;) {
            if (localBootCode [i].equals("")) {
                return false;
            }

            switch (localBootCode [i].substring(0, 3)) {
                case "jmp":
                    int newIndex = Integer.parseInt(localBootCode [i].substring(4));
                    localBootCode [i] = "";
                    i += newIndex;
                    break;
                default:
                    localBootCode [i++] = "";
                    break;
            }
        }
        return true;
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            List<String> bootCode = new LinkedList<>();
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
