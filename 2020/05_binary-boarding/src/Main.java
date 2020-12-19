import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static final File file = new File("input.txt");

    private static String [] seats;

    public static void main(String [] args) {
        parseFile();
        partA();
        partB();
    }

    static void partA() {
        int [] seatIDs = getSeatIDs();
        Arrays.sort(seatIDs);
        System.out.printf("The highest seat ID is %d%n", seatIDs [seatIDs.length - 1]);
    }

    static void partB() {
        int [] seatIDs = getSeatIDs();
        Arrays.sort(seatIDs);

        for (int i = 0; i < seatIDs.length - 1; i++) {
            if ((seatIDs [i] + 1) != seatIDs [i + 1]
                    && (seatIDs [i] + 2) == seatIDs [i + 1]) {
                System.out.printf("Your seat ID is %d%n", seatIDs [i] + 1);
            }
        }
    }

    static int [] getSeatIDs() {
        int [] seatIDs = new int [seats.length];
        for (int i = 0; i < seats.length; i++) {
            /*
             * replaceChars(seats [i], "FLBR", "0011") from the apache commons
             * library is a nicer alternative
             */
            String binary = seats [i].replace('F', '0').replace('B', '1')
                                     .replace('L', '0').replace('R', '1');

            seatIDs [i] = Integer.parseInt(binary, 2);
        }
        return seatIDs;
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
