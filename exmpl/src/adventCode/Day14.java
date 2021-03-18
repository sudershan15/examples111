package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day14 {

	public static void main(String[] args) {
		Path path = Paths.get("resources/day14.txt");
		try {
			List<String> inputs = Files.readAllLines(path);
			part1(inputs);
			part2(inputs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void part1(List<String> inputs) {
		HashMap<Integer, String> hmap = new HashMap<Integer, String>();
		String mask = "";
		for (String l : inputs) {
			if (l.substring(0, 3).equals("mem")) {
				int memAddrss = Integer.parseInt(l.substring(4, l.indexOf("]")));
				String num = String
						.format("%36s", Integer.toBinaryString(Integer.parseInt(l.substring(l.indexOf("=") + 2))))
						.replace(' ', '0');
				num = applyMask(num, mask);
				hmap.put(memAddrss, num);
			} else if (l.substring(0, 4).equals("mask")) {
				mask = l.substring(l.indexOf("=") + 2);
			}
		}
		long sum = 0;
		for (String i : hmap.values()) {
			sum += Long.parseLong(i, 2);
		}
		System.out.println("Part 1:" + sum);
	}

	static String applyMask(String num, String mask) {
		for (int i = 0; i < mask.length(); i++) {
			if (mask.charAt(i) != 'X') {
				num = num.substring(0, i) + mask.charAt(i) + num.substring(i + 1);
			}
		}
		return num;
	}

	static ArrayList<String> applyMask2(String address, String mask, int index) {
		if (index == address.length()) {
			ArrayList<String> solution = new ArrayList<>();
			solution.add(address);
			return solution;
		}
		if (mask.charAt(index) == '1') {
			address = address.substring(0, index) + mask.charAt(index) + address.substring(index + 1);
			return applyMask2(address, mask, index + 1);
		} else if (mask.charAt(index) == 'X') {
			address = address.substring(0, index) + "1" + address.substring(index + 1);
			ArrayList<String> one = applyMask2(address, mask, index + 1);
			address = address.substring(0, index) + "0" + address.substring(index + 1);
			ArrayList<String> two = applyMask2(address, mask, index + 1);
			one.addAll(two);
			return one;
		}

		return applyMask2(address, mask, index + 1);
	}

	public static void part2(List<String> inputs) {
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		String mask = "";
		for (String l : inputs) {
			if (l.substring(0, 3).equals("mem")) {
				String memAddrss = String
						.format("%36s", Integer.toBinaryString(Integer.parseInt(l.substring(4, l.indexOf("]")))))
						.replace(' ', '0');
				;
				int num = Integer.parseInt(l.substring(l.indexOf("=") + 2));
				ArrayList<String> addresses = applyMask2(memAddrss, mask, 0);
				for (String address : addresses) {
					hmap.put(address, num);
				}
			} else if (l.substring(0, 4).equals("mask")) {
				mask = l.substring(l.indexOf("=") + 2);
			}
		}
		long sum = 0;
		for (int i : hmap.values()) {
			sum += i;
		}
		System.out.println("Part 2:" + sum);
	}
}
