package sud.exmp1;

public class Coins {

	static int MAX_N = 100;

	void printMoves(int P[][], int A[], int N) {
		int m = 0, n = N - 1;
		boolean myTurn = true;
		while (m <= n) {
			int P1 = P[m + 1][n]; // If take A[m], opponent can get...
			int P2 = P[m][n - 1]; // If take A[n]
			System.out.println((myTurn ? "I" : "You") + " take coin no. ");
			if (P1 <= P2) {
				System.out.println(m + 1 + " (" + A[m] + ")");
				m++;
			} else {
				System.out.println(n + 1 + " (" + A[n] + ")");
				n--;
			}
			System.out.println(myTurn ? ", " : ".\n");
			myTurn = !myTurn;
		}
		System.out.println("\nThe total amount of money (maximum) I get is "
				+ P[0][N - 1] + ".\n");
	}

	int maxMoney(int A[], int N) {
		int P[][] = new int[MAX_N][MAX_N];
		int a, b, c;
		for (int i = 0; i < N; i++) {
			System.out.println("here");
			for (int m = 0, n = i; n < N; m++, n++) {
				assert (m < N);
				assert (n < N);
				
				a = ((m + 2 <= N - 1) ? P[m + 2][n] : 0);
				b = ((m + 1 <= N - 1 && n - 1 >= 0) ? P[m + 1][n - 1] : 0);
				c = ((n - 2 >= 0) ? P[m][n - 2] : 0);
				System.out.println("m: " + m);
				System.out.println("n: " + n);
				System.out.println("a: " + a);
				System.out.println("b: " + b);
				System.out.println("c: " + c);
				P[m][n] = Math
						.max(A[m] + Math.min(a, b), A[n] + Math.min(b, c));
				System.out.println("P[m][n]: " + P[m][n]);
			}
		}
		printMoves(P, A, N);
		return P[0][N - 1];
	}
	
	public static void main(String[] args) {
		int A[] = {3, 2, 2, 3, 1, 2};
		Coins c = new Coins();
		System.out.println(c.maxMoney(A, A.length));
	}
}
