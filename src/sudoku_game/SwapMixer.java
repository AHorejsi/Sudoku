package sudoku_game;

import java.util.Random;

/**
 * Mixes up a Sudoku puzzle
 * by swapping all occurrences of
 * each value with another legal value.
 * This is done for all possible values
 * @author Alex Horejsi
 */
class SwapMixer implements Mixer {
	private static Mixer mixer = new SwapMixer();
	
	private SwapMixer() {}
	
	/**
	 * Returns the single instance of {@code SwapMixer}
	 * @return The Single instance of {@code SwapMixer}
	 */
	public static Mixer getInstance() {
		return SwapMixer.mixer;
	}
	
	@Override
	public void mix(Board board, Random rng) {
		Cell[][] table = board.getTable();
		char[] values = this.shuffle(board.getLegalValues().getValues(), rng);
		int dimensions = board.getDimensions();
		char current;
		int index;
		
		for (int row = 0 ; row < dimensions ; row++) {
			for (int col = 0 ; col < dimensions ; col++) {
				current = table[row][col].getValue();
				
				if (current != '\u0000') {
					if (Character.isDigit(current))
						index = current - '0' - 1;
					else
						index = current - 'A' + 9;
					table[row][col].setValueForSetUp(values[index]);
				}
			}
		}
	}
	
	private char[] shuffle(char[] values, Random rng) {		
		for (int i = values.length - 1 ; i > 0 ; i--) {
			int pos = rng.nextInt(i);
			char temp = values[pos];
			values[pos] = values[i];
			values[i] = temp;
		}
		
		return values;
	}
}