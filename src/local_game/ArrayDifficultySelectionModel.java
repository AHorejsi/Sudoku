package local_game;

/**
 * Array-based implementation
 * of {@code DifficultySelectionModel}
 * @author Alex Horejsi
 */
public class ArrayDifficultySelectionModel extends DifficultySelectionModel {
	private static DifficultySelectionModel model = new ArrayDifficultySelectionModel();
	private String[] difficulties = {"Basic", "Easy", "Medium", "Hard", "Insane"};
	
	private ArrayDifficultySelectionModel() {}
	
	/**
	 * Returns the single instance of {@code ArrayDifficultySelectionModel}
	 * @return The single instance of {@code ArrayDifficultySelectionModel}
	 */
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