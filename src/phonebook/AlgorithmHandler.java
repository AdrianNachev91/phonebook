package phonebook;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static phonebook.LogHelper.*;
import static phonebook.LogHelper.logSearching;
import static phonebook.SearchAlgorithm.*;
import static phonebook.SortingAlgorithm.*;

public class AlgorithmHandler {

    public static long handleLinearSearch(List<String> namesToFind, List<String> directory) {
        long startTime = currentTimeMillis();
        out.println("Start searching (linear search)...");
        int foundEntries = linearSearch(directory, namesToFind);

        long endTime = currentTimeMillis();
        long linearSearchTime = endTime - startTime;
        logFoundAndTimeTaken(namesToFind, foundEntries, linearSearchTime);
        return linearSearchTime;
    }

    public static void handleBubbleSortJumpSearch(List<String> directory, List<String> namesToFind, long linearSearchTime) {
        int foundEntries;
        out.println("\nStart searching (bubble sort + jump search)...");
        long bubbleSortStartTime = currentTimeMillis();
        List<String> sortedDirectory = bubbleSort(new ArrayList<>(directory), linearSearchTime * 10);
        long bubbleSortEndTime = currentTimeMillis();
        long bubbleSortTime = bubbleSortEndTime - bubbleSortStartTime;

        long jumpSearchStartTime = currentTimeMillis();
        if (sortedDirectory != null) {
            foundEntries = initiateJumpSearch(sortedDirectory, namesToFind);
        } else {
            foundEntries = linearSearch(directory, namesToFind);
        }
        long jumpSearchEndTime = currentTimeMillis();
        long jumpSearchTime = jumpSearchEndTime - jumpSearchStartTime;

        logFoundAndTimeTaken(namesToFind, foundEntries, bubbleSortTime + jumpSearchTime);
        logSorting(bubbleSortTime, sortedDirectory == null);
        logSearching(jumpSearchTime);
    }

    public static void handleQuickSortBinarySearch(List<String> directory, List<String> namesToFind, long linearSearchTime) {
        int foundEntries;
        out.println("\nStart searching (quick sort + binary search)...");
        long quickSortStartTime = currentTimeMillis();
        List<String> sortedDirectory = quickSort(new ArrayList<>(directory), 0, directory.size() - 1, quickSortStartTime, linearSearchTime * 10);
        long quickSortEndTime = currentTimeMillis();
        long quickSortTime = quickSortEndTime - quickSortStartTime;

        long binarySearchStartTime = currentTimeMillis();
        if (sortedDirectory != null) {
            foundEntries = initiateBinarySearch(sortedDirectory, namesToFind);
        } else {
            foundEntries = linearSearch(directory, namesToFind);
        }
        long binarySearchEndTime = currentTimeMillis();
        long binarySearchTime = binarySearchEndTime - binarySearchStartTime;

        logFoundAndTimeTaken(namesToFind, foundEntries, quickSortTime + binarySearchTime);
        logSorting(quickSortTime, sortedDirectory == null);
        logSearching(binarySearchTime);
    }
}
