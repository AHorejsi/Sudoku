package local_game;

class GridPane16x16Creator extends GridPaneCreator {
	public GridPane16x16Creator(GUIPuzzle gui) {
		super(gui);
	}
	
	@Override
	public void run() {
		super.create(16);
	}
}