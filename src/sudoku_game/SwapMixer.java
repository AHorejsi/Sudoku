package sudoku_game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Mixes up a Sudoku puzzle
 * by swapping all occurrences of
 * each value with another legal value.
 * This is done for all possible values
 * @author Alex Horejsi
 */
public class SwapMixer implements Mixer {
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
		Map<Character, Character> map = this.shuffle(board.getLegalValues().getValues(), rng);
		int dimensions = board.getDimensions();
		
		for (int row = 0 ; row < dimensions ; row++) {
			for (int col = 0 ; col < dimensions ; col++) {
				char key = table[row][col].getValue();
				
				if (key != '\u0000') {
					char value = map.get(key);
					table[row][col].setValueForSetUp(value);
				}
			}
		}
	}
	
	private Map<Character, Character> shuffle(char[] values, Random rng) {
		char[] copy = Arrays.copyOf(values, values.length);
		Map<Character, Character> map = new HashMap<Character, Character>();
		
		for (int i = values.length - 1 ; i > 0 ; i--) {
			int pos = rng.nextInt(i);
			char temp = values[pos];
			values[pos] = values[i];
			values[i] = temp;
		}
		
		for (int i = 0 ; i < values.length ; i++)
			map.put(copy[i], values[i]);
		
		return map;
	}
}