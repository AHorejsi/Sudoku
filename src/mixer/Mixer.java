package mixer;

import java.util.Random;

@FunctionalInterface
public interface Mixer {
	public void mix(char[][] table, Random rng);
}