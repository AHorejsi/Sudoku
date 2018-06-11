package sudoku_game;

import java.util.Arrays;
import java.util.Random;

public class SwapMixer implements Mixer {
	private static Mixer mixer = new SwapMixer();
	
	private SwapMixer() {}
	
	public static Mixer getInstance() {
		return SwapMixer.mixer;
	}
	
	@Override
	public void mix(Board board, Random rng) {
		Cell[][] table = board.table;
		char[] values = this.shuffle(board.legalValues.getLegalValues(), rng);
		char current;
		int index;
		
		for (int row = 0 ; row < table.length ; row++) {
			for (int col = 0 ; col < table[row].length ; col++) {
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
	
	private char[] shuffle(char[] legalValues, Random rng) {
		char[] values = Arrays.copyOf(legalValues, legalValues.length);
		
		for (int i = 0 ; i < values.length ; i++) {
			int pos = rng.nextInt(values.length);
			char temp = values[pos];
			values[pos] = values[i];
			values[i] = temp;
		}
		
		return values;
	}
}