import java.io.*;

public class DFS{
	public static void visit(byte[][] G,int u,String[] c, int[] p){
		c[u] = "grey";
		for(int v=0; v<G[u].length; v++){
			if(G[u][v]==1 & c[v].equals("white")){
				p[v] = u;
				visit(G,v,c,p);
			}
		}
		c[u] = "black";
	}

	public static void visit(int[][] G,int u,String[] c, int[] p){
		c[u] = "grey";
		for(int v : G[u]){
			if(c[v].equals("white")){
				p[v] = u;
				visit(G,v,c,p);
			}
		}
		c[u] = "black";
	}
	
	public static void main(String[] args)throws IOException{
		String file = new String("mGraph1.txt");
		byte[][] adj = GraphReader.adjacency(file);
//		int[][] vermat = GraphReader.connectV2(file);
		
		String[] color1 = new String[adj.length];
		int[] pi1 = new int[adj.length];
//		String[] color2 = new String[vermat.length];
//		int[] pi2 = new int[vermat.length];

		for(int u=0; u<adj.length; u++){
			color1[u] = "white";
			pi1[u] = -1;
		}
		
		int source = 0; //
		visit(adj,source,color1,pi1);
		for(int i : pi1) System.out.print(i+" ");
		System.out.println();

//		visit(vermat,source,color2,pi2);
//		for(int i : pi2) System.out.print(i+" ");
	}
}