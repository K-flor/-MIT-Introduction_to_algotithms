import java.util.*;
/*
 * ���ͽ�Ʈ�� �˰��� 2
 * [2020-03-25]
 * MIT å�� ���� �����ڵ带 ���� �������ڵ�� 
 * priority queue�� �̿��Ѵ�.
 *
*/

public class Dijkstra2{

	// G�� vertex x vertex ũ���� ������ �Ÿ��� ������ �ִ� array
	// L �� jagged array�� L[i]�� ���� i�� ������ �������� ������. L[i][0]���� i�� degree��.
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