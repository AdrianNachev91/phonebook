package phonebook;

import java.util.List;

import static java.lang.System.currentTimeMillis;

public class SortingAlgorithm {

    public static List<String> bubbleSort(List<String> directory, long timeLimit) {

        long startTime = currentTimeMillis();

        for (int i = 0; i < directory.size() - 1; i++) {
            for (int j = 0; j < directory.size() - i - 1; j++) {
                /* if a pair of adjacent elements has the wrong order it swaps them */
                if (directory.get(j).compareTo(directory.get(j + 1)) > 0) {
                    String temp = directory.get(j);
                    directory.set(j, directory.get(j + 1));
                    directory.set(j + 1, temp);
                }
                if (currentTimeMillis() - startTime > timeLimit) {
                    return null;
                }
            }
        }

        return directory;
    }

    public static List<String> quickSort(List<String> directory, int left, int right, long startTime, long timeLimit) {
        if (currentTimeMillis() - startTime > timeLimit) {
            return null;
        }
        if (left < right) {
            int pivotIndex = partition(directory, left, right); // the pivot is already on its place
            quickSort(directory, left, pivotIndex - 1, startTime, timeLimit);  // sort the left subarray
            quickSort(directory, pivotIndex + 1, right, startTime, timeLimit); // sort the right subarray
        }

        return directory;
    }

    private static int partition(List<String> directory, int left, int right) {
        String pivot = directory.get(right);  // choose the rightmost element as the pivot
        int partitionIndex = left; // the first element greater than the pivot

        /* move large values into the right side of the array */
        for (int i = left; i < right; i++) {
            if (directory.get(i).compareTo(pivot) <= 0) { // may be used '<' as well
                swap(directory, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(directory, partitionIndex, right); // put the pivot on a suitable position

        return partitionIndex;
    }

    private static void swap(List<String> directory, int i, int j) {
        String temp = directory.get(i);
        directory.set(i, directory.get(j));
        directory.set(j, temp);
    }
}
