import java.util.*;
class graph{
        int n;
        int adj[][];
        int vis[];
        Stack<Integer> st;
        graph(){
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter number of vertices: ");
                n = sc.nextInt();
                adj = new int[n][n];
                vis = new int[n];
                st = new Stack<> ();
                System.out.println("Enter adjacency matrix: ");
                for(int i=0; i<n; i++){
                        for(int j=0; j<n; j++){
                                adj[i][j]=sc.nextInt();
                        }
                }
                sc.close();
        }
        void dfs(int v){
                vis[v]=1;
                for(int i=0; i<n; i++){
                        if(adj[v][i]==1 && vis[i]==0){
                                dfs(i);
                        }
                }
                st.push(v);
        }
        void getTopoSort(){
                for(int i=0; i<n; i++){
                        if(vis[i]==0) dfs(i);
                }
                System.out.print("Topological sort: ");
                while(!st.empty()){
                        System.out.print(st.peek() + " ");
                        st.pop();
                }
                System.out.println();
        }
}
class Toposort{
        public static void main(String args[]){
                graph g = new graph();
                g.getTopoSort();
        }
}


