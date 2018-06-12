package local_game;

class ArrayDifficultySelectionModel extends DifficultySelectionModel {
	private static DifficultySelectionModel model = new ArrayDifficultySelectionModel();
	private String[] difficulties = {"BASIC", "EASY", "MEDIUM", "HARD", "INSANE"};
	
	private ArrayDifficultySelectionModel() {}
	
	public static DifficultySelectionModel getInstance() {
		return ArrayDifficultySelectionModel.model;
	}
	
	@Override
	public int getItemCount() {
		return this.difficulties.length;
	}
	
	@Override
	public String getModelItem(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.difficulties.length)
			throw new IndexOutOfBoundsException();
		return this.difficulties[index];
	}
}