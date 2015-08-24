package sud.exmp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinations {
	String input;
	StringBuffer cur;

	public void comb(List<List<String>> list, List<String> res, int k) {

		if (k == list.size()) {
			System.out.println(res);
			// res.clear();
			return;
		}

		List<String> subList = list.get(k);
		for (int i = 0; i < subList.size(); i++) {
			res.add(subList.get(i));
			comb(list, res, k + 1);
			res.remove(subList.get(i));
		}
	}

	private void next(int pos, int reminder) {
		cur.append(input.charAt(pos));

		if (reminder == 1) {
			System.out.println(cur);
		} else {
			for (int i = pos + 1; i + reminder - 1 <= input.length(); i++)
				next(i, reminder - 1);
		}
		cur.deleteCharAt(cur.length() - 1);
	}

	public void generate(String input) {
		cur = new StringBuffer();
		this.input = input;
		for (int length = 1; length <= input.length(); length++)
			for (int pos = 0; pos + length <= input.length(); pos++)
				next(pos, length);
	}

	public void doCombine(char[] str, StringBuilder out, int len, int level,
			int start) {
		assert(start<len);
		if (start==len)
		    System.out.println("--------->"+out.toString());
		for (int i = start; i < len; i++) {
			out.append(str[i]);
			System.out.println(out);
			if (i < len - 1) {
				doCombine(str, out, len, level + 1, start + 1);
			}
			//out.deleteCharAt(out.length() - 1);
			out.setLength(out.length() - 1);
		}
	}

	public static void main(String[] args) {
		Combinations c = new Combinations();
		String s = "abcd";
		String s1[] = s.split("\\(");
		System.out.println(s1.length);
		StringBuilder output = new StringBuilder();
		// c.generate(s);
		List<List<String>> lists = new ArrayList<List<String>>();
		ArrayList<String> list1 = new ArrayList<String>(Arrays.asList("quick","slow"));
		ArrayList<String> list2 = new ArrayList<String>(Arrays.asList("brown","red"));
		ArrayList<String> list3 = new ArrayList<String>(Arrays.asList("fox","dog"));
		lists.add(list1);
		lists.add(list2);
		lists.add(list3);
			List<String> res = new ArrayList<String>();
			c.comb(lists, res , 0);
		c.doCombine(s.toCharArray(), output, s.length(), 0, 0);
	}
}
