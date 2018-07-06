package sudoku_game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This implementation of {@code MixerFactory} allows
 * a user to choose what implementations of {@code Mixer}
 * to use and what kind of data structure to put them in.
 * The input {@code String} should consist of several words,
 * separated by whitespace characters. The first word should be the
 * type of data structure to be used. The following words
 * should indicate the implementations of {@code Mixer} to
 * use
 * <br><br>
 * Available data structures:
 * <table>
 * 	<thead>
 * 		<tr>
 * 			<th>Class</th>
 * 			<th>Input String</th>
 * 		</tr>
 * 	</thead>
 * 	<tbody>
 * 		<tr>
 * 			<td>{@code ArrayList}</td>
 * 			<td>"arraylist"</td>
 * 		</tr>
 * 		<tr>
 * 			<td>{@code LinkedList}</td>
 * 			<td>"linkedlist"</td>
 * 		</tr>
 * 		<tr>
 * 			<td>{@code Vector}</td>
 * 			<td>"vector"</td>
 * 		</tr>
 * 		<tr>
 * 			<td>{@code CopyOnWriteArrayList}</td>
 * 			<td>"copyonwritearraylist"</td>
 * 		</tr>
 * 		<tr>
 * 			<td>{@code HashSet}</td>
 * 			<td>"hashset"</td>
 * 		</tr>
 * 		<tr>
 * 			<td>{@code LinkedHashSet}</td>
 * 			<td>"linkedhashset"</td>
 * 		</tr>
 * 		<tr>
 * 			<td>{@code CopyOnWriteArraySet}</td>
 * 			<td>"copyonwritearrayset"</td>
 * 		</tr>
 * 	</tbody>
 * </table>
 * 
 * <br>
 * Available implementations of {@code Mixer}:
 * <table>
 * 	<thead>
 * 		<tr>
 * 			<th>Class</th>
 * 			<th>Input String</th>
 * 		</tr>
 * 	</thead>
 * 	<tbody>
 * 		<tr>
 * 			<td>{@code RotateMixer}</td>
 * 			<td>"rotate"</td>
 * 		</tr>
 * 		<tr>
 * 			<td>{@code FlipMixer}</td>
 * 			<td>"flip"</td>
 * 		</tr>
 * 		<tr>
 * 			<td>{@code FlipBoxMixer}</td>
 * 			<td>"flipbox"</td>
 * 		</tr>
 * 		<tr>
 * 			<td>{@code SwapMixer}</td>
 * 			<td>"swap"</td>
 * 		</tr>
 * 	</tbody>
 * </table>
 * @author Alex Horejsi
 */
public class SimpleMixerFactory implements MixerFactory {
	private static MixerFactory factory = new SimpleMixerFactory();
	
	private SimpleMixerFactory() {}
	
	/**
	 * Returns the single instance
	 * of {@code SimpleMixerFactory}
	 * @return The single instance
	 * of {@code SimpleMixerFactory}
	 */
	public static MixerFactory getInstance() {
		return SimpleMixerFactory.factory;
	}
	
	@Override
	public Collection<Mixer> getFactory(String info) {
		String[] data = info.split(" ");
		
		Collection<Mixer> mixers = this.determineDataStructure(data[0]);
		
		for (String str : data) {
			if (str.equalsIgnoreCase("flip"))
				mixers.add(FlipMixer.getInstance());
			else if (str.equalsIgnoreCase("flipbox"))
				mixers.add(FlipBoxMixer.getInstance());
			else if (str.equalsIgnoreCase("swap"))
				mixers.add(SwapMixer.getInstance());
			else if (str.equalsIgnoreCase("rotate"))
				mixers.add(RotateMixer.getInstance());
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