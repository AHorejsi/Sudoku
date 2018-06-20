package local_game;

class GridPane9x9Creator extends GridPaneCreator {
	GridPane9x9Creator() {
		super();
	}
	
	@Override
	public void run() {
		super.create(9, 60);
	}
}