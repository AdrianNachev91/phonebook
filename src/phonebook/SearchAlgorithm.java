package phonebook;

import java.util.List;

public class SearchAlgorithm {

    public static int linearSearch(List<String> directory, List<String> namesToFind) {
        int foundEntries = 0;

        for (String nameToFind : namesToFind) {
            for (String currentInDirectory : directory) {
                if (currentInDirectory.equals(nameToFind)) {
                    foundEntries++;
                    break;
                }
            }
        }
        return foundEntries;
    }

    public static int initiateJumpSearch(List<String> directory, List<String> namesToFind) {
        int foundEntries = 0;

        for (String target : namesToFind) {
            if (jumpSearch(directory, target)) {
                foundEntries++;
            }
        }

        return foundEntries;
    }

    private static boolean jumpSearch(List<String> sortedDirectory, String target) {
        int currentRight = 0; // right border of the current block
        int prevRight = 0; // right border of the previous block

        /* If array is empty, the element is not found */
        if (sortedDirectory.size() == 0) {
            return false;
        }

        /* Check the first element */
        if (sortedDirectory.get(currentRight).equals(target)) {
            return true;
        }

        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(sortedDirectory.size());

        /* Finding a block where the element may be present */
        while (currentRight < sortedDirectory.size() - 1) {

            /* Calculating the right border of the following block */
            currentRight = Math.min(sortedDirectory.size() - 1, currentRight + jumpLength);

            if (sortedDirectory.get(currentRight).compareTo(target) >= 0) {
                break; // Found a block that may contain the target element
            }

            prevRight = currentRight; // update the previous right block border
        }

        /* If the last block is reached and it cannot contain the target value => not found */
        if ((currentRight == sortedDirectory.size() - 1) && target.compareTo(sortedDirectory.get(currentRight)) > 0) {
            return false;
        }

        /* Doing linear search in the found block */
        return backwardSearch(sortedDirectory, target, prevRight, currentRight);
    }

    private static boolean backwardSearch(List<String> list, String target, int leftExcl, int rightIncl) {
        for (int i = rightIncl; i > leftExcl; i--) {
            if (list.get(i).equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static int initiateBinarySearch(List<String> directory, List<String> namesToFind) {
        int foundEntries = 0;

        for (String target : namesToFind) {
            if (binarySearch(directory, target, 0, directory.size() - 1)) {
                foundEntries++;
            }
        }

        return foundEntries;
    }

    private static boolean binarySearch(List<String> sortedDirectory, String target, int left, int right) {

        if (left > right) {
            return false; // search interval is empty, the element is not found
        }

        int mid = left + (right - left) / 2; // the index of the middle element

        if (target.equals(sortedDirectory.get(mid))) {
            return true; // the element is found, return its index
        } else if (target.compareTo(sortedDirectory.get(mid)) < 0) {
            return binarySearch(sortedDirectory, target, left, mid - 1); // go to the left subarray
        } else {
            return binarySearch(sortedDirectory, target, mid + 1, right); // go the the right subarray
        }


    }
}
