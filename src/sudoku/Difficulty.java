package sudoku;

import java.util.Random;

public enum Difficulty {
	EASY (0.44f, 0.57f, 0.56f),
	HARD (0.35f, 0.38f, 0.22f);
	
	private float minPercent;
	private float maxPercent;
	private float lowerBound;
	
	private Difficulty(float minPercent, float maxPercent, float lowerBound) {
		this.minPercent = minPercent;
		this.maxPercent = maxPercent;
	}
	
	public float getMinPercent() {
		return this.minPercent;
	}
	
	public float getMaxPercent() {
		return this.maxPercent;
	}
	
	public float getLowerBound() {
		return this.lowerBound;
	}
	
	public double getRandomPercent(Random rng) {
		return this.minPercent + (this.maxPercent - this.minPercent) * rng.nextFloat();
	}
}