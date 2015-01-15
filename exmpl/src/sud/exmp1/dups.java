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
		int a1=5, b1= 9, c1 =a1-b1;
		//you shifts -15 to right for 31 times using >> operator so your right most 31 bits are loosed and results is all bits 1 that is actually -1 in magnitude.
		//0x1 for 1st bit, 0x2 for 2nd, 0x4 for 3rd, 0x8 for 4th, 0x10 for 5th etc.
		int k=(c1>>31)&0x1;
		System.out.println((c1>>31) + "     " + 0x1 + "       "+ k);
		int max = a1-k*c1;
		
		System.out.println(max);
		
		int a = 60;	/* 60 = 0011 1100 */  
	     int b = 13;	/* 13 = 0000 1101 */
	     int c = 0;

	     c = a & b;       /* 12 = 0000 1100 */ 
	     System.out.println("a & b = " + c );

	     c = a | b;       /* 61 = 0011 1101 */
	     System.out.println("a | b = " + c );

	     c = a ^ b;       /* 49 = 0011 0001 */
	     System.out.println("a ^ b = " + c );

	     c = ~a;          /*-61 = 1100 0011 */
	     System.out.println("~a = " + c );

	     c = a << 2;     /* 240 = 1111 0000 */
	     System.out.println("a << 2 = " + c );

	     c = a >> 2;     /* 215 = 1111 */
	     System.out.println("a >> 2  = " + c );

	     c = a >>> 2;     /* 215 = 0000 1111 */
	     System.out.println("a >>> 2 = " + c );
	     
	     
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
