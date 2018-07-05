package sudoku_game;

class Generator16x16 implements Generator {
	private static Generator gen = new Generator16x16();
	private Cell[][] table;
	
	private Generator16x16() {}
	
	public static Generator getInstance() {
		return Generator16x16.gen;
	}
}