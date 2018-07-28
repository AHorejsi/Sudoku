package sudoku_game;

import java.util.Random;

/**
 * Swaps the rows and columns
 * within a row or column of
 * boxes
 * @author Alex Horejsi
 */
public class InnerMixer implements Mixer {
	private static Mixer mixer = new InnerMixer();
	
	private InnerMixer() {}
	
	/**
	 * Returns the single instance
	 * of {@code InnerMixer}
	 * @return The single instance
	 * of {@code InnerMixer}
	 */
	public static Mixer getInstance() {
		return InnerMixer.mixer;
	}
	
	@Override
	public void mix(Board board, Random rng) {
		Cell[][] table = board.getTable();
		
		this.innerRowMix(board.rowSizeInBox(), table, rng);
		this.innerColumnMix(board.colSizeInBox(), table, rng);
	}
	
	private void innerRowMix(int rowSize, Cell[][] table, Random rng) {
		int current = 0;
		int end = table.length;
		int currentEnd = rowSize;
		
		while (currentEnd <= end) {
			int rowToShuffle = rng.nextInt(currentEnd - current) + current;
			
			if (rowToShuffle != current) {
				Cell[] temp = table[current];
				table[current] = table[rowToShuffle];
				table[rowToShuffle] = temp;
			}
			
			current++;
			if (current == currentEnd)
				currentEnd += rowSize;
		}
	}
	
	private void innerColumnMix(int colSize, Cell[][] table, Random rng) {
		int current = 0;
		int end = table.length;
		int currentEnd = colSize;
		
		while (currentEnd <= end) {
			int columnToShuffle = rng.nextInt(currentEnd - current) + current;
			
			if (columnToShuffle != current) {
				for (int row = 0 ; row < end ; row++) {
					Cell temp = table[row][columnToShuffle];
					table[row][columnToShuffle] = table[row][current];
					table[row][current] = temp;
				}
			}
			
			current++;
			if (current == currentEnd)
				currentEnd += colSize;
		}
	}
}