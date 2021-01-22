package CSCI5308.GroupFormationTool.AccessControl;

public class UserConfiguration {
	
	private static UserConfiguration uniqueInstance = null;
	private IUserAbstractFactory userAbstractFactory;
	private IUserPersistence userDB;
	
	private UserConfiguration() {
		userAbstractFactory = new UserAbstractFactory();
		userDB = userAbstractFactory.returnUserDB();
	}
	
	public static UserConfiguration instance() {
		// Using lazy initialization, this is the one and only place that the System
		// object will be instantiated.
		if (null == uniqueInstance) {
			uniqueInstance = new UserConfiguration();
		}
		return uniqueInstance;
	}
	
	public IUserAbstractFactory getUserAbstractFactory() {
        return userAbstractFactory;
    }

    public void setUserAbstractFactory(IUserAbstractFactory userAbstractFactory) {
        this.userAbstractFactory = userAbstractFactory;
    }
    
    public void setUserDB(IUserPersistence userDB) {
		this.userDB = userDB;
	}

	public IUserPersistence getUserDB() {
		return userDB;
	}
}
