package sud.exmp1;

//a is a magic number iff a*a = c (6 digit number) = (first 3 digits of c) + (last 3 digits of c)
public class magic {
	public static void main(String args[]){
		int final_number;
		int first_half;
		int second_half;
		int sum;
		for ( int i= 317; i <= 999; i++) {
			final_number =  i*i;
			first_half = final_number/1000;
			second_half = final_number%1000;
			sum = first_half + second_half;
			if(sum == i)
			{
				System.out.println(i + "   " + final_number);
			}
		}
	}		
}
