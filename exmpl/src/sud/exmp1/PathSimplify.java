package sud.exmp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PathSimplify {
	public String simplifyPath(String path) {
		Stack<String> stack = new Stack<String>();
		String[] splitPath = path.split("/");
		for (String s : splitPath) {
			if (!stack.isEmpty() && s.equals(".."))
				stack.pop();
			else if (!s.equals(".") && !s.equals("") && !s.equals(".."))
				stack.push(s);
		}
		List<String> list = new ArrayList(stack);
		return "/" + String.join("/", list);
	}
	
	public static void main(String[] args) {
		PathSimplify ps = new PathSimplify();
		String ps1 = "/home/";
		String ps2 = "/a/./b/../../c/";
		System.out.println(ps.simplifyPath(ps1));
		System.out.println(ps.simplifyPath(ps2));
	}
}
