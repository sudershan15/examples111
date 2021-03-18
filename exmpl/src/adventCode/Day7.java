package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {
	
	public static void main(String[] args) {
		Map<String, List<String>> dict = getDict();
		//System.out.println(countBagsForColor(dict, "shiny gold"));
		System.out.println("Day7 PartB - TOTAL: " + countBagsIn(dict, "shiny gold"));
	}

	static Map<String, List<String>> getDict() {
		Path path = Paths.get("resources/day7.txt");
		Map<String, List<String>> dict = new HashMap<>(); 
		try {
			Files.readAllLines(path).forEach(l ->{
				String[] bcb = l.replaceAll("bags", "").replaceAll("bag", "").replaceAll("\\.", "").trim().split("contain");
				List<String> v = new ArrayList<>();
				if (!bcb[1].trim().equals("no other")) {
					for (String c: bcb[1].trim().split(",")) {
						v.add(c.trim());
					}
				}
				dict.put(bcb[0].trim(), v);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dict;
	}
	
	static long countBagsIn(final Map<String, List<String>> dict, final String color) {
		long count = 0;
		for (String s: dict.get(color)) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(color + " - " + s);
			String copy = s;
			int numberOfBags = Integer.parseInt(copy.replaceAll("[^\\d]", ""));
			count = count + numberOfBags + numberOfBags * countBagsIn(dict, s.replaceAll("\\d", "").trim());
		}
		return count;
	}
	
	static long countBagsForColor(final Map<String, List<String>> dict, final String color) {
		String regex = "\\d " + color;
		return dict.entrySet().parallelStream().filter(e -> e.getValue().stream().filter(c -> c.matches(regex) || checkIfColorInKey(c, dict, regex)).count() > 0).count();
	}
	
	static boolean checkIfColorInKey(final String colorString, final Map<String, List<String>> dict, final String regex) {
		return dict.containsKey(colorString.substring(2)) && dict.get(colorString.substring(2)).stream().filter(c -> c.matches(regex) || checkIfColorInKey(c, dict, regex)).count() > 0;
	}
}
