import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {
    private static final File file = new File("input.txt");

    private static int [] adapterJoltages;

    public static void main(String [] args) {
        parseFile();
        partA();
        partB();
    }

    static void partA() {
        int [] joltages = adapterJoltages.clone();
        Arrays.sort(joltages);

        int oneDiffs = 0;
        int threeDiffs = 1;
        for (int i = 0; i < joltages.length; i++) {
            int prevJoltage = i != 0 ? joltages [i - 1] : 0;
            switch (joltages [i] -  prevJoltage) {
                case 1: oneDiffs++; break;
                case 3: threeDiffs++; break;
            }
        }

        System.out.printf("The count of joltage differences is %d%n",
                oneDiffs * threeDiffs);
    }

    static void partB() {
        int [] joltages = adapterJoltages.clone();
        Arrays.sort(joltages);

        // Insert a 0 to the start of the array to account for the wall outlet
        int [] temp = new int [joltages.length + 1];
        for (int i = 1; i < temp.length; i++) {
            temp [i] = joltages [i - 1];
        }
        joltages = temp;

        /*
         * If we assume that the root (0) of the array starts with a possible
         * path of '1' then:
         *   The number unique paths to an adapter with joltage 'n' is
         *   equal:
         *      Pn = Pn-1 + Pn-2 + Pn-3, n > 0
         *   and if the adapter with the joltage 'n-k' is not available then
         *   the value of 'Pn-k' is '0'
         */
        long [] pathsToN = new long [joltages.length];
        pathsToN [0] = 1;
        for (int i = 1; i < joltages.length; i++) {
            for (int j = i - 1; j > i - 4; j--) {
                if (j < 0) {
                    break;
                }

                if ((joltages [i] - joltages [j]) > 3) {
                    continue;
                }

                pathsToN [i] += pathsToN [j];
            }
        }

        System.out.printf("The unique arrangements of adapters is %d%n",
                pathsToN [pathsToN.length - 1]);
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            List<Integer> adapterJoltages = new ArrayList<>();
            input.useDelimiter("\n");
            while (input.hasNext()) {
                adapterJoltages.add(input.nextInt());
            }

        Main.adapterJoltages =
            Arrays.stream(adapterJoltages.toArray(new Integer [adapterJoltages.size()]))
            .mapToInt(Integer::intValue).toArray();
        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
