package sudoku_game;

import java.util.Random;

class FlipBoxMixer implements Mixer {
	private static Mixer mixer = new FlipBoxMixer();
	
	private FlipBoxMixer() {}
	
	public static Mixer getInstance() {
		return FlipBoxMixer.mixer;
	}
	
	@Override
	public void mix(Board board, Random rng) {
		this.rowFlip(board, rng);
		this.columnFlip(board, rng);
	}
	
	private void rowFlip(Board board, Random rng) {
		if (board.getDimensions() == 9)
			this.rowFlip9x9(board.table, rng);
		else
			this.rowFlip16x16(board.table, rng);
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
	
	private void columnFlip(Board board, Random rng) {
		if (board.getDimensions() == 9)
			this.columnFlip9x9(board.table, rng);
		else
			this.columnFlip16x16(board.table, rng);
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