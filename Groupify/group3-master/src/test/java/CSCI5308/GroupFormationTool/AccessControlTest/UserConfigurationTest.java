package CSCI5308.GroupFormationTool.AccessControlTest;

public class UserConfigurationTest {

	private static UserConfigurationTest uniqueInstance = null;
	private IUserAbstractFactoryTest userAbstractFactory;
	
	private UserConfigurationTest() {
		userAbstractFactory = new UserAbstractFactoryTest();
    }
	
	public static UserConfigurationTest instance() {

        if (uniqueInstance == null) {
            return new UserConfigurationTest();
        }
        return uniqueInstance;
    }
	
	public IUserAbstractFactoryTest getUserAbstractFactory() {
        return userAbstractFactory;
    }
}
