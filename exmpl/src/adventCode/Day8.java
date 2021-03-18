package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day8 {

	static int acc = 0;
	public static void main(String[] args) {
		final List<String> input = getInput();
		boolean programTerminates = false;
		int i = 0;
		while(!programTerminates && i < input.size()) {
			String originalElement = input.get(i);
			String el = null;
			switch(input.get(i).substring(0, 3)) {
			case "nop":
				el = input.get(i).replace("nop", "jmp");
				break;
			case "jmp":
				el = input.get(i).replace("jmp", "nop");
				break;
			}
			if (el != null) {
				input.remove(i);
				input.add(i, el);
				programTerminates = checkIfProgramTerminates(input);
				if (!programTerminates) {
					input.remove(i);
					input.add(i, originalElement);
				}
			}
			i++;
		}
	}
	
	static boolean checkIfProgramTerminates(List<String> input) {
		final boolean[] used = new boolean[input.size()];
		int localacc = 0;
		int jmp = 1;
		for (int i = 0; i < input.size(); i += jmp) {
			if (used[i]) {
				// Part1 
				// System.out.println(input.get(i) + "         acc = " + acc);
				return false;
			}
			used[i] = true;
			switch(input.get(i).substring(0, 3)) {
			case "acc":
				localacc += (input.get(i).substring(4, 5).trim().equals("+") ? 1 : -1) * Integer.parseInt(input.get(i).substring(5)); 
				jmp = 1; 
				break;
			case "nop": jmp = 1; break;
			case "jmp": jmp = (input.get(i).substring(4, 5).equals("+") ? 1 : -1) * Integer.parseInt(input.get(i).substring(5)); break;
			}
		}
		System.out.println("acc = " + localacc);
		return true;
	}
	
	static List<String> getInput() {
		Path path = Paths.get("resources/day8.txt");
		List<String> lines = null;
		try {
			lines = Files.readAllLines(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}
}
