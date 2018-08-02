package sudoku_game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Generates sixteen-by-sixteen
 * Sudoku puzzles
 * @author Alex Horejsi
 */
public class Generator16x16 implements Generator {
	private static Generator gen = new Generator16x16();
	private Cell[][] returnTable;
	
	private Generator16x16() {
		char[][] charTable = new char[][] {{'4', '2', '5', 'F', '3', '1', '7', 'B', '0', 'E', '6', '9', 'A', 'D', 'C', '8'},
										   {'3', 'E', '0', '6', 'D', '9', 'A', 'C', '4', 'B', '8', '7', '2', 'F', '5', '1'},
										   {'A', '7', 'C', 'B', '0', '8', '5', 'E', 'F', 'D', '2', '1', '4', '9', '3', '6'},
										   {'D', '1', '8', '9', '2', '4', 'F', '6', '3', 'C', '5', 'A', '0', 'E', 'B', '7'},
										   {'0', 'C', 'E', '5', '6', '3', 'B', '8', '1', 'A', '4', 'F', '7', '2', 'D', '9'},
										   {'9', '4', 'A', 'D', '1', 'F', 'C', '7', '2', '8', '0', '6', '3', '5', 'E', 'B'},
										   {'6', 'B', '1', '2', 'A', '0', '9', '5', 'E', '7', '3', 'D', '8', 'C', 'F', '4'},
										   {'8', '3', 'F', '7', '4', 'D', 'E', '2', 'C', '9', 'B', '5', '6', 'A', '1', '0'},
										   {'F', '9', 'D', 'C', '5', '2', '4', '1', '6', '3', '7', '0', 'B', '8', 'A', 'E'},
										   {'7', '0', '4', 'E', '9', 'B', '3', 'A', 'D', '2', 'C', '8', '5', '1', '6', 'F'},
										   {'2', '5', '3', '1', '7', '6', '8', 'D', 'B', 'F', 'A', 'E', '9', '4', '0', 'C'},
										   {'B', '8', '6', 'A', 'E', 'C', '0', 'F', '9', '5', '1', '4', 'D', '3', '7', '2'},
										   {'C', 'A', '9', '3', 'F', '7', '6', '0', '5', '4', 'E', '2', '1', 'B', '8', 'D'},
										   {'E', 'D', '2', '0', '8', '5', '1', '4', 'A', '6', 'F', 'B', 'C', '7', '9', '3'},
										   {'1', 'F', '7', '4', 'B', 'A', 'D', '3', '8', '0', '9', 'C', 'E', '6', '2', '5'},
										   {'5', '6', 'B', '8', 'C', 'E', '2', '9', '7', '1', 'D', '3', 'F', '0', '4', 'A'}};

		Cell[][] cellTable = new Cell[16][16];
		
		for (int i = 0 ; i < 16 ; i++) {
			for (int j = 0 ; j < 16 ; j++)
				cellTable[i][j] = new ConcreteCell(charTable[i][j]);
		}
			
		this.returnTable = cellTable;
	}
	
	/**
	 * Returns the single instance
	 * of {@code Generator16x16}
	 * @return The single instance
	 * of {@code Generator16x16}
	 */
	public static Generator getInstance() {
		return Generator16x16.gen;
	}
	
	@Override
	public Cell[][] generate(Random rng) {
		this.doSwapping(this.returnTable, rng);		
		return this.copyTable();
	}
	
	private void doSwapping(Cell[][] table, Random rng) {
		Map<Character, Character> map = this.shuffle(rng);
		
		for (int i = 0 ; i < 16 ; i++) {
			for (int j = 0 ; j < 16 ; j++) {
				char key = table[i][j].getValue();
				char value = map.get(key);
				table[i][j].setValueForSetUp(value);
			}
		}
	}
	
	private Map<Character, Character> shuffle(Random rng) {
		char[] values = LegalValues16x16.getInstance().getValues();
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
	
	private Cell[][] copyTable() {
		Cell[][] table = new Cell[16][16];
		
		for (int i = 0 ; i < 16 ; i++) {
			for (int j = 0 ; j < 16 ; j++)
				table[i][j] = new ConcreteCell(this.returnTable[i][j].getValue());
		}
		
		return table;
	}
}