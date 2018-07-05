package sudoku_game;

import java.util.Random;

class Generator12x12 implements Generator {	
	private static Generator gen = new Generator12x12();
	private Cell[][] table;
	
	private Generator12x12() {}
	
	public static Generator getInstance() {
		return Generator12x12.gen;
	}
	
	@Override
	public Cell[][] generate(Random rng) {
		this.table = new Cell[12][12];
		
		this.fillInitialBoxes(rng);
	}
	
	private void fillInitialBoxes(Random rng) {
		this.fillBox(rng);
		this.fillRow(rng);
		this.fillColumn(rng);
	}
	
	private void fillBox(Random rng) {
		char[] values = LegalValues12x12.getInstance().getValues();
		this.shuffle(values, rng);
		int index = 0;
		
		for (int i = 0 ; i < 3 ; i++) {
			for (int j = 0 ; j < 4 ; j++) {
				this.table[i][j] = new ConcreteCell(values[index]);
				index++;
			}
		}
	}
	
	private void fillRow(Random rng) {
		char[] values = LegalValues12x12.getInstance().getValues();
		this.shuffle(values, rng);
		int j = 4;
		
		for (int index = 0 ; index < 12 ; index++) {
			if (!this.rowContains(values[index])) {
				this.table[0][j] = new ConcreteCell(values[index]);
				j++;
			}
		}
	}
	
	private boolean rowContains(char value) {
		for (int j = 0 ; j < 4 ; j++) {
			if (value == this.table[0][j].getValue())
				return true;
		}
		
		return false;
	}
	
	private void fillColumn(Random rng) {
		char[] values = LegalValues12x12.getInstance().getValues();
		this.shuffle(values, rng);
		int i = 3;
		
		for (int index = 0 ; index < 12 ; index++) {
			if (!this.columnContains(values[index])) {
				this.table[i][0] = new ConcreteCell(values[index]);
				i++;
			}
		}
	}
	
	private boolean columnContains(char value) {
		for (int i = 0 ; i < 3 ; i++) {
			if (value == this.table[i][0].getValue())
				return true;
		}
		
		return false;
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