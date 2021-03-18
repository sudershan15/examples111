package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Day21 {

	public static void main(String[] args) {
		Path path = Paths.get("resources/day21" + ".txt");
		try {
			List<String> lines = Files.readAllLines(path);
			Set<String> allergies = new HashSet<>();
			Set<String> ingredients = new HashSet<>();
			Map<String, Integer> counts = new HashMap<>();
			for (String line : lines) {
				String[] parts = line.split(" \\(contains ");
				for (String ingr : parts[0].split(" ")) {
					ingredients.add(ingr);
					counts.merge(ingr, 1, (o, n) -> o + n);
				}
				for (String aller : parts[1].replace(')', ' ').trim().split(", ")) {
					allergies.add(aller.trim());
				}
			}

			System.out.println(ingredients);
			System.out.println(allergies);
			System.out.println(counts);

			Map<String, Set<String>> possible = new TreeMap<>();
			for (String aller : allergies) {
				possible.put(aller, new HashSet<>(ingredients));
			}
			for (String line : lines) {
				String[] parts = line.split(" \\(contains ");
				List<String> ingrs = Arrays.asList(parts[0].split(" "));
				for (String aller : parts[1].replace(')', ' ').trim().split(", ")) {
					for (String ingr : ingredients) {
						if (!ingrs.contains(ingr)) {
							possible.get(aller).remove(ingr);
						}
					}
				}
			}

			System.out.println(possible);
			int cnt = 0;
			outer: for (String ingr : ingredients) {
				for (Set<String> poss : possible.values()) {
					if (poss.contains(ingr)) {
						continue outer;
					}
				}
				cnt += counts.get(ingr);
			}
			System.out.println(cnt);

			possible = resetPossible(possible);
			System.out.println(possible);
			
			List<String> vals = new ArrayList<>();
			for (Set<String> p : possible.values()) {
				vals.addAll(p);
			}
			System.out.println(String.join(",", vals).replaceAll(" ", ""));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static Map<String, Set<String>> resetPossible(Map<String, Set<String>> possible) {
		boolean reset = false;
		for (Entry<String, Set<String>> entry : possible.entrySet()) {
			if (entry.getValue().size() == 1) {
				for (Entry<String, Set<String>> entry1 : possible.entrySet()) {
					if (entry1.getValue().size() != 1) {
						entry.getValue().forEach(v1 -> possible.get(entry1.getKey()).remove(v1));
						reset = true;
					}
				}
			}
		}
		return reset ? resetPossible(possible) : possible;
	}
}
