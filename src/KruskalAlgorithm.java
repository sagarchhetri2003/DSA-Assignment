//Question no 3(b)
import java.util.*;

// Represents an edge in the graph
class Edge {
    int source, destination, weight;

    // Constructor to initialize the edge
    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

// Represents the graph and its operations
class Graph {
    private int vertices; // Number of vertices in the graph
    private List<Edge> edges; // List to store edges

    // Constructor to initialize the graph with a given number of vertices
    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>(); // Initialize the list of edges
    }

    // Method to add an edge to the graph
    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight)); // Add the edge to the list of edges
    }

    // Method to find the Minimum Spanning Tree (MST) using Kruskal's algorithm
    public List<Edge> kruskalMST() {
        List<Edge> result = new ArrayList<>(); // List to store the MST edges

        // Sort the edges based on their weights
        edges.sort(Comparator.comparingInt(e -> e.weight));

        // Create a disjoint set to keep track of connected components
        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i; // Initialize each vertex as its own parent (initially disjoint)
        }

        // Perform Kruskal's algorithm
        for (Edge edge : edges) {
            int rootSource = find(parent, edge.source); // Find the root parent of the source vertex
            int rootDest = find(parent, edge.destination); // Find the root parent of the destination vertex

            // Add the edge to the MST only if it doesn't create a cycle
            if (rootSource != rootDest) {
                result.add(edge); // Add the edge to the MST
                union(parent, rootSource, rootDest); // Merge the two disjoint sets
            }
        }

        return result; // Return the list of MST edges
    }

    // Method to find the root parent of a vertex (with path compression)
    private int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent, parent[vertex]); // Path compression
        }
        return parent[vertex]; // Return the root parent of the vertex
    }

    // Method to merge two disjoint sets (union operation)
    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x); // Find the root parent of vertex x
        int rootY = find(parent, y); // Find the root parent of vertex y
        parent[rootX] = rootY; // Set the root parent of x as the parent of y
    }
}

// Main class to test the Kruskal's algorithm implementation
public class KruskalAlgorithm {
    public static void main(String[] args) {
        int vertices = 4; // Number of vertices in the graph
        Graph graph = new Graph(vertices); // Create a graph with the given number of vertices

        // Add edges to the graph
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        // Find the Minimum Spanning Tree (MST) using Kruskal's algorithm
        List<Edge> minimumSpanningTree = graph.kruskalMST();

        // Print the edges of the Minimum Spanning Tree
        System.out.println("Minimum Spanning Tree edges:");
        for (Edge edge : minimumSpanningTree) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
}
