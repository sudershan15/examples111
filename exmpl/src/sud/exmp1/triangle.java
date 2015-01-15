package sud.exmp1;

public class triangle {

	public triangle() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		int x = 4;
		int c = 7;
		for (int i = 1; i <= x; i++) {
			for (int j = i; j < c; j++) {
				System.out.print(" ");
			}
			for(int k=0; k < i; k++) {
				System.out.print(c);
				System.out.print(" ");
			}
			for (int l = 0; l < i-1; l++) {
				System.out.print(c);
				System.out.print(" ");
			}
			c--;
			System.out.println();
		}
	}
}
