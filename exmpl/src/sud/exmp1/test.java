package sud.exmp1;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import javax.sound.sampled.ReverbType;

class test {
	
	static void reverseStringWords1(char[] str)
	{
		int i,j, t;
		char temp;
		i=j=0;
		while(j!=str.length)
		{
			while(str[j]!=' '){
				j++;
			}
			
			for(int k = i; k < j; k++, j--)
			{
				temp = str[k];
				str[k] = str [j];
				str[j] = temp;
				
			}
			System.out.println(str);
			while(i!=' ')i++;
			while(j!=' ')j++;
		}
		System.out.println(str);
	}
	public static void main(String args[]) {
		
		double payment = 0.1;
		double sum = 0.0;

		for (int i=0; i<10; i++)
		{
		sum += payment;
		System.out.println(sum);
		}
		
		
		double payment1 = 0.125;
		double sum1 = 0.0;

		for (int i=0; i<8; i++) {
		sum1 += payment1;
		System.out.println(sum1);
		}
		
		}
		
//		String str1 = "grass is green";
//		
//		// Approach 1
//		char[] str= str1.toCharArray();
//		reverseStringWords1(str);

		// Approach 2
		/*String[] str1 = str.split(" "); 
		for(int i=0; i < str1.length(); i++) {
			System.out.println(str1[i]);
		}*/
		
		//Approach 3
		/*TreeMap<Integer, Integer> locs = new TreeMap<Integer, Integer>();
		for(int i=0; i < str1.length(); i++) {
			
			
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
		}*/
	}
//}