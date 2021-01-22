package CSCI5308.GroupFormationTool.Security;

import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;

public interface IUserPasswordRelationshipPersistence {

	public boolean savePassword(User user);

	public List<String> getUserPasswords(User user, int historyLimit);

}
