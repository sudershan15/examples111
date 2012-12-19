package sud.exmp1;

import java.util.Hashtable;

public class dups {

	public dups() {
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * @param args
	 */
	//@SuppressWarnings("null")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=5, b= 9, c =a-b;
		System.out.println((c>>31) + "     " + 0x1 );
		int k=(c>>31)&0x1;
		System.out.println((c>>31) + "     " + 0x1 + "       "+ k);
		System.out.println(c);
		int max = a-k*c;
		
		System.out.println(max);
		/*int list[] = {1,2,5,2,3,4,1,3}, res[] = {0,0,0,0,0,0,0};
		int k=0, i;
		for (i=0; i < list.length; i++) {
			k = k ^ list[i];
			if(res[k] == 0) {
				res[k] = list[i];
			} else {
				res[k] = 0;
			}
			k=0;
		}
		for(i = 0; i < res.length; i++) {
			System.out.println("=----->" + res[i]);
		}
		System.out.println("is first dup: " + list[k]);*/
		Hashtable charhash = new Hashtable();
		int i, len;
		String str = "My name is Merry Jones Merry Christmas";
		String[] s1= str.split(" ");
		Integer intgr;
		len = s1.length;
		for (i = 0; i < len; i++) {
			intgr = (Integer) charhash.get(s1[i]);
			if(intgr == null) {
				charhash.put(s1[i], new Integer(1));
			} else {
				charhash.put(s1[i], new Integer(intgr.intValue() + 1));
				
			}
		}
		for(i=0;i<len;i++) {
			System.out.println(s1[i] + "    " + charhash.get(s1[i]));
		}
		
		
	}

}
