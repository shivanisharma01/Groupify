package CSCI5308.GroupFormationTool.AccessControlTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControl.UserDB;

public class UserAbstractFactoryTest implements IUserAbstractFactoryTest {

	@Override
	public User returnUserInstance() {
		return new User();
	}

	@Override
	public UserDBMock returnUserDBMockInstance() {
		return new UserDBMock();
	}

	@Override
	public User returnUserInstance(long value, IUserPersistence userDBMock) {
		return new User(value, userDBMock);
	}

	@Override
	public User returnUserInstance(String value, IUserPersistence userDBMock) {
		return new User(value, userDBMock);
	}

	@Override
	public IUserPersistence returnUserDBInstance() {
		return new UserDB();
	}

	@Override
	public List<User> returnUserListInstance() {
		return new ArrayList<User>();
	}

	@Override
	public CurrentUserMock returnCurrentUserMockInstance() {
		return new CurrentUserMock();
	}
}
