package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

public class Day18 {

	public static void main(String[] args) {
		Path path = Paths.get("resources/day18.txt");
		try {
			List<String> inputs = Files.readAllLines(path);

			long result = 0;

			for(String l: inputs) {
				Stack<StringBuilder> a = new Stack<>();

				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < l.length(); i++) {
					if (l.charAt(i) == '(') {
						a.push(sb);
						sb = new StringBuilder();
					} else if (l.charAt(i) == ')') {
						String ex = sb.toString();
						String p = calc2(ex);
						sb = a.pop();
						sb.append(p);
					} else {
						sb.append(l.charAt(i));
					}
				}
				String res = calc2(sb.toString());
				result += Long.parseLong(res);
			}
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String calc(String ex){
        String[] c = ex.split(" ");
        long accum = Long.parseLong(c[0]);
        for (int i = 2; i < c.length; i = i+2){
            int to = Integer.parseInt(c[i]);
            switch (c[i - 1]) {
                case "+": accum += to; break;
                case "*": accum *= to; break;
            }
        }
        return Long.toString(accum);
    }
	
	public static String calc2(String ex){
        ex = ex.replaceAll("\\s", "");
        String[] c = ex.split("\\*");
        long prod = 1;
        for (String a : c) {
            String[] b = a.split("\\+");
            long sum = 0;
            for (String q : b) {
                sum += Long.parseLong(q);
            }
            prod *= sum;
        }
        return Long.toString(prod);
    }


}
