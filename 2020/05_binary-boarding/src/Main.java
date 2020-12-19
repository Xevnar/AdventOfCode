import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final File file = new File("input.txt");

    private static String [] seats;

    public static void main(String [] args) {
        parseFile();
        partA();
    }

    static void partA() {
        int largestSeatID = Integer.MIN_VALUE;
        for (int i = 0; i < seats.length; i++) {
            /*
             * replaceChars(seats [i], "FLBR", "0011") from the apache commons
             * library is a nicer alterantive
             */
            String binary = seats [i].replace('F', '0').replace('B', '1')
                                     .replace('L', '0').replace('R', '1');

            int seatID = Integer.parseInt(binary, 2);
            largestSeatID = (seatID > largestSeatID) ? seatID : largestSeatID;
        }

        System.out.printf("The highest seat ID is %d%n", largestSeatID);
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
