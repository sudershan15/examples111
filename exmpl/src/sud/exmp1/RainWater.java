package sud.exmp1;

/**
 * which island has more water 
 * | | | | | | | || | 
 * ||||| || || ||||||||
 * |||||||||||| |||| ||
 * |||||||||||||||||||||||||
 * 
 * @author smalpani
 *
 */
public class RainWater {
	public int Solution(int[] A) {

		if (A.length <= 2)
			return 0;

		int start = 0, end = A.length - 1, left = A[0], right = A[A.length - 1], result = 0;

		while (start < end - 1) {
			if (left <= right) {
				start++;
				if (A[start] >= left)
					left = A[start];
				else
					result += left - A[start];
			} else {
				end--;
				if (A[end] >= right)
					right = A[end];
				else
					result += right - A[end];
			}
		}
		return result;
	}

	public int maxArea(int[] height) {
		int maxArea = 0;
		int left = 0;
		int right = height.length - 1;
		while (left < right) {
			maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
			if (height[left] < height[right])
				left++;
			else
				right--;
		}
		return maxArea;
	}

	public static void main(String[] args) {
		RainWater rw = new RainWater();
		int[] A = { 1, 5, 3, 7, 2 }; // 2
		int[] B = { 1, 6, 2, 4, 7, 1, 8 };
		System.out.println(rw.Solution(B));
		System.out.println(rw.maxArea(B));
	}
}
