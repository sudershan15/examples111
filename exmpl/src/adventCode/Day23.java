package adventCode;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day23 {
	public static void main(String[] args) {
		String input = "586439172";
		String inputEx = input;//"389125467";
		
		List<Integer> arrList = new ArrayList<>();
		for (char c: inputEx.toCharArray()) {
			arrList.add(Integer.parseInt(Character.toString(c)));
		}
		int currCup = 0;
		int offSet = 3;
		int moves = 100;
		for (int i = 0; i < moves; i++) {
			int start = arrList.get(currCup);
			List<Integer> intermedList = new ArrayList<>();
			int getCup = currCup + 1;
			int idx1 = 0;
			for (int j = getCup; j <= getCup + offSet - 1; j++) {
				if (j < arrList.size())
				   intermedList.add(arrList.get(j));
				else {
					intermedList.add(arrList.get(idx1++));
				}
			}
			arrList.removeAll(intermedList);
			int dest = findDest(arrList, start);
			arrList = insert(intermedList, arrList, arrList.indexOf(dest));
			currCup = arrList.indexOf(start) + 1 < arrList.size() ? arrList.indexOf(start) + 1 : 0;
			//System.out.println(start + "   " + dest + "  " + arrList + "   " + intermedList);
		}
		System.out.println(arrList);
	}
	
	public static int findDest(List<Integer> list, int start) {
		int dest = (start - 1) >= 1 ? start - 1 : 9;
		if (list.contains(dest)) {
			return dest;
		}
		return findDest(list, dest);
	}
	
	static List<Integer> insert(List<Integer> ilist, List<Integer> alist, int idx) {
		List<Integer> olist = new ArrayList<>();
		for (int i = idx + 1 ; i < alist.size(); i++) {
			olist.add(alist.get(i));
		}
		alist.removeAll(olist);
		alist.addAll(ilist);
		alist.addAll(olist);
		return alist;
	}
}
