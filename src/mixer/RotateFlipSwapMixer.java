package mixer;

import java.util.Arrays;
import java.util.Random;
//import generators.Checker9x9;
//import generators.Generator;
//import generators.Generator9x9;

/**
 * Mixes up Sudoku puzzles through 3 step:
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
//	public static void main(String[] args) {
//		Generator gen = Generator9x9.getInstance();
//		Mixer mixer = new RotateFlipSwapMixer();
//		Random rng = new Random();
//		
//		for (int test = 0 ; test < 1000 ; test++) {
//			char[][] table = gen.generate(rng);
//			mixer.mix(table, rng);
//			if (!Checker9x9.check9x9(table)) {
//				for (int i = 0 ; i < 9 ; i++) {
//					for (int j = 0 ; j < 9 ; j++)
//						System.out.print(table[i][j] + " ");
//					System.out.println();
//				}
//				System.out.println("Fail " + test);
//				System.exit(1);
//			}
//		}
//	
//		System.out.println("Success");
//	}
	
//	public static void main(String[] args) {
//		Generator gen = Generator9x9.getInstance();
//		Mixer mixer = new RotateFlipSwapMixer();
//		Random rng = new Random();
//		
//		char[][] table = gen.generate(rng);
//		
//		for (int i = 0 ; i < 9 ; i++) {
//			for (int j = 0 ; j < 9 ; j++)
//				System.out.print(table[i][j] + " ");
//			System.out.println();
//		}
//		
//		System.out.println();
//		mixer.mix(table, rng);
//		
//		for (int i = 0 ; i < 9 ; i++) {
//			for (int j = 0 ; j < 9 ; j++)
//				System.out.print(table[i][j] + " ");
//			System.out.println();
//		}
//	}
	
	@Override
	public void mix(char[][] table, Random rng) {
		this.rotate(table, rng);
		this.flip(table, rng);
		this.swap(table, rng);
	}
	
	private void rotate(char[][] table, Random rng) {
		int option = rng.nextInt(4);
		
		if (option == 0)
			this.rotate90(table);
		else if (option == 1)
			this.rotate180(table);
		else if (option == 2)
			this.rotate270(table);
	}
	
	private void rotate90(char[][] table) {
		int end = table.length / 2;
		char temp;
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
	
	private void rotate180(char[][] table) {
		this.rotate90(table);
		this.rotate90(table);
	}
	
	private void rotate270(char[][] table) {
		int end = table.length / 2;
		char temp;
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
	
	private void flip(char[][] table, Random rng) {
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
	
	private void hFlip(char[][] table) {
		int i = 0;
		int j = table.length - 1;
		char temp;
		
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
	
	private void vFlip(char[][] table) {
		int i = 0;
		int j = table.length - 1;
		char[] temp;
		
		while (i <= j) {
			temp = table[i];
			table[i] = table[j];
			table[j] = temp;
			i++;
			j--;
		}
	}
	
	private void swap(char[][] table, Random rng) {
		char[] firstRow = Arrays.copyOf(table[0], table[0].length);
		char current;
		int index;
		
		for (int row = 0 ; row < table.length ; row++) {
			for (int col = 0 ; col < table[row].length ; col++) {
				current = table[row][col];
				if (Character.isDigit(current))
					index = current - '0' - 1;
				else
					index = current - 'A' + 9;
				table[row][col] = firstRow[index];
			}
		}
	}
}