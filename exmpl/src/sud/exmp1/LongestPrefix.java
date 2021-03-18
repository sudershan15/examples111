package sud.exmp1;

public class LongestPrefix {
	public static String longestPrefix(String s) {
		// get all words
		String[] words = s.split(" ");
		// initial prefix length is set to the length of the first word
		int prefixLength = words[0].length();
		// iterate through the remaining words checking the prefix
		for (int i = 1; i < words.length; i++) {
			// if the length of ith word is less then prefix so far,
			// prefix cannot be longer then that
			if (prefixLength > words[i].length())
				prefixLength = words[i].length();
			// check charachters one by one and break on differing charachter
			for (int j = 0; j < Math.min(prefixLength, words[i].length()); j++) {
				if (words[i].charAt(j) != words[0].charAt(j)) {
					prefixLength = j;
					break;
				}
			}
		}
		return s.substring(0, prefixLength);
	}

	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0)
			return "";
		String firstWord = strs[0];
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(firstWord) != 0) {
				firstWord = firstWord.substring(0, firstWord.length() - 1);
			}
		}
		return firstWord;
	}

	public static void main(String[] args) {
		System.out.println(longestPrefix("abcdef abcdxxx abcdabcdef abcyy"));
	}
}
