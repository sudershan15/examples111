package sud.exmp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

class test {
	public static void main(String args[]) {
		System.out.println("Hello World");
		String str1 = "Hello this is my, name Sudershan.";
		int counter = 0;
		TreeMap<Integer, Integer> locs = new TreeMap<Integer, Integer>();
		//String[] str1 = str.split(" "); 
		for(int i=0; i < str1.length(); i++) {
			//System.out.println(str1[i]);
			
			if(str1.charAt(i) == ' ') {
				
				locs.put(counter, i);
				counter = i+1;
			}
			
		}
		locs.put(counter, str1.length());
		System.out.println("Locations: " + locs + "         " + locs.keySet());
		
		Set<Integer> a = locs.descendingKeySet();
		Iterator<Integer> itr = a.iterator();
		while (itr.hasNext()) {
			
			int aa = itr.next();
			//System.out.println (aa + " " + locs.get(aa));
			
			for (int i = aa; i < locs.get(aa); i++) {
				System.out.print(str1.charAt(i));
			}
			System.out.print(" ");
		}
	}
}