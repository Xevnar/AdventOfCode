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
    }

    static void partA() {
        int totalAnswers = 0;
        for (int i = 0; i < groupAnswers.length; i++) {
            String answers = groupAnswers [i].replaceAll("(\\h|\\v)+", "");

            char[] chars = answers.toCharArray();
            Set<Character> uniqueChars = new HashSet<>();

            for (int j = 0; j < chars.length; j++) {
               uniqueChars.add(chars [j]);

            }

            totalAnswers += uniqueChars.size();
        }

        System.out.printf("The total number of answers for all groups is %d%n",
                totalAnswers);
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            ArrayList <String> groupAnswers = new ArrayList<>();
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
