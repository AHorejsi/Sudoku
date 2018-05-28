package generation;

public class InsaneAdjustor implements DifficultyAdjustor {
	private static InsaneAdjustor insane = new InsaneAdjustor();
	
	private InsaneAdjustor() {}
	
	public static InsaneAdjustor getInstance() {
		return InsaneAdjustor.insane;
	}
}