
public class PercolationUF implements IPercolate {
	private boolean[][] myGrid;
	private int myOpenCount;
	private IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	/**
	 * @param size: represents number of rows and columns of myGrid
	 * @param finder: IUnionFind object used to keep track of full and open cells
	 * Initializes variables:
	 * 		myGrid to a two-dimensional array of boolean values
	 * 		VTOP: final value of size*size
	 * 		VBOTTOM: final value of size*size + 1
	 * 		initializes myFinder to IUnionFind finder(size * size + 2)
	 */
	public PercolationUF(int size, IUnionFind finder) {
		boolean[][] temp = new boolean [size][size];
		myFinder = finder;
		VTOP = size * size;
		VBOTTOM = size * size +1;
		finder.initialize(size * size+2);
		myGrid = temp;
	}
	/*
	 * (non-Javadoc)
	 * @see IPercolate#open(int, int)
	 */
	@Override
	public void open(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (myGrid[row][col] == true) return;
		myGrid[row][col] = true;
		myOpenCount ++;
		int x = getInt(row, col);
		if (row == 0) myFinder.union(x,  VTOP);
		if (row == myGrid.length - 1) myFinder.union(x, VBOTTOM);
		if (inBounds(row +1, col) && isOpen(row +1, col)) {
			myFinder.union(x, getInt(row+1, col));
		}
		if (inBounds(row -1, col) && isOpen(row -1, col)) {
			myFinder.union(x, getInt(row-1, col));
		}
		if (inBounds(row, col+ 1) && isOpen(row, col + 1)) {
			myFinder.union(x, getInt(row, col + 1));
		}
		if (inBounds(row, col- 1) && isOpen(row, col - 1)) {
			myFinder.union(x, getInt(row, col - 1));
		}
	}
	/*
	 * (non-Javadoc)
	 * @see IPercolate#isOpen(int, int)
	 */
	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	
		}
	/*
	 * (non-Javadoc)
	 * @see IPercolate#isFull(int, int)
	 */
	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		
		return myFinder.connected(getInt(row, col), VTOP);
	}
	/*
	 * (non-Javadoc)
	 * @see IPercolate#percolates()
	 */
	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
	/*
	 * (non-Javadoc)
	 * @see IPercolate#numberOfOpenSites()
	 */
	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	/*
	 * @param row: integer representing selected row of opened cell
	 * @param col: integer representing selected column of opened cell
	 * determines whether or not the input row & column is inBounds
	 */
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	/*
	 * @param row: integer representing selected row of opened cell
	 * @param col: integer representing selected column of opened cell
	 * Gets corresponding integer to the input row and column
	 */
	public int getInt(int row, int col) {
		int totSize = myGrid.length;
		return (row*totSize) + col;
	}
}
