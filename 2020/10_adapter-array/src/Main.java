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
