import java.util.Scanner;
public class Floyds {
    
    public void compute(int[][] dist){
        int V = dist.length;
        for(int k=0; k<V; k++){
            for(int i=0; i<V ; i++){
                for(int j=0; j<V ; j++){
                    if((dist[i][k] + dist[k][j])<dist[i][j]){
                        dist[i][j] = dist[i][k]+dist[k][j];
                    }
                }
            }
        }
    }

    public static void main(String args[]){
        Scanner sc =  new Scanner(System.in);
        System.out.print("Enter number of nodes:");
        int n = sc.nextInt();
        int[][] graph = new int[n][n];
        System.out.println("Enter the adjacency matrix (999 for no edges, 0 for diagonal):");
        for(int i=0; i<n; i++){
            for(int j=0;j<n;j++){
                graph[i][j] = sc.nextInt();
            }
        }
        sc.close();

        new Floyds().compute(graph);

        System.out.println("Output distance matrix:");
        for(int[] row :graph){
            for(int val : row){
                System.out.print(val+" ");
            }
            System.out.println();
        }

    }
}
