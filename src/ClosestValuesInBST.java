//Question no 4(b)
import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

public class ClosestValuesInBST {

    // Method to find the closest values to the target in a Binary Search Tree (BST)
    public static List<Integer> closestValues(TreeNode root, double target, int x) {
        List<Integer> result = new ArrayList<>();
        closestValuesHelper(root, target, x, result); // Call the helper function to find the closest values
        return result;
    }

    // Helper method to recursively traverse the BST and find the closest values to the target
    private static void closestValuesHelper(TreeNode node, double target, int x, List<Integer> result) {
        if (node == null) {
            return; // Base case: if the node is null, return
        }

        closestValuesHelper(node.left, target, x, result); // Recursively traverse the left subtree

        // Check if the result list has fewer than x elements
        if (result.size() < x) {
            result.add(node.val); // Add the current node value to the result list
        } else {
            double currentDiff = Math.abs(node.val - target); // Calculate the difference between current node value and target
            double maxDiff = Math.abs(result.get(0) - target); // Calculate the difference between the first element in result list and target

            // If the current difference is smaller than the maximum difference in result list, update result list
            if (currentDiff < maxDiff) {
                result.remove(0); // Remove the first element in the result list
                result.add(node.val); // Add the current node value to the result list
            } else {
                // Since the tree is balanced, if we encounter a larger difference, we can stop searching in the right subtree
                return;
            }
        }

        closestValuesHelper(node.right, target, x, result); // Recursively traverse the right subtree
    }

    public static void main(String[] args) {
        /*
         * Provided Tree:
         *       4
         *      / \
         *     2   5
         *    / \
         *   1   3
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        double target = 3.8;
        int x = 2;

        List<Integer> closestValues = closestValues(root, target, x);
        System.out.println("Closest values to " + target + " are: " + closestValues); // Output: [4, 5]
    }
}
