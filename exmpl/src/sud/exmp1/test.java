package sud.exmp1;


class test {

	static void reverseStringWords1(char[] str) {
		int i, j, t;
		char temp;
		i = j = 0;
		while (j != str.length) {
			while (str[j] != ' ') {
				j++;
			}

			for (int k = i; k < j; k++, j--) {
				temp = str[k];
				str[k] = str[j];
				str[j] = temp;

			}
			System.out.println(str);
			while (i != ' ')
				i++;
			while (j != ' ')
				j++;
		}
		System.out.println(str);
	}

	static boolean isPalindrome(int x, int y) {
		if (x < 0)
			return false;
		if (x == 0)
			return true;
		System.out.println("x: " + x + "  y: " + y);
		if (isPalindrome(x / 10, y) && (x % 10 == y % 10)) {
			System.out.println("x: " + x + "  y: " + y);
			y /= 10;
			System.out.println("x: " + x + "  y: " + y);
			return true;
		} else {
			return false;
		}
	}

	//working
	static boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		int div = 1;
		while (x / div >= 10) {
			div *= 10;
		}
		while (x != 0) {
			int l = x / div;
			int r = x % 10;
			if (l != r)
				return false;
			x = (x % div) / 10;
			div /= 100;
		}
		return true;
	}

	//working
	static String preProcess(String s) {
		int n = s.length();
		if (n == 0)
			return "^$";
		String ret = "^";
		for (int i = 0; i < n; i++) {
			ret += "#" + s.substring(i, i + 1);
		}
		ret += "#$";
		return ret;
	}

	//working
	static String longestPalindrome(String s) {
		String T = preProcess(s);
		System.out.println(T.length() + "   " + T);
		int n = T.length();
		int[] P = new int[n];
		int C = 0, R = 0;
		for (int i = 1; i < n - 1; i++) {
			int i_mirror = 2 * C - i; // equals to i' = C - (i-C)

			P[i] = (R > i) ? Math.min(R - i, P[i_mirror]) : 0;

			System.out.println("I` " + i_mirror + "     P[" + i + "] " + P[i]
					+ "     C:" + C + "   R:" + R + "     I:" + i + "  "
					+ T.charAt(i));
			// Attempt to expand palindrome centered at i
			while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i]))
				P[i]++;

			// If palindrome centered at i expand past R,
			// adjust center based on expanded palindrome.
			if (i + P[i] > R) {
				C = i;
				R = i + P[i];
			}
		}

		// Find the maximum element in P.
		int maxLen = 0;
		int centerIndex = 0;
		for (int i = 1; i < n - 1; i++) {
			if (P[i] > maxLen) {
				maxLen = P[i];
				centerIndex = i;
			}
		}
		System.out.println(centerIndex + "   " + maxLen + "\n" + P);
		return s.substring((centerIndex - 1 - maxLen) / 2, maxLen);
	}

	public static boolean matches(String text, String pattern) {
		// if pattern is empty - whole string should be empty to match
		if (pattern.length() == 0)
			return text.length() == 0;

		// retrieve 2nd character of the pattern
		String nextChar = (pattern.length() > 1) ? String.valueOf(pattern
				.charAt(1)) : "";
		char t = (text.length() > 0) ? text.charAt(0) : 0;
		char p = pattern.charAt(0);

		// if 2nd char is not '*' - it means that we are checking if next char
		// in text matches with next char in pattern
		// and recursively run the code starting from +1 char in both
		// pattern/text
		if (!"*".equals(nextChar)) {
			return ((t == p) || (p == '.' && t != 0))
					&& matches(text.substring(1), pattern.substring(1));
		} else {
			while ((t == p) || (p == '.' && t != 0)) {
				// check if current text matches tail part of the pattern (+2
				// symbols)
				if (matches(text, pattern.substring(2)))
					return true;
				// cut first symbol in original text and repeat the check in the
				// loop
				text = text.substring(1);
				t = (text.length() > 0) ? text.charAt(0) : 0;
			}
			return matches(text, pattern.substring(2));
		}
	}

	public static void main(String args[]) {

		double payment = 0.1;
		double sum = 0.0;

		System.out.println(isPalindrome(238932));

		System.out.println(longestPalindrome("asdfgfds"));

		System.out.println(matches("awc", "awb*c"));

		
		
		/*
		 * for (int i=0; i<10; i++) { sum += payment; System.out.println(sum); }
		 * 
		 * 
		 * double payment1 = 0.125; double sum1 = 0.0;
		 * 
		 * for (int i=0; i<8; i++) { sum1 += payment1; System.out.println(sum1);
		 * }
		 */
	}
/*
 * 
 * 
 * public static Node insertInCircularSortedLL(Node node, int x)
    {
    	Node newNode = new Node(x);
    	
        if(node == null)//empty list
        {
            newNode.next = newNode;
            return newNode;
        }
        
        Node start = node;
        
        while(node.next != start)
        {
            if(node.val <= x)
            {
                newNode.next = node.next;
                node.next = newNode;
                return node;
            }
            node = node.next;
        }
        
        //if list has one value, min, max
        newNode.next = start;
        node.next = newNode;
        return node;
        
    }
 * 
 * 
 * 
 */
	// String str1 = "grass is green";
	//
	// // Approach 1
	// char[] str= str1.toCharArray();
	// reverseStringWords1(str);

	// Approach 2
	/*
	 * String[] str1 = str.split(" "); for(int i=0; i < str1.length(); i++) {
	 * System.out.println(str1[i]); }
	 */

	// Approach 3
	/*
	 * TreeMap<Integer, Integer> locs = new TreeMap<Integer, Integer>(); for(int
	 * i=0; i < str1.length(); i++) {
	 * 
	 * 
	 * if(str1.charAt(i) == ' ') {
	 * 
	 * locs.put(counter, i); counter = i+1; }
	 * 
	 * } locs.put(counter, str1.length()); System.out.println("Locations: " +
	 * locs + "         " + locs.keySet());
	 * 
	 * Set<Integer> a = locs.descendingKeySet(); Iterator<Integer> itr =
	 * a.iterator(); while (itr.hasNext()) {
	 * 
	 * int aa = itr.next(); //System.out.println (aa + " " + locs.get(aa));
	 * 
	 * for (int i = aa; i < locs.get(aa); i++) {
	 * System.out.print(str1.charAt(i)); } System.out.print(" "); }
	 */
}
// }