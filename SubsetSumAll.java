import java.util.Scanner;

public class SubsetSumAll {

    static void subset(int arr[], int index, int sum, String result) {

        // If subset found
        if (sum == 0) {
            System.out.println("Subset: " + result);
            return;
        }

        // Stop condition
        if (index == arr.length || sum < 0)
            return;

        // Include current element
        subset(arr, index + 1,
               sum - arr[index],
               result + arr[index] + " ");

        // Exclude current element
        subset(arr, index + 1,
               sum,
               result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Get the size of the array
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        // 2. Initialize and populate the array
        int[] arr = new int[n];
        System.out.println("Enter the " + n + " elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // 3. Get the target sum
        System.out.print("Enter the target sum: ");
        int sum = scanner.nextInt();

        System.out.println("\nPossible Subsets:");
        subset(arr, 0, sum, "");
        
        scanner.close();
    }
}
