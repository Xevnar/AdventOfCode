import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Thread;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Main {
    private static final File file = new File("input.txt");

    private static String [] seatLayout;

    public static void main(String [] args) throws InterruptedException {
        parseFile();
        partA();
        partB();
    }

    static void partA() throws InterruptedException {
        String [] finalSeatLayout = changeSeatLayout(seatLayout.clone());

        System.out.printf("The number of occupied seats at the end is %d%n",
                countOccupiedSeats(finalSeatLayout));
    }

    static String [] changeSeatLayout(String [] seatLayout) throws InterruptedException {
        String [] modifiedLayout = seatLayout.clone();
        for (int i = 0; i < seatLayout.length; i++) {
            char [] newSeatStates = modifiedLayout [i].toCharArray();

            for (int j = 0; j < seatLayout [i].length(); j++) {
                switch (seatLayout [i].charAt(j)) {
                    case 'L':
                        boolean noAdjacency = true;
                        for (int k = i - 1; k < i + 2; k++) {
                            for (int l = j - 1; l < j + 2; l ++) {
                                // Boundary checking
                                if ((k < 0 || k >= seatLayout.length)
                                        || (l < 0 || l >= seatLayout [k].length())) {
                                    continue;
                                }

                                if (seatLayout [k].charAt(l) == '#') {
                                    noAdjacency = false;
                                }
                            }
                        }

                        if (noAdjacency) {
                            newSeatStates [j] = '#';
                        }
                        break;

                    case '#':
                        int adjacencyCount = 0;
                        for (int k = i - 1; k < i + 2; k++) {
                            for (int l = j - 1; l < j + 2; l ++) {
                                // Avoid counting current cell
                                if (k == i && l == j) {
                                    continue;
                                }

                                // Boundary checking
                                if ((k < 0 || k >= seatLayout.length)
                                        || (l < 0 || l >= seatLayout [k].length())) {
                                    continue;
                                }

                                if (seatLayout [k].charAt(l) == '#') {
                                    adjacencyCount++;
                                }
                            }
                        }

                        if (adjacencyCount >= 4) {
                            newSeatStates [j] = 'L';
                        }
                        break;

                    case '.': // Do nothing
                        break;
                }
            }
            modifiedLayout [i] = new String(newSeatStates);
        }

        boolean isLayoutDifferent = false;
        for (int i = 0; i < seatLayout.length; i++) {
            if (!seatLayout [i].equals(modifiedLayout [i])) {
                isLayoutDifferent = true;
            }
        }

        if (isLayoutDifferent) {
            return changeSeatLayout(modifiedLayout);
        } else {
            return modifiedLayout;
        }
    }

    static int countOccupiedSeats(String [] seatLayout) {
        int occupiedCount = 0;
        for (int i = 0; i < seatLayout.length; i++) {
            for (int j = 0; j < seatLayout [i].length(); j++) {
                if (seatLayout [i].charAt(j) == '#') {
                    occupiedCount++;
                }
            }
        }
        return occupiedCount;
    }

    static void partB() {
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            List<String> seatLayout = new LinkedList<>();
            input.useDelimiter("\n");
            while (input.hasNext()) {
                seatLayout.add(input.next());
            }

        Main.seatLayout = seatLayout.toArray(new String [seatLayout.size()]);
        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
