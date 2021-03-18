package sud.exmp1;

public class PainthouseMinCost {
	//n x k house x colors
	public int minCostII(int[][] costs) {
		if (costs.length == 0) {
			return 0;
		}
		int min1 = 0, min2 = 0, index1 = -1;
		for (int i = 0; i < costs.length; i++) {
			int m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE, idx1 = -1;
			for (int j = 0; j < costs[0].length; j++) {
				costs[i][j] += (j != index1 ? min1 : min2);
				if (costs[i][j] < m1) { // cost < m1 < m2
					m2 = m1;
					m1 = costs[i][j];
					idx1 = j;
				} else if (costs[i][j] < m2) { // m1 < cost < m2
					m2 = costs[i][j];
				}
			}
			min1 = m1;
			min2 = m2;
			index1 = idx1;
		}
		return min1;
	}

	public int minCost(int[][] costs) {
		if (costs == null || costs.length == 0)
			return 0;
		for (int i = 1; i < costs.length; i++) {
			costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
			costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
			costs[i][2] += Math.min(costs[i - 1][1], costs[i - 1][0]);
		}
		int n = costs.length - 1;
		return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
	}

	public static void main(String[] args) {
		int[][] costs = { { 17, 2, 17 }, { 16, 16, 5 }, { 14, 3, 19 } };
		PainthouseMinCost pmc = new PainthouseMinCost();
		System.out.println(pmc.minCostII(costs));
	}
}
