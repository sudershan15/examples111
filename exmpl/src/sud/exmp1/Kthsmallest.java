package sud.exmp1;

public class Kthsmallest {

	private static final int INT_MIN = Integer.MIN_VALUE;
	private static final int INT_MAX = Integer.MAX_VALUE;

	static int findKthSmallest(int A[], int m, int B[], int n, int k) {
		assert (m >= 0);
		assert (n >= 0);
		assert (k > 0);
		assert (k <= m + n);

		int i = (int) ((double) m / (m + n) * (k - 1));
		int j = (k - 1) - i;

		assert (i >= 0);
		assert (j >= 0);
		assert (i <= m);
		assert (j <= n);
		// invariant: i + j = k-1
		// Note: A[-1] = -INF and A[m] = +INF to maintain invariant
		int Ai_1 = ((i == 0) ? INT_MIN : A[i - 1]);
		int Bj_1 = ((j == 0) ? INT_MIN : B[j - 1]);
		int Ai = ((i == m) ? INT_MAX : A[i]);
		int Bj = ((j == n) ? INT_MAX : B[j]);

		if (Bj_1 < Ai && Ai < Bj)
			return Ai;
		else if (Ai_1 < Bj && Bj < Ai)
			return Bj;

		assert ((Ai > Bj && Ai_1 > Bj) || (Ai < Bj && Ai < Bj_1));

		// if none of the cases above, then it is either:

		if (Ai < Bj) {
			// exclude Ai and below portion
			// exclude Bj and above portion
			int len = A.length - i - 1 + 1;
			A = new int[len];
			int count = 0;
			for (int x = i + 1; x < A.length; x++) {
				A[count] = A[x];
				count++;
			}
			return findKthSmallest(A, m - i - 1, B, j, k - i - 1);
		} else {/* Bj < Ai */
			// exclude Ai and above portion
			// exclude Bj and below portion
			int len = B.length - j - 1 + 1;
			B = new int[len];
			int count = 0;
			for (int x = j + 1; x < B.length; x++) {
				B[count] = B[x];
				count++;
			}
			return findKthSmallest(A, i, B, n - j - 1, k - j - 1);
		}
	}
	
	 public static void main(String[] args) {
	        // TODO code application logic here
	        int[] A={1, 2, 5, 10, 12, 14, 19, 282, 290};
	        int[] B={3, 4, 6, 9, 281, 289};
	        int k0 = 6;
	        Kthsmallest obj = new Kthsmallest();
	        System.out.println(obj.kthSmallest(A, B, 0, 0, k0 - 1));
	    }
	 
	 public static Integer kthSmallest(int[] a, int[] b, int p1, int p2, int k)
	     {
		 	if ( a.length + b.length < k) { return null; }
	         if (k == 0)
	             return (p1 != -1? a[p1] : Integer.MAX_VALUE) <= (p2 != -1? b[p2] : Integer.MAX_VALUE) ? a[p1] : b[p2];
	         
	         if ((p1 != -1? a[p1] : Integer.MAX_VALUE) <= (p2 != -1? b[p2] : Integer.MAX_VALUE))
	             return kthSmallest(a, b, p1 == a.length -1 ? -1 : ++p1, p2, --k);
	         else
	             return kthSmallest(a, b, p1, p2 == b.length - 1 ? -1 : ++p2, --k);
	     }
	 
}
