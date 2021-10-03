

public class QuickSort
{
	
	public static void Exchange(int[] A, int a, int b) // a,b 자리의 값을 바꾼다.
	{
		int temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}

	public static int Partition(int[] A, int start,int end){
					
		int k = (start + end)/2;
		Exchange(A,k,start);

		int pivot = A[start];
		int j = start;	
		
		for(int i = start+1; i <= end ; i++ ){
			
			if(A[i] <= pivot){
				j++;
				Exchange(A,i,j);
			}

		}
		Exchange(A,start,j);
		return j;
		
	}

	public static void QSort(int[] A,int s,int e)
	{
			if(s<e){
			
				int j = Partition(A,s,e);
						
				QSort(A,s,j-1);
				QSort(A,j+1,e);
			}
		}
		
	

	public static void ArrPrint(int[] A){ //array 출력함수
		for(int i=0; i < A.length; i++){
			System.out.printf(A[i]+",");
		}
	}

	public static int[] creArr(int a) { // 길이가 a인 배열 생성
	}


	public static void main(String[] args){
		//int[] A = new int[10];
		int[] arrQ = {5,6,78,1,2,55,33,76,25,15,22};
		QSort(arrQ,0,arrQ.length-1);
		ArrPrint(arrQ);

	}

}