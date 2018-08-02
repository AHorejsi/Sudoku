package sudoku_game;

import java.util.BitSet;
import java.util.Random;

public class Generator12x12 implements Generator {
	public static void main(String[] args) {
		for (int i = 0 ; i < 1000 ; i++) {
			Board board = new Board12x12();
			if (!AlphanumericChecker.getInstance().isSolved(board)) {
				System.out.println("FAIL");
				System.exit(1);
			}
		}
		
		System.out.println("SUCCESS");
	}
	
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
		this.fillRemaining(1, 4, SimpleSafeCellChecker.getInstance());
		
		Cell[][] table = this.table;
		this.table = null;
		
		return table;
	}
	
	private void fillInitialCells(Random rng) {
		BitSet[] bits = this.fillBox(rng);
		this.fillRow(rng, bits[0]);
		this.fillColumn(rng, bits[1]);
	}
	
	private BitSet[] fillBox(Random rng) {
		BitSet[] bits = new BitSet[2];
		bits[0] = new BitSet();
		bits[1] = new BitSet();
		char[] values = LegalValues12x12.getInstance().getValues();
		this.shuffle(values, rng);
		int index = 0;
		
		for (int i = 0 ; i < 3 ; i++) {
			for (int j = 0 ; j < 4 ; j++) {
				if (i == 0)
					bits[0].set(values[index]);
				if (j == 0)
					bits[1].set(values[index]);
				
				this.table[i][j] = new ConcreteCell(values[index]);
				index++;
			}
		}
		
		return bits;
	}
	
	private void fillRow(Random rng, BitSet bits) {
		char[] values = LegalValues12x12.getInstance().getValues();
		this.shuffle(values, rng);
		int j = 4;
		
		for (int index = 0 ; index < 12 ; index++) {
			if (!bits.get(values[index])) {
				this.table[0][j] = new ConcreteCell(values[index]);
				j++;
			}
		}
	}
	
	private void fillColumn(Random rng, BitSet bits) {
		char[] values = LegalValues12x12.getInstance().getValues();
		this.shuffle(values, rng);
		int i = 3;
		
		for (int index = 0 ; index < 12 ; index++) {
			if (!bits.get(values[index])) {
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
	
	private boolean fillRemaining(int i, int j, SafeCellChecker checker) {
		if (j == 12) {
			i++;
			
			if (i <= 2)
				j = 4;
			else
				j = 1;
		}
		
		if (i == 12)
			return true;
		
		for (char digit = '0' ; digit <= 'B' ; digit++) {
			if (digit == ':')
				digit = 'A';
			
			if (checker.safe(this.table, digit, i, j, 3, 4)) {
				if (this.table[i][j] == null)
					this.table[i][j] = new ConcreteCell(digit);
				else
					this.table[i][j].setValueForSetUp(digit);
					
				if (this.fillRemaining(i, j + 1, checker))
					return true;
				this.table[i][j].setEmptyForSetUp();
			}
		}
		
		return false;
	}
}