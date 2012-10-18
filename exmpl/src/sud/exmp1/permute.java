package sud.exmp1;

import java.util.*;
import sud.exmp1.Combinations;

public class permute
{
	
	  
	public static void main(String[] args) throws Exception {
	    String str = "abc";
	    Combinations cm = new Combinations();
	    cm.generate(str);
	    //StringBuffer strBuf = new StringBuffer(str);
	   // doPerm(strBuf,str.length());

	}

	private static void doPerm(StringBuffer str, int index) {
	    if(index == 0)
	        System.out.println(str);            
	    else { //recursively solve this by placing all other chars at current first pos
	        doPerm(str, index-1);
	        int currPos = str.length()-index;
	        for (int i = currPos+1; i < str.length(); i++) {//start swapping all other chars with current first char
	            swap(str,currPos, i);
	            doPerm(str, index-1);
	            swap(str,i, currPos);//restore back my string buffer
	        }
	    }
	}

	private  static void swap(StringBuffer str, int pos1, int pos2) {
	    char t1 = str.charAt(pos1);
	    str.setCharAt(pos1, str.charAt(pos2));
	    str.setCharAt(pos2, t1);
	} 
}   

