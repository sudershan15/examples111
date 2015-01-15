package sud.exmp1;

import java.util.ArrayList;
import java.util.List;

public class MinimumSumPair {
	public MinimumSumPair() {
		//super();
	}

	public static void main(String[] args) {
		new MinimumSumPair();
		int[] a = {-200,4,2,15,-19,8,4,6,-9};
		List<Integer> l = minimumSum(a);
		System.out.println(l.toString());
		System.out.println(maxSum(a));
	}
	public static List<Integer> minimumSum(int[] a){
		int sum = 0,minsum =Integer.MAX_VALUE;
		int l=0,r=0;
		for(int i = 0; i < a.length; i++){
			for(int j = i+1; j < a.length; j++){
				sum = a[i] + a[j];
				if(sum < 0) sum = -sum;
				if(minsum > sum && sum >= 0){
					minsum = sum ;
					l=a[i];
					r=a[j];
				}
			}
		}
		List<Integer> arrList = new ArrayList<Integer>();
		arrList.add(l);
		arrList.add(r);
		return arrList;
	}
	
	public static int maxSum(int[] a) {
		int maxsum = 0, sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (maxsum < sum) {
				maxsum = sum;
			} else if (sum < 0) {
				sum = 0;
			}
		} 
		return maxsum;
	}
}