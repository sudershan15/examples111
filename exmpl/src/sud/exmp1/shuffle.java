package sud.exmp1;

import java.util.Collection;
import java.util.HashMap;

public class shuffle {
	public static void main(String[] args) {
		String[] deck = {"H1","H2","H3","H4","H5","H6","H7","H8","H9","H10","H11","H12","H13",
				"D1","D2","D3","D4","D5","D6","D7","D8","D9","D10","D11","D12","D13",
				"S1","S2","S3","S4","S5","S6","S7","S8","S9","S10","S11","S12","S13",
				"C1","C2","C3","C4","C5","C6","C7","C8","C9","C10","C11","C12","C13"};
		
		System.out.println("Deck Earlier: ");
		printDeck(deck);
		
		System.out.println("\n");
		
		System.out.println("Deck after: ");
		deck = shuffleCards(deck);
		printDeck(deck);
		
		
	}
 public static String[] shuffleCards(String[] deck) {
	 String temp;
	 int index;
	 for (int i = 0; i < deck.length; i++) {
		 index = (int) (Math.random() * (deck.length - i)) + i;
		 temp = deck[i];
		 deck [i] = deck[index];
		 deck[index] = temp;
	 }
	 return deck;
 }
 
 public static void printDeck(String[] a) {
	 for (String aa: a) {
		 System.out.print(aa);
		 System.out.print("  ");
	 }
 }
}
