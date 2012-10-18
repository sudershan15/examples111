package sud.exmp1;

import java.util.ArrayList;

public class Solution {
	public static void main(String[] args) {
		String input = "2556";
		for (String s : ip(input, 3)) {
			System.out.println(s);
		}
	}

	public static ArrayList<String> ip(String s, int dot) {
		ArrayList<String> r = new ArrayList<String>();
		if (s == null || s.length() == 0)
			return r;
		if (dot == 0) {
			int i = Integer.parseInt(s);
			if (i < 256) {
				r.add(s);
			}
			return r;
		}
		for (int i = 1; i <= Math.min(3, s.length()); i++) {
			int n = Integer.parseInt(s.substring(0, i));
			if (n < 256) {
				for (String st : ip(s.substring(i), dot - 1)) {
					r.add(n + "." + st);
				}
			}
		}
		return r;
	}
}