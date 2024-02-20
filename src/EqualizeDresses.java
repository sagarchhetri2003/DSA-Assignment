
//Question 2 (a)



public class EqualizeDresses {
    // Method to calculate the minimum number of moves required to equalize the number of dresses
    // in each closet
    public static int minMovesToEqualize(int[] dresses) {
        // Calculate the total number of dresses across all closets
        int totalDresses = 0;
        for (int dress : dresses) {
            totalDresses += dress;
        }

        // If the total number of dresses cannot be evenly distributed among all closets,
        // return -1 indicating it's not possible to equalize
        int n = dresses.length;
        if (totalDresses % n != 0) {
            return -1;
        }

        // Calculate the target number of dresses each closet should have after equalization
        int target = totalDresses / n;
        int moves = 0;

        // Iterate through each closet and perform necessary moves to equalize the number of dresses
        for (int i = 0; i < n; i++) {
            // If the current closet has more dresses than the target, move excess dresses to the next closet
            if (dresses[i] > target) {
                moves += dresses[i] - target;  // Increment moves count by the excess dresses
                dresses[(i + 1) % n] += dresses[i] - target;  // Move excess dresses to the next closet
                dresses[i] = target;  // Set the current closet's dress count to the target
            }
        }
        return moves;  // Return the total number of moves required to equalize
    }

    // Main method for testing the minMovesToEqualize function
    public static void main(String[] args) {
        int[] inputDresses = {1, 0, 5};
        System.out.println(minMovesToEqualize(inputDresses));
    }
}
