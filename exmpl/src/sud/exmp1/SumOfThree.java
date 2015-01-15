package sud.exmp1;

import java.util.ArrayList;
import java.util.Arrays;

public class SumOfThree {
    public static void main(String[] args) {
        int[][] expected = new int[][] {
                {1, 2, 3},
                {1, 2, 4}
        };
        ArrayList<int[]> actual = findTriplets(new int[]{4, 5, 6, 7, 1, 2, 3}, 7);
        for (int[] a: actual) {
        	System.out.print("{");
        	for (int i: a) {
        		System.out.print(" " + i + " ");
        	}
        	System.out.print("}");
        }
    }

    public static ArrayList<int[]> findTriplets(int[] values, int n) {
        Arrays.sort(values);

        ArrayList<int[]> result = new ArrayList<int[]>();

        for (int i = 0; 3 * values[i] <= n; i++) {
            int maxRestOfTwo = n - values[i];
            for (int j = i + 1; 2 * values[j] <= maxRestOfTwo; j++) {
                int maxRestOfOne = maxRestOfTwo - values[j];
                for (int k = j + 1; values[k] <= maxRestOfOne; k++) {
                    result.add(new int[]{values[i], values[j], values[k]});
                }
            }
        }
        return result;
    }
}