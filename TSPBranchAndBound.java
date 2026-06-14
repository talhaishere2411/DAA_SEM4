import java.util.Arrays;

public class TSPBranchAndBound {
    private int numberOfCities;
    private int[] bestPath;
    private boolean[] visited;
    private int minimumCost = Integer.MAX_VALUE;

    public TSPBranchAndBound(int numberOfCities) {
        this.numberOfCities = numberOfCities;
        // The path will have numberOfCities + 1 to return to the starting city
        this.bestPath = new int[numberOfCities + 1];
        this.visited = new boolean[numberOfCities];
    }

    // Utility function to copy the current path to the best path
    private void saveBestPath(int[] currentPath) {
        for (int i = 0; i < numberOfCities; i++) {
            bestPath[i] = currentPath[i];
        }
        bestPath[numberOfCities] = currentPath[0]; // Return to start
    }

    // Finds the absolute minimum edge cost leaving a given city
    private int getFirstMinEdge(int[][] distanceMatrix, int city) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfCities; i++) {
            if (distanceMatrix[city][i] < min && city != i) {
                min = distanceMatrix[city][i];
            }
        }
        return min;
    }

    // Finds the second minimum edge cost leaving a given city
    private int getSecondMinEdge(int[][] distanceMatrix, int city) {
        int firstMin = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfCities; i++) {
            if (city == i) continue;

            if (distanceMatrix[city][i] <= firstMin) {
                secondMin = firstMin;
                firstMin = distanceMatrix[city][i];
            } else if (distanceMatrix[city][i] <= secondMin) {
                secondMin = distanceMatrix[city][i];
            }
        }
        return secondMin;
    }

    // The core Branch and Bound recursive function
    private void tspRecursive(int[][] distanceMatrix, int currentBound, int currentWeight, 
                              int level, int[] currentPath) {
        
        // Base Case: We have visited all cities
        if (level == numberOfCities) {
            // Check if there is a path back to the starting city
            if (distanceMatrix[currentPath[level - 1]][currentPath[0]] != 0) {
                int totalCost = currentWeight + distanceMatrix[currentPath[level - 1]][currentPath[0]];
                
                // If this complete path is better than our current best, update it
                if (totalCost < minimumCost) {
                    saveBestPath(currentPath);
                    minimumCost = totalCost;
                }
            }
            return;
        }

        // Try to visit all other cities from the current city
        for (int i = 0; i < numberOfCities; i++) {
            // If the city is not visited and there is a valid path
            if (distanceMatrix[currentPath[level - 1]][i] != 0 && !visited[i]) {
                
                int previousBound = currentBound; // Store the bound to backtrack later
                currentWeight += distanceMatrix[currentPath[level - 1]][i]; // Add distance

                // Calculate the new bound for the remaining cities
                if (level == 1) {
                    currentBound -= ((getFirstMinEdge(distanceMatrix, currentPath[level - 1]) + 
                                      getFirstMinEdge(distanceMatrix, i)) / 2);
                } else {
                    currentBound -= ((getSecondMinEdge(distanceMatrix, currentPath[level - 1]) + 
                                      getFirstMinEdge(distanceMatrix, i)) / 2);
                }

                // BRANCH & BOUND CORE LOGIC: 
                // Only explore this branch if the current weight + best possible future cost 
                // is strictly less than the minimum cost we've already found.
                if (currentBound + currentWeight < minimumCost) {
                    currentPath[level] = i;
                    visited[i] = true;
                    
                    // Move to the next level (next city)
                    tspRecursive(distanceMatrix, currentBound, currentWeight, level + 1, currentPath);
                }

                // BACKTRACKING: Undo changes to explore other potential paths
                currentWeight -= distanceMatrix[currentPath[level - 1]][i];
                currentBound = previousBound;
                
                // Reset the visited array for the backtracked nodes
                Arrays.fill(visited, false);
                for (int j = 0; j <= level - 1; j++) {
                    visited[currentPath[j]] = true;
                }
            }
        }
    }

    // Public method to start the algorithm
    public void solve(int[][] distanceMatrix) {
        int[] currentPath = new int[numberOfCities + 1];
        int initialBound = 0;
        Arrays.fill(currentPath, -1);
        Arrays.fill(visited, false);

        // Step 1: Calculate the initial bound 
        // (Sum of the two smallest edges for every node, divided by 2)
        for (int i = 0; i < numberOfCities; i++) {
            initialBound += (getFirstMinEdge(distanceMatrix, i) + 
                             getSecondMinEdge(distanceMatrix, i));
        }
        // Rounding off for odd bounds
        initialBound = (initialBound == 1) ? (initialBound / 2) + 1 : initialBound / 2;

        // Step 2: Start from the first city (City 0)
        visited[0] = true;
        currentPath[0] = 0;

        // Step 3: Trigger the recursion
        tspRecursive(distanceMatrix, initialBound, 0, 1, currentPath);

        // Step 4: Print the results
        System.out.println("Minimum Cost: " + minimumCost);
        System.out.print("Optimal Path: ");
        for (int i = 0; i <= numberOfCities; i++) {
            System.out.print(bestPath[i] + (i == numberOfCities ? "" : " -> "));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Distance matrix representing the cost to travel between cities
        // 0 means you cannot travel from a city to itself
        int[][] distanceMatrix = {
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 }
        };

        TSPBranchAndBound tsp = new TSPBranchAndBound(distanceMatrix.length);
        tsp.solve(distanceMatrix);
    }
}
