package sudoku_game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Generator16x16 implements Generator {
	public static void main(String[] args) {
		Cell[][] table = Generator16x16.getInstance().generate();
		
		for (int i = 0 ; i < 16 ; i++) {
			for (int j = 0 ; j < 16 ; j++)
				System.out.print(table[i][j] + " ");
			System.out.println();
		}
	}
	
	private static Generator gen = new Generator16x16();
	private Cell[][] table;
	private char[] values;
	private List<Node> answer;
	private ColumnNode header;
	
	private Generator16x16() {}
	
	public static Generator getInstance() {
		return Generator16x16.gen;
	}
	
	@Override
	public Cell[][] generate(Random rng) {
		this.table = new Cell[16][16];
		this.values = LegalValues16x16.getInstance().getValues();
		this.answer = new LinkedList<Node>();
		this.fillMajorDiagonal(rng);
		boolean[][] matrix = this.createMatrix();
		this.placeInitialValues(matrix);
		this.header = this.createDoublyLinkedMatrix(matrix);
		Cell[][] table = this.search(0, new Cell[16][16]);
		this.table = null;
		this.values = null;
		this.answer = null;
		this.header = null;
		
		return table;
	}
	
	private void fillMajorDiagonal(Random rng) {
		this.fillBox(0, 0, rng);
		this.fillBox(4, 4, rng);
		this.fillBox(8, 8, rng);
		this.fillBox(12, 12, rng);
	}
	
	private void fillBox(int i, int j, Random rng) {
		char[] values = LegalValues16x16.getInstance().getValues();
		this.shuffle(values, rng);
		int end = i + 4;
		int index = 0;
		
		for (int row = i ; row < end ; row++) {
			for (int col = j ; col < end ; col++) {
				this.table[row][col] = new ConcreteCell(values[index]);
				index++;
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
	
	private boolean[][] createMatrix() {
		boolean[][] matrix = new boolean[4096][1024];
		
		int hBase = 0;
		hBase = this.checkCellConstraint(hBase, matrix);
		hBase = this.checkRowConstraint(hBase, matrix);
		hBase = this.checkColumnConstraint(hBase, matrix);
		this.checkBoxConstraint(hBase, matrix);
		
		return matrix;
	}
	
	private int checkCellConstraint(int hBase, boolean[][] matrix) {
		for (int row = 0 ; row < 16 ; row++) {
			for (int column = 0 ; column < 16 ; column++, hBase++) {
				for (int valuesIndex = 0 ; valuesIndex < 16 ; valuesIndex++) {
					int index = this.getIndex(row, column, valuesIndex);
					matrix[index][hBase] = true;
				}
			}
		}
		
		return hBase;
	}
	
	private int checkRowConstraint(int hBase, boolean[][] matrix) {
		for (int row = 0 ; row < 16 ; row++) {
			for (int valuesIndex = 0 ; valuesIndex < 16 ; valuesIndex++, hBase++) {
				for (int column = 0 ; column < 16 ; column++) {
					int index = this.getIndex(row, column, valuesIndex);
					matrix[index][hBase] = true;
				}
			}
		}
		
		return hBase;
	}
	
	private int checkColumnConstraint(int hBase, boolean[][] matrix) {
		for (int column = 0 ; column < 16 ; column++) {
			for (int valuesIndex = 0 ; valuesIndex < 16 ; valuesIndex++, hBase++) {
				for (int row = 0 ; row < 16 ; row++) {
					int index = this.getIndex(row, column, valuesIndex);
					matrix[index][hBase] = true;
				}
			}
		}
		
		return hBase;
	}
	
	private int getIndex(int row, int column, int valuesIndex) {
		return row * 16 * 16 + column * 16 + valuesIndex;
	}
	
	private void checkBoxConstraint(int hBase, boolean[][] matrix) {
		for (int row = 0 ; row < 16 ; row += 4) {
			for (int column = 0 ; column < 16 ; column += 4) {
				for (int valuesIndex = 0 ; valuesIndex < 16 ; valuesIndex++, hBase++) {
					for (int rowDelta = 0 ; rowDelta < 4 ; rowDelta++) {
						for (int columnDelta = 0 ; columnDelta < 4 ; columnDelta++) {
							int index = this.getIndex(row + rowDelta, column + columnDelta, valuesIndex);
							matrix[index][hBase] = true;
						}
					}
				}
			}
		}
	}
	
	private void placeInitialValues(boolean[][] matrix) {
		this.placeBox(0, 0, matrix);
		this.placeBox(4, 4, matrix);
		this.placeBox(8, 8, matrix);
		this.placeBox(12, 12, matrix);
	}
	
	private void placeBox(int i, int j, boolean[][] matrix) {
		int end = i + 4;
		
		for (int row = i ; row < end ; row++) {
			for (int col = j ; col < end ; col++) {
				char value = this.table[row][col].getValue();
				
				for (int valuesIndex = 0 ; valuesIndex < 16 ; valuesIndex++) {
					if (value != this.values[valuesIndex])
						Arrays.fill(matrix[this.getIndex(row, col, valuesIndex)], false);
				}
			}
		}
	}
	
	private ColumnNode createDoublyLinkedMatrix(boolean[][] matrix) {
		ColumnNode hNode = new ColumnNode("header");
		List<ColumnNode> columnNodes = new ArrayList<ColumnNode>();
		
		for (int i = 0 ; i < 1024 ; i++) {
			ColumnNode node = new ColumnNode(String.valueOf(i));
			columnNodes.add(node);
			hNode = (ColumnNode)hNode.hookRight(node); 
		}
		
		hNode = hNode.right.column;
		
		for (boolean[] row : matrix) {
			Node prev = null;
			
			for (int col = 0 ; col < 1024 ; col++) {
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
		
		hNode.size = 1024;
		
		return hNode;
	}
	
	private Cell[][] search(int k, Cell[][] table) {
		if (this.header.right == this.header) {
			System.out.println("TEST");
			this.constructTable(table);
		}
		else {
			ColumnNode col = this.chooseNextColumn();
			col.cover();
			
			for (Node row = col.down ; row != col ; row = row.down) {
				this.answer.add(row);
				
				for (Node node = row.right ; node != row ; node = node.right)
					node.column.cover();
				
				table = this.search(k + 1, table);
				
				if (table != null)
					return table;
				
				row = this.answer.remove(this.answer.size() - 1);
				col = row.column;
				
				for (Node node = row.left ; node != row ; node = node.left)
					node.column.uncover();
			}
		}
		
		return table;
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
	
	private Cell[][] constructTable(Cell[][] table) {
		
		for (Node node : this.answer) {
			Node rcNode = node;
			int min = Integer.parseInt(rcNode.column.name);
			
			for (Node temp = node.right ; temp != node ; temp = temp.right) {
				int val = Integer.parseInt(temp.column.name);
				
				if (val < min) {
					min = val;
					rcNode = temp;
				}
			}
			
			int answer1 = Integer.parseInt(rcNode.column.name);
			int answer2 = Integer.parseInt(rcNode.right.column.name);
			int row = answer1 / 16;
			int col = answer1 % 16;
			char value = this.values[(answer2 % 16) + 1];
			table[row][col] = new ConcreteCell(value);
		}
		
		return table;
	}
	
	private static class Node {
		Node up;
		Node down;
		Node left;
		Node right;
		ColumnNode column;
		
		Node() {
			this.left = this.right = this.up = this.down = this;
		}
		
		Node(ColumnNode column) {
			this();
			this.column = column;
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
		int size;
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