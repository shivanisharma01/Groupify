package CSCI5308.GroupFormationTool.AccessControlTest;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;

public interface IUserAbstractFactoryTest {

	User returnUserInstance();
	List<User> returnUserListInstance();
	UserDBMock returnUserDBMockInstance();
	User returnUserInstance(long value, IUserPersistence userDBMock );
	User returnUserInstance(String value, IUserPersistence userDBMock);
	IUserPersistence returnUserDBInstance();
	CurrentUserMock returnCurrentUserMockInstance();
}
