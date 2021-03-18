package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day24 {

	public static void main(String[] args) {
		Path path = Paths.get("resources/day23ex" + ".txt");
		List<String> dirs = Arrays.asList("e", "se", "sw", "w", "nw", "ne");
		try {
			List<String> lines = Files.readAllLines(path);
			int count = 0;
			
			for (String line: lines) {
				Map<String, Integer> tileDir = new HashMap<>();
				int len = line.length();
				int idx = 0;
				//System.out.println(line);
				while (idx < len) {
					String dir1 = line.substring(idx, idx + 1);
					String dir2 = line.substring(idx, idx + 2 <= len ? idx + 2 : idx + 1);
					//System.out.println(dir1 + "  " + dir2);
					if (dirs.contains(dir1)) {
						//System.out.println("putting " + dir1 + "   " + tileDir.getOrDefault(dir1, 0));
						tileDir.put(dir1, tileDir.getOrDefault(dir1, 0) + 1);
						idx++;
					} else if (dirs.contains(dir2)) {
						//System.out.println("putting " + dir2 + "   " + tileDir.getOrDefault(dir2, 0));
						tileDir.put(dir2, tileDir.getOrDefault(dir2, 0) + 1);
						idx = idx + 2;
					}
				}
				
				for (int val: tileDir.values()) {
					if (val%2 != 0) count++;
				}
				System.out.println(tileDir + "   "  + count);
			}
			
			System.out.println(count);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
