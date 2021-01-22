package CSCI5308.GroupFormationTool.SecurityTest;

import CSCI5308.GroupFormationTool.Security.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.Security.PasswordPolicy;

public class PasswordPolicyDBMock implements IPasswordPolicyPersistence {

	@Override
	public void loadPasswordPolicy(PasswordPolicy passwordPolicyObj) {
		passwordPolicyObj.setMaxLength(15);
		passwordPolicyObj.setMinLength(1);
		passwordPolicyObj.setMinLowerCase(1);
		passwordPolicyObj.setMinUpperCase(1);
		passwordPolicyObj.setSpecialChar("#");
		passwordPolicyObj.setMinSpecialChar(1);
		passwordPolicyObj.setPasswordHistory(1);
	}

}
