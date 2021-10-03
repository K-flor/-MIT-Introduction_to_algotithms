public class MergeSort{

	public static void sort(int[] a, int start, int end){ // end : not include
		if( end > start ){
			sort(a, start, (end+start)/2);
			sort(a, (end+start)/2, end);
			merge(a, start, (end+start)/2, end);
		}
	}

	public static void merge(int[] A, int s, int half, int e){ // e : not include
		int n1 = half - s;
		int n2 = e - half;

		int[] a1 = new int[n1];
		int[] a2 = new int[n2];

		for(int i = 0; i<a1.length; i++)
			a1[i] = A[s+i];

		for(int i=0; i<a2.length; i++)
			a2[i] = A[half+i];

		int i , j; i = j = 0;

		for(int k = s; k < e; k++){
			if( a1[i] <= a2[j] ){
				A[k] = a1[i];
				++i;
			}
			else{
				A[k] = a2[j];
				++j;
			}
		}
	}

	public static void main(String[] args){
		int[] arr = {89,45,66,21,78,43,63,15,56};
		sort(arr, 0, arr.length);

	}
}