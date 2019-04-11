public class PercolationDFSFast extends PercolationDFS {

	public PercolationDFSFast(int n) {
		super(n);
		
	}
	@Override
	protected void updateOnOpen(int row, int col) {
		boolean check1 = false;
		if (inBounds(row -1, col)) check1 = isFull(row-1, col);
		boolean c2 = false;
		if (inBounds(row +1, col)) c2 = isFull(row +1, col);
		boolean c3 = false;
		if (inBounds(row, col -1)) c3 = isFull(row, col -1);
		boolean c4 = false;
		if (inBounds(row, col +1)) c4 = isFull(row, col + 1);
		boolean check = false;
		if (check1 == true || c2 == true || c3 == true || c4 == true) check = true;
		if (row == 0 || check == true) {
			dfs(row, col);
		}
	
	}

}
