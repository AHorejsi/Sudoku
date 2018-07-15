package sudoku_game;

import java.util.Random;

/**
 * Mixes up a Sudoku puzzle
 * by rotating it 180 degrees
 * or not at all
 * @author Alex Horejsi
 */
public class RotateMixer implements Mixer {
	private static Mixer mixer = new RotateMixer();
	
	private RotateMixer() {}
	
	/**
	 * Returns the single instance
	 * of {@code RotateMixer}
	 * @return The single instance
	 * of {@code RotateMixer}
	 */
	public static Mixer getInstance() {
		return RotateMixer.mixer;
	}
	
	@Override
	public void mix(Board board, Random rng) {
		if (rng.nextBoolean())
			this.rotate180(board.getTable());
	}
	
	private void rotate180(Cell[][] table) {
		this.transpose(table);
		this.reverseColumns(table);
		this.transpose(table);
		this.reverseColumns(table);
	}
	
	private void transpose(Cell[][] table) {
		Cell temp;
		
		for (int i = 0 ; i < table.length ; i++) {
			for (int j = i ; j < table.length ; j++) {
				temp = table[i][j];
				table[i][j] = table[j][i];
				table[j][i] = temp;
			}
		}
	}
	
	private void reverseColumns(Cell[][] table) {
		int i = 0;
		int j = table.length - 1;
		Cell temp;
		
		while (i < j) {
			for (int row = 0 ; row < table.length ; row++) {
				temp = table[row][i];
				table[row][i] = table[row][j];
				table[row][j] = temp;
			}
			
			i++;
			j--;
		}
	}
}