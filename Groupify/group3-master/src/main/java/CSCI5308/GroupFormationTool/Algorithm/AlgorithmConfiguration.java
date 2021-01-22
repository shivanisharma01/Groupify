package CSCI5308.GroupFormationTool.Algorithm;

public class AlgorithmConfiguration {

	private static AlgorithmConfiguration uniqueInstance = null;
	private IAlgorithmAbstractFactory algorithmAbstractFactory;
	
	private AlgorithmConfiguration() {
		algorithmAbstractFactory = new AlgorithmAbstractFactory();
	}
	
	public static AlgorithmConfiguration instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new AlgorithmConfiguration();
		}
		return uniqueInstance;
	}
	
	public IAlgorithmAbstractFactory getAlgorithmAbstractFactory() {
        return algorithmAbstractFactory;
    }

    public void setAlgorithmAbstractFactory(IAlgorithmAbstractFactory algorithmAbstractFactory) {
        this.algorithmAbstractFactory = algorithmAbstractFactory;
    }
}
