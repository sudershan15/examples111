package sud.exmp1;

public class TelephoneWords {
	static final int PHONE_NUMBER_LENGTH = 7;

	public static void main(String[] args) {
		TelephoneWords tw = new TelephoneWords();
		int[] phoneNum = {3,6,6,8,4,1,1};
		tw.printTelephoneWords(phoneNum );
	}
	void printTelephoneWords(int[] phoneNum) {
		char[] result = new char[PHONE_NUMBER_LENGTH];
		doPrintTelephoneWords(phoneNum, 0, result);
	}

	void doPrintTelephoneWords(int[] phoneNum, int curDigit, char[] result) {
		if (curDigit == PHONE_NUMBER_LENGTH) {
			System.out.println(new String(result));
			return;
		}
		for (int i = 1; i <= 3; i++) {
			result[curDigit] = getCharKey(phoneNum[curDigit], i);
			doPrintTelephoneWords(phoneNum, curDigit + 1, result);
			if (phoneNum[curDigit] == 0 || phoneNum[curDigit] == 1)
				return;
		}
	}

	private char getCharKey(int i, int i2) {
		char c = 0;
		switch (i) {
		case 2:
			switch (i2) {
			case 1:
				c = 'A';
				break;
			case 2:
				c = 'B';
				break;
			case 3:
				c = 'C';
				break;
			}
			break;
		case 3:
			switch (i2) {
			case 1:
				c = 'D';
				break;
			case 2:
				c = 'E';
				break;
			case 3:
				c = 'F';
				break;
			}
			break;
		case 4:
			switch (i2) {
			case 1:
				c = 'G';
				break;
			case 2:
				c = 'H';
				break;
			case 3:
				c = 'I';
				break;
			}
			break;
		case 5:
			switch (i2) {
			case 1:
				c = 'J';
				break;
			case 2:
				c = 'K';
				break;
			case 3:
				c = 'L';
				break;
			}
			break;
		case 6:
			switch (i2) {
			case 1:
				c = 'M';
				break;
			case 2:
				c = 'N';
				break;
			case 3:
				c = 'O';
				break;
			}
			break;
		case 7:
			switch (i2) {
			case 1:
				c = 'P';
				break;
			case 2:
				c = 'R';
				break;
			case 3:
				c = 'S';
				break;
			}
			break;
		case 8:
			switch (i2) {
			case 1:
				c = 'T';
				break;
			case 2:
				c = 'U';
				break;
			case 3:
				c = 'V';
				break;
			}
			break;
		case 9:
			switch (i2) {
			case 1:
				c = 'W';
				break;
			case 2:
				c = 'X';
				break;
			case 3:
				c = 'Y';
				break;
			}
			break;
		case 0:
			c = '0';
			break;
		case 1:
			c = '1';
			break;
		default:
			c = 'i';

		}
		return c;
	}

}
