import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final File file = new File("input.txt");

    private static String [] groupAnswers;

    public static void main(String [] args) {
        parseFile();
        partA();
        partB();
    }

    static void partA() {
        int totalAnswers = 0;
        for (int i = 0; i < groupAnswers.length; i++) {
            String answers = groupAnswers [i].replaceAll("(\\h|\\v)+", "");

            Set<Character> uniqueChars = new HashSet<>();
            storeCharsInSet(answers, uniqueChars);

            totalAnswers += uniqueChars.size();
        }

        System.out.printf("The total number of answers for all groups is %d%n",
                totalAnswers);
    }

    static void partB() {
        int totalAnswers = 0;
        for (int i = 0; i < groupAnswers.length; i++) {
            String [] individualAnswers = groupAnswers [i].split("\\v+");

            // Have the first person's answers act as a base
            Set<Character> baseChars = new HashSet<>();
            storeCharsInSet(individualAnswers [0], baseChars);

            for (int j = 1; j < individualAnswers.length; j++) {
                Set<Character> tempChars = new HashSet<>();
                storeCharsInSet(individualAnswers [j], tempChars);

                // Keep set intersection
                baseChars.retainAll(tempChars);
            }

            totalAnswers += baseChars.size();
        }

        System.out.printf("The total number of answers for all groups is %d%n",
                totalAnswers);
    }

    static void storeCharsInSet(String array, Set<Character> s) {
        char [] chars = array.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            s.add(chars [i]);
        }
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            ArrayList<String> groupAnswers = new ArrayList<>();
            input.useDelimiter("\n\n");
            while (input.hasNext()) {
                groupAnswers.add(input.next());
            }

            Main.groupAnswers = groupAnswers.toArray(new String [groupAnswers.size()]);
        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
