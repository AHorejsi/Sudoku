package sudoku_game;

/**
 * This class calculates whether or not a Sudoku
 * puzzle has a unique solution by using an exact
 * cover algorithm
 * @author Alex Horejsi
 */
class ExactCoverSolver implements Solver {
	private static Solver solver = new ExactCoverSolver();
	
	private ExactCoverSolver() {}
	
	/**
	 * Returns the single instance of {@code ExactCoverSolver}
	 * @return The single instance of {@code ExactCoverSolver}
	 */
	public static Solver getInstance() {
		return ExactCoverSolver.solver;
	}
	
	public static void main(String[] args) {
		Cell[][] table = Generator9x9.getInstance().generate();
		for (int i = 0 ; i < 9 ; i++) {
			for (int j = 0 ; j < 9 ; j++)
				System.out.print(table[i][j].getValue() + " ");
			System.out.println();
		}
		LinkedMatrix mat = new LinkedMatrix(table);
		System.out.println(mat);
	}
	
	private static class LinkedMatrix {
		private LinkedCell head;
		
		public LinkedMatrix(Cell[][] table) {
			this.head = this.setUpCells(table, 0, 0);
		}
		
		private LinkedCell setUpCells(Cell[][] table, int row, int col) {
			LinkedCell cell = null;
			
			if (row < table.length && col < table.length) {
				cell = new LinkedCell(table[row][col].getValue());
				cell.down = this.setUpCells(table, row + 1, col);
				cell.right = this.setUpCells(table, row, col + 1);
			}
			
			return cell;
		}
		
		public char getValue(int row, int col) {
			int currentRow = 0;
			int currentCol = 0;
			LinkedCell cell = this.head;
			
			while (currentRow < row || currentCol < col) {
				if (currentRow < row) {
					cell = cell.down;
					currentRow++;
				}
				
				if (currentCol < col) {
					cell = cell.right;
					currentCol++;
				}
			}
			
			return cell.getValue();
		}
		
		private static class LinkedCell extends Cell {
			private char value;
			private LinkedCell left;
			private LinkedCell right;
			private LinkedCell up;
			private LinkedCell down;
			
			public LinkedCell(char value) {
				this.value = value;
			}
			
			@Override
			public char getValue() {
				return this.value;
			}
			
			@Override
			public void setValue(char value) {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public void setEmptyValue() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public boolean isEditable() {
				throw new UnsupportedOperationException();
			}
			
			@Override
			void setEditable(boolean editable) {
				throw new UnsupportedOperationException();
			}
			
			@Override
			void setValueForSetUp(char value) {
				this.value = value;
			}
			
			@Override
			void setEmptyForSetUp() {
				this.value = '\u0000';
			}
		}
	}
}