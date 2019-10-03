package phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static phonebook.AlgorithmHandler.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("C:/GITWORK/TUTORIALS/Data/Phone Book/find.txt"));

        List<String> namesToFind = new ArrayList<>();

        while (scanner.hasNextLine()) {
            namesToFind.add(scanner.nextLine());
        }

        Scanner directoryScanner = new Scanner(new File("C:/GITWORK/TUTORIALS/Data/Phone Book/directory.txt"));

        List<String> directory = new ArrayList<>();

        while (directoryScanner.hasNextLine()) {
            String[] parts = directoryScanner.nextLine().split(" ");
            directory.add(parts[1] + (parts.length == 3 ? " " + parts[2] : ""));
        }

        long linearSearchTime = handleLinearSearch(namesToFind, directory);
        handleBubbleSortJumpSearch(directory, namesToFind, linearSearchTime);
        handleQuickSortBinarySearch(directory, namesToFind, linearSearchTime);
    }
}
