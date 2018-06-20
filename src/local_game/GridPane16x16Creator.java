package local_game;

class GridPane16x16Creator extends GridPaneCreator {
	GridPane16x16Creator() {
		super();
	}
	
	@Override
	public void run() {
		super.create(16, 30);
	}
}