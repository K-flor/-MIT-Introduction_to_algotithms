/*
MIT 책 chapter 23(p.634)의 수도코드를 보고 구현했다.
priority Queue를 이용하지 않고, MST 함수에서 for loop과정을 마친 정점을 inT 배열에서 True값을 전달하며 
Q에서 정점을 제거하는 명령문을 대체하였다.
*/
import java.io.*;
import java.util.Arrays;

public class Prim{
	public static int min(int[] k, boolean[] in){
		int min=Integer.MAX_VALUE;
		int index=-1;
		for(int i=0; i<k.length; i++){
			if(k[i]<min & in[i]==false){
				index = i;
				min = k[i];
			}
		}
		return index;
	}

	public static boolean allTrue(boolean[] a){
		for(boolean b:a)
			if(!b) return false;
		return true;
	}

	public static int[] MST(int[][] adj,int r){
		int v_num = adj.length;
		int[] key = new int[v_num];
		int[] parent = new int[v_num];
		boolean[] inT = new boolean[v_num];

		for(int i=0;i<v_num;i++){
			key[i] = Integer.MAX_VALUE;
			parent[i] = -1;
			inT[i] = false;
		}
		key[r] = 0;

		while( !allTrue(inT) ){
			int u = min(key,inT);
			inT[u] = true;
			for(int i=0; i<adj[u].length; i++){
				if(adj[u][i] !=0 & inT[i]==false & adj[u][i]<key[i]){
					parent[i] = u;
					key[i] = adj[u][i];
				}
			}
		}
		return key;
	}

	public static int sum(int[] a){
		int s=0;
		for(int i : a){
			s = s+i;
		}
		return s;
	}

	public static void main(String[] args) throws IOException {
		String file = "a280.tsp";

		int[][] adj = GraphReader.tspInt(file);
		int root = 0;

		//int[][] adj11 = {{0,2,0,6,0},{2,0,3,8,5},{0,3,0,0,6},{6,8,0,0,9},{0,5,6,9,0}};
		int[] p = MST(adj,root);
		System.out.println(Arrays.toString(p));
		System.out.println(sum(p));
		System.out.println(p.length);
	}
}