package sud.exmp1;

public class Islands {

	public int islandPerimeter(int[][] grid) {
		int islands = 0;
		int neighbors = 0;
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[r].length; c++) {
				if (grid[r][c] == 1) {
					islands++;
					// right neighbor check
					if (c < grid[r].length - 1 && grid[r][c + 1] == 1)
						neighbors++;
					// down neighbor check
					if (r < grid.length - 1 && grid[r + 1][c] == 1)
						neighbors++;
				}
			}
		}
		return islands * 4 - neighbors * 2;
	}

	void dfs(char[][] grid, int r, int c) {
		int nr = grid.length;
		int nc = grid[0].length;
		if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
			return;
		}
		grid[r][c] = '0';
		dfs(grid, r - 1, c);
		dfs(grid, r + 1, c);

		dfs(grid, r, c - 1);
		dfs(grid, r, c + 1);
	}

	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int nr = grid.length;
		int nc = grid[0].length;
		int num_islands = 0;
		for (int r = 0; r < nr; ++r) {
			for (int c = 0; c < nc; ++c) {
				if (grid[r][c] == '1') {
					++num_islands;
					dfs(grid, r, c);
				}
			}
		}
		return num_islands;
	}
	
	public static void main(String[]args) {
		char[][] isl = {{'1','1','0','0','0'},
			{'1','1','0','0','0'},
			{'0','0','1','0','0'},
			{'0','0','0','1','1'}};
		
		Islands i = new Islands();
		System.out.println(i.numIslands(isl));
	}
}
