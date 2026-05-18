import java.util.*;

class Knapsack01 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of objects: ");
        int n = sc.nextInt();

        int w[] = new int[n + 1];
        int p[] = new int[n + 1];
        // FIXED: Start i at 1 so w[1] matches the first item in the DP table v[1][j]
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter weight and profit for Item " + i + ": ");
            w[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }

        System.out.print("Enter capacity: ");
        int m = sc.nextInt();
        int v[][] = new int[n + 1][m + 1];
        sc.close();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (w[i] <= j) {
                    // Now w[i] and p[i] correctly point to the current item i
                    v[i][j] = Math.max(v[i - 1][j], v[i - 1][j - w[i]] + p[i]);
                } else {
                    v[i][j] = v[i - 1][j];
                }
            }
        }

        System.out.println("Max Profit: " + v[n][m]);
        System.out.println("Selected Items:");
        for (int i = n, j = m; i > 0 && j > 0; i--) {
            if (v[i][j] != v[i - 1][j]) {
                System.out.println("Item " + i);
                j -= w[i];
            }
        }
    }
}