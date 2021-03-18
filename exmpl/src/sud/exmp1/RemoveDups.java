package sud.exmp1;

import java.util.LinkedHashSet;

public class RemoveDups {
	public static String removeDuplicates(char[] str) {
		if (str == null) return "";
		int len = str.length;
		if (len < 2) return "";
		int tail = 1;
		for (int i = 1; i < len; ++i) {
			int j;
			for (j = 0; j < tail; ++j) {
				if (str[i] == str[j]) break;
			}
			if (j == tail) {
				str[tail] = str[i];
				++tail;
			}
		}
		str[tail] = 0;
		return new String(str);
	}
	void removeDuplicates(String str) 
    { 
        LinkedHashSet<Character> lhs = new LinkedHashSet<Character>(); 
        for(int i=0;i<str.length();i++) 
            lhs.add(str.charAt(i)); 
          
        // print string after deleting duplicate elements 
        for(Character ch : lhs) 
            System.out.print(ch); 
    } 
	
	public static void removeDuplicatesEff(char[] str) {
		if (str == null) return;
		int len = str.length;
		if (len < 2) return;
		boolean[] hit = new boolean[256];
		for (int i = 0; i < 256; ++i) {
			hit[i] = false;
		}
		hit[str[0]] = true;
		int tail = 1;
		for (int i = 1; i < len; ++i) {
			if (!hit[str[i]]) {
				str[tail] = str[i];
				++tail;
				hit[str[i]] = true;
			}
		}
		str[tail] = 0;
		System.out.println(new String(str));
	}
	public static void main(String[] args) {
		String s = "shuddershaun";
		String contiguousDups = "abababa";
		RemoveDups r = new RemoveDups();
		System.out.println(removeDuplicates(s.toCharArray()));
		r.removeDuplicates(s);
		System.out.println();
		removeDuplicatesEff(contiguousDups.toCharArray());
		r.removeDuplicates(contiguousDups);
		//Or use sorting or indexOf method java
	}
}
