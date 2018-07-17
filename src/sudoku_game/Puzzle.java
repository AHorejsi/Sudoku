package sudoku_game;

import java.util.Objects;
import java.util.Random;

/**
 * Instances of this class represent
 * Sudoku puzzles of varying dimensions
 * and difficulty levels
 * @author Alex Horejsi
 */
public abstract class Puzzle {
	private Board board;
	private String difficulty;
	
	/**
	 * Constructs a Sudoku puzzle using
	 * the given completed Sudoku board
	 * and the given difficulty adjustor
	 * to set the difficulty. The lower
	 * and upper range on given values
	 * as well as the lower bound on given
	 * values per unit should be determined
	 * by the desired difficulty level. The
	 * default random number generator is used
	 * as values are removed for difficulty
	 * @param board A completed Sudoku board
	 * to have values removed from it based on
	 * the difficulty level
	 * @param adjustor The difficulty adjustor
	 * algorithm which will decide how values
	 * should be removed
	 * @param lowerRangeOnGivens A percentage
	 * indicating the minimum number of possible 
	 * given values the Sudoku puzzle shall 
	 * initially have
	 * @param upperRangeOnGivens A percentage 
	 * indicating the maximum number of possible 
	 * given values the Sudoku puzzle shall 
	 * initially have
	 * @param lowerBoundOnGivensPerUnit A percentage
	 * indicating the minimum number of values that
	 * can be contained in any row/column/box
	 * @throws NullPointerException Thrown if either
	 * the Sudoku board or the difficulty adjustor is
	 * <tt>null</tt>
	 */
	protected Puzzle(Board board, DifficultyAdjustor adjustor, int lowerRangeOnGivens, int upperRangeOnGivens, 
					 int lowerBoundOnGivensPerUnit, String difficulty) throws NullPointerException {
		this(board, adjustor, lowerRangeOnGivens, upperRangeOnGivens, lowerBoundOnGivensPerUnit,
			 DefaultRNG.getDefaultGenerator(), difficulty);
	}
	
	/**
	 * Constructs a Sudoku puzzle using
	 * the given completed Sudoku board
	 * and the given difficulty adjustor
	 * to set the difficulty. The lower
	 * and upper range on given values
	 * as well as the lower bound on given
	 * values per unit should be determined
	 * by the desired difficulty level. A
	 * given random number generator
	 * is used as values are removed for
	 * difficulty adjustment
	 * adjustment
	 * @param board A completed Sudoku board
	 * to have values removed from it based on
	 * the difficulty level
	 * @param adjustor The difficulty adjustor
	 * algorithm which will decide how values
	 * should be removed
	 * @param lowerRangeOnGivens A percentage
	 * indicating the minimum number of possible 
	 * given values the Sudoku puzzle shall 
	 * initially have
	 * @param upperRangeOnGivens A percentage 
	 * indicating the maximum number of possible 
	 * given values the Sudoku puzzle shall 
	 * initially have
	 * @param lowerBoundOnGivensPerUnit A percentage
	 * indicating the minimum number of values that
	 * can be contained in any row/column/box
	 * @param rng The random number generator used
	 * for difficulty adjustment
	 * @throws NullPointerException Thrown if either
	 * the Sudoku board or the difficulty adjustor is
	 * <tt>null</tt>
	 */
	protected Puzzle(Board board, DifficultyAdjustor adjustor, int lowerRangeOnGivens, int upperRangeOnGivens,
					 int lowerBoundOnGivensPerUnit, Random rng, String difficulty) throws NullPointerException {
		this.board = Objects.requireNonNull(board);
		this.difficulty = Objects.requireNonNull(difficulty);
		adjustor.adjust(board, rng, lowerRangeOnGivens, upperRangeOnGivens, lowerBoundOnGivensPerUnit);
	}
	
	/**
	 * Returns the board for this
	 * Sudoku puzzle
	 * @return The board for this
	 * Sudoku puzzle
	 */
	Board getBoard() {
		return this.board;
	}
	
	/**
	 * Obtains the value at the given
	 * row and column indices
	 * @param row The row index of the
	 * value to be returned
	 * @param col The column index of
	 * the value to be returned
	 * @return The value at the given
	 * row and column indices
	 * @throws IndexOutOfBoundsException Thrown
	 * if either the row or column index is
	 * outside the bounds of this Sudoku
	 * puzzle
	 */
	public char getValueAt(int row, int col) throws IndexOutOfBoundsException {
		return this.board.getValueAt(row, col);
	}
	
	/**
	 * Sets the value of the {@code Cell}
	 * at the given row and column indices
	 * @param value The value to be placed
	 * into the {@code Cell} at the given
	 * row and column indices
	 * @param row The row index of the
	 * {@code Cell} to have its value set
	 * @param col The column index of
	 * the {@code Cell} to have its value
	 * set
	 * @throws IndexOutOfBoundsException Thrown
	 * if either the row or column index is
	 * outside the bounds of this Sudoku
	 * puzzle
	 * @throws IllegalArgumentException Thrown
	 * if the given value is not legal for this
	 * Sudoku puzzle
	 * @throws NoneditableCellException Thrown if
	 * to be edited has been set as non-editable
	 */
	public void setValueAt(char value, int row, int col) throws IndexOutOfBoundsException, IllegalArgumentException, 
			NoneditableCellException {
		this.board.setValueAt(value, row, col);
	}
	
	/**
	 * Removes the value from the {@code Cell}
	 * at the given row and column indices
	 * @param row The row index of the {@code Cell}
	 * to have its value removed
	 * @param col The column index of the
	 * {@code Cell} to have its value removed
	 * @throws NoneditableCellException Thrown if
	 * to be edited has been set as non-editable
	 * @throws IndexOutOfBoundsException Thrown
	 * if either the row or column index is
	 * outside the bounds of this Sudoku
	 * puzzle
	 */
	public void deleteValueAt(int row, int col) throws NoneditableCellException, IndexOutOfBoundsException {
		this.board.deleteValueAt(row, col);
	}
	
	/**
	 * Checks if the {@code Cell} at
	 * the given row and column indices
	 * has been set as editable
	 * @param row The row index of the
	 * {@code Cell} to be checked
	 * @param col The column index of the
	 * {@code Cell} to be checked
	 * @return <tt>true</tt> if the {@code Cell}
	 * at the given row and column indices is
	 * editable, <tt>false</tt> if not
	 */
	public boolean editableCellAt(int row, int col) {
		return this.board.editableCellAt(row, col);
	}
	
	/**
	 * Returns the dimensions of this Sudoku puzzle
	 * @return The dimensions of this Sudoku puzzle
	 */
	public int getDimensions() {
		return this.board.getDimensions();
	}
	
	/**
	 * Checks if a given value is
	 * legal for this Sudoku puzzle
	 * @param value The value to be
	 * checked for legality
	 * @return <tt>true</tt> if the
	 * given value is legal for this
	 * Sudoku puzzle, <tt>false</tt>
	 * if not
	 */
	public boolean isLegalValue(char value) {
		return this.board.isLegalValue(value);
	}
	
	/**
	 * Checks if this Sudoku puzzle is solved
	 * @return <tt>true</tt> if this Sudoku
	 * puzzle is solved, <tt>false</tt> if
	 * not
	 */
	public boolean isSolved() {
		return this.board.isSolved();
	}
	
	/**
	 * Returns the number of rows
	 * in this Sudoku puzzle's
	 * boxes
	 * @return The number of rows
	 * in this Sudoku puzzle's
	 * boxes
	 */
	public int rowSizeInBox() {
		return this.board.rowSizeInBox();
	}
	
	/**
	 * Returns the number of columns
	 * in this Sudoku puzzle's
	 * boxes
	 * @return The number of columns
	 * in this Sudoku puzzle's
	 * boxes
	 */
	public int colSizeInBox() {
		return this.board.colSizeInBox();
	}
	
	/**
	 * Returns the difficulty level of
	 * this Sudoku puzzle
	 * @return The difficulty level of
	 * this Sudoku puzzle
	 */
	public String getDifficulty() {
		return this.difficulty;
	}
	
	@Override
	public String toString() {
		return this.board.toString();
	}
}