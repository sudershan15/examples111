package sud.exmp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Given an array A of integers, find the maximum of j-i subjected to the constraint of A[i] < A[j] */

public class DistanceMaximizing {

	static int getMaximumDistance(int [] distance, int n) {
		int [] lMin = new int[n];
		int [] rMax = new int[n];
		lMin[0] = distance[0];
		rMax[n-1] = distance[n-1];
		for(int i=n-1; i>=0; i--) {
			rMax[i] = Math.max(distance[i], rMax[i+1]);
		}
		int i=0, j=0, maxIndexDiff=-1;
		while(i < n && j < n) {
			if(lMin[i] < rMax[j]) {
				maxIndexDiff = Math.max(maxIndexDiff, j-i);
				j++;
			}
			else i++;
		}
		return maxIndexDiff;
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] distance = new int[n];
		for (int i = 0; i < n; i++)
			distance[i] = Integer.parseInt(br.readLine());
		System.out.println(getMaximumDistance(distance, n));
	}
}