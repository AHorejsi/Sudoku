package sudoku_game;

class ExactCoverMatrix {
	private Node head;
	
	ExactCoverMatrix(Cell[][] table) {
		
	}
	
	private static class Node {
		private boolean filled;
		private Node up;
		private Node down;
		private Node left;
		private Node right;
		
		public Node(boolean filled) {
			this.filled = filled;
		}
	}
	
	private static class ColumnNode extends Node {
		private char name;
		
		public ColumnNode(boolean filled, char name) {
			super(filled);
			this.name = name;
		}
	}
}