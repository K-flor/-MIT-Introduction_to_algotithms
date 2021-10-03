//import java.lang.Math;

public class HeapSort{
	public static void buildMaxHeap(int[] A,int n){
		int s=(int)Math.floor(n/2);
		
		for(int i=s;i>0;i--)
			maxHeapify(A,i,n);
	}
	
	public static void maxHeapify(int[] A,int i,int n){
		int l = 2*i;
		int r = 2*i+1;
		int largest = 0;

		if( l<=n && A[i]<A[l] )
			largest = l;
		else
			largest = i;
		if( r<=n && A[largest]<A[r])
			largest = r;
		
		if(largest != i){
			int tmp = A[i];
			A[i] = A[largest]; A[largest] = tmp;
			maxHeapify(A,largest,n);
		}
	}

	public static void buildMinHeap(int[] A,int n){
		int s=(int)Math.floor(n/2);
		for(int i=s;i>0;i--)
			minHeapify(A,i,n);
	}

	public static void minHeapify(int[] A,int i, int n){
		int l = 2*i;
		int r = 2*i+1;
		int smallest = 0;

		if(l<=n && A[i]>A[l])
			smallest = l;
		else
			smallest = i;
		if(r<=n && A[r]<A[smallest])
			smallest = r;

		if(smallest != i){
			int tmp = A[i];
			A[i] = A[smallest]; A[smallest] = tmp;
			minHeapify(A,smallest,n);
		}
	}

// heap 이 유효한지 검사
	public static boolean isValidMax(int[] A,int i, int n){	
		if( (2*i)<=n ){
			boolean l_boo=false;

			if(A[2*i] < A[i])
				l_boo = isValidMax(A,2*i,n);

			if(!l_boo)
				return false;
		}

		if((2*i+1)<=n){
			boolean r_boo=false;

			if(A[2*i+1] < A[i])
				r_boo = isValidMax(A,2*i+1,n);

			if(!r_boo)
				return false;
		}

		return true;
	}

	public static boolean isValidMin(int[] A,int i,int n){	
		if( (2*i)<=n ){
			boolean l_boo = false;

			if(A[2*i] > A[i])
				l_boo = isValidMin(A,2*i,n);

			if(!l_boo)
				return false;
		}

		if( (2*i+1)<=n ){
			boolean r_boo = false;

			if(A[2*i+1] > A[i])
				r_boo = isValidMin(A,2*i+1,n);

			if(!r_boo)
				return false;
		}

		return true;
	}

	public static void insertMax(int[] A,int n,int element){
		int last = n+1;
		if(n+1>=A.length)
			return;

		A[last] = element;
		maxHeapify(A,1,n);
	}

	public static void insertMin(int[] A,int n,int element){
		int last = n+1;
		if(n+1>=A.length)
			return;

		A[last] = element;
		minHeapify(A,1,n);
	}

	public static int extractMax(int[] A,int h_length){
		int max = A[1];
		A[1] = A[h_length];
		h_length = h_length-1;
		maxHeapify(A,1,h_length);
		return max;
	}

	public static int extractMin(int[] A,int h_length){
		int min = A[1];
		A[1] = A[h_length];
		h_length = h_length-1;
		minHeapify(A,1,h_length);
		return min;
	}

	public static void main(String[] args){
		int length = 20;
		int heap_length = 10;
		
		//int[] A = new int[length];
		int[] A = {-1,4,1,3,2,16,9,10,14,8,7};
		buildMaxHeap(A,heap_length);
		//buildMinHeap(A,heap_length);
		for(int i=1;i<=heap_length;i++){
			System.out.print(A[i]+", ");
		}
		System.out.println();
		System.out.println(isValidMax(A,1,heap_length));

		int m = extractMax(A,heap_length);
		System.out.println(m);
		for(int i=1;i<=heap_length-1;i++){
			System.out.print(A[i]+", ");
		}
	}
}
