package generators;

public class Checker9x9 {
	public static boolean check9x9(char[][] table) {
		for (int row = 0 ; row < 9 ; row++) {
			if (!validRow(row, table))
				return false;
		}
		
		for (int col = 0 ; col < 9 ; col++) {
			if (!validCol(col, table))
				return false;
		}
		
		for (int row = 0 ; row < 9 ; row += 3) {
			for (int col = 0 ; col < 9 ; col += 3) {
				if (!validBox(row, col, table))
					return false;
			}
		}
		
		return true;
	}
	
	private static boolean validRow(int row, char[][] table) {
		int bits = 0;
		
		for (int col = 0 ; col < 9 ; col++)
			bits |= 1 << table[row][col] - '0' - 1;
		
		return bits == 511;
	}
	
	private static boolean validCol(int col, char[][] table) {
		int bits = 0;
		
		for (int row = 0 ; row < 9 ; row++)
			bits |= 1 << table[row][col] - '0' - 1;
		
		return bits == 511;
	}
	
	private static boolean validBox(int row, int col, char[][] table) {
		int endRow = row + 3;
		int endCol = col + 3;
		int bits = 0;
		
		for (int i = row ; i < endRow ; i++) {
			for (int j = col ; j < endCol ; j++) {
				bits |= 1 << table[i][j] - '0' - 1;
			}
		}
		
		return bits == 511;
	}
}