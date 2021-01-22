package CSCI5308.GroupFormationTool.SecurityTest;

import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.Security.IUserPasswordRelationshipPersistence;

public class SecurityAbstractFactoryTest implements ISecurityAbstractFactoryTest {

	@Override
	public IPasswordPolicyPersistence returnPasswordPolicyDBMock() {
		return new PasswordPolicyDBMock();
	}

	@Override
	public IUserPasswordRelationshipPersistence returnUserPasswordRelationshipDBMock() {
		return new UserPasswordRelationshipDBMock();
	}

	@Override
	public IPasswordEncryption returnPasswordEncryptionMock() {
		return new PasswordEncryptionMock();
	}

}
