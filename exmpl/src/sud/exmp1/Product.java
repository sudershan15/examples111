package sud.exmp1;

public class Product {

	public static void main(String[] args) {
		int num[] = { 1, 2, 8, 3, 0, 4, 5 };
		int result[];
		result = productExceptSelf(num);
		for (int i=0; i< result.length; i++)
			System.out.print(result[i] + " ");
	}

	private static int[] prod(int[] num) {
		int product[] = new int[num.length], p = 1;
		for (int j = 0; j < num.length; j++) {
			p = 1;
			for (int i = 0; i < num.length; i++) {
				if (i == j) {
					p *= 1;
				} else {
					p *= num[i];
				}
			}
			product[j] = p;
			//System.out.println(product[j]);
		}
		return product;
	}

	public static int[] productExceptSelf(int[] nums) {
		int[] result = new int[nums.length];
		result[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			result[i] = result[i - 1] * nums[i - 1];
			System.out.println(result[i]);
		}
		
		int temp = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			result[i] *= temp;
			temp = temp * nums[i];
			System.out.println(result[i] + "  " + temp);
		}
		return result;
	}
}