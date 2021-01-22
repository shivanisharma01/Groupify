package CSCI5308.GroupFormationTool.Security;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface IUserPasswordRelationship {

	public boolean savePassword(User user, IUserPasswordRelationshipPersistence userPasswordRelationShipDB);

	public List<String> getUserPasswords(User user, IUserPasswordRelationshipPersistence userPasswordDBObj,
			int historyLimit);

}
