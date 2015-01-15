package sud.exmp1;

//No of unordered paairs
public class InversionCount {
	int numberOfInversions(int a[]) {
		int inversionCount = 0;
		Node root = new Node();
		root.v = a[0];
		root.count = 1;
		for (int i = 1; i < a.length; ++i) {
			Node node = root;
			while (true) {
				node.count++;
				if (a[i] < node.v) {
					inversionCount += (node.right != null ? node.right.count : 0) + 1;
					if (node.left == null) {
						node.left = new Node();
						node.left.v = a[i];
						node.left.count = 1;
						break;
					}
					else {
						node = node.left;
					}
				}
				else {
					if (node.right == null) {
						node.right = new Node();
						node.right.v = a[i];
						node.right.count = 1;
						break;
					}
					else {
						node = node.right;
					}
				}
			}
		}
		return inversionCount;
	}

	static class Node {
		public int v;
		public Node left;
		public Node right;
		public int count;
	}
	
	public static void main(String[] args) {
		InversionCount ic = new InversionCount();
		int[] a = {1,2,5,3,7,4,6};
		System.out.println(ic.numberOfInversions(a));
	}
}
