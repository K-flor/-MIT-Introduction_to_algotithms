import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
	int v1, v2;
	int w;

	Edge(int v1, int v2, int w){
		this.v1 = v1;
		this.v2 = v2;
		this.w = w;
	}

	@Override
	public int compareTo(Edge other) {
		return w - other.w;
	}

	@Override
	public String toString(){
		return "Edge "+v1+"-"+v2+"(weight = "+w+")\n";
	}
}

public class Kruskal2{
	public static Edge[] makeEdgeSet(int[][] adj){
		int numE = calNumEdge(adj);
		System.out.println("num of edge : "+numE);
		Edge[] es = new Edge[numE];
		int n = 0;

		for(int i=0; i<adj.length; i++){
			for(int j=i; j < adj[i].length; j++){
				if( adj[i][j] != 0 )
					es[n++] = new Edge(i, j, adj[i][j]);
			}
		}
		return es;
	}

	public static int calNumEdge(int[][] adj){
		int cnt = 0;
		for(int i=0; i<adj.length; i++){
			for(int j=i; j < adj[i].length; j++){
				if(adj[i][j] != 0)
					cnt++;
			}
		}
		return cnt;
	}

	public static int find(int[] id,int a){
		if( a == id[a] )
			return a;
		else{
			id[a] = find(id, id[a]);
			return id[a];
		}
	}

	public static boolean union(int[] id, int a, int b){
		int aRoot = find(id, a);
		int bRoot = find(id, b);

		if(aRoot == bRoot)
			return false;
		else{
			id[aRoot] = bRoot;
			return true;
		}
	}

/* union find function */
	public static void ufMST(Edge[] es, int numV){
		Arrays.sort(es);
		HashSet<Edge> mst = new HashSet<>();

		int[] id = new int[numV];
		for(int i=0; i<id.length; i++)
			id[i] = i;

		for(Edge e : es){
			int p = e.v1; int q = e.v2;

			if( union(id, p, q) ){
				mst.add(e);
			}

			if( mst.size() == numV )
				break;
		}

		Iterator<Edge> Iter = mst.iterator();
		int sum = 0;
		Edge tmp;

		while( Iter.hasNext() ){
			tmp = Iter.next();
			sum = sum + tmp.w;
			//System.out.println(tmp);
		}
		System.out.println(sum);
	}

/* myyyyy */
	public static void MST(Edge[] es, int numV){
		Arrays.sort(es);
		HashSet<Edge> mst = new HashSet<>();
				
		int[] id = new int[numV];
		for(int i=0; i<id.length; i++)
			id[i] = i;

		for(Edge e : es){
			int p = e.v1; int q = e.v2;

			if(id[p] == id[q])
				continue;	
			
			for(int i=0; i < numV; i++){
				if( id[i] == id[p])
					id[i] = id[q];
			}
			mst.add(e);
			if(mst.size() == numV-1)
				break;
		}
		System.out.println(mst.size());

		Iterator<Edge> Iter = mst.iterator();
		int sum = 0;
		Edge tmp;

		while( Iter.hasNext() ){
			tmp = Iter.next();
			sum = sum + tmp.w;
			//System.out.println(tmp);
		}
		System.out.println(sum);
	}

	public static void main(String[] args) throws IOException {
		String file = "a280.tsp";
		int[][] adj = GraphReader.tspInt(file);
		int numV = adj.length;
		System.out.println(numV);

		//for(int i=0; i < numV; i++)
		//	System.out.println(Arrays.toString(adj[i]));

		Edge[] E = makeEdgeSet(adj);
		//System.out.println(Arrays.toString(E));
		ufMST(E, numV);
		
	}
}