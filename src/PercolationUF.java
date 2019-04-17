
public class PercolationUF implements IPercolate {
	private boolean[][] myGrid;
	private int myOpenCount;
	private IUnionFind myFinder;
	private final int VTOP;
	private final int VBOTTOM;
	
	
	public PercolationUF(int size, IUnionFind finder) {
		boolean[][] temp = new boolean [size][size];
		myFinder = finder;
		VTOP = size * size;
		VBOTTOM = size * size +1;
		finder.initialize(size * size+2);
		myGrid = temp;
	}
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
			myOpenCount ++;
		}
		if (inBounds(row -1, col) && isOpen(row -1, col)) {
			myFinder.union(x, getInt(row-1, col));
			myOpenCount ++;
		}
		if (inBounds(row, col+ 1) && isOpen(row, col + 1)) {
			myFinder.union(x, getInt(row, col + 1));
			myOpenCount ++;
		}
		if (inBounds(row, col- 1) && isOpen(row, col - 1)) {
			myFinder.union(x, getInt(row, col - 1));
			myOpenCount ++;
		}
	}

	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	
		}

	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		
		return myFinder.connected(getInt(row, col), VTOP);
	}

	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}

	@Override
	public int numberOfOpenSites() {
		return myOpenCount - 1;
	}
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	
	public int getInt(int row, int col) {
		int totSize = myGrid.length;
		return (row*totSize) + col;
	}
}
