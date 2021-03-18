package sud.exmp1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The Next Greater Number of a number x in nums1 is
the first greater number to its right in nums2. If it
does not exist, output -1 for this number.
 * @author sudershan.malpani
 *
 */
public class NextGreaterNo {
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		for (int num : nums2) {
			while (!stack.isEmpty() && stack.peek() < num) {
				map.put(stack.pop(), num);
			}
			stack.push(num);
		}
		int[] res = new int[nums1.length];
		for (int i = 0; i < nums1.length; i++) {
			res[i] = map.getOrDefault(nums1[i], -1);
		}
		return res;
	}
}
