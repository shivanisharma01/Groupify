package CSCI5308.GroupFormationTool.SecurityTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Security.IUserPasswordRelationshipPersistence;

public class UserPasswordRelationshipDBMock implements IUserPasswordRelationshipPersistence {

	@Override
	public boolean savePassword(User user) {
		if (user.getBannerID().equals("B00798284") && user.getPassword().equals("Test123")) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> getUserPasswords(User user, int historyLimit) {
		List<String> passwords = new ArrayList<String>();
		if (user.getID() == 1 && historyLimit == 1) {
			passwords.add("$2a$10$cuRP/117PvhLjBmz2.0JOezAM4.4WwTt2O192tnDCjGo7EBa9JWeS");
		}
		return passwords;
	}

}
