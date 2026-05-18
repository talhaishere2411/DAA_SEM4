import java.util.*;
class Edge{
	int u,v,w;
	Edge(int u,int v, int w){
		this.u=u;
		this.v=v;
		this.w=w;
	}
}
class Graph{
	int n,e;
	boolean vis[];
	int adj[][];
	PriorityQueue<Edge> pq;
	Graph(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of vertices: ");
		n = sc.nextInt();
		adj = new int[n][n];
		vis = new boolean[n];
		pq = new PriorityQueue<Edge>((a,b)->{
			if(a.w==b.w) return Integer.compare(a.u,b.u);
			return Integer.compare(a.w,b.w);
		});
		System.out.print("Enter number of edges: ");
		e = sc.nextInt();
		System.out.println("Enter edges in the form (Source,Destination,weight)");
		for(int i=0; i<e; i++){
			int u=sc.nextInt();
			int v=sc.nextInt();
			int w=sc.nextInt();
			adj[u][v]=w;
			adj[v][u]=w;
		}
        sc.close();
	}
	void doPrims(){
		int edgeCnt=0,sum=0;
		pq.add(new Edge(-1,0,0));
		while(edgeCnt!=n-1){
			Edge e = pq.poll();
			if(vis[e.v]) continue;
			vis[e.v]=true;
			if(e.u!=-1){
				System.out.println(e.u+" - "+e.v+" -> "+e.w);
				sum+=e.w;
				edgeCnt++;
			}
			for(int i=0; i<n; i++){
				if(adj[e.v][i]!=0 && !vis[i]){
					pq.add(new Edge(e.v,i,adj[e.v][i]));
				}
			}
		}
		System.out.println("The of MST weights is: "+sum); 
	}
}
class Prim{
	public static void main(String args[]){
		Graph g = new Graph();
		g.doPrims();
	}
}
