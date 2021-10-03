import java.io.*;
import java.util.*;
import org.apache.commons.lang3.*;

/*----------------------------------------------------------------------------------------------------------
WEdge class 는 weight 가 주어진 간선을 나타내는 class 이다. 간선들을 weight 에 따라 오름차순으로
정렬하여야 하기 때문에 본 class 는 Comparable<WEdge> 를 implement 하여야 하며 따라서 compareTo()
method 를 overide 하여야 한다. 또한 equals() method 를 override 하고 있다.
-----------------------------------------------------------------------------------------------------------*/
class WEdge implements Comparable<WEdge> {
	int u;
	int v;
	double w;			//weight of this edge

	WEdge(int u, int v, double w) {
		assert (u < v) : "ERROR:WEdge class u > v";
		this.u = u; this.v = v; this.w = w;
	}

	@Override
	public int compareTo(WEdge other) {
		if (w - other.w > 0.0)
			return 1;
		else if (w - other.w < 0.0)
			return -1;
		else
			return 0;
	}

	@Override
	public boolean equals(Object otherObj) {
		WEdge other = (WEdge)otherObj;

		if (u == other.u && v == other.v && w == other.w)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "(" + u + "," + v + ":" + w + ")";
	}
}

class DisjointSet {
	LinkedList<HashSet<Integer>> S = new LinkedList<HashSet<Integer>>();
	int[] pos;

	DisjointSet(int size) {
		pos = new int[size];

		for (int i = 0; i < size; i++)
			pos[i] = i;

		for (int i = 0; i < size; i++) {
			HashSet<Integer> hs = new HashSet<Integer>();
			hs.add(i);
			S.add(hs);
		}
	}

	void updatePOS(HashSet<Integer> hs) {
		for (int e : hs) {
			pos[e] = pos[e] - 1;
		}
	}

	// u 가 저장되어 있는 Hashset 과 v 가 저장되어 있는 hashset 을 union 시킨다.
	// 이들의 union 은 posU, posV 중 작은 Hashset 에 저장시킨다.
	void union(int u, int v) {
		int posU = pos[u];			//posU 는 u 가 저장된 HashSet 의 S 내 index
		int posV = pos[v];			//posV 는 v 가 저장된 HashSet 의 S 내 index

		//posU == posV 라면 u, v 는 이미 동일한 HashSet 내에 저장되어 있다. 이 경우 단순히 본 method 를 종료.
		if (posU == posV)
			return;

		if (posV < posU) {			// posU < posV 가 되도록 한다.
			int tmp = posV;
			posV = posU;
			posU = tmp;
		}

		S.get(posU).addAll(S.get(posV));
		HashSet<Integer> hs = S.get(posV);

		for (int e : hs) {
			pos[e] = posU;
		}

		S.remove(posV);

		//S 에서 posV 및 그 이후의 HashSet 내 원소들의 pos 값을 1 씩 감소시킨다.
		ListIterator<HashSet<Integer>> it2 = S.listIterator(posV);

		while (it2.hasNext()) {
			HashSet<Integer> e = it2.next();
			updatePOS(e);
		}		
	}

	HashSet<Integer> findSet(int u) {
		return S.get(pos[u]);
	}
}

public class MinimumSpanningTree {
	static int[][] G;
	static int n;
	static int e;
	static DisjointSet ds;
	static LinkedList<WEdge> mst;		//MST 의 각 edge 들이 저장된다.
	static int mstCost;

	MinimumSpanningTree(int[][] G) {
		this.G = G;
		n = G.length;
		e = numEdge(G);  // e 에는 G 의 간선개수가 저장됨.
		System.out.println("num of Edge:"+e);
		mstCost = 0;

		buildMST();
	}

	void buildMST() {
		ds = new DisjointSet(n);
		WEdge[] E = new WEdge[e];

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				if (G[i][j] > 0) {
					E[cnt++] = new WEdge(i,j,G[i][j]);
				}
			}
		}
		Arrays.sort(E);
		mst = new LinkedList<WEdge>();

		for (int i = 0; i < e; i++) {
			int u = E[i].u;
			int v = E[i].v;

			if (ds.findSet(u) != ds.findSet(v)) {
				mst.add(E[i]);
				ds.union(u,v);
				mstCost += E[i].w;
			}
		}
	}

	LinkedList<WEdge> getMST() {
		return mst;
	}

	// 여기서부터는 Tool method 들이다.
	static Scanner openScanner(File file) {
		Scanner in = null;

		try {
			in = new Scanner(file);
		} catch(FileNotFoundException e) {
			System.out.println(e);
		}
		return in;
	}

	public static int numEdge(int[][] G) {
		int n = G.length;
		int cnt = 0;

		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				if (G[i][j] > 0)
					cnt++;

		return cnt;
	}

	static int[][] dimacToAdjMatWeightZero(File inGraph) throws IOException {
		Scanner in = openScanner(inGraph);
		int n, u, v, w;
		int[][] G = new int[1][1];

		while (in.hasNextLine()) {
			String str = in.nextLine();

			if (StringUtils.startsWith(str, "c") || str.isEmpty())
				continue;

			String[] token = str.split("\\s+");

			if (token[0].equals("p")) {
				n = Integer.parseInt(token[2]);
				G = new int[n][n];
			}

			if (token[0].equals("e")) {
				u = Integer.parseInt(token[1]);
				v = Integer.parseInt(token[2]);
				w = Integer.parseInt(token[3]);
				G[u][v] = G[v][u] = w;
			}
		}
		in.close();
		return G;
	}

	public static void main(String[] args) throws IOException {

		String inGraph = ".tsp";
		int[][] G = GraphReader.tspInt(inGraph);
		System.out.println("g "+G.length);

		MinimumSpanningTree app = new MinimumSpanningTree(G);
		System.out.println("Weights of Minimum MST = " + app.mstCost);

		LinkedList<WEdge> mst = app.getMST();

//		for (int i = 0; i < mst.size(); i++) {
//			System.out.println(mst.get(i));
//		}

		System.out.println(mst.size());
		double s= 0;
		for (int i = 0; i < mst.size(); i++) {
			s = s+mst.get(i).w;
			System.out.println(s);
		}
	}
}