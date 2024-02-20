//question no3(a)
import java.util.PriorityQueue;

// Class to track scores and calculate the median efficiently
public class ScoreTracker {

    private PriorityQueue<Double> minHeap; // Stores the larger half of the scores
    private PriorityQueue<Double> maxHeap; // Stores the smaller half of the scores

    // Constructor to initialize the ScoreTracker
    public ScoreTracker() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b, a)); // Max heap comparator
    }

    // Method to add a score to the tracker
    public void addScore(double score) {
        // Determine which heap to add the score to
        if (maxHeap.isEmpty() || score <= maxHeap.peek()) {
            maxHeap.offer(score); // Add to the max heap if it's empty or the score is less than or equal to the max
        } else {
            minHeap.offer(score); // Otherwise, add to the min heap
        }

        // Balance the heaps to ensure their sizes differ by at most 1
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll()); // Move the maximum from maxHeap to minHeap if necessary
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll()); // Move the minimum from minHeap to maxHeap if necessary
        }
    }

    // Method to calculate and return the median score
    public double getMedianScore() {
        // Check if both heaps are empty
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            throw new IllegalStateException("No scores available.");
        }

        // Determine median based on heap sizes
        if (maxHeap.size() == minHeap.size()) {
            // Even number of scores, return the average of the two middle scores
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            // Odd number of scores, return the middle score
            return maxHeap.peek();
        }
    }

    // Main method to test the ScoreTracker
    public static void main(String[] args) {
        ScoreTracker scoreTracker = new ScoreTracker();
        scoreTracker.addScore(85.5);
        scoreTracker.addScore(92.3);
        scoreTracker.addScore(77.8);
        scoreTracker.addScore(90.1);
        double median1 = scoreTracker.getMedianScore();
        System.out.println("Median 1: " + median1);

        scoreTracker.addScore(81.2);
        scoreTracker.addScore(88.7);
        double median2 = scoreTracker.getMedianScore();
        System.out.println("Median 2: " + median2);
    }
}
