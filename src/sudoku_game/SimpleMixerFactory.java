package sudoku_game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class SimpleMixerFactory implements MixerFactory {
	private static MixerFactory factory = new SimpleMixerFactory();
	
	private SimpleMixerFactory() {}
	
	public static MixerFactory getInstance() {
		return SimpleMixerFactory.factory;
	}
	
	@Override
	public Collection<Mixer> getFactory(String info) {
		String[] data = info.split(" ");
		
		Collection<Mixer> mixers = this.determineDataStructure(data[0]);
		
		for (String str : data) {
			if (str.equalsIgnoreCase("rotate"))
				mixers.add(RotateMixer.getInstance());
			else if (str.equalsIgnoreCase("flip"))
				mixers.add(FlipMixer.getInstance());
			else if (str.equalsIgnoreCase("flipbox"))
				mixers.add(FlipBoxMixer.getInstance());
			else if (str.equalsIgnoreCase("swap"))
				mixers.add(SwapMixer.getInstance());
		}
		
		return mixers;
	}
	
	private Collection<Mixer> determineDataStructure(String type) {
		Collection<Mixer> mixers = null;
		
		if (type.equalsIgnoreCase("arraylist"))
			mixers = new ArrayList<Mixer>();
		else if (type.equalsIgnoreCase("linkedlist"))
			mixers = new LinkedList<Mixer>();
		else if (type.equalsIgnoreCase("hashset"))
			mixers = new HashSet<Mixer>();
		else if (type.equalsIgnoreCase("linkedhashset"))
			mixers = new LinkedHashSet<Mixer>();
		else if (type.equalsIgnoreCase("vector"))
			mixers = new Vector<Mixer>();
		else if (type.equalsIgnoreCase("copyonwritearraylist"))
			mixers = new CopyOnWriteArrayList<Mixer>();
		else if (type.equalsIgnoreCase("copyonwritearrayset"))
			mixers = new CopyOnWriteArraySet<Mixer>();
			
		return mixers;
	}
}