package sudoku_game;

import java.util.Random;


@FunctionalInterface
public interface DifficultyAdjustor {
	public void adjust(Board board, Random rng, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit);
	
	public default void adjust(Board board, int lowerRangeOnGivens, int upperRangeOnGivens, int lowerBoundOnGivensPerUnit) {
		this.adjust(board, DefaultRNG.getDefaultGenerator(), lowerRangeOnGivens, upperRangeOnGivens, lowerBoundOnGivensPerUnit);
	}
}