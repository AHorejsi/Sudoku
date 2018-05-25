package mixer;

import java.util.Random;

public class RotateFlipSwapMixer implements Mixer {
	public static void main(String[] args) {
		char[][] table = {{'1','2','3'},
						  {'4','5','6'},
						  {'7','8','9'}};
		Random r = new Random();
		new RotateFlipSwapMixer().mix(table, r);
		for (int i = 0 ; i < table.length ; i++) {
			for (int j = 0 ; j < table[i].length ; j++)
				System.out.print(table[i][j] + " ");
			System.out.println();
		}
	}
	
	@Override
	public void mix(char[][] table, Random rng) {
		this.rotate(table, rng);
		//this.flip(table, rng);
		//this.swap(table, rng);
	}
	
	private void rotate(char[][] table, Random rng) {
		int option = rng.nextInt(4);
		
		if (option == 0)
			RotateFlipSwapMixer.rotate90(table);
		else if (option == 1)
			RotateFlipSwapMixer.rotate180(table);
		else if (option == 2)
			RotateFlipSwapMixer.rotate270(table);
	}
	
	static native void rotate90(char[][] table);
	
	static native void rotate180(char[][] table);
	
	static native void rotate270(char[][] table);
}