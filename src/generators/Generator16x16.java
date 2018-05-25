package generators;

import java.util.Random;

public class Generator16x16 implements Generator {
	private static Generator16x16 gen = new Generator16x16();
	private char[][] table;
	
	private Generator16x16() {}
	
	public static Generator16x16 getInstance() {
		return Generator16x16.gen;
	}
	
	@Override
	public char[][] generate(Random rng) {
		
	}
}