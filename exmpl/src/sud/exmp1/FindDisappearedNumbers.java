package sud.exmp1;

import java.util.ArrayList;
import java.util.List;

class FindDisappearedNumbers {
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> list = new ArrayList<Integer>();
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			int index = Math.abs(nums[i]) - 1;
			nums[index] = -Math.abs(nums[index]);
		}
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0)
				list.add(i + 1);
		}
		return list;
	}

	public static void main(String[] args) {
		FindDisappearedNumbers rw = new FindDisappearedNumbers();
		int[] A = { 1, 5, 3, 7, 2 }; // error case
		int[] B = { 4, 3, 2, 7, 8, 2, 3, 1 };
		System.out.println(rw.findDisappearedNumbers(B));
	}
}