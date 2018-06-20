package local_game;

public class ArrayDimensionSelectionModel extends DimensionSelectionModel {
	private static DimensionSelectionModel model = new ArrayDimensionSelectionModel();
	private Integer[] dimensions = {9, 16};
	
	private ArrayDimensionSelectionModel() {}
	
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