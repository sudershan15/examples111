package sud.exmp1;

public class replace {
	public static void main(String[] args) {
		String given = "abdgbhdbbbbd";
		String find = "bd";
		String replace_bd = "***REPLACED***";
		int no_of_occurences = 0;
		StringBuffer str = new StringBuffer();
		int length = given.length();
		int find_length = find.length();
		for (int i = 0; i < length; i++) {
			if(given.charAt(i) == find.charAt(0)) {
				int count = i;
				for (int f = 0; f < find_length; f++) {
					if(given.charAt(count++) == find.charAt(f)) {
						if (f == find_length - 1) {
							str.append(replace_bd);
							i = i + find_length - 1;
							no_of_occurences++;
						}
					} else {
						str.append(given.charAt(i));
						break;
					}
				}
			} else {
				str.append(given.charAt(i));
			}
		}
		System.out.println(str.toString() + "\nNo of Occurences: " + no_of_occurences);
	}
}
