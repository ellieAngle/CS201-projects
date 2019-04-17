public class PercolationDFSFast extends PercolationDFS {
	/*
	 * @param n: integer representing the number of rows and columns in grid
	 * Initializes variables from PercolationDFS using super(n)
	 */
	public PercolationDFSFast(int n) {
		super(n);
		
	}
	/*
	 * @see PercolationDFS#updateOnOpen(int, int)
	 * @Overrides DFS updateOnOpen
	 * @param row: integer representing selected row of opened cell
	 * @param col: integer representing selected column of opened cell
	 * uses only one dfs call, as opposed to calling dfs on all possible neighbors
	 */
	@Override
	protected void updateOnOpen(int row, int col) {
		boolean c1 = false;
		if (inBounds(row -1, col)) c1 = isFull(row-1, col);
		boolean c2 = false;
		if (inBounds(row +1, col)) c2 = isFull(row +1, col);
		boolean c3 = false;
		if (inBounds(row, col -1)) c3 = isFull(row, col -1);
		boolean c4 = false;
		if (inBounds(row, col +1)) c4 = isFull(row, col + 1);
		boolean check = false;
		if (c1 == true || c2 == true || c3 == true || c4 == true) check = true;
		if (row == 0 || check == true) {
			dfs(row, col);
		}
	
	}

}
