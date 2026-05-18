import java.util.*;

class PathNode {
    int u, w;
    PathNode(int u, int w) {
        this.u = u;
        this.w = w;
    }
}

class Graph {
    int n, e;
    int dist[];
    int adj[][];
    PriorityQueue<PathNode> pq;
    Scanner sc = new Scanner(System.in);
    
    Graph() {
        System.out.print("Enter number of vertices: ");
        n = sc.nextInt();
        adj = new int[n][n];
        dist = new int[n];
        
        for(int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        
        pq = new PriorityQueue<PathNode>((a, b) -> {
            if (a.w == b.w) return Integer.compare(a.u, b.u);
            return Integer.compare(a.w, b.w);
        });
        
        System.out.print("Enter number of edges: ");
        e = sc.nextInt();
        System.out.println("Enter edges in the form (Source, Destination, weight):");
        for(int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adj[u][v] = w;
            adj[v][u] = w; // Assuming undirected graph based on your original code
        }
    }
    
    void doDijkstra() {
        System.out.print("Enter the source: ");
        int src = sc.nextInt();
        dist[src] = 0; 
        pq.add(new PathNode(src, 0));
        
        while(pq.size() != 0) {
            PathNode current = pq.poll();
            
            // Optimization: Skip if we've already found a shorter path to this node
            if(current.w > dist[current.u]) continue;
            
            for(int i = 0; i < n; i++) {
                // FIXED: Check if an edge actually exists (adj[current.u][i] != 0)
                if(adj[current.u][i] != 0 && dist[current.u] + adj[current.u][i] < dist[i]) {
                    dist[i] = dist[current.u] + adj[current.u][i];
                    pq.add(new PathNode(i, dist[i]));
                }
            }
        }
        
        System.out.println("\nMinimum distance of each node from source using Dijkstra:");
        for(int i = 0; i < n; i++) {
            System.out.println(i + " -> " + dist[i]);
        }
        sc.close();
    }
}

public class Dijkstra {
    public static void main(String args[]) {
        Graph g = new Graph();
        g.doDijkstra();
    }
}