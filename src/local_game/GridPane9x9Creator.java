package local_game;

class GridPane9x9Creator extends GridPaneCreator {
	public GridPane9x9Creator(GUIPuzzle gui) {
		super(gui);
	}
	
	@Override
	public void run() {
		super.create(9);
	}
}