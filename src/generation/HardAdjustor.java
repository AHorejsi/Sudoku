package generation;

public class HardAdjustor implements DifficultyAdjustor {
	private static HardAdjustor hard = new HardAdjustor();
	
	private HardAdjustor() {}
	
	public static HardAdjustor getInstance() {
		return HardAdjustor.hard;
	}
}