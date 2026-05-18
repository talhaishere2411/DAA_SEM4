import java.util.*;
public class StableMarriage{
    static int N;
    static boolean prefers(int womenpref[][], int w, int m, int m1){
        for(int i=0;i<N;i++){
            if(womenpref[w][i] == m) return true;
            if(womenpref[w][i] == m1) return false;
        }
        return false;
    }

    static void StableMarriage(int menpref[][], int womenpref[][]){
        int[] womanPartner = new int[N];
        boolean[] menFree = new boolean[N];
        int[] nextProposal = new int[N];
        Arrays.fill(menFree,true);
        Arrays.fill(womanPartner,-1);
        int freecount = N;

        while(freecount > 0){
            int m = -1;
            for(int i=0; i<N; i++){
                if(menFree[i] && nextProposal[i]<N){
                    m=i;
                    break;
                }
            }
            if(m==-1) break;

            int w = menpref[m][nextProposal[m]];
            nextProposal[m]++;
            if(womanPartner[w] == -1){
                womanPartner[w]=m;
                menFree[m] = false;
                freecount--;
            }
            else{
                int m1 = womanPartner[w];
                if(prefers(womenpref,w,m,m1)){
                    womanPartner[w] = m;
                    menFree[m] = false;
                    menFree[m1] = true; 
                }
            }
        }
        System.out.println("Stable Matching:");
        for(int i=0; i<N; i++){
            System.out.println("Woman "+i+" is matched with Man "+womanPartner[i]);
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of pairs:");
        N = sc.nextInt();

        int[][] menpref = new int[N][N];
        int[][] womenpref = new int[N][N];

        System.out.println("Enter mens preferences:");
        for(int i=0; i<N; i++){
            System.out.print("Preferences of man "+i+" :");
            for(int j=0;j<N; j++){
                menpref[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter womens preferences:");
        for(int i=0; i<N; i++){
            System.out.print("Preferences of woman "+i+" :");
            for(int j=0;j<N; j++){
                womenpref[i][j] = sc.nextInt();
            }
        }

        StableMarriage(menpref, womenpref);
        sc.close();

    }
}