package local_game;

import java.util.Objects;

class Settings {
	private int dimensions;
	private String difficulty;
	
	public Settings(int dimensions, String difficulty) {
		this.dimensions = dimensions;
		this.difficulty = Objects.requireNonNull(difficulty);
	}
	
	public int getDimensions() {
		return this.dimensions;
	}
	
	public void setDimensions(int dimensions) {
		this.dimensions = dimensions;
	}
	
	public String getDifficulty() {
		return this.difficulty;
	}
	
	public void setDifficulty(String difficulty) {
		this.difficulty = Objects.requireNonNull(difficulty);
	}
	
	@Override
	public String toString() {
		return this.dimensions + "x" + this.dimensions + " " + this.difficulty;
	}
}