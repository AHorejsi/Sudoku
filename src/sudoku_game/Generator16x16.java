package sudoku_game;

import java.util.Random;

/**
 * Generates sixteen-by-sixteen
 * Sudoku puzzles
 * @author Alex Horejsi
 */
public class Generator16x16 implements Generator {
	private static Generator gen = new Generator16x16();
	private static Cell[][] returnTable = Generator16x16.constructTable();
	
	private Generator16x16() {}
	
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
		SwapMixer.getInstance().mix(new Board16x16(Generator16x16.returnTable), rng);
		
		return Generator16x16.returnTable;
	}
	
	private static Cell[][] constructTable() {
		char[][] charTable = new char[][] {{'5', 'E', '0', 'B', '2', '7', 'F', '4', 'D', 'C', 'A', '8', '9', '6', '3', '1'},
										   {'F', '3', '4', 'A', '0', 'E', 'D', '5', '1', '7', '6', '9', 'B', '8', 'C', '2'},
										   {'C', '9', 'D', '1', 'A', '8', '3', '6', 'B', '0', '2', '5', '4', '7', 'F', 'E'},
										   {'6', '2', '7', '8', '9', '1', 'B', 'C', '4', 'E', '3', 'F', 'A', '0', 'D', '5'},
										   {'B', '9', '5', 'D', '3', '2', '8', '7', 'C', '0', '4', 'E', 'A', '1', '6', 'F'},
										   {'4', 'C', '1', 'F', 'A', 'B', '0', 'E', 'D', '6', '5', '3', '7', '2', '9', '8'},
										   {'7', '6', '8', '0', 'D', 'F', '1', '4', '9', '2', 'B', 'A', '3', '5', 'E', 'C'},
										   {'3', 'A', 'E', '2', '5', 'C', '9', '6', 'F', '8', '1', '7', 'D', 'B', '0', '4'},
										   {'7', '8', 'B', '0', '4', '3', 'E', '5', 'F', 'A', 'C', '6', '1', 'D', '2', '9'},
										   {'5', 'A', 'F', 'C', '2', 'D', '7', '6', '9', '0', 'E', '1', '8', '4', '3', 'B'},
										   {'E', 'D', '6', '3', '1', 'C', '9', '8', '2', 'B', '4', '7', '0', 'A', '5', 'F'},
										   {'2', '9', '4', '1', 'B', 'F', 'A', '0', '8', '3', '5', 'D', 'C', '7', '6', 'E'},
										   {'8', '4', 'D', '2', '6', '5', '1', 'C', 'E', 'F', '9', 'A', '0', 'B', '7', '3'},
										   {'3', 'F', 'B', '7', 'E', '9', '8', '0', 'C', '1', '2', '4', '6', '5', 'A', 'D'},
										   {'5', 'E', '0', '9', 'F', '4', 'A', 'B', '6', '3', '7', 'D', '8', '1', 'C', '2'},
										   {'1', '6', 'C', 'A', '7', 'D', '2', '3', '0', '5', '8', 'B', 'E', '4', 'F', '9'}};
		
		Cell[][] cellTable = new Cell[16][16];
		
		for (int i = 0 ; i < 16 ; i++) {
			for (int j = 0 ; j < 16 ; j++)
				cellTable[i][j] = new ConcreteCell(charTable[i][j]);
		}
		
		return cellTable;
	}
}