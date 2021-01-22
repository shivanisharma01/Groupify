package CSCI5308.GroupFormationTool.AlgorithmTest;


public class AlgorithmConfigurationTest {
	
	private static AlgorithmConfigurationTest uniqueInstance = null;
	private IAlgorithmAbstractFactoryTest algorithmAbstractFactory;
	
	private AlgorithmConfigurationTest() {
		algorithmAbstractFactory = new AlgorithmAbstractFactoryTest();
    }
	
	public static AlgorithmConfigurationTest instance() {

        if (uniqueInstance == null) {
            return new AlgorithmConfigurationTest();
        }
        return uniqueInstance;
    }
	
	public IAlgorithmAbstractFactoryTest getAlgorithmAbstractFactory() {
        return algorithmAbstractFactory;
    }
}
