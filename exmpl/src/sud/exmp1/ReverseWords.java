package sud.exmp1;

public class ReverseWords {

	public ReverseWords() {
		// TODO Auto-generated constructor stub
	}
	
	static char[] ReverseString(char str[], int start, int end) {
		char temp;
		while(end > start) {
			temp = str[start];
			str[start++] = str[end];
			str[end--] = temp;
		}
		return str;
	}
	
	static void ReverseSentence(char[] str1, int length) {
		int start = 0, end = 0;
		str1 = ReverseString(str1, start, length-1);
		for(int i = 0; i < str1.length; i++)
			System.out.print(str1[i]);
		System.out.println();
		while(end < length) {
			if (str1[end] != ' ') {
				start = end;
				while(end < length && str1[end] != ' ')
					end++;
				end--;
				str1 = ReverseString(str1, start, end);
				for(int i = 0; i < str1.length; i++)
					System.out.print(str1[i]);
				System.out.println();
			}
			end++;
		}
		return;
	}
	public static void main(String args[]) {
		String str = "grass is green";
		char[] str1 = str.toCharArray();
		int len = str.length(); 
		ReverseSentence(str1, len);
		for(int i = 0; i < str1.length; i++)
		System.out.print(str1[i]);
	}
}
