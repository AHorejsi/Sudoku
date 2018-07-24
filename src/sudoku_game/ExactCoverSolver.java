package sudoku_game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ExactCoverSolver implements Solver {
	private static Solver solver = new ExactCoverSolver();
	private Board board;
	private char[] values;
	private ColumnNode header;
	private List<Node> answer;
	
	private ExactCoverSolver() {}
	
	public static Solver getInstance() {
		return ExactCoverSolver.solver;
	}
	
	@Override
	public boolean hasUniqueSolution(Board board) {
		this.board = board;
		this.values = board.getLegalValues().getValues();
		this.answer = new LinkedList<Node>();
		
		int dimensions = board.getDimensions();
		boolean[][] matrix = this.createMatrix(board, dimensions);
		this.placeInitialValues(matrix, dimensions);
		this.header = this.createDoublyLinkedMatrix(matrix);
		
		int result = this.search(0, 0);
		this.board = null;
		this.values = null;
		this.header = null;
		this.answer = null;
		
		return result == 1;
	}
	
	private boolean[][] createMatrix(Board board, int dimensions) {
		boolean[][] matrix = new boolean[dimensions * dimensions * dimensions][4 * dimensions * dimensions];
		
		int hBase = 0;
		hBase = this.checkCellConstraint(hBase, dimensions, matrix);
		hBase = this.checkRowConstraint(hBase, dimensions, matrix);
		hBase = this.checkColumnConstraint(hBase, dimensions, matrix);
		this.checkBoxConstraint(hBase, dimensions, matrix);
		
		return matrix;
	}
	
	private int checkCellConstraint(int hBase, int dimensions, boolean[][] matrix) {
		for (int row = 0 ; row < dimensions ; row++) {
			for (int col = 0 ; col < dimensions ; col++, hBase++) {
				for (int valIndex = 0 ; valIndex < dimensions ; valIndex++) {
					int index = this.getIndex(row, col, valIndex, dimensions);
					matrix[index][hBase] = true;
				}
			}
		}
		
		return hBase;
	}
	
	private int checkRowConstraint(int hBase, int dimensions, boolean[][] matrix) {
		for (int row = 0 ; row < dimensions ; row++) {
			for (int valIndex = 0 ; valIndex < dimensions ; valIndex++, hBase++) {
				for (int col = 0 ; col < dimensions ; col++) {
					int index = this.getIndex(row, col, valIndex, dimensions);
					matrix[index][hBase] = true;
				}
			}
		}
		
		return hBase;
	}
	
	private int checkColumnConstraint(int hBase, int dimensions, boolean[][] matrix) {
		for (int col = 0 ; col < dimensions ; col++) {
			for (int valIndex = 0 ; valIndex < dimensions ; valIndex++, hBase++) {
				for (int row = 0 ; row < dimensions ; row++) {
					int index = this.getIndex(row, col, valIndex, dimensions);
					matrix[index][hBase] = true;
				}
			}
		}
		
		return hBase;
	}
	
	private void checkBoxConstraint(int hBase, int dimensions, boolean[][] matrix) {
		int end = (int)Math.sqrt(dimensions);
		
		for (int row = 0 ; row < dimensions ; row += end) {
			for (int col = 0 ; col < dimensions ; col += end) {
				for (int valIndex = 0 ; valIndex < dimensions ; valIndex++, hBase++) {
					for (int rowDelta = 0 ; rowDelta < end ; rowDelta++) {
						for (int colDelta = 0 ; colDelta < end ; colDelta++) {
							int index = this.getIndex(row + rowDelta, col + colDelta, valIndex, dimensions);
							matrix[index][hBase] = true;
						}
					}
				}
			}
		}
	}
	
	private int getIndex(int row, int col, int valIndex, int dimensions) {
		return row * dimensions * dimensions + col * dimensions + valIndex;
	}
	
	private void placeInitialValues(boolean[][] matrix, int dimensions) {
		for (int i = 0 ; i < dimensions ; i++) {
			for (int j = 0 ; j < dimensions ; j++) {
				char value = board.getValueAt(i, j);
				
				if (value != '\u0000') {
					for (int valIndex = 0 ; valIndex < dimensions ; valIndex++) {
						if (value != this.values[valIndex])
							Arrays.fill(matrix[this.getIndex(i, j, valIndex, dimensions)], false);
					}
				}
			}
		}
	}
	
	private ColumnNode createDoublyLinkedMatrix(boolean[][] matrix) {
		ColumnNode hNode = new ColumnNode("header");
		List<ColumnNode> columnNodes = new ArrayList<ColumnNode>();
		int cols = matrix[0].length;
		
		for (int i = 0 ; i < cols ; i++) {
			ColumnNode node = new ColumnNode(String.valueOf(i));
			columnNodes.add(node);
			hNode = (ColumnNode)hNode.hookRight(node);
		}
		
		hNode = hNode.right.column;
		
		for (boolean[] row : matrix) {
			Node prev = null;
			
			for (int col = 0 ; col < cols ; col++) {
				if (row[col]) {
					ColumnNode colNode = columnNodes.get(col);
					Node newNode = new Node(colNode);
					
					if (prev == null)
						prev = newNode;
					
					colNode.up.hookDown(newNode);
					prev = prev.hookRight(newNode);
					colNode.size++;
				}
			}
		}
		
		hNode.size = cols;
		
		return hNode;
	}
	
	private int search(int k, int count) {
		if (this.header.right == this.header)
			count++;
		else {
			ColumnNode col = this.chooseNextColumn();
			col.cover();
			
			for (Node row = col.down ; row != col ; row = row.down) {
				this.answer.add(row);
				
				for (Node node = row.right ; node != row ; node = node.right)
					node.column.cover();
				
				count = this.search(k + 1, count);
				
				row = this.answer.remove(this.answer.size() - 1);
				col = row.column;
				
				for (Node node = row.left ; node != row ; node = node.left)
					node.column.uncover();
			}
		}
		
		return count;
	}
	
	private ColumnNode chooseNextColumn() {
		int min = Integer.MAX_VALUE;
		ColumnNode ret = null;
		
		for (ColumnNode col = (ColumnNode)this.header.right ; col != this.header ; col = (ColumnNode)col.right) {
			if (col.size < min) {
				min = col.size;
				ret = col;
			}
		}
		
		return ret;
	}
	
	private static class Node {
		Node up;
		Node down;
		Node left;
		Node right;
		ColumnNode column;
		
		Node() {
			this.up = this;
			this.down = this;
			this.left = this;
			this.right = this;
		}
		
		Node(ColumnNode columnNode) {
			this();
			this.column = columnNode;
		}
		
		Node hookDown(Node node) {
			node.down = this.down;
			node.down.up = node;
			node.up = this;
			this.down = node;
			
			return node;
		}
		
		Node hookRight(Node node) {
			node.right = this.right;
			node.right.left = node;
			node.left = this;
			this.right = node;
			
			return node;
		}
		
		void unlinkLeftRight() {
			this.left.right = this.right;
			this.right.left = this.left;
		}
		
		void relinkLeftRight() {
			this.left.right = this.right.left = this;
		}
		
		void unlinkUpDown() {
			this.up.down = this.down;
			this.down.up = this.up;
		}
		
		void relinkUpDown() {
			this.up.down = this.down.up = this;
		}
	}
	
	private static class ColumnNode extends Node {
		int size = 0;
		String name;
		
		ColumnNode(String name) {
			super();
			this.name = name;
			super.column = this;
		}
		
		void cover() {
			super.unlinkLeftRight();
			
			for (Node node1 = super.down ; node1 != this ; node1 = node1.down) {
				for (Node node2 = node1.right ; node2 != node1 ; node2 = node2.right) {
					node2.unlinkUpDown();
					node2.column.size--;
				}
			}
		}
		
		void uncover() {
			for (Node node1 = super.up ; node1 != this ; node1 = node1.up) {
				for (Node node2 = node1.left ; node2 != node1 ; node2 = node2.left) {
					node2.column.size++;
					node2.relinkUpDown();
				}
			}
			
			super.relinkLeftRight();
		}
	}
}