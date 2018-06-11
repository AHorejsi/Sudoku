package sudoku_game;

public class SimpleBoxGeneratorRunner implements BoxGeneratorRunner {
	private static BoxGeneratorRunner runner = new SimpleBoxGeneratorRunner();
	
	private SimpleBoxGeneratorRunner() {}
	
	public static BoxGeneratorRunner getInstance() {
		return SimpleBoxGeneratorRunner.runner;
	}
	
	@Override
	public void doRun(BoxGenerator... boxGenerators) {
		Thread[] ts = new Thread[boxGenerators.length];
		
		for (int index = 0 ; index < ts.length ; index++) {
			ts[index] = new Thread(boxGenerators[index]);
			ts[index].start();
		}
		
		try {
			for (Thread t : ts)
				t.join();
		} catch (InterruptedException ex) {
			throw new InternalError();
		}
	}
}