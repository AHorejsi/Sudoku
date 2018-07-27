package sudoku_game;

import java.util.Random;

/**
 * Mixes up a Sudoku puzzle
 * by rotating it 90, 180, 
 * 270 degrees or not at all
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
		if (this.isPerfectSquare(board.getDimensions())) {
			int option = rng.nextInt(4);
			
			if (option == 0)
				this.rotate90(board.getTable());
			else if (option == 1)
				this.rotate180(board.getTable());
			else if (option == 2)
				this.rotate270(board.getTable());
		}
		else {
			if (rng.nextBoolean())
				this.rotate180(board.getTable());
		}
	}
	
	private boolean isPerfectSquare(int dimensions) {
		double sqrt = Math.sqrt(dimensions);
		int roundedSqrt = (int)sqrt;
		
		return sqrt == roundedSqrt;
	}
	
	private void rotate90(Cell[][] table) {
		int end = table.length >>> 1;
		Cell temp;
		int x;
		int y;
		
		for (int i = 0 ; i < end ; i++) {
			x = table.length - 1 - i;
			
			for (int j = i ; j < x ; j++) {
				temp = table[i][j];
				y = table.length - 1 - j;
				
				table[i][j] = table[j][x];
				table[j][x] = table[x][y];
				table[x][y] = table[y][i];
				table[y][i] = temp;
			}
		}
	}
	
	private void rotate180(Cell[][] table) {
		this.rotate90(table);
		this.rotate90(table);
	}
	
	private void rotate270(Cell[][] table) {
		int end = table.length >>> 1;
		Cell temp;
		int x;
		int y;
		
		for (int i = 0 ; i < end ; i++) {
			x = table.length - 1 - i;
			
			for (int j = i ; j < x ; j++) {
				temp = table[i][j];
				y = table.length - 1 - j;
				
				table[i][j] = table[y][i];
				table[y][i] = table[x][y];
				table[x][y] = table[j][x];
				table[j][x] = temp;
			}
		}
	}
}