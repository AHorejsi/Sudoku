package sudoku_game;

import java.util.Random;

public class FlipMixer implements Mixer {
	private static Mixer mixer = new FlipMixer();
	
	private FlipMixer() {}
	
	public static Mixer getInstance() {
		return FlipMixer.mixer;
	}
	
	@Override
	public void mix(Board board, Random rng) {
		Cell[][] table = board.table;
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
	
	private void vFlip(Cell[][] table) {
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