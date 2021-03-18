package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.TreeSet;

public class Day9 {
	
	static int N = 25;
	
	public static void main(String args[]) {
		Path path = Paths.get("resources/day9.txt");
		try {
			List<String> inputs = Files.readAllLines(path);
			long val = getFirstInvalidNumber(inputs);
			System.out.println("P1: " + val);
			System.out.println("P2: " + getPart2Ans(inputs, val));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static boolean isValSumOfAny2InQ(final long currVal, final Queue<Long> pq) {
		Iterator<Long> iterator = pq.iterator();
		while (iterator.hasNext()) {
			if (pq.contains(currVal - iterator.next())) {
				return true;
			}
		}
		return false;
	}
	
	static long getFirstInvalidNumber(List<String> lines) {
		Queue<Long> pq = new LinkedList<>();
		Optional<String> s = lines.stream().filter(val -> {
			long currVal = Long.parseLong(val);
			boolean bool = false;
			if (pq.size() >= N) {
				if (!isValSumOfAny2InQ(currVal, pq)) {
					bool = true; 
				}
				pq.poll();
			}
			pq.add(currVal);
			return bool;
		}).findFirst();
		return Integer.parseInt(s.orElse("0"));
	}
	
	static long getPart2Ans(List<String> inputs, long val) {
		long curr_sum = 0;
		for (int i = 0; i < inputs.size(); i++) { 
			TreeSet<Long> sumList = new TreeSet<>();
			long currVal = Long.parseLong(inputs.get(i));
            curr_sum = currVal; 
            for (int j = i + 1; j <= inputs.size(); j++) { 
              	sumList.add(Long.parseLong(inputs.get(j-1)));
                if (curr_sum == val) {
                    return sumList.first() + sumList.last();
                } 
                if (curr_sum > val || j == inputs.size()) 
                    break; 
                curr_sum = curr_sum + Long.parseLong(inputs.get(j)); 
            }
        }
		return 0;
	}
}
