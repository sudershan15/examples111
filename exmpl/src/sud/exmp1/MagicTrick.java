package sud.exmp1;

import java.util.HashSet;
import java.util.Scanner;

/*
 * Problem

Recently you went to a magic show. You were very impressed by one of the tricks, so you decided to try to figure out the secret behind it!

The magician starts by arranging 16 cards in a square grid: 4 rows of cards, with 4 cards in each row. Each card has a different number from 1 to 16 written on the side that is showing. Next, the magician asks a volunteer to choose a card, and to tell him which row that card is in.

Finally, the magician arranges the 16 cards in a square grid again, possibly in a different order. Once again, he asks the volunteer which row her card is in. With only the answers to these two questions, the magician then correctly determines which card the volunteer chose. Amazing, right?

You decide to write a program to help you understand the magician's technique. The program will be given the two arrangements of the cards, and the volunteer's answers to the two questions: the row number of the selected card in the first arrangement, and the row number of the selected card in the second arrangement. The rows are numbered 1 to 4 from top to bottom.

Your program should determine which card the volunteer chose; or if there is more than one card the volunteer might have chosen (the magician did a bad job); or if there's no card consistent with the volunteer's answers (the volunteer cheated).
 */

public class MagicTrick {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=1; t<=T; t++) {
			int n = in.nextInt()-1;
			HashSet<Integer> set1 = new HashSet<Integer>(), set2 = new HashSet<Integer>();
			for(int i=0; i<4; i++)
				for(int j=0; j<4; j++)
					if(i == n) set1.add(in.nextInt());
					else in.nextInt();
			int m = in.nextInt()-1;
			for(int i=0; i<4; i++)
				for(int j=0; j<4; j++)
					if(i == m) set2.add(in.nextInt());
					else in.nextInt();
					
			set1.retainAll(set2);
			System.out.printf("Case #%d: ", t);
			int ans = 0;
			for(int x : set1) ans = x;
			if(set1.size() == 0) System.out.println("Volunteer cheated!");
			else if(set1.size() > 1) System.out.println("Bad magician!");
			else System.out.println(ans);
		}
	}
}
