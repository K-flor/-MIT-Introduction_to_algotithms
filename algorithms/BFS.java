import java.io.*;
import java.util.ArrayDeque;

public class BFS{
	public static void main(String[] args) throws IOException {
		String file = new String("mGraph1.txt");
		byte[][] adj = GraphReader.adjacency(file);

		ArrayDeque<Integer> que = new ArrayDeque<>(adj.length);
		String[] color = new String[adj.length];
		int[] d = new int[adj.length];

		int s = 0;
		for(int i=0;i<adj.length;i++){
			if(s==i) {
				color[i] = "grey";
				d[i] = 0;
			}
			else{
				color[i] = "white";
				d[i] = Integer.MAX_VALUE;
			}
		}

		que.addFirst(s);
		while(!que.isEmpty()){
			int u = que.poll();
			for(int i=0; i<adj.length; i++){
				if(adj[u][i]==1 & color[i].equals("white")){
					color[i] = "grey";
					d[i] = d[u]+1;
					que.add(i);
				}
			}
			color[u]="black";
		}
		for(int dia : d){
			System.out.print(dia+" ");
		}
	}
}