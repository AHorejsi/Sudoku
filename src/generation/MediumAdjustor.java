package generation;

public class MediumAdjustor implements DifficultyAdjustor {
	private static MediumAdjustor medium = new MediumAdjustor();
	
	private MediumAdjustor() {}
	
	public static MediumAdjustor getInstance() {
		return MediumAdjustor.medium;
	}
}