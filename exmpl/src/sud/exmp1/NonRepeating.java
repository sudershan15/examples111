package sud.exmp1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

class NonRepeating

{

	LinkedHashMap<Character, Integer> charMap = new LinkedHashMap<Character, Integer>();

	public char findFirstUniqueCharInString(String s)
	{
		int f=1;

		for(int i=0;i<s.length();i++) {
			if(!charMap.containsKey(s.charAt(i)))
				charMap.put(s.charAt(i),f);
			else
				charMap.put(s.charAt(i),charMap.get(s.charAt(i))+1); 
		} 
		for(Character z:charMap.keySet()) { 
			if(charMap.get(z).equals("1"))
				return z; 
		} 
		return 0; 
	}

	class countIndex {
		int count;
		int index;
	}

	public countIndex[] mapOfCountIndex(String str) {
		countIndex count[] = new countIndex[256];
		for (int i = 0; i < str.length(); i++) {
			if (count[str.charAt(i)] == null) {
				count[str.charAt(i)] = new countIndex();
				count[str.charAt(i)].count = 1;
				count[str.charAt(i)].index = i;
			} else {
				(count[str.charAt(i)].count)++;
			}
		}
		return count;
	}

	private String convertToString(int a) {

	    int c;
	    char m;
	    StringBuilder ans = new StringBuilder();
	    // convert the String to int
	    while (a > 0) {
	        c = a % 10;
	        a = a / 10;
	        m = (char) ('0' + c);
	        ans.append(m);
	    }
	    return ans.reverse().toString();
	}

	public Integer firstNonRepeatingCharacter(String str) {
		countIndex[] ci = mapOfCountIndex(str);
		Integer result = Integer.MAX_VALUE;
		for (int i = 0; i < 256; i++) {
			System.out.print(Integer.toString(i));
			if (ci[i] != null) {
				System.out.print("   " + ci[i].count + "     " + ci[i].index);
			} 
			System.out.println();
			if (ci[i] != null && ci[i].count == 1 && result > ci[i].index) {
				result = ci[i].index;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		NonRepeating nr = new NonRepeating();
		String s = "abracadabra";
		System.out.println(s.charAt(nr.firstNonRepeatingCharacter(s)));
		//System.out.println(nr.findFirstUniqueCharInString("geeksforgeeks"));
	}
}