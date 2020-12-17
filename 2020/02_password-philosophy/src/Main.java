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
        for (int i = 0; i < passwords.length; i++) {
            System.out.printf("%s%n", passwords [i]);
        }
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
