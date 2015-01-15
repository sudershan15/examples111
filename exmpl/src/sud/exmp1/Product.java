package sud.exmp1;

public class Product {


	public static void main(String[] args) {
		int num[]={1,2,8,3,0,4,5};
		int result[];
		result=prod(num);
	}



	private static int[] prod(int[] num) {
		int product[]=new int[num.length],p=1;
		for(int j = 0; j < num.length; j++) {
			p=1;
			for(int i = 0; i < num.length; i++) {
				if(i == j) {
					p*=1;
				} else {
					p*=num[i];
				}
			}
			product[j] = p;
			System.out.println(product[j]);
		}
		return product;
	}
}