package sud.exmp1;

public class FindOccurence {
	static char[][] inpArray = { { 'S', 'N', 'B', 'S', 'N' },
			{ 'B', 'A', 'K', 'E', 'A' }, { 'B', 'K', 'B', 'B', 'K' },
			{ 'S', 'E', 'B', 'S', 'E' } };

	public static void main(String args[]) {
		String inpString = "SNAKES";
		int numOccurances = 0;
		for (int i = 0; i < inpArray.length; i++) {
			for (int j = 0; j < inpArray[0].length; j++) {
				if (inpArray[i][j] == inpString.charAt(0))
					numOccurances += findNewOccurance(inpString, i, j);
			}
		}
		System.out.println("Number of occurances of " + inpString + " is "
				+ numOccurances);
	}

	public static int findNewOccurance(String inpString, int row, int col) {
		int numOccurances = 0;

		if (inpString.length() == 0)
			return 0;
		if (inpString.length() == 1
				&& inpString.charAt(0) == inpArray[row][col])
			return 1;
		if (inpString.charAt(0) != inpArray[row][col])
			return 0;
		else {
			String remainingString = inpString.substring(1, inpString.length());
			System.out.println(remainingString);
			if (row + 1 < inpArray.length)
				numOccurances += findNewOccurance(remainingString, row + 1, col);
			if (row - 1 >= 0)
				numOccurances += findNewOccurance(remainingString, row - 1, col);
			if (col + 1 < inpArray[0].length)
				numOccurances += findNewOccurance(remainingString, row, col + 1);
			if (col - 1 >= 0)
				numOccurances += findNewOccurance(remainingString, row, col - 1);
		}
		return numOccurances;
	}
}
