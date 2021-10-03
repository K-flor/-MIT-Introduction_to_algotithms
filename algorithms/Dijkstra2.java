import java.util.*;
/*
 * 디익스트라 알고리즘 2
 * [2020-03-25]
 * MIT 책에 나온 수도코드를 보고 구현한코드로 
 * priority queue를 이용한다.
 *
*/

public class Dijkstra2{

	// G는 vertex x vertex 크기의 정점간 거리를 가지고 있는 array
	// L 은 jagged array로 L[i]는 정점 i와 인접한 정점들을 가진다. L[i][0]에는 i의 degree값.
	public static void shortestPath(int[][] G, int [][] L, int s){
		int n = L.length; // number of vertex
		int[] D = new int[n];
		boolean[] found = new boolean[n];
		
		PriorityQueue<Integer> Q = new PriorityQueue<>();
		Arrays.fill(D, Integer.MAX_VALUE);
		D[s] = 0;
		found[s] = true;
		
		for(int i : D){
			Q.add(i);
		}
	}

	public static void main(String[] args){
		PriorityQueue<Integer> q = new PriorityQueue<>();
		
		Integer[] D = {7,5,6,9,1,4,3,2};
		int[] handle = new int[D.length];

		for(int i=0; i<handle.length; i++){
			handle[i] = D[i];
		}

		for(int i: D){
			System.out.print("input :"+i+" ");
			q.add(i);
			System.out.println("-->"+ Arrays.toString(q.toArray()));
		}
		System.out.println();

		Iterator<Integer> iter = q.iterator();
		while(iter.hasNext()){
			Integer i = iter.next();
			System.out.print(i+" ");
		}
		
		D[2] = 8;
		Iterator<Integer> iter2 = q.iterator();
		while(iter.hasNext()){
			Integer i = iter2.next();
			System.out.print(i+" ");
		}
	
	}
}