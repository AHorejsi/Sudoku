package sudoku_game;

import java.util.Collection;

/**
 * A factory that produces a {@code Collection} consisting
 * of one {@code Mixer} or more
 * @author Alex Horejsi
 */
@FunctionalInterface
public interface MixerFactory {
	/**
	 * Creates a {@code Mixer} {@code Collection}
	 * based on the given info
	 * @param info The info determining the contents
	 * of the {@code Collection}
	 * @return A {@code Collection} consisting
	 * of one {@code Mixer} or more
	 */
	public Collection<Mixer> getFactory(String info);
}