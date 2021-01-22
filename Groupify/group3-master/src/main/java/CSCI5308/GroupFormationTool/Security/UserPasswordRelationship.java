package CSCI5308.GroupFormationTool.Security;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public class UserPasswordRelationship implements IUserPasswordRelationship {

	@Override
	public boolean savePassword(User user, IUserPasswordRelationshipPersistence userPasswordRelationShipDB) {
		return userPasswordRelationShipDB.savePassword(user);
	}

	@Override
	public List<String> getUserPasswords(User user, IUserPasswordRelationshipPersistence userPasswordDBObj,
			int historyLimit) {
		return userPasswordDBObj.getUserPasswords(user, historyLimit);
	}

}
