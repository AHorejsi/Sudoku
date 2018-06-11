package sudoku_game;

import java.util.Collection;
import java.util.Random;

public class LocalFactory implements PuzzleFactory {
	private static PuzzleFactory factory = new LocalFactory();
	
	private LocalFactory() {}
	
	public static PuzzleFactory getInstance() {
		return LocalFactory.factory;
	}
	
	@Override
	public Puzzle createPuzzle(String info, Random rng, Collection<Mixer> mixers) {
		Puzzle puzzle = null;
		Board board = null;
		
		if (info.contains("9x9"))
			board = new Board9x9(rng);
		else if (info.contains("16x16"))
			board = new Board16x16(rng);
		
		if (info.contains("basic"))
			puzzle = new BasicPuzzle(board, rng);
		else if (info.contains("easy"))
			puzzle = new EasyPuzzle(board, rng);
		else if (info.contains("medium"))
			puzzle = new MediumPuzzle(board, rng);
		else if (info.contains("hard"))
			puzzle = new HardPuzzle(board, rng);
		else if (info.contains("insane"))
			puzzle = new InsanePuzzle(board, rng);
		
		if (mixers != null) {
			for (Mixer mixer : mixers)
				mixer.mix(board, rng);
		}
		
		this.setEditableCells(board.table);
		
		return puzzle;
	}
	
	private void setEditableCells(Cell[][] table) {
		for (int i = 0 ; i < table.length ; i++) {
			for (int j = 0 ; j < table.length ; j++) {
				if (table[i][j].getValue() != '\u0000')
					table[i][j].setEditable(false);
			}
		}
	}
}