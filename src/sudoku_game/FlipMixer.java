package sudoku_game;

import java.util.Random;

/**
 * Mixes up a Sudoku board by
 * flipping it horizontally,
 * vertically, both or not at all.
 * @author Alex Horejsi
 */
public class FlipMixer implements Mixer {
	private static Mixer mixer = new FlipMixer();
	
	private FlipMixer() {}
	
	/**
	 * Returns the single instance of {@code FlipMixer}
	 * @return The single instance of {@code FlipMixer}
	 */
	public static Mixer getInstance() {
		return FlipMixer.mixer;
	}
	
	@Override
	public void mix(Board board, Random rng) {
		Cell[][] table = board.getTable();
		int option = rng.nextInt(4);
		
		if (option == 0)
			this.horizontalFlip(table);
		else if (option == 1)
			this.verticalFlip(table);
		else if (option == 2) {
			this.horizontalFlip(table);
			this.verticalFlip(table);
		}
	}
	
	private void horizontalFlip(Cell[][] table) {
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
	
	private void verticalFlip(Cell[][] table) {
		int i = 0;
		int j = table.length - 1;
		Cell[] temp;
		
		while (i < j) {
			temp = table[i];
			table[i] = table[j];
			table[j] = temp;
			i++;
			j--;
		}
	}
}