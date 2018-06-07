package sudoku_game;

import java.util.Arrays;
import java.util.Random;

/**
 * Mixes up Sudoku puzzles through 3 steps:
 * 
 * <ol>
 * <li>
 * Rotates 90, 180, 270 degrees or
 * not at all
 * </li>
 * <li>
 * Flip horizontally, vertically,
 * both or not at all
 * </li>
 * <li>
 * Corresponds each digit with
 * another digit and swaps each
 * digit with its corresponding
 * counterpart
 * </li>
 * </ol>
 * @author Alex Horejsi
 */
public class RotateFlipSwapMixer implements Mixer {	
	private static RotateFlipSwapMixer mixer = new RotateFlipSwapMixer();
	
	private RotateFlipSwapMixer() {}
	
	public static Mixer getInstance() {
		return RotateFlipSwapMixer.mixer;
	}
	
	@Override
	public void mix(Board board, Random rng) {
		this.rotate(board.table, rng);
		this.flip(board.table, rng);
		this.swap(board.table, rng, board.legalValues.getLegalValues());
	}
	
	private void rotate(Cell[][] table, Random rng) {
		int option = rng.nextInt(4);
		
		if (option == 0)
			this.rotate90(table);
		else if (option == 1)
			this.rotate180(table);
		else if (option == 2)
			this.rotate270(table);
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
	
	private void flip(Cell[][] table, Random rng) {
		int option = rng.nextInt(4);
		
		if (option == 0)
			this.hFlip(table);
		else if (option == 1)
			this.vFlip(table);
		else if (option == 2) {
			this.hFlip(table);
			this.vFlip(table);
		}
	}
	
	private void hFlip(Cell[][] table) {
		int i = 0;
		int j = table.length - 1;
		Cell temp;
		
		while (i <= j) {
			for (int row = 0 ; row < table.length ; row++) {
				temp = table[row][i];
				table[row][i] = table[row][j];
				table[row][j] = temp;
			}
			
			i++;
			j--;
		}
	}
	
	private void vFlip(Cell[][] table) {
		int i = 0;
		int j = table.length - 1;
		Cell[] temp;
		
		while (i <= j) {
			temp = table[i];
			table[i] = table[j];
			table[j] = temp;
			i++;
			j--;
		}
	}
	
	private void swap(Cell[][] table, Random rng, char[] legalValues) {
		char[] values = this.shuffle(legalValues, rng);
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