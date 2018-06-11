package sudoku_game;

import java.util.Random;

abstract class BoxGenerator implements Runnable {
	private Cell[][] table;
	private Random rng;
	private int startRow;
	private int startCol;
	private int endRow;
	private int endCol;
	
	public BoxGenerator(Cell[][] table, Random rng, int startRow, int startCol, int endRow, int endCol) {
		this.table = table;
		this.rng = rng;
		this.startRow = startRow;
		this.startCol = startCol;
		this.endRow = endRow;
		this.endCol = endCol;
	}
	
	@Override
	public void run() {
		int range = this.table.length;
		int bits = 0;
		int n;
		
		for (int row = this.startRow ; row < this.endRow ; row++) {
			for (int col = this.startCol ; col < this.endCol ; col++) {
				do {
					n = this.rng.nextInt(range);
				} while ((bits & (1 << n)) != 0);
				
				bits |= (1 << n);
				int digit = n + 1;
				this.table[row][col] = new ConcreteCell(this.toChar(digit));
			}
		}
	}
	
	abstract char toChar(int digit);
}