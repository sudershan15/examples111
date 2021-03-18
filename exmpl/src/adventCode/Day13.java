package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Day13 {
	public static void main(String[] args) {
		Path path = Paths.get("resources/day13" + ".txt");
		try {
			List<String> inputs = Files.readAllLines(path);
			// P1
			long timeToLeave = Long.parseLong(inputs.get(0));
			String[] busSchedules = inputs.get(1).replaceAll("x,", "").split(",");
			int[] bss = new int[busSchedules.length];
			int i = 0;
			for (String bs : busSchedules) {
				int currBs = Integer.parseInt(bs);
				bss[i] = Integer.parseInt(bs);
				System.out.println(currBs + "  " + (currBs - timeToLeave % currBs));
				i++;
			}

			// P2
			String[] busSchedulesWithx = inputs.get(1).split(",");
			Map<Integer, Integer> indexedBusSchedules = new TreeMap<>();
			for (int j = 0; j < busSchedulesWithx.length; j++) {
				if (!busSchedulesWithx[j].equalsIgnoreCase("x")) {
					indexedBusSchedules.put(j, Integer.parseInt(busSchedulesWithx[j]));
				}
			}
			System.out.println(calculateAnswerPart2(indexedBusSchedules));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static long calculateAnswerPart2(Map<Integer, Integer> indexedBusSchedules) {
		long time = 0;
		long increment = 0;
		for (Entry<Integer, Integer> e : indexedBusSchedules.entrySet()) {
			System.out.println(e + "  " + time + "  " + increment);
			if (time == 0) {
				time = e.getValue();
				increment = e.getValue();
			} else {
				while ((time + e.getKey()) % e.getValue() != 0) {
					time += increment;
				}
				increment *= e.getValue();
			}
		}
		return time;
	}
}
