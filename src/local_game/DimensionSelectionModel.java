package local_game;

import javafx.scene.control.SingleSelectionModel;

/**
 * Abstract class for selecting available
 * dimensions in a JavaFX GUI settings
 * menu with a {@code ComboBox}
 * @author Alex Horejsi
 */
abstract class DimensionSelectionModel extends SingleSelectionModel<Integer> {
	/**
	 * Default empty constructor
	 * for subclasses
	 */
	protected DimensionSelectionModel() {}
}