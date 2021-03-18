package sud.exmp1;

import java.util.*;

import sud.exmp1.Combinations;

public class permute
{
	
	  
	public static void main(String[] args) throws Exception {
	    String str = "abc";
	    //Combinations cm = new Combinations();
	    //cm.generate(str);
	    boolean[] used = new boolean[str.length()];
	    StringBuffer out = new StringBuffer();
	    permute p = new permute();
	    p.doPermute(str.toCharArray(), out, used, str.length(), 0);
	    //doPerm(out,str.length());

	}
	
	// complexity: O(n!)
	public static ArrayList<String> getPerms(String s) {
		ArrayList<String> permutations = new ArrayList<String>();
		if (s == null) { // error case4 
			return null;
		} else if (s.length() == 0) { // base case6 
			permutations.add("");
			return permutations;
		}
		
		char first = s.charAt(0); // get the first character
		String remainder = s.substring(1); // remove the first character
		ArrayList<String> words = getPerms(remainder);
		for (String word : words) {
			for (int j = 0; j <= word.length(); j++) {
				permutations.add(insertCharAt(word, first, j));
			}
		}
		
		return permutations;
	}
	
	public static String insertCharAt(String word, char c, int i) {
		String start = word.substring(0, i);
		String end = word.substring(i);
		return start + c + end;
	}
	
	public void doPermute(char[] str, StringBuffer out, boolean[] used, int len, int level) {
		if (level == len) {
			System.out.println(out.toString());
			return;
		}
		for (int i = 0; i < len; ++i) {
			if (used[i]) {
				continue;
			}
			out.append(str[i]);
			used[i] = true;
			doPermute(str, out, used, len, level + 1);
			used[i] = false;
			out.setLength(out.length() - 1);
		}
	}

	private static void doPerm(StringBuffer str, int index) {
	    if(index == 0)
	        System.out.println(str);            
	    else { //recursively solve this by placing all other chars at current first pos
	        doPerm(str, index-1);
	        int currPos = str.length()-index;
	        for (int i = currPos+1; i < str.length(); i++) {//start swapping all other chars with current first char
	            swap(str,currPos, i);
	            doPerm(str, index-1);
	            swap(str,i, currPos);//restore back my string buffer
	        }
	    }
	}

	private  static void swap(StringBuffer str, int pos1, int pos2) {
	    char t1 = str.charAt(pos1);
	    str.setCharAt(pos1, str.charAt(pos2));
	    str.setCharAt(pos2, t1);
	} 
}   

