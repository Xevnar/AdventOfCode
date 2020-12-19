import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final File file = new File("input.txt");

    private static String [] seats;

    public static void main(String [] args) {
        parseFile();
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            ArrayList <String> seats = new ArrayList<>();

            while (input.hasNext()) {
                seats.add(input.next());
            }

            Main.seats = seats.toArray(new String [seats.size()]);
        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
