package phonebook;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static java.lang.System.out;

public class LogHelper {

    public static void logFoundAndTimeTaken(List<String> namesToFind, int foundEntries, long linearSearchTime) {
        int seconds = (int) (linearSearchTime / 1000);
        int finalMilliseconds = (int) (linearSearchTime % 1000);
        int finalMinutes = seconds / 60;
        int finalSeconds = seconds % 60;

        out.println("Found " + foundEntries + " / " + namesToFind.size() + " entries. Time taken: " + logTimeTaken(finalMinutes, finalSeconds, finalMilliseconds));
    }

    @NotNull
    private static String logTimeTaken(int finalMinutes, int finalSeconds, int finalMilliseconds) {
        return finalMinutes + " min. " + finalSeconds + " sec. " + finalMilliseconds + " ms.";
    }

    public static void logSorting(long endTime, boolean stopped) {
        int seconds = (int) (endTime / 1000);
        int finalMilliseconds = (int) (endTime % 1000);
        int finalMinutes = seconds / 60;
        int finalSeconds = seconds % 60;

        out.println("Sorting time: " + logTimeTaken(finalMinutes, finalSeconds, finalMilliseconds) + (stopped ? " - STOPPED, moved to linear search" : ""));
    }

    public static void logSearching(long endTime) {
        int seconds = (int) (endTime / 1000);
        int finalMilliseconds = (int) (endTime % 1000);
        int finalMinutes = seconds / 60;
        int finalSeconds = seconds % 60;

        out.println("Searching time: " + logTimeTaken(finalMinutes, finalSeconds, finalMilliseconds));
    }
}
