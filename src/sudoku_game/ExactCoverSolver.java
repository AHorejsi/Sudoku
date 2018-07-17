package sudoku_game;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Checks if a Sudoku board
 * has a unique solution by
 * reducing it to an exact
 * cover problem
 * @author Alex Horejsi
 */
public class ExactCoverSolver implements Solver {	
	private static Solver solver = new ExactCoverSolver();
	
	private ExactCoverSolver() {}
	
	/**
	 * Returns the single instance
	 * of {@code ExactCoverSolver}
	 * @return The single instance
	 * of {@code ExactCoverSolver}
	 */
	public static Solver getInstance() {
		return ExactCoverSolver.solver;
	}
	
	@Override
	public boolean hasUniqueSolution(Board board) {
		boolean[][] matrix = this.createMatrix(board);
		ColumnNode root = this.createDoublyLinkedMatrix(board, board.getLegalValues().getValues(), matrix);
		
		return this.search(root, 0, 0) == 1;
	}
	
	private boolean[][] createMatrix(Board board) {
		HashSet<Clue> clues = new HashSet<Clue>();
		Cell[][] table = board.getTable();
		int dimensions = board.getDimensions();
		int boxRows = board.rowSizeInBox();
		int boxCols = board.colSizeInBox();
		
		for (int i = 0 ; i < dimensions ; i++) {
			for (int j = 0 ; j < dimensions ; j++) {
				if (table[i][j].getValue() != '\u0000')
					clues.add(new Clue(table[i][j].getValue(), i, j));
			}
		}
		
		boolean[][] matrix = new boolean[dimensions * dimensions * dimensions][4 * dimensions * dimensions];
		char[] values = board.getLegalValues().getValues();
		
		for (int valuesIndex = 0 ; valuesIndex < dimensions ; valuesIndex++) {
			for (int row = 0 ; row < dimensions ; row++) {
				for (int col = 0 ; col < dimensions ; col++) {
					if (clues.contains(new Clue(values[valuesIndex], row, col))) {
						int rowIndex = col + (dimensions * row) + (dimensions * dimensions * valuesIndex);
						int blockIndex = ((col / boxCols) + ((row / boxRows) * boxRows));
						
						matrix[rowIndex][3 * dimensions * valuesIndex + row] = true;
						matrix[rowIndex][3 * dimensions * valuesIndex + dimensions + col] = true;
						matrix[rowIndex][3 * dimensions * valuesIndex + 2 * dimensions + blockIndex] = true;
						matrix[rowIndex][3 * dimensions * dimensions + (col + dimensions * row)] = true;
					}
				}
			}
		}
		
		return matrix;
	}
	
	private ColumnNode createDoublyLinkedMatrix(Board board, char[] values, boolean[][] matrix) {
		//int dimensions = board.getDimensions();
		ColumnNode root = new ColumnNode();
		ColumnNode currentColumn = root;
		
		for (int col = 0 ; col < matrix[0].length ; col++) {
			//ColumnID id = new ColumnID();
			
//			if (col < 3 * dimensions * dimensions) {
//				int i = (col / (3 * dimensions)) + 1;
//				id.number = values[i];
//				int index = col - (i - 1) * 3 * dimensions;
//				
//				if (index < dimensions) {
//					id.constraint = 0;
//					id.position = index;
//				}
//				else if (index < 2 * dimensions) {
//					id.constraint = 1;
//					id.position = index - dimensions;
//				}
//				else {
//					id.constraint = 2;
//					id.position = index - 2 * dimensions;
//				}
//			}
//			else {
//				id.constraint = 3;
//				id.position = col - 3 * dimensions * dimensions;
//			}
			
			currentColumn.right = new ColumnNode();
			currentColumn.right.left = currentColumn;
			currentColumn = (ColumnNode)currentColumn.right;
			//currentColumn.info = id;
			currentColumn.head = currentColumn;
		}
		
		currentColumn.right = root;
		root.left = currentColumn;
		
		for (int row = 0 ; row < matrix.length ; row++) {
			currentColumn = (ColumnNode)root.right;
			Node lastCreatedElement = null;
			Node firstElement = null;
			
			for (int col = 0 ; col < matrix[row].length ; col++) {
				if (matrix[row][col]) {
					Node columnElement = currentColumn;
					
					while (columnElement.down != null)
						columnElement = columnElement.down;
					
					columnElement.down = new Node();
					
					if (firstElement == null)
						firstElement = columnElement.down;
					
					columnElement.down.up = columnElement;
					columnElement.down.left = lastCreatedElement;
					columnElement.down.head = currentColumn;
					
					if (lastCreatedElement != null)
						columnElement.down.left.right = columnElement.down;
					
					lastCreatedElement = columnElement.down;
					currentColumn.size++;
				}
				
				currentColumn = (ColumnNode)currentColumn.right;
			}
			
			if (lastCreatedElement != null) {
				lastCreatedElement.right = firstElement;
				firstElement.left = lastCreatedElement;
			}
		}
		
		currentColumn = (ColumnNode)root.right;
		
		for (int i = 0 ; i < matrix[0].length ; i++) {
			Node columnElement = currentColumn;
			
			while (columnElement.down != null)
				columnElement = columnElement.down;
			
			columnElement.down = currentColumn;
			currentColumn.up = columnElement;
			currentColumn = (ColumnNode)currentColumn.right;
		}
		
		return root;
	}
	
	private int search(ColumnNode root, int count, int check) {
		System.out.println(count);
		
		if (root.right == root) {
			count++;
			return count;
		}
		
		ColumnNode column = this.chooseNextColumn(root);
		this.cover(column);
		Node row = column.down;
		ArrayList<Node> sol = new ArrayList<Node>();
		
		while (row != column) {
			sol.add(row);
			Node node = row.right;
			
			while (node != row) {
				this.cover(node.head);
				node = node.right;
			}
			
			count = this.search(root, count, check + 1);
			
			sol.remove(row);
			Node row2 = sol.get(check);
			Node node2 = row2.left;
			
			while (row2 != node2) {
				this.uncover(node2.head);
				node2 = node2.left;
			}
			
			row = row.down;
		}
		
		this.uncover(column);
		
		return count;
	}
	
	private ColumnNode chooseNextColumn(ColumnNode root) {
		ColumnNode rightOfRoot = (ColumnNode)root.right;
		ColumnNode smallest = rightOfRoot;
		
		while (rightOfRoot != root) {
			rightOfRoot = (ColumnNode)rightOfRoot.right;
			
			if (rightOfRoot.size < smallest.size)
				smallest = rightOfRoot;
		}
		
		return smallest;
	}
	
	private void cover(ColumnNode column) {
		column.right.left = column.left;
		column.left.right = column.right;
		Node currentRow = column.down;
		
		while (currentRow != column) {
			Node currentNode = currentRow.right;
			
			while (currentNode != currentRow) {
				currentNode.down.up = currentNode.up;
				currentNode.up.down = currentNode.down;
				currentNode.head.size--;
				currentNode = currentNode.right;
			}
			
			currentRow = currentRow.down;
		}
	}
	
	private void uncover(ColumnNode column) {
		Node currentRow = column.up;
		
		while (currentRow != column) {
			Node currentNode = currentRow.left;
			
			while (currentNode != currentRow) {
				currentNode.head.size++;
				currentNode.down.up = currentNode;
				currentNode.up.down = currentNode;
				currentNode = currentNode.left;
			}
			
			column.right.left = column;
			column.left.right = column;
			currentRow = currentRow.up;
		}
	}
	
	private static class Node {
		ColumnNode head;
		Node up;
		Node down;
		Node left;
		Node right;
	}
	
	private static class ColumnNode extends Node {
		int size;
		//ColumnID info;
	}
	
//	private static class ColumnID {
//		int constraint;
//		char number;
//		int position;
//	}
	
	private static class Clue {
		char val;
		int row;
		int col;
		
		private Clue(char val, int row, int col) {
			this.val = val;
			this.row = row;
			this.col = col;
		}
		
		@Override
		public int hashCode() {
			int hash = 31 * this.val;
			hash += 31 * this.row;
			hash += 31 * this.col;
			
			return hash;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Clue))
				return false;
			Clue clue = (Clue)obj;
			return this.val == clue.val && this.row == clue.row && this.col == clue.col;
		}
	}
}