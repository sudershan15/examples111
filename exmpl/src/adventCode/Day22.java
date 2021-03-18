package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day22 {

	public static void main(String[] args) {
		Path path = Paths.get("resources/day22" + ".txt");
		List<Integer> player1 = new ArrayList<>();
		List<Integer> player2 = new ArrayList<>();
		try {
			List<String> lines = Files.readAllLines(path);
			boolean player1Filled = false;
			for (String line: lines) {
				if (line.contains("Player")) {
					continue;
				}
				if (line.isEmpty()) {
					player1Filled = true;
					continue;
				}
				if (player1Filled) {
					player2.add(Integer.parseInt(line));
				} else {
					player1.add(Integer.parseInt(line));
				}
			}
			
			// P1
			/*while (!(player1.isEmpty() || player2.isEmpty())) {
				int p1Val = player1.get(0);
				int p2Val = player2.get(0);
				player1.remove(0); player2.remove(0);
				if (p1Val > p2Val) {
					player1.add(p1Val);
					player1.add(p2Val);
				} else {
					player2.add(p2Val);
					player2.add(p1Val);
				}
			}*/
			
			//P2
			playGame(player1, player2);
			List<Integer> result = player1.isEmpty() ? player2 : player1;
			int j = result.size();
			int score = 0;
			for (int i: result) {
				score += i * j--;
			}
			System.out.println(player1);
			System.out.println(player2);
			System.out.println(score);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static int playGame(List<Integer> p1d, List<Integer> p2d) {
        Set<String> states = new HashSet<>();
        while (p1d.size() > 0 && p2d.size() > 0) {
            String state = getState(p1d, p2d);
            if (!states.add(state)) { return 1; }

            int a = p1d.remove(0);
            int b = p2d.remove(0);

            int winner = 0;
            if (a <= p1d.size() && b <= p2d.size()) {
                winner = playGame(new LinkedList<>(p1d.subList(0, a)), new LinkedList<>(p2d.subList(0, b)));
            } else {
                winner = (a > b) ? 1 : 2;
            }

            if (winner == 1) {
                p1d.add(a);
                p1d.add(b);
            } else {
                p2d.add(b);
                p2d.add(a);
            }
        }

        return p1d.size() == 0 ? 2 : 1;
    }

    private static String getState(List<Integer> p1d, List<Integer> p2d) {
        return p1d.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(",")) + "@"
                + p2d.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(","));
    }
}
