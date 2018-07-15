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
		char[] values = board.getLegalValues().getValues();
		Map<Character, Character> map = this.shuffle(values, rng);
		int dimensions = board.getDimensions();
		char current;
		
		for (int row = 0 ; row < dimensions ; row++) {
			for (int col = 0 ; col < dimensions ; col++) {
				current = table[row][col].getValue();
				
				if (current != '\u0000')
					table[row][col].setValueForSetUp(map.get(current));
			}
		}
	}
	
	private Map<Character, Character> shuffle(char[] values, Random rng) {
		char[] copy = Arrays.copyOf(values, values.length);
		
		for (int i = values.length - 1 ; i > 0 ; i--) {
			int pos = rng.nextInt(i);
			char temp = values[pos];
			values[pos] = values[i];
			values[i] = temp;
		}
		
		Map<Character, Character> map = new HashMap<Character, Character>();
		
		for (int i = 0 ; i < values.length ; i++)
			map.put(copy[i], values[i]);
		
		return map;
	}
}