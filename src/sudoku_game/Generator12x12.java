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
		
		this.fillInitialCells(rng);
	}
	
	private void fillInitialCells(Random rng) {
		int[] bits = this.fillBox(rng);
		this.fillRow(rng, bits[0]);
		this.fillColumn(rng, bits[1]);
	}
	
	private int[] fillBox(Random rng) {
		int[] bits = new int[2];
		char[] values = LegalValues12x12.getInstance().getValues();
		this.shuffle(values, rng);
		int index = 0;
		
		for (int i = 0 ; i < 3 ; i++) {
			for (int j = 0 ; j < 4 ; j++) {
				if (i == 0)
					bits[0] |= 1 << (Character.isDigit(values[index]) ? values[index] - '0' : values[index] - 54);
				if (j == 0)
					bits[1] |= 1 << (Character.isDigit(values[index]) ? values[index] - '0' : values[index] - 54);
				
				this.table[i][j] = new ConcreteCell(values[index]);
				index++;
			}
		}
		
		return bits;
	}
	
	private void fillRow(Random rng, int bits) {
		char[] values = LegalValues12x12.getInstance().getValues();
		this.shuffle(values, rng);
		int j = 4;
		
		for (int index = 0 ; index < 12 ; index++) {
			int bit = (Character.isDigit(values[index]) ? values[index] - '0' : values[index] - 54);
			
			if ((bits & (1 << bit)) == 0) {
				this.table[0][j] = new ConcreteCell(values[index]);
				j++;
			}
		}
	}
	
	private void fillColumn(Random rng, int bits) {
		char[] values = LegalValues12x12.getInstance().getValues();
		this.shuffle(values, rng);
		int i = 3;
		
		for (int index = 0 ; index < 12 ; index++) {
			int bit = (Character.isDigit(values[index]) ? values[index] - '0' : values[index] - 54);
			
			if ((bits & (1 << bit)) == 0) {
				this.table[i][0] = new ConcreteCell(values[index]);
				i++;
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