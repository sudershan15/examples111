package sud.exmp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Schedule {
	public static void main(String[] args) {
		Map<String, List<Integer>> movies = new HashMap<String, List<Integer>>();
		movies.put("Shining", Arrays.asList(14, 15, 16));
		movies.put("Kill Bill", Arrays.asList(14, 15));
		movies.put("Pulp Fiction", Arrays.asList(14, 15));
		List<String> movieNames = new ArrayList<String>(movies.keySet());
		Map<Integer, String> schedule = new HashMap<Integer, String>();
		if (schedule(movies, movieNames, 0, schedule)) {
			System.out.println("Schedule is " + schedule);
		} else {
			System.out.println("Unable to schedule!!");
		}
	}

	private static boolean schedule(Map<String, List<Integer>> movies, List<String> movieNames, int index,
			Map<Integer, String> schedule) {
		if (index == movieNames.size())
			return true;
		String movie = movieNames.get(index);
		List<Integer> timings = movies.get(movie);
		for (int timing : timings) {
			if (!schedule.containsKey(timing)) {
				Map<Integer, String> scheduleCopy = new HashMap<Integer, String>(schedule);
				scheduleCopy.put(timing, movie);
				if (schedule(movies, movieNames, index + 1, scheduleCopy)) {
					schedule.clear();
					schedule.putAll(scheduleCopy);
					return true;
				}
			}
		}
		return false;
	}
}
