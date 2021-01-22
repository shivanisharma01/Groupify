package CSCI5308.GroupFormationTool.Security;

public class SecurityConfig {

	private static SecurityConfig uniqueInstance = null;

	private ISecurityAbstractFactory securityAbstractFactory;
	private IPasswordEncryption passwordEncryption;
	private IPasswordPolicyPersistence passwordPolicyDBObj;
	private IUserPasswordRelationshipPersistence userPasswordRelationshipDB;
	
	private SecurityConfig() {
		
		securityAbstractFactory = new SecurityAbstractFactory();
		passwordEncryption = securityAbstractFactory.returnBCryptPasswordEncryptionInstance();
		passwordPolicyDBObj = securityAbstractFactory.returnPasswordPolicyDB();
		userPasswordRelationshipDB = securityAbstractFactory.returnUserPasswordRelationshipDB();
	}
	
	public static SecurityConfig instance() {
		// Using lazy initialization, this is the one and only place that the System
		// object will be instantiated.
		if (null == uniqueInstance) {
			uniqueInstance = new SecurityConfig();
		}
		return uniqueInstance;
	}
	
	public ISecurityAbstractFactory getSecurityAbstractFactory() {
        return securityAbstractFactory;
    }

    public void setSecurityAbstractFactory(ISecurityAbstractFactory securityAbstractFactory) {
        this.securityAbstractFactory = securityAbstractFactory;
    }
    
    public IUserPasswordRelationshipPersistence getUserPasswordRelationshipDB() {
		return userPasswordRelationshipDB;
	}

	public void setUserPasswordRelationshipDB(IUserPasswordRelationshipPersistence userPasswordRelationshipDB) {
		this.userPasswordRelationshipDB = userPasswordRelationshipDB;
	}

	public IPasswordEncryption getPasswordEncryption() {
		return passwordEncryption;
	}

	public void setPasswordEncryption(IPasswordEncryption passwordEncryption) {
		this.passwordEncryption = passwordEncryption;
	}

	public PasswordPolicy getPasswordPolicyObj() {
		return PasswordPolicy.instance(passwordPolicyDBObj);
	}

	public IPasswordPolicyPersistence getPasswordPolicyDBObj() {
		return passwordPolicyDBObj;
	}
}
