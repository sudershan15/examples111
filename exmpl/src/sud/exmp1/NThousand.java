package sud.exmp1;

public class NThousand {
	void print(int N) {
	    for (int i = 1; i < 10; i++)
	        printRec(i, N);
	}

	void printRec(int base, int N) {
	    if (base > N)
	        return;
	    System.out.print(base + "  ");
	    for (int i = 0; i < 10; i++)
	        printRec(base * 10 + i, N);
	}
	
	public static void main(String[] args) {
		NThousand n = new NThousand();
		n.print(1000);
	}
}
