import java.util.Scanner;

class Hp {
    String text;
    String pattern;

    Hp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text:");
        text = sc.nextLine();
        System.out.println("Enter the pattern:");
        pattern = sc.nextLine();
        sc.close();
    }

    void hors() {
        int m = pattern.length();
        int n = text.length();
        
        // Edge case: if pattern is longer than text, it can't be found
        if (m > n || m == 0) {
            System.out.println("Pattern not found in the given text");
            return;
        }

        // 1. Generate the Shift Table
        int shift[] = new int[256];
        for (int i = 0; i < 256; i++) {
            shift[i] = m;
        }
        for (int i = 0; i < m - 1; i++) {
            shift[pattern.charAt(i)] = m - 1 - i;
        }

        // 2. Search for the Pattern
        int i = m - 1;
        while (i < n) {
            int k = 0;
            
            // Compare characters from right to left
            while (k < m && (pattern.charAt(m - 1 - k) == text.charAt(i - k))) {
                k++;
            }

            // If we matched all characters in the pattern
            if (k == m) {
                System.out.println("Pattern found at index " + (i - m + 1));
                return; 
            }

            // BUG FIX: Increment 'i' by the shift amount
            i += shift[text.charAt(i)];
        }
        
        System.out.println("Pattern not found in the given text");
    }
}

class Horspool {
    public static void main(String args[]) {
        Hp obj = new Hp();
        obj.hors();
    }
}