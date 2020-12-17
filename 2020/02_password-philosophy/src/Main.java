import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final File file = new File("input.txt");

    private static int [][] appearances;
    private static String [] characters;
    private static String [] passwords;

    public static void main(String [] args) {
        parseFile();
        partA();
        //for (int i = 0; i < passwords.length; i++) {
        //    System.out.printf("%s%n", passwords [i]);
        //}
    }

    static void partA() {
        int validPasswords = 0;
        for (int i = 0; i < passwords.length; i++) {

            int count = 0;
            int characterIndex = -1;
            do {
                characterIndex = passwords[i].indexOf(characters [i],
                        characterIndex + 1);

                if (characterIndex != -1) {
                    count++;
                }
            } while (characterIndex != -1);

            if (count < appearances [i][0]) {
                continue;
            }

            if (count > appearances [i][1]) {
                continue;
            }

            validPasswords++;
        }

        System.out.printf("The amount of valid passwords is %d%n", validPasswords);
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            ArrayList <int []> tempAppearances = new ArrayList<>();
            ArrayList <String> tempCharacters = new ArrayList<>();
            ArrayList <String> tempPasswords = new ArrayList<>();

            while (input.hasNext()) {
                input.useDelimiter(":?\\p{javaWhitespace}+");

                String [] temp = input.next().split("-");
                int [] tempInt = new int [temp.length];
                for (int i = 0; i < temp.length; i++) {
                    tempInt [i] = Integer.parseInt(temp [i]);
                }
                tempAppearances.add(tempInt);

                tempCharacters.add(input.next());
                tempPasswords.add(input.next());
            }

            appearances = tempAppearances.toArray(
                    new int [tempAppearances.size()][]);

            characters = tempCharacters.toArray(
                    new String [tempCharacters.size()]);

            passwords = tempPasswords.toArray(
                    new String [tempPasswords.size()]);

        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
