package adventCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {

	public static void main(String[] args ) {
		int[] inputs = {2,0,1,9,5,19};
		int[] exInputs = {0,3,6};
		//inputs = exInputs;
		int turn = 30000000;
		Map<Integer, List<Integer>> nosCalled = new HashMap<>();
		int lastNumber = -1;
		for (int i = 0; i < turn; i++) {
			if (inputs.length > i) {
				List<Integer> val = new ArrayList<>();
				val.add(i);
				lastNumber = inputs[i];
				nosCalled.put(lastNumber, val);
			}
			else if (nosCalled.get(lastNumber).size() <= 1) {
				List<Integer> val = nosCalled.getOrDefault(0, new ArrayList<>());
				val.add(i);
				nosCalled.put(0, val);
				lastNumber = 0;
			}
			else if (nosCalled.get(lastNumber).size() > 1) {
				List<Integer> val = nosCalled.get(lastNumber);
				lastNumber = val.get(val.size() - 1) - val.get(val.size() - 2);
				List<Integer> newVal = nosCalled.getOrDefault(lastNumber, new ArrayList<>());
				newVal.add(i);
				nosCalled.put(lastNumber, newVal);
			}
			//System.out.println( i + " " + lastNumber);
		}
		System.out.println(lastNumber);
	}
}
