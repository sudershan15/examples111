package sud.exmp1;

import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Zigzag {
	static void printZigZagConcat(String str, 
            int n)  
    { 
  
        // Corner Case (Only one row) 
        if (n == 1)  
        { 
            System.out.print(str); 
            return; 
        } 
        char[] str1 = str.toCharArray(); 
  
        // Find length of string 
        int len = str.length(); 
  
        // Create an array of 
        // strings for all n rows 
        String[] arr = new String[n]; 
        Arrays.fill(arr, ""); 
  
        // Initialize index for 
        // array of strings arr[] 
        int row = 0; 
        boolean down = true; // True if we are moving  
        // down in rows, else false 
  
        // Travers through 
        // given string 
        for (int i = 0; i < len; ++i)  
        { 
            // append current character 
            // to current row 
            arr[row] += (str1[i]); 
  
            // If last row is reached, 
            // change direction to 'up' 
            if (row == n - 1)  
            { 
                down = false; 
            }  
              
            // If 1st row is reached,  
            // change direction to 'down' 
            else if (row == 0)  
            { 
                down = true; 
            } 
  
            // If direction is down,  
            // increment, else decrement 
            if (down) 
            { 
                row++; 
            }  
            else 
            { 
                row--; 
            } 
        } 
  
        // Print concatenation 
        // of all rows 
        for (int i = 0; i < n; ++i)  
        { 
            System.out.print(arr[i]); 
        } 
    } 
  
    // Driver Code 
    public static void main(String[] args) 
    { 
        String str = "GEEKSFORGEEKS"; 
        String str1 = "PAYPALISHIRING";
        int n = 3; 
        System.out.println(String.format("MAC %s", String.format("id=\\\"%s\\\",ts=\"%s\",nonce=\"%s\",mac=\"%s\"",
                "aa", "bb", "cc", "dd")));
        
        try {
        		final String macKey = "";
        		final Mac hmac = Mac.getInstance("HmacSHA256");
        		hmac.init(new SecretKeySpec(macKey.getBytes(), "HmacSHA256"));
        		 
			byte[] bytes = Base64.decode("XXRWsli++WXNMVi1Xc+z1YsTYsc0+U1sYFxNG32Jk8M=");
			System.out.println(new String(hmac.bytes));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        printZigZagConcat(str1, n); 
    } 
}
