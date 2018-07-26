package sudoku_game;

import java.util.Random;

public class InnerMixer implements Mixer {
	public static void main(String[] args) {
		Board board = new Board9x9();
		System.out.println(board);
		InnerMixer.getInstance().mix(board);
		System.out.println(board);
	}
	
	private static Mixer mixer = new InnerMixer();
	
	private InnerMixer() {}
	
	public static Mixer getInstance() {
		return InnerMixer.mixer;
	}
	
	@Override
	public void mix(Board board, Random rng) {
		Cell[][] table = board.getTable();
		
		this.innerRowMix(board, table, rng);
		this.innerColumnMix(board, table, rng);
	}
	
	private void innerRowMix(Board board, Cell[][] table, Random rng) {
		int rowSize = board.rowSizeInBox();
		int current = 0;
		int end = table.length;
		int currentEnd = rowSize;
		
		while (currentEnd <= end) {
			int rowToShuffle = rng.nextInt(currentEnd - current) + current;
			
			Cell[] temp = table[current];
			table[current] = table[rowToShuffle];
			table[rowToShuffle] = temp;
			
			current++;
			if (current == currentEnd)
				currentEnd += rowSize;
		}
	}
	
	private void innerColumnMix(Board board, Cell[][] table, Random rng) {
		int colSize = board.colSizeInBox();
		int current = 0;
		int end = table.length;
		int currentEnd = colSize;
		
		while (currentEnd <= end) {
			int columnToShuffle = rng.nextInt(currentEnd - current) + current;
			
			for (int row = 0 ; row < end ; row++) {
				Cell temp = table[row][columnToShuffle];
				table[row][columnToShuffle] = table[row][current];
				table[row][current] = temp;
			}
			
			current++;
			if (current == currentEnd)
				currentEnd += colSize;
		}
	}
}