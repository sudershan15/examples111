package sud.exmp1;

import java.util.HashSet;

public class print {
	public static void printPairs(int []a,int x){
		HashSet<Integer> h= new HashSet<Integer>();
		for(int i=0; i < a.length; i++){
			System.out.println("Adding element a[i]: " + a[i]);
			h.add(a[i]);
		}
		for(int i=0; i < a.length; i++){
			if(h.contains(x-a[i])){
				System.out.println(a[i]+" "+(x-a[i]));
				h.remove(x-a[i]);
			}
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a={2,4,5,6,8,9,1,5};
		printPairs(a,10);
		int A[] = new int[] { 2, 1, 3, 5, 7, 6 };
		int B[] = new int[A.length];



		int evenIndex = 0;
		int oddIndex = 1;



		for (int i = 0; i < A.length; i++) {
			if (A[i] % 2 == 0) { // even
				if (evenIndex < A.length) {
					B[evenIndex] = A[i];
					evenIndex += 2;
				} else { // overflow for even, write at odd places, there's no escape!
					B[oddIndex] = A[i];
					oddIndex += 2;
				}
			} else // odd
				if (oddIndex < A.length) {
					B[oddIndex] = A[i];
					oddIndex += 2;
				} else { // overflow for odd, write at even places, , there's no escape!
					B[evenIndex] = A[i];
					evenIndex += 2;
				}
		}

		for (int i = 0; i < B.length; i++) {
			System.out.print(B[i] + ",");
		}

	}

}
