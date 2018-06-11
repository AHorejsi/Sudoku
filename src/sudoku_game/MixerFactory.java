package sudoku_game;

import java.util.Collection;

@FunctionalInterface
public interface MixerFactory {
	public Collection<Mixer> getFactory(String info);
}