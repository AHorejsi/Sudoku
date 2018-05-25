package sudoku_game;

public class Checker16x16 {	
	public static boolean check16x16(char[][] table) {
		for (int row = 0 ; row < 16 ; row++) {
			if (!validRow(row, table))
				return false;
		}
		
		for (int col = 0 ; col < 16 ; col++) {
			if (!validCol(col, table))
				return false;
		}
		
		for (int row = 0 ; row < 16 ; row += 4) {
			for (int col = 0 ; col < 16 ; col += 4) {
				if (!validBox(row, col, table))
					return false;
			}
		}
		
		return true;
	}
	
	private static boolean validRow(int row, char[][] table) {
		int bits = 0;
		
		for (int col = 0 ; col < 16 ; col++) {
			if (Character.isDigit(table[row][col]))
				bits |= 1 << table[row][col] - '0' - 1;
			else
				bits |= 1 << table[row][col] - 'A' + 9;
		}		
		
		return bits == 65535;
	}
	
	private static boolean validCol(int col, char[][] table) {
		int bits = 0;
		
		for (int row = 0 ; row < 16 ; row++) {
			if (Character.isDigit(table[row][col]))
				bits |= 1 << table[row][col] - '0' - 1;
			else
				bits |= 1 << table[row][col] - 'A' + 9;
		}
		
		return bits == 65535;
	}
	
	private static boolean validBox(int row, int col, char[][] table) {
		int endRow = row + 4;
		int endCol = col + 4;
		int bits = 0;
		
		for (int i = row ; i < endRow ; i++) {
			for (int j = col ; j < endCol ; j++) {
				if (Character.isDigit(table[i][j]))
					bits |= 1 << table[i][j] - '0' - 1;
				else
					bits |= 1 << table[i][j] - 'A' + 9;
			}
		}
//		
//		System.out.println("Boxes " + bits);
//		System.out.println(Integer.toBinaryString(bits));
		
		return bits == 65535;
	}
}