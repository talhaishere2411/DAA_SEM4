public class TSPBB {

    static int minCost = Integer.MAX_VALUE;
    static int n = 4;

    static int[] bestPath = new int[n + 1];
    static int[] currPath = new int[n + 1];

    static void tsp(int[][] cost, boolean[] visited, int currCity, int count, int currCost) {

        if (count == n && cost[currCity][0] > 0) {
            int totalCost = currCost + cost[currCity][0];

            if (totalCost < minCost) {
                minCost = totalCost;

                for (int i = 0; i < n; i++) {
                    bestPath[i] = currPath[i];
                }

                bestPath[n] = 0; // return to start
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && cost[currCity][i] > 0) {
                int newCost = currCost + cost[currCity][i];

                // Branch and Bound (Pruning)
                if (newCost < minCost) {
                    visited[i] = true;
                    currPath[count] = i;

                    tsp(cost, visited, i, count + 1, newCost);

                    visited[i] = false; // Backtracking reset
                }
            }
        }
    }

    public static void main(String[] args) {

        int[][] cost = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        boolean[] visited = new boolean[n];

        // Start from city 0
        visited[0] = true;
        currPath[0] = 0;

        tsp(cost, visited, 0, 1, 0);

        System.out.println("Minimum Cost = " + minCost);

        System.out.print("Optimal Path = ");
        for (int i = 0; i <= n; i++) {
            System.out.print(bestPath[i] + " ");
        }
    }
}