package adventCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Day6 {

	public static void main(String[] args) {
		System.out.println(getCountEveryoneAnsYes());
	}

	static int getCount() {
		int count = 0;
		try {
			File myObj = new File("resources/day6.txt");
			Scanner myReader = new Scanner(myObj);
			Set<Character> sb = new HashSet<>();

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (data.hashCode() == 0 || data == "" || data == "\t" || data == "\n") {
					count += sb.size();
					sb.clear();
				}
				data.chars().forEach(s -> sb.add((char) s));
			}
			count += sb.size();
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return count;
	}
	
	static int getCountEveryoneAnsYes() {
		int count = 0;
		try {
			File myObj = new File("resources/day6.txt");
			Scanner myReader = new Scanner(myObj);
			Set<Character> sb = new HashSet<>();
			boolean a = true;

			/*
			Path path = Paths.get("resources/day6.txt");
			Files.readAllLines(path).forEach(data -> {
				if (a) {
					data.chars().forEach(s -> sb.add((char) s));
					a = false;
				}
				if (data.hashCode() == 0 || data == "" || data == "\t" || data == "\n") {
					count += sb.size();
					sb.clear();
					a = true;
				}
				Set<Character> is = new HashSet<>();
				sb.forEach(c -> {
					if(!data.contains(c.toString())) {
						is.add(c);
					}
				});
				sb.removeAll(is);
			});
			*/
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (a) {
					data.chars().forEach(s -> sb.add((char) s));
					a = false;
				}
				if (data.hashCode() == 0 || data == "" || data == "\t" || data == "\n") {
					count += sb.size();
					sb.clear();
					a = true;
				}
				Set<Character> is = new HashSet<>();
				sb.forEach(c -> {
					if(!data.contains(c.toString())) {
						is.add(c);
					}
				});
				sb.removeAll(is);
			}
			count += sb.size();
			myReader.close();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return count;

	}
}
