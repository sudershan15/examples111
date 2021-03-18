package sud.exmp1;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		List<Integer> prevRowList = new ArrayList<Integer>();

		for (int i = 1; i <= numRows; i++) {
			List<Integer> subList = new ArrayList<Integer>();
			for (int j = 0; j < i; j++) {
				if (j == 0 || j == i - 1)
					subList.add(1);
				else
					subList.add(prevRowList.get(j) + prevRowList.get(j - 1));
			}
			prevRowList = subList;
			list.add(subList);
		}
		return list;
	}

	public List<Integer> getRow(int rowIndex) {
		List<Integer> rowList = new ArrayList<Integer>();
		for (int i = 0; i <= rowIndex; i++) {
			rowList.add(1);
			for (int j = i - 1; j > 0; j--) {
				rowList.set(j, rowList.get(j) + rowList.get(j - 1));
			}
		}
		return rowList;
	}

	// Driver Code
	public static void main(String[] args) {
		PascalTriangle pt = new PascalTriangle();
		System.out.println(pt.generate(5));
		System.out.println();
		System.out.println(pt.getRow(3));
	}
}
