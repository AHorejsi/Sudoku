package local_game;

import javafx.scene.control.SingleSelectionModel;

/**
 * Abstract class for selecting available
 * difficulties in a JavaGX GUI settings
 * menu with a {@code ComboBox}
 * @author Alex Horejsi
 */
public abstract class DifficultySelectionModel extends SingleSelectionModel<String> {
	/**
	 * Default empty constructor
	 * for subclasses
	 */
	protected DifficultySelectionModel() {}
}