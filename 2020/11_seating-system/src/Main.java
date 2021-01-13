import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
/*
 * BEWARE ---------------------------------------------------------------------
 * IF SCROLL FURTHER YOU WILL ENCOUNTER A PILE OF SHIT THAT WASN'T CLEANED DUE
 * TO IT BEING 2 AM
 */

public class Main {
    private static final File file = new File("input.txt");

    private static String [] seatLayout;

    public static void main(String [] args) {
        parseFile();
        partA();
        partB();
    }

    static void partA() {
        String [] finalSeatLayout = changeSeatLayoutA(seatLayout.clone());

        System.out.printf("The number of occupied seats at the end is %d%n",
                countOccupiedSeats(finalSeatLayout));
    }

    static String [] changeSeatLayoutA(String [] seatLayout) {
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

        if (areLayoutsDifferent(seatLayout, modifiedLayout)) {
            return changeSeatLayoutA(modifiedLayout);
        } else {
            return modifiedLayout;
        }
    }

    static boolean areLayoutsDifferent(String [] layout1, String [] layout2) {
        for (int i = 0; i < layout1.length; i++) {
            if (!layout1 [i].equals(layout2 [i])) {
                return true;
            }
        }
        return false;
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
        String [] finalSeatLayout = changeSeatLayoutB(seatLayout.clone());

        System.out.printf("The number of occupied seats at the end is %d%n",
                countOccupiedSeats(finalSeatLayout));
    }

    static String [] changeSeatLayoutB(String [] seatLayout) {
        String [] modifiedLayout = seatLayout.clone();
        for (int i = 0; i < seatLayout.length; i++) {
            char [] newSeatStates = modifiedLayout [i].toCharArray();

            for (int j = 0; j < seatLayout [i].length(); j++) {
                switch (seatLayout [i].charAt(j)) {
                    case 'L':
                        boolean noAdjacency = true;

                        boolean [] checkPath = {
                            true, true, true, true, true, true
                        };
                        // Check all cardinal directions except north and south
                        Adjacency_Check_1:
                        for (int colOffset = 1, rowOffset = 1;
                                notAllFalse(checkPath); colOffset++, rowOffset++) {

                            int [] cols = {
                                j - colOffset,
                                j + colOffset,
                            };

                            if (cols [0] < 0) {
                                checkPath [0] = false;
                                checkPath [1] = false;
                                checkPath [2] = false;
                            }

                            if (cols [1] >= seatLayout [i].length()) {
                                checkPath [3] = false;
                                checkPath [4] = false;
                                checkPath [5] = false;
                            }

                            int [] rows = {
                                i - rowOffset,
                                i,
                                i + rowOffset
                            };

                            if (rows [0] < 0) {
                                checkPath [0] = false;
                                checkPath [3] = false;
                            }

                            if (rows [2] >= seatLayout.length) {
                                checkPath [2] = false;
                                checkPath [5] = false;
                            }

                            for (int k = 0; k < cols.length; k++) {
                                for (int l = 0; l < rows.length; l++) {
                                    int path = l + (k * 3);
                                    if (!checkPath [path]) {
                                        continue;
                                    }

                                    if (seatLayout [rows [l]].charAt(cols [k]) == 'L') {
                                        checkPath [path] = false;
                                        continue;
                                    }

                                    if (seatLayout [rows [l]].charAt(cols [k]) == '#') {
                                        noAdjacency = false;
                                        break Adjacency_Check_1;
                                    }
                                }
                            }
                        }

                        checkPath = new boolean [] { true, true };
                        // Check north and south
                        Adjacency_Check_2:
                        for (int rowOffset = 1; notAllFalse(checkPath); rowOffset++) {
                            if (!noAdjacency) {
                                break;
                            }

                            int [] rows = {
                                i - rowOffset,
                                i + rowOffset
                            };

                            if (rows [0] < 0) {
                                checkPath [0] = false;
                            }

                            if (rows [1] >= seatLayout.length) {
                                checkPath [1] = false;
                            }

                            for (int k = 0; k < rows.length; k++) {
                                int path = k;
                                if (!checkPath [path]) {
                                    continue;
                                }

                                if (seatLayout [rows [k]].charAt(j) == 'L') {
                                    checkPath [path] = false;
                                    continue;
                                }

                                if (seatLayout [rows [k]].charAt(j) == '#') {
                                    noAdjacency = false;
                                    break Adjacency_Check_2;
                                }
                            }
                        }

                        if (noAdjacency) {
                            newSeatStates [j] = '#';
                        }
                        break;

                    case '#':
                        int adjacencyCount = 0;

                        checkPath = new boolean [] {
                            true, true, true, true, true, true
                        };
                        // Check all cardinal directions except north and south
                        Adjacency_Check_1:
                        for (int colOffset = 1, rowOffset = 1;
                                notAllFalse(checkPath); colOffset++, rowOffset++) {

                            int [] cols = {
                                j - colOffset,
                                j + colOffset,
                            };

                            if (cols [0] < 0) {
                                checkPath [0] = false;
                                checkPath [1] = false;
                                checkPath [2] = false;
                            }

                            if (cols [1] >= seatLayout [i].length()) {
                                checkPath [3] = false;
                                checkPath [4] = false;
                                checkPath [5] = false;
                            }

                            int [] rows = {
                                i - rowOffset,
                                i,
                                i + rowOffset
                            };

                            if (rows [0] < 0) {
                                checkPath [0] = false;
                                checkPath [3] = false;
                            }

                            if (rows [2] >= seatLayout.length) {
                                checkPath [2] = false;
                                checkPath [5] = false;
                            }

                            for (int k = 0; k < cols.length; k++) {
                                for (int l = 0; l < rows.length; l++) {
                                    int path = l + (k * 3);
                                    if (!checkPath [path]) {
                                        continue;
                                    }

                                    if (seatLayout [rows [l]].charAt(cols [k]) == 'L') {
                                        checkPath [path] = false;
                                        continue;
                                    }

                                    if (seatLayout [rows [l]].charAt(cols [k]) == '#') {
                                        checkPath [path] = false;
                                        adjacencyCount++;
                                    }
                                }
                            }
                        }

                        checkPath = new boolean [] { true, true };
                        // Check north and south
                        Adjacency_Check_2:
                        for (int rowOffset = 1; notAllFalse(checkPath); rowOffset++) {
                            if (adjacencyCount > 4) {
                                break;
                            }

                            int [] rows = {
                                i - rowOffset,
                                i + rowOffset
                            };

                            if (rows [0] < 0) {
                                checkPath [0] = false;
                            }

                            if (rows [1] >= seatLayout.length) {
                                checkPath [1] = false;
                            };


                            for (int k = 0; k < rows.length; k++) {
                                int path = k;
                                if (!checkPath [path]) {
                                    continue;
                                }

                                if (seatLayout [rows [k]].charAt(j) == 'L') {
                                    checkPath [path] = false;
                                    continue;
                                }

                                if (seatLayout [rows [k]].charAt(j) == '#') {
                                    checkPath [path] = false;
                                    adjacencyCount++;
                                }
                            }
                        }

                        if (adjacencyCount > 4) {
                            newSeatStates [j] = 'L';
                        }
                        break;

                    case '.': // Do nothing
                        break;
                }
            }
            modifiedLayout [i] = new String(newSeatStates);
        }

        for (int i = 0; i < modifiedLayout.length; i++) {
        //    System.out.printf("%s%n", modifiedLayout [i]);
        }

        if (areLayoutsDifferent(seatLayout, modifiedLayout)) {
            return changeSeatLayoutB(modifiedLayout);
        } else {
            return modifiedLayout;
        }
    }

    static boolean notAllFalse(boolean [] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr [i]) {
                return true;
            }
        }
        return false;
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
