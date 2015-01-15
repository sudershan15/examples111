package sud.exmp1;

import java.util.*;

public class Triplets {

	public boolean canArrange(int[] servers, int[] tasks) {
		boolean[] used = new boolean[tasks.length];
		return canArrangeRecursive(servers, tasks, used);
	}

	public boolean canArrangeRecursive(int[] servers, int[] tasks,
			boolean[] used) {
		boolean allUsed = true;
		for (boolean b : used) {
			allUsed &= b;
		}
		if (allUsed) {
			return true;
		}
		for (int i = 0; i < tasks.length; i++) {
			if (!used[i]) {
				used[i] = true;
				for (int j = 0; j < servers.length; j++) {
					if (servers[j] >= tasks[i]) {
						servers[j] = servers[j] - tasks[i];
						if (canArrangeRecursive(servers, tasks, used)) {
							return true;
						}
						servers[j] = servers[j] + tasks[i];
					}
				}
				used[i] = false;
			}
		}
		return false;
	}

	private static boolean findTaskAssignment(ArrayList<Integer> taskNeeds,
			ArrayList<Integer> serCaps) {
		if (taskNeeds.size() == 0)
			return true;
		int task = taskNeeds.get(0);
		Collections.sort(serCaps, Collections.reverseOrder());
		System.out.println("task to be assigned:" + task);
		System.out.println("server Caps remaining:" + serCaps);

		boolean flag = false;
		int i = 0;
		int cap = serCaps.get(i);
		if (task > cap) {
			return false;
		} // if task is same as cap then assign
		else if (task == cap) {
			serCaps.remove(Integer.valueOf(task));
			taskNeeds.remove(0);
		} // else if the same size is ther
		else if (serCaps.contains(task)) {
			serCaps.remove(Integer.valueOf(task));
			taskNeeds.remove(0);
		} else {
			serCaps.set(i, cap - task);
			taskNeeds.remove(0);
		}
		System.out.println("Next iteration");
		return findTaskAssignment(taskNeeds, serCaps);
	}

	static class Triple {
		public final String id;
		public final Integer a, b;

		public Triple(String id, Integer a, Integer b) {
			this.id = id;
			this.a = a;
			this.b = b;
		}
	}

	static class Tuple implements Comparable<Object> {
		public final String id;
		public final Integer a;

		public Tuple(String id, Integer a) {
			this.id = id;
			this.a = a;
		}

		@Override
		public int compareTo(Object other) {
			return this.a - ((Tuple) other).a;
		}
	}

	public static ArrayList<Tuple> solve(ArrayList<Triple> in) {
		PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
		ArrayList<Tuple> res = new ArrayList<Tuple>();
		for (Triple e : in) {
			Tuple cur = new Tuple(e.id, e.a);
			pq.add(new Tuple(e.id, e.b));
			while (pq.size() > 0 && pq.peek().compareTo(cur) <= 0) {
				res.add(pq.poll());
			}
			res.add(cur);
		}
		while (pq.size() > 0) {
			res.add(pq.poll());
		}
		return res;
	}

	public static void main(String[] args) {
		ArrayList<Triple> arr = new ArrayList<Triple>();
		arr.add(new Triple("a", 1, 9));
		arr.add(new Triple("b", 2, 6));
		arr.add(new Triple("c", 3, 7));
		arr.add(new Triple("d", 4, 5));
		for (Tuple e : solve(arr)) {
			System.out.println(e.id + " " + e.a);
		}
	}
}