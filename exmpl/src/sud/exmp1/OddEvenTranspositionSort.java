package sud.exmp1;

public class OddEvenTranspositionSort{

	public static void main(String a[]){
		int i;
		int array[] = {12,9,4,99,120,1,3,10,13};

		System.out.println("Values Before the sort:\n");
		for(i = 0; i < array.length; i++)
			System.out.print( array[i]+"  ");
		System.out.println();

		odd_even_srt(array,array.length);

		System.out.print("Values after the sort:\n");
		for(i = 0; i <array.length; i++)
			System.out.print(array[i]+"  ");
	}

	public static void odd_even_srt(int array[],int n){
		for (int i = 0; i < n/2; i++ ) {
			System.out.println("Phase " + i);
			for (int j = 0; j+1 < n; j += 2) {
				array = check(array, j, j+1);
			}
			for (int j = 1; j+1 < n; j += 2) {
				array = check(array, j, j+1);
			}
		}
	}

	private static int[] check(int[] array, int i, int j) {
		if (array[i] > array[j]) {
			int T = array[i];
			array[i] = array[j];
			array[j] = T;
		}
		for (int k = 0; k < array.length; k++) {
			if (k == i) { System.out.print("("); }
			System.out.print(array[k]);
			if (k == j) { System.out.print(")"); }
			System.out.print(" ");
		}
		System.out.println();
		return array;
	}
}