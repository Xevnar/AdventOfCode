import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {
    private static final File file = new File("input.txt");

    private static long [] values;

    public static void main(String [] args) {
        parseFile();
        partA();
        partB();
    }

    static void partA() {
        System.out.printf("The invalid value in the list is %d%n",
                getInvalidNum(25));
    }

    static long getInvalidNum(int preamble) {
        for (int i = preamble; i < values.length; i++) {
            boolean hasPair = false;
            for (int j = i - preamble; j < i; j++) {
                long complement = values [i] - values [j];

                if (complement <= 0) {
                    continue;
                }

                for (int k = i - preamble; k < i; k++) {
                    if (k == j) {
                        continue;
                    }

                    if (values [k] == complement) {
                        hasPair = true;
                    }
                }
            }

            if (!hasPair) {
                return values [i];
            }
        }

        return -1;
    }

    static void partB() {
        long [] set = getContiguousSet(getInvalidNum(25));
        Arrays.sort(set);

        System.out.printf("The encryption weakness is %d%n",
                set [set.length - 1] + set [0]);
    }

    static long [] getContiguousSet(long key) {
        int setStart = 0;
        int setEnd = 0;
        for (int i = 0, sum = 0; i < values.length; i++) {
            sum += values [i];

            while (sum > key) {
                sum -= values [setStart++];
            }

            if (sum == key) {
                setEnd = i;
                break;
            }
        }

        return Arrays.copyOfRange(values, setStart, setEnd);
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            List<Long> values = new ArrayList<>();
            input.useDelimiter("\n");
            while (input.hasNext()) {
                values.add(input.nextLong());
            }

        Main.values = Arrays.stream(values.toArray(new Long [values.size()]))
            .mapToLong(Long::longValue).toArray();
        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
