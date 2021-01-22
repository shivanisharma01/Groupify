package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;

public class CurrentUserMock {
	public User getCurrentAuthenticatedUser() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		IUserPersistence userDB = userAbstractFactoryTest.returnUserDBMockInstance();
		String bannerID = "B00000000";
		User u = userAbstractFactoryTest.returnUserInstance();
		userDB.loadUserByBannerID(bannerID, u);
		return u;
	}

}
