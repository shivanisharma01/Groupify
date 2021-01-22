package CSCI5308.GroupFormationTool.SecurityTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.Security.IPasswordPolicyPersistence;
import CSCI5308.GroupFormationTool.Security.PasswordPolicy;

public class PasswordPolicyTest {

	@Test
	public void ConstructorTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setSpecialChar("#");
		policyObj.setMinSpecialChar(1);
		policyObj.setMinSpecialChar(1);
		policyObj.setMinUpperCase(1);
		policyObj.setMinLowerCase(1);
		policyObj.setMinLength(1);
		policyObj.setMaxLength(15);
		assertTrue(policyObj.getMaxLength() == 15);
		assertTrue(policyObj.getMinLength() == 1);
		assertTrue(policyObj.getMinLowerCase() == 1);
		assertTrue(policyObj.getMinUpperCase() == 1);
		assertTrue(policyObj.getMinSpecialChar() == 1);
		assertTrue(policyObj.getSpecialChar().equals("#"));
	}

	@Test
	public void getSpecialCharTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setSpecialChar("#");
		assertTrue(policyObj.getSpecialChar().equals("#"));
	}

	@Test
	public void getMinSpecialCharTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setMinSpecialChar(1);
		assertTrue(policyObj.getMinSpecialChar() == 1);
	}

	@Test
	public void getMinUpperCaseTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setMinUpperCase(2);
		assertTrue(policyObj.getMinUpperCase() == 2);
	}

	@Test
	public void getMinLowerCaseTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setMinLowerCase(2);
		assertTrue(policyObj.getMinLowerCase() == 2);
	}

	@Test
	public void getMinLengthTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setMinLength(1);
		assertTrue(policyObj.getMinLength() == 1);
	}

	@Test
	public void getMaxLengthTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setMaxLength(20);
		assertTrue(policyObj.getMaxLength() == 20);
	}

	@Test
	public void setSpecialCharTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();

		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setSpecialChar("$#");
		assertTrue(policyObj.getSpecialChar().equals("$#"));
	}

	@Test
	public void setMinSpecialCharTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setMinSpecialChar(1);
		assertTrue(policyObj.getMinSpecialChar() == 1);
	}

	@Test
	public void setMinUpperCaseTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setMinUpperCase(2);
		assertTrue(policyObj.getMinUpperCase() == 2);
	}

	@Test
	public void setMinLowerCaseTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setMinLowerCase(2);
		assertTrue(policyObj.getMinLowerCase() == 2);
	}

	@Test
	public void setMinLengthTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setMinLength(2);
		assertTrue(policyObj.getMinLength() == 2);
	}

	@Test
	public void setMaxLengthTest() {
		ISecurityAbstractFactoryTest securityAbstractFactoryTest = SecurityConfigTest.instance().getSecurityAbstractFactory();
		IPasswordPolicyPersistence p = securityAbstractFactoryTest.returnPasswordPolicyDBMock();
		PasswordPolicy policyObj = PasswordPolicy.instance(p);
		policyObj.setMaxLength(15);
		assertTrue(policyObj.getMaxLength() == 15);
	}

	@Test
	public void passwordCriteriaTest() {
		String policy = PasswordPolicy.passwordCriteria();
		assertNotNull(policy);
	}

}
