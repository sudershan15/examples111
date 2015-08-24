package sud.exmp1;

public class RemoveWhitespaces {
    public static void main(String[] args) {
        System.out.println(removeWhitespaces("      a    b       c dd  "));
    }

    public static String removeWhitespaces(String s) {
        if (s.length() <= 1) return s;

        char[] symbols = s.toCharArray();
        int j = 1;
        for (int i = 1; i < symbols.length; i++) {
            if (symbols[i] != ' ' || symbols[i - 1] != ' ') {
                symbols[j++] = symbols[i];
            }
        }
        return new String(symbols, 0, j).trim();
    }
}