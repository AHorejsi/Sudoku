package local_game;

/**
 * Array-based implementation
 * of {@code DimensionSelectionModel}
 * @author Alex Horejsi
 */
public class ArrayDimensionSelectionModel extends DimensionSelectionModel {
	private static DimensionSelectionModel model = new ArrayDimensionSelectionModel();
	private Integer[] dimensions = {4, 6, 8, 9, 12, 16};
	
	private ArrayDimensionSelectionModel() {}
	
	/**
	 * Returns the single instance of {@code ArrayDimensionSelectionModel}
	 * @return The single instance of {@code ArrayDimensionSelectionModel}
	 */
	public static DimensionSelectionModel getInstance() {
		return ArrayDimensionSelectionModel.model;
	}
	
	@Override
	public int getItemCount() {
		return this.dimensions.length;
	}
	
	@Override
	public Integer getModelItem(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= this.dimensions.length)
			throw new IndexOutOfBoundsException();
		return this.dimensions[index];
	}
}