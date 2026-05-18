import java.util.Scanner;

public class AllNQueens {
    // Declare N and board, but initialize them later in main
    static int N; 
    static int[] board; 
    static int solutionCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Prompt the user for the board size
        System.out.print("Enter the number of queens (N): ");
        N = scanner.nextInt();
        
        // 2. Initialize the board array now that N is known
        board = new int[N];
        
        System.out.println("\nFinding solutions for " + N + " Queens...\n");
        
        solve(0);
        System.out.println("Total solutions: " + solutionCount);
        
        scanner.close();
    }

    static void solve(int row) {
        if (row == N) {
            printBoard();
            solutionCount++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row] = col; // Place queen
                solve(row + 1);   // Recurse for next row
                // No need to reset board[row] since it will be overwritten
            }
        }
    }

    static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(i - row) == Math.abs(board[i] - col)) {
                return false; // Same column or same diagonal
            }
        }
        return true;
    }

    static void printBoard() {
        System.out.println("Solution " + (solutionCount + 1) + ":");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i] == j) System.out.print("Q ");
                else System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println();
    }
}