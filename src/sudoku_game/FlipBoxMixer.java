package sudoku_game;

import java.util.Random;

/**
 * Swaps the rows and columns of boxes
 * of a Sudoku puzzle
 * @author Alex Horejsi
 */
class FlipBoxMixer implements Mixer {
	private static Mixer mixer = new FlipBoxMixer();
	
	private FlipBoxMixer() {}
	
	/**
	 * Returns the single instance of {@code FlipBoxMixer}
	 * @return The single instance of {@code FlipBoxMixer}
	 */
	public static Mixer getInstance() {
		return FlipBoxMixer.mixer;
	}
	
	@Override
	public void mix(Board board, Random rng) {
		Cell[][] table = board.getTable();
		
		this.rowFlip(table, rng);
		this.columnFlip(table, rng);
	}
	
	private void rowFlip(Cell[][] table, Random rng) {
		if (table.length == 4)
			this.rowFlip4x4(table, rng);
		else if (table.length == 6)
			this.rowFlip6x6(table, rng);
		else if (table.length == 8)
			this.rowFlip8x8(table, rng);
		else if (table.length == 9)
			this.rowFlip9x9(table, rng);
		else if (table.length == 12)
			this.rowFlip12x12(table, rng);
		else
			this.rowFlip16x16(table, rng);
	}
	
	private void rowFlip4x4(Cell[][] table, Random rng) {
		Cell[] temp;
		
		if (rng.nextBoolean()) {
			for (int i = 0 ; i < 2 ; i++) {
				temp = table[i];
				table[i] = table[i + 2];
				table[i + 2] = temp;
			}
		}
	}
	
	private void rowFlip6x6(Cell[][] table, Random rng) {
		Cell[] temp;
		int option = rng.nextInt(3);
		
		if (option == 0) {
			for (int i = 0 ; i < 2 ; i++) {
				temp = table[i];
				table[i] = table[i + 2];
				table[i + 2] = temp;
			}
		}
		else {
			for (int i = 0 ; i < 2 ; i++) {
				temp = table[i];
				table[i] = table[i + 4];
				table[i + 4] = temp;
			}
		}
		
		if (rng.nextBoolean()) {
			for (int i = 2 ; i < 4 ; i++) {
				temp = table[i];
				table[i] = table[i + 2];
				table[i + 2] = temp;
			}
		}
	}
	
	private void rowFlip8x8(Cell[][] table, Random rng) {
		Cell[] temp;
		int option = rng.nextInt(4);
		
		if (option == 0) {
			for (int i = 0 ; i < 2 ; i++) {
				temp = table[i];
				table[i] = table[i + 2];
				table[i + 2] = temp;
			}
		}
		else if (option == 1) {
			for (int i = 0 ; i < 2 ; i++) {
				temp = table[i];
				table[i] = table[i + 4];
				table[i + 4] = temp;
			}
		}
		else if (option == 2) {
			for (int i = 0 ; i < 2 ; i++) {
				temp = table[i];
				table[i] = table[i + 6];
				table[i + 6] = temp;
			}
		}
		
		option = rng.nextInt(3);
		
		if (option == 0) {
			for (int i = 2 ; i < 4 ; i++) {
				temp = table[i];
				table[i] = table[i + 2];
				table[i + 2] = temp;
			}
		}
		else if (option == 1) {
			for (int i = 2 ; i < 4 ; i++) {
				temp = table[i];
				table[i] = table[i + 4];
				table[i + 4] = temp;
			}
		}
		
		if (rng.nextBoolean()) {
			for (int i = 4 ; i < 6 ; i++) {
				temp = table[i];
				table[i] = table[i + 2];
				table[i + 2] = temp;
			}
		}
	}
	
	private void rowFlip9x9(Cell[][] table, Random rng) {
		Cell[] temp;
		int option = rng.nextInt(3);
		
		if (option == 0) {
			for (int i = 0 ; i < 3 ; i++) {
				temp = table[i];
				table[i] = table[i + 3];
				table[i + 3] = temp;
			}
		}
		else if (option == 1) {
			for (int i = 0 ; i < 3 ; i++) {
				temp = table[i];
				table[i] = table[i + 6];
				table[i + 6] = temp;
			}
		}
		
		if (rng.nextBoolean()) {
			for (int i = 3 ; i < 6 ; i++) {
				temp = table[i];
				table[i] = table[i + 3];
				table[i + 3] = temp;
			}
		}
	}
	
	private void rowFlip12x12(Cell[][] table, Random rng) {
		Cell[] temp;
		int option = rng.nextInt(4);
		
		if (option == 0) {
			for (int i = 0 ; i < 3 ; i++) {
				temp = table[i];
				table[i] = table[i + 3];
				table[i + 3] = temp;
			}
		}
		else if (option == 1) {
			for (int i = 0 ; i < 3 ; i++) {
				temp = table[i];
				table[i] = table[i + 6];
				table[i + 6] = temp;
			}
		}
		else if (option == 2) {
			for (int i = 0 ; i < 3 ; i++) {
				temp = table[i];
				table[i] = table[i + 9];
				table[i + 9] = temp;
			}
		}
		
		option = rng.nextInt(3);
		
		if (option == 0) {
			for (int i = 3 ; i < 6 ; i++) {
				temp = table[i];
				table[i] = table[i + 3];
				table[i + 3] = temp;
			}
		}
		else if (option == 1) {
			for (int i = 3 ; i < 6 ; i++) {
				temp = table[i];
				table[i] = table[i + 6];
				table[i + 6] = temp;
			}
		}
		
		if (rng.nextBoolean()) {
			for (int i = 6 ; i < 9 ; i++) {
				temp = table[i];
				table[i] = table[i + 3];
				table[i + 3] = temp;
			}
		}
	}
	
	private void rowFlip16x16(Cell[][] table, Random rng) {
		Cell[] temp;
		int option = rng.nextInt(4);
		
		if (option == 0) {
			for (int i = 0 ; i < 4 ; i++) {
				temp = table[i];
				table[i] = table[i + 4];
				table[i + 4] = temp;
			}
		}
		else if (option == 1) {
			for (int i = 0 ; i < 4 ; i++) {
				temp = table[i];
				table[i] = table[i + 8];
				table[i + 8] = temp;
			}
		}
		else if (option == 2) {
			for (int i = 0 ; i < 4 ; i++) {
				temp = table[i];
				table[i] = table[i + 12];
				table[i + 12] = temp;
			}
		}
		
		option = rng.nextInt(3);
		
		if (option == 0) {
			for (int i = 4 ; i < 8 ; i++) {
				temp = table[i];
				table[i] = table[i + 4];
				table[i + 4] = temp;
			}
		}
		else if (option == 1) {
			for (int i = 4 ; i < 8 ; i++) {
				temp = table[i];
				table[i] = table[i + 8];
				table[i + 8] = temp;
			}
		}
		
		if (rng.nextBoolean()) {
			for (int i = 8 ; i < 12 ; i++) {
				temp = table[i];
				table[i] = table[i + 4];
				table[i + 4] = temp;
			}
		}
	}
	
	private void columnFlip(Cell[][] table, Random rng) {
		if (table.length == 4)
			this.columnFlip4x4(table, rng);
		else if (table.length == 6)
			this.columnFlip6x6(table, rng);
		else if (table.length == 8)
			this.columnFlip8x8(table, rng);
		else if (table.length == 9)
			this.columnFlip9x9(table, rng);
		else if (table.length == 12)
			this.columnFlip12x12(table, rng);
		else
			this.columnFlip16x16(table, rng);
	}
	
	private void columnFlip4x4(Cell[][] table, Random rng) {
		Cell temp;
		
		if (rng.nextBoolean()) {
			for (int i = 0 ; i < 4 ; i++) {
				for (int j = 0 ; j < 2 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 2];
					table[i][j + 2] = temp;
				}
			}
		}
	}
	
	private void columnFlip6x6(Cell[][] table, Random rng) {
		Cell temp;
		
		if (rng.nextBoolean()) {
			for (int i = 0 ; i < 6 ; i++) {
				for (int j = 0 ; j < 3 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 3];
					table[i][j + 3] = temp;
				}
			}
		}
	}
	
	private void columnFlip8x8(Cell[][] table, Random rng) {
		if (rng.nextBoolean()) {
			Cell temp;
			
			for (int i = 0 ; i < 8 ; i++) {
				for (int j = 0 ; j < 2 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 4];
					table[i][j + 4] = temp;
				}
			}
		}
	}
	
	private void columnFlip9x9(Cell[][] table, Random rng) {
		Cell temp;
		int option = rng.nextInt(3);
		
		if (option == 0) {
			for (int i = 0 ; i < 9 ; i++) {
				for (int j = 0 ; j < 3 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 3];
					table[i][j + 3] = temp;
				}
			}
		}
		else if (option == 1) {
			for (int i = 0 ; i < 9 ; i++) {
				for (int j = 0 ; j < 3 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 6];
					table[i][j + 6] = temp;
				}
			}
		}
		
		if (rng.nextBoolean()) {
			for (int i = 0 ; i < 9 ; i++) {
				for (int j = 3 ; j < 6 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 3];
					table[i][j + 3] = temp;
				}
			}
		}
	}
	
	private void columnFlip12x12(Cell[][] table, Random rng) {
		Cell temp;
		int option = rng.nextInt(3);
		
		if (option == 0) {
			for (int i = 0 ; i < 12 ; i++) {
				for (int j = 0 ; j < 4 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 4];
					table[i][j + 4] = temp;
				}
			}
		}
		else if (option == 1) {
			for (int i = 0 ; i < 12 ; i++) {
				for (int j = 0 ; j < 4 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 8];
					table[i][j + 8] = temp;
				}
			}
		}
		
		if (rng.nextBoolean()) {
			for (int i = 0 ; i < 12 ; i++) {
				for (int j = 4 ; j < 8 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 4];
					table[i][j + 4] = temp;
				}
			}
		}
	}
	
	private void columnFlip16x16(Cell[][] table, Random rng) {
		Cell temp;
		int option = rng.nextInt(4);
		
		if (option == 0) {
			for (int i = 0 ; i < 16 ; i++) {
				for (int j = 0 ; j < 4 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 4];
					table[i][j + 4] = temp;
				}
			}
		}
		else if (option == 1) {
			for (int i = 0 ; i < 16 ; i++) {
				for (int j = 0 ; j < 4 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 8];
					table[i][j + 8] = temp;
				}
			}
		}
		else if (option == 2) {
			for (int i = 0 ; i < 16 ; i++) {
				for (int j = 0 ; j < 4 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 12];
					table[i][j + 12] = temp;
				}
			}
		}
		
		option = rng.nextInt(3);
		
		if (option == 0) {
			for (int i = 0 ; i < 16 ; i++) {
				for (int j = 4 ; j < 8 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 4];
					table[i][j + 4] = temp;
				}
			}
		}
		else if (option == 1) {
			for (int i = 0 ; i < 16 ; i++) {
				for (int j = 4 ; j < 8 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 8];
					table[i][j + 8] = temp;
				}
			}
		}
		
		if (rng.nextBoolean()) {
			for (int i = 0 ; i < 16 ; i++) {
				for (int j = 8 ; j < 12 ; j++) {
					temp = table[i][j];
					table[i][j] = table[i][j + 4];
					table[i][j + 4] = temp;
				}
			}
		}
	}
}