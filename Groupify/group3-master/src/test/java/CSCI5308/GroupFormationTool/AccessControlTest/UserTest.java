package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Security.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.Security.IUserPasswordRelationshipPersistence;
import CSCI5308.GroupFormationTool.Security.PasswordPolicy;
import CSCI5308.GroupFormationTool.SecurityTest.PasswordPolicyDBMock;
import CSCI5308.GroupFormationTool.SecurityTest.UserPasswordRelationshipDBMock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
@SuppressWarnings("deprecation")
public class UserTest {

	@Test
	public void setIDTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setID(10);
		Assert.isTrue(10 == u.getID());
	}

	@Test
	public void getIDTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setID(10);
		Assert.isTrue(10 == u.getID());
	}

	@Test
	public void setBannerIDTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setBannerID("B00000000");
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}

	@Test
	public void getBannerIDTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setBannerID("B00000000");
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}

	@Test
	public void setFirstNameTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setFirstName("Rob");
		Assert.isTrue(u.getFirstName().equals("Rob"));
	}

	@Test
	public void getFirstNameTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setFirstName("Rob");
		Assert.isTrue(u.getFirstName().equals("Rob"));
	}

	@Test
	public void setLastNameTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setLastName("Hawkey");
		Assert.isTrue(u.getLastName().equals("Hawkey"));
	}

	@Test
	public void getLastNameTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setLastName("Hawkey");
		Assert.isTrue(u.getLastName().equals("Hawkey"));
	}

	@Test
	public void setEmailTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setEmail("th876217@dal.ca");
		Assert.isTrue(u.getEmail().equals("th876217@dal.ca"));
	}

	@Test
	public void getEmailTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setEmail("th876217@dal.ca");
		Assert.isTrue(u.getEmail().equals("th876217@dal.ca"));
	}

	@Test
	public void createUser() {
		IUserPersistence userDB = new UserDBMock();
		User user = new User();
		userDB.createUser(user);
		Assert.isTrue(user.getId() == 0);
		Assert.isTrue(user.getBannerID().equals("B00000000"));
	}

	@Test
	public void isBannerIDValidTest() {
		Assert.isTrue(User.isBannerIDValid("B000000000"));
		Assert.isTrue(!User.isBannerIDValid(null));
		Assert.isTrue(!User.isBannerIDValid(""));
	}

	@Test
	public void isFirstNameValidTest() {
		Assert.isTrue(User.isFirstNameValid("rob"));
		Assert.isTrue(!User.isFirstNameValid(null));
		Assert.isTrue(!User.isFirstNameValid(""));
	}

	@Test
	public void isLastNameValidTest() {
		Assert.isTrue(User.isLastNameValid("hawkey"));
		Assert.isTrue(!User.isLastNameValid(null));
		Assert.isTrue(!User.isLastNameValid(""));
	}

	@Test
	public void isEmailValidTest() {
		Assert.isTrue(User.isEmailValid("th876217@dal.ca"));
		Assert.isTrue(!User.isEmailValid(null));
		Assert.isTrue(!User.isEmailValid(""));
		Assert.isTrue(!User.isEmailValid("@dal.ca"));
		Assert.isTrue(!User.isEmailValid("th876217@"));
	}

	@Test
	public void isPasswordValidTest() {
		IPasswordPolicyPersistence p = new PasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setMaxLength(15);
		policyObj.setMinLength(1);
		policyObj.setMinLowerCase(1);
		policyObj.setMinUpperCase(1);
		policyObj.setSpecialChar("#");
		policyObj.setMinSpecialChar(1);
		policyObj.setPasswordHistory(1);
		assertTrue(User.isPasswordValid("Thanigai%", policyObj));
		assertFalse(User.isPasswordValid("Thanigai#", policyObj));
	}

	@Test
	public void savePasswordTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setBannerID("B00798284");
		u.setPassword("Test123");
		IUserPasswordRelationshipPersistence pobj = new UserPasswordRelationshipDBMock();
		assertTrue(u.savePassword(pobj));
		u.setPassword("Test1234");
		assertFalse(u.savePassword(pobj));
	}

	@Test
	public void isUserPasswordHistoryValidTest() {
		IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
		IUserPasswordRelationshipPersistence pobj = new UserPasswordRelationshipDBMock();
		String upassword = "Thanigai@";
		int historyLimit = 1;
		User u = userAbstractFactoryTest.returnUserInstance();
		u.setID(1);
		assertFalse(u.isUserPasswordHistoryValid(upassword, pobj, historyLimit));
		upassword = "Thanigai#";
		assertTrue(u.isUserPasswordHistoryValid(upassword, pobj, historyLimit));
	}
	
	@Test
	public void ConstructorTests() {
		User u = new User();
		Assert.isTrue(u.getBannerID().isEmpty());
		Assert.isTrue(u.getFirstName().isEmpty());
		Assert.isTrue(u.getLastName().isEmpty());
		Assert.isTrue(u.getEmail().isEmpty());
		IUserPersistence userDBMock = new UserDBMock();
		u = new User(1, userDBMock);
		Assert.isTrue(u.getBannerID().equals("B00000000"));
		u = new User("B00000000", userDBMock);
		Assert.isTrue(u.getBannerID().equals("B00000000"));
	}

}
