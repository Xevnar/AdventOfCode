import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {
    private static final File file = new File("input.txt");

    private static String [] rules;

    public static void main(String [] args) {
        parseFile();
        partA();
        partB();
    }

    static void partA() {
        String [] containers = new String [rules.length];
        String [] contents = new String [rules.length];
        splitRules(containers, contents);

        int count = getContainerCount("shiny gold", containers, contents);
        System.out.printf("The amount of bags that can carry a shiny gold bag is %d%n",
                count);
    }

    static int getContainerCount(String key, String [] containers,
            String [] contents) {
        int [] indices = searchStringArray(contents, key);

        // Base Case: Can't key doesn't exist in containers
        if (indices.length == 0) {
            return 0;
        }

        // Delete contents first to avoid counting the same container twice
        for (int i = 0; i < indices.length; i++) {
            contents [indices [i]] = "";
        }

        int containerCount = indices.length;
        for (int i = 0; i < indices.length; i++) {
            containerCount += getContainerCount(containers [indices [i]],
                    containers, contents);
        }
        return containerCount;
    }

    static void partB() {
    }

    static void splitRules(String [] containers, String [] contents) {
        for (int i = 0; i < rules.length; i++) {
            // Remove 'bags' from keys
            String [] temp = rules [i].split(" bags contain ");
            containers [i] = temp [0];
            contents [i] = temp [1];
        }
    }

    static int [] searchStringArray(String [] array, String key) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array [i].indexOf(key) != -1) {
                indices.add(i);
            }
        }

        return Arrays.stream(indices.toArray(new Integer [indices.size()]))
            .mapToInt(Integer::intValue).toArray();
    }

    static void parseFile() {
        try (Scanner input = new Scanner(file)) {
            ArrayList <String> rules = new ArrayList<>();
            input.useDelimiter("\n");
            while (input.hasNext()) {
                rules.add(input.next());
            }

            Main.rules = rules.toArray(new String [rules.size()]);
        } catch (FileNotFoundException e) {
            System.out.printf("%s", e.getMessage());
        }
    }
}
