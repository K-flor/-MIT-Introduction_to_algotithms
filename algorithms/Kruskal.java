import java.util.HashSet;
import java.io.*;
import java.util.Arrays;

class Edge{
	int v1,v2;
	int w ;

	Edge(int v1,int v2, int w){
		this.v1 = v1;
		this.v2 = v2;
		this.w = w;
	}
}

public class Kruskal{
	public static Edge[] makeSet(int[][] a){
		int e_num = 0;
		for(int i=0;i<a.length; i++){
			for(int j=i; j<a[0].length; j++){
				if(a[i][j] !=0 )
					e_num++;
			}
		}
		Edge[] E = new Edge[e_num];
		Edge e1;
		int index = 0;
		for(int i=0;i<a.length; i++){
			for(int j=i; j<a[0].length; j++){
				if(a[i][j] !=0 ){
					e1 = new Edge(i,j,a[i][j]);
					E[index] = e1; index++;
				}
			}
		}
		return E;
	}

	public static int min(Edge[] E){
		int min=Integer.MAX_VALUE;
		int index = 0;
		for(int i=0; i<E.length; i++){
			if(E[i] == null)
				continue;
			if( E[i].w < min ){
				min = E[i].w;
				index = i;
			}
		}
		return index;
	}

		public static int min(Edge[] E, HashSet A){
		int min=Integer.MAX_VALUE;
		int index = 0;
		for(int i=0; i<E.length; i++){
			if(A.contains(E[i].v1) & A.contains(E[i].v2))
				E[i].w = Integer.MAX_VALUE;
			if( E[i].w < min ){
				min = E[i].w;
				index = i;
			}
		}
		return index;
	}



	public static int MST(Edge[] E,int v_num){
		HashSet<Integer> A = new HashSet<>();
		Edge[] EE = new Edge[v_num];
		int weight = 0;

		while( A.size() != v_num ){
			int i = min(E);
			if( A.add(E[i].v1) | A.add(E[i].v2) ){
				weight = weight + E[i].w;
				E[i] = null;
			}
			else{
				E[i] = null;
				continue;
			}
		}
		return weight;
	}

	public static void main(String[] args)throws IOException{
		String file = "a280.tsp";

		int[][] adj1 = GraphReader.tspInt(file);
		int[][] adj = {{0,2,0,6,0},{2,0,3,8,5},{0,3,0,0,6},{6,8,0,0,9},{0,5,6,9,0}};
		Edge[] E = makeSet(adj1);
		System.out.println(MST(E,adj1.length));
	}
}