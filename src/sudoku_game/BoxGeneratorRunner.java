package sudoku_game;

@FunctionalInterface
interface BoxGeneratorRunner {
	public void doRun(BoxGenerator... boxGenerators);
}