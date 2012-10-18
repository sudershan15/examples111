package sud.exmp1;

public class rotate {

	static String arr[] = {"R", "G", "B", "G", "G", "R", "B", "B", "G", "R", "G", "B", "B"};

	public static void main(String args[]) {

		int final_len = arr.length;
		int tem_length = final_len;
		for(int i = 0; i < tem_length; i++) {
			if(arr[i] == "G")
			{
				swap(i , final_len);
				i = i -1;
				tem_length = tem_length -1;
			}
			if(arr[i] == "R")
			{
				int counter = 0;
				if (i != 0) {
					swap(counter , i);
					counter ++;
				}

			}
		}
		for (int i=0; i< final_len; i++)
			System.out.println(arr[i]);
	}

	private static void swap(int start , int end) {
		// TODO Auto-generated method stub
		String temp;
		if(end == arr.length)
		{
			temp = arr[start];
			for (int i = start; i < end; i++) {
				if((i+1) != end)
					arr[i] = arr[i+1];
			}
			arr[end-1] = temp;
		}else{
			temp = arr[end];
			for (int i = end; i > start; i--) {
				arr[i] = arr[i-1];
			}
			arr[start] = temp;
		}

	}
}
