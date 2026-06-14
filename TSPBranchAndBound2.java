import java.util.Arrays;

public class TSPBranchAndBound2 {

    private int N;
    private int[] finalPath;
    private boolean[] visited;
    private int finalRes = Integer.MAX_VALUE;

    public TSPBranchAndBound2(int n) {
        this.N = n;
        finalPath = new int[N + 1];
        visited = new boolean[N];
    }

    // Function to find the minimum edge cost ending at vertex i
    private int firstMin(int[][] adj, int i) {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < N; k++) {
            if (adj[i][k] < min && i != k) {
                min = adj[i][k];
            }
        }
        return min;
    }

    // Function to find the second minimum edge cost ending at vertex i
    private int secondMin(int[][] adj, int i) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int j = 0; j < N; j++) {
            if (i == j) continue;
            if (adj[i][j] <= first) {
                second = first;
                first = adj[i][j];
            } else if (adj[i][j] <= second && adj[i][j] != first) {
                second = adj[i][j];
            }
        }
        return second;
    }

    // Recursive function for branch and bound
    private void TSPRec(int[][] adj, int currBound, int currWeight, int level, int[] currPath) {
        // Base case: all cities have been visited
        if (level == N) {
            // Check if there is an edge from the last visited city to the starting city
            if (adj[currPath[level - 1]][currPath[0]] != 0) {
                int currRes = currWeight + adj[currPath[level - 1]][currPath[0]];

                // Update final result and final path if current result is better
                if (currRes < finalRes) {
                    System.arraycopy(currPath, 0, finalPath, 0, N);
                    finalPath[N] = currPath[0];
                    finalRes = currRes;
                }
            }
            return;
        }

        // Try exploring all other cities
        for (int i = 0; i < N; i++) {
            // Consider city 'i' only if it is not visited and there is a direct edge
            if (adj[currPath[level - 1]][i] != 0 && !visited[i]) {
                int temp = currBound;
                currWeight += adj[currPath[level - 1]][i];

                // Calculate the new bound for the next level
                if (level == 1) {
                    currBound -= ((firstMin(adj, currPath[level - 1]) + firstMin(adj, i)) / 2);
                } else {
                    currBound -= ((secondMin(adj, currPath[level - 1]) + firstMin(adj, i)) / 2);
                }

                // If the current lower bound + current weight is strictly less than the final result, 
                // we explore further (branching). Otherwise, we prune this path.
                if (currBound + currWeight < finalRes) {
                    currPath[level] = i;
                    visited[i] = true;

                    // Recursively call for the next level
                    TSPRec(adj, currBound, currWeight, level + 1, currPath);
                }

                // Backtrack: restore the weight, bound, and visited array state
                currWeight -= adj[currPath[level - 1]][i];
                currBound = temp;

                // Reset visited array
                Arrays.fill(visited, false);
                for (int j = 0; j <= level - 1; j++) {
                    visited[currPath[j]] = true;
                }
            }
        }
    }

    public int solve(int[][] adj) {
        int[] currPath = new int[N + 1];
        Arrays.fill(currPath, -1);

        // Calculate initial bound
        int currBound = 0;
        for (int i = 0; i < N; i++) {
            currBound += (firstMin(adj, i) + secondMin(adj, i));
        }
        
        // Rounding off the lower bound
        currBound = (currBound == 1) ? currBound / 2 + 1 : currBound / 2;

        // Start from the first city (index 0)
        visited[0] = true;
        currPath[0] = 0;

        // Start recursive search
        TSPRec(adj, currBound, 0, 1, currPath);

        return finalRes;
    }

    public static void main(String[] args) {
        int[][] distanceGraph = {
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 }
        };

        TSPBranchAndBound2 tsp = new TSPBranchAndBound2(distanceGraph.length);
        int minimumCost = tsp.solve(distanceGraph);
        
        System.out.println("Minimum cost of the TSP tour is: " + minimumCost);
    }
}