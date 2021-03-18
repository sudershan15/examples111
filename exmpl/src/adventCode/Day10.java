package adventCode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Day10 {
	public static void main(String[] args) {
		Path path = Paths.get("resources/day10.txt");
		TreeSet<Integer> joltage = new TreeSet<>();
		try {
			Files.readAllLines(path).forEach(v -> {
				int val = Integer.parseInt(v);
				joltage.add(val);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		joltage.add(joltage.last() + 3);
		
		int first = 0;
		
		// P1
		Map<Integer, Integer> jolts = new HashMap<>();
		for (int jolt: joltage) {
			jolts.put(jolt - first, jolts.getOrDefault(jolt - first, 0) + 1);
			first = jolt;
		}
		System.out.println(jolts);
		
		//P2
		joltage.add(0);
		List<Integer> joltList = new ArrayList<>(joltage);
		
		Map<Integer, List<Integer>> possibleConnections = new HashMap<>();
	    for (int i = joltList.size() - 1; i >=0; --i) {
	        int j = i - 1;
	        List<Integer> children = new ArrayList<>();
	        while (j >= 0 && joltList.get(i) <= joltList.get(j) + 3) {
	            children.add(joltList.get(j));
	            --j;
	        }
	        possibleConnections.put(joltList.get(i), children);
	    }

	    Map<Integer, Long> waysToGetTo = new HashMap<>();
	    waysToGetTo.put(0, 1L);
	    for (int i = 1; i < joltList.size(); ++i) {
	        long total = 0;
	        for (int e : possibleConnections.get(joltList.get(i))) {
	            total += waysToGetTo.get(e);
	        }
	        waysToGetTo.put(joltList.get(i), total);
	    }
	    System.out.println("Ways to get to : " + waysToGetTo.get(joltList.get(joltList.size() - 1)));
	    
		// P2
	    long[] dp = new long[joltage.size()];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = -1;
		}
		System.out.println(canCreateList(joltList, 0, dp));
		
	}
	
	static long canCreateList(List<Integer> joltage, int pos, long[] dp) {
		if (pos == joltage.size() - 1) {
			return 1;
		}
		if (dp[pos] != -1) {
			return dp[pos];
		}
		int count = 0;
		for (int j = pos + 1; j < joltage.size(); j ++) {
			if (joltage.get(j) - joltage.get(pos) <= 3) {
				count += canCreateList(joltage, j, dp);
			}
		}
		dp[pos] = count;
		return count;
	}
}
