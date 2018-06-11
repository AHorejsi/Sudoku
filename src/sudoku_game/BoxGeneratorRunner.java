package sudoku_game;

@FunctionalInterface
public interface BoxGeneratorRunner {
	public void doRun(BoxGenerator... boxGenerators);
}