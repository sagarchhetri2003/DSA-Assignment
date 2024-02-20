//Question no 1(b)
import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumEngineBuildTime {

    // Method to calculate the minimum time required to build engines
    public static int minTimeToBuildEngines(int[] engines, int splitCost) {
        int n = engines.length;
        Arrays.sort(engines); // Sort the array of engine build times

        // Use a priority queue to track the time taken by each engineer
        PriorityQueue<Integer> timeQueue = new PriorityQueue<>();

        // Initially, there is one engineer
        timeQueue.offer(0);

        // Iterate through the engine build times in descending order
        for (int i = n - 1; i >= 0; i--) {
            int shortestTime = timeQueue.poll(); // Get the shortest time taken by an engineer
            timeQueue.offer(shortestTime + engines[i]); // Add the time taken by the current engine
            timeQueue.offer(shortestTime + splitCost); // Add the split cost time
        }

        // The total time is the maximum time taken by any engineer
        return timeQueue.poll();
    }

    // Main method to test the MinimumEngineBuildTime class
    public static void main(String[] args) {
        int[] engines = {1,2,3}; // Array of engine build times
        int splitCost = 2; // Split cost time
        int result = minTimeToBuildEngines(engines, splitCost); // Calculate the minimum build time
        System.out.println(result); // Output the result
    }
}
