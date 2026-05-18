import java.util.Arrays;
import java.util.Scanner;
public class BellmanFord {
    public static void bellmanford(int[][] edges, int V, int src){
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for(int i=1; i<=V; i++){
            for(int[] edge : edges){
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                if((dist[u] != Integer.MAX_VALUE) && (dist[u] + wt) < dist[v]){
                    dist[v] = dist[u] + wt;
                }
            }
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            if((dist[u] != Integer.MAX_VALUE) && (dist[u] + wt) < dist[v]){
                System.out.println("Negative weight cycle detected!");
                return;
            }
        }
        System.out.println("Vertex\tDistance from source ("+src+")");
        for(int i=0;i<V;i++){
            System.out.println(i+"\t\t"+dist[i]);
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();
        int[][] edges = new int[E][3];
        System.out.println("Enter edges in the form (Source, Destination, weight):");
        for(int i=0; i<E; i++){
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            edges[i][2] = sc.nextInt();
        }
        System.out.print("Enter the source vertex: ");
        int src = sc.nextInt();
        bellmanford(edges, V, src);
        sc.close();
    }
}
