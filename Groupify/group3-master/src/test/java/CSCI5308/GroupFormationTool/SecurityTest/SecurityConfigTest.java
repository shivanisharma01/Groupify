package CSCI5308.GroupFormationTool.SecurityTest;

public class SecurityConfigTest {
	private static SecurityConfigTest uniqueInstance = null;
	private ISecurityAbstractFactoryTest securityAbstractFactory;
	
	private SecurityConfigTest() {
		securityAbstractFactory = new SecurityAbstractFactoryTest();
    }
	
	public static SecurityConfigTest instance() {

        if (uniqueInstance == null) {
            return new SecurityConfigTest();
        }
        return uniqueInstance;
    }
	
	public ISecurityAbstractFactoryTest getSecurityAbstractFactory() {
        return securityAbstractFactory;
    }
}
