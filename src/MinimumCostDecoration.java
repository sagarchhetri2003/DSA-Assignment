//question no 1(a)
public class MinimumCostDecoration {

    // Method to calculate the minimum cost to decorate the venues
    public static int minCostToDecorate(int[][] costs) {
        // Check if the costs array is valid
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0; // Return 0 if costs array is empty or invalid
        }

        // Get the number of venues (rows) and decoration options (columns)
        int n = costs.length;
        int k = costs[0].length;

        // Initialize variables to keep track of the minimum and second minimum cost
        int minCost = 0;
        int secondMinCost = 0;
        int lastMinIndex = -1; // Index of the last used decoration option

        // Iterate through the venues
        for (int i = 0; i < n; i++) {
            int currentMinCost = Integer.MAX_VALUE; // Initialize current minimum cost for the current venue
            int currentSecondMinCost = Integer.MAX_VALUE; // Initialize current second minimum cost for the current venue
            int currentMinIndex = -1; // Initialize index of the current minimum cost decoration option

            // Update the costs for the current venue
            for (int j = 0; j < k; j++) {
                // Calculate the total cost including the cost of the current decoration option
                int cost = costs[i][j] + (j == lastMinIndex ? secondMinCost : minCost);

                // Update the current minimum and second minimum costs along with the index of the minimum cost option
                if (cost < currentMinCost) {
                    currentSecondMinCost = currentMinCost;
                    currentMinCost = cost;
                    currentMinIndex = j;
                } else if (cost < currentSecondMinCost) {
                    currentSecondMinCost = cost;
                }
            }

            // Update the overall minimum and second minimum costs for the next iteration
            minCost = currentMinCost;
            secondMinCost = currentSecondMinCost;
            lastMinIndex = currentMinIndex;
        }

        // The minimum cost is in the last row
        return minCost;
    }

    // Main method to test the MinimumCostDecoration class
    public static void main(String[] args) {
        int[][] costMatrix = {{1, 3, 2}, {4, 6, 8}, {3, 1, 5}}; // Example cost matrix
        int result = minCostToDecorate(costMatrix); // Calculate the minimum cost to decorate
        System.out.println(result); // Output the result
    }
}
