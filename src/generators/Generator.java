package generators;

import java.util.Random;

@FunctionalInterface
public interface Generator {
	public char[][] generate(Random rng);
}