package sudoku_game;

import java.util.Random;

class Generator16x16 implements Generator {
	private static Generator gen = new Generator16x16();
	private Cell[][] table;
	
	private Generator16x16() {}
	
	public static Generator getInstance() {
		return Generator16x16.gen;
	}
	
	@Override
	public Cell[][] generate(Random rng) {
		this.table = new Cell[16][16];
		
		this.fillMajorDiagonal(rng);
	}
	
	private void fillMajorDiagonal(Random rng) {
		this.fillBox(0, 0, rng);
		this.fillBox(4, 4, rng);
		this.fillBox(8, 8, rng);
		this.fillBox(12, 12, rng);
	}
	
	private void fillBox(int i, int j, Random rng) {
		char[] values = LegalValues16x16.getInstance().getValues();
		this.shuffle(values, rng);
		int end = i + 4;
		int index = 0;
		
		for (int row = i ; row < end ; row++) {
			for (int col = j ; col < end ; col++) {
				this.table[row][col] = new ConcreteCell(values[index]);
				index++;
			}
		}
	}
	
	private void shuffle(char[] values, Random rng) {
		for (int i = values.length - 1 ; i > 0 ; i--) {
			int pos = rng.nextInt(i);
			char temp = values[pos];
			values[pos] = values[i];
			values[i] = temp;
		}
	}
}