package CSCI5308.GroupFormationTool.Security;

public class PasswordPolicy {

	private static PasswordPolicy uniqueInstance = null;

	private int minLength;
	private int maxLength;
	private int minUpperCase;
	private int minLowerCase;
	private int minSpecialChar;
	private String specialChar;
	private int passwordHistory;
	private boolean isValid;

	public static PasswordPolicy instance(IPasswordPolicyPersistence passwordPersistenceObj) {
		if (null == uniqueInstance) {
			ISecurityAbstractFactory securityAbstractFactory = SecurityConfig.instance().getSecurityAbstractFactory();
			uniqueInstance = securityAbstractFactory.returnPasswordPolicyInstance(passwordPersistenceObj);
		}
		return uniqueInstance;
	}

	public int getMinLength() {
		return minLength;
	}

	public PasswordPolicy(IPasswordPolicyPersistence passwordPersistenceObj) {
		passwordPersistenceObj.loadPasswordPolicy(this);
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMinUpperCase() {
		return minUpperCase;
	}

	public void setMinUpperCase(int minUpperCase) {
		this.minUpperCase = minUpperCase;
	}

	public int getMinLowerCase() {
		return minLowerCase;
	}

	public void setMinLowerCase(int minLowerCase) {
		this.minLowerCase = minLowerCase;
	}

	public int getMinSpecialChar() {
		return minSpecialChar;
	}

	public void setMinSpecialChar(int minSpecialChar) {
		this.minSpecialChar = minSpecialChar;
	}

	public String getSpecialChar() {
		return specialChar;
	}

	public void setSpecialChar(String specialChar) {
		this.specialChar = specialChar;
	}

	public int getPasswordHistory() {
		return passwordHistory;
	}

	public void setPasswordHistory(int passwordHistory) {
		this.passwordHistory = passwordHistory;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public static String passwordCriteria() {
		String policy = "Password should meet the following criteria <br>";
		int count = 0;
		if (uniqueInstance.minLength != -1) {
			count++;
			policy += count + ". Minimum Length of the password should be " + uniqueInstance.minLength + "<br>";
		}
		if (uniqueInstance.maxLength != -1) {
			count++;
			policy += count + ". Maximum Length of the password should be " + uniqueInstance.maxLength + "<br>";
		}
		if (uniqueInstance.minUpperCase != -1) {
			count++;
			policy += count + ". " + uniqueInstance.minUpperCase
					+ " Uppercase alphabets should be in the password <br>";
		}
		if (uniqueInstance.minLowerCase != -1) {
			count++;
			policy += count + ". " + uniqueInstance.minLowerCase
					+ " Lowercase alphabets should be in the password <br>";
		}
		if (uniqueInstance.minSpecialChar != -1) {
			count++;
			policy += count + ". " + uniqueInstance.minSpecialChar
					+ " Special characters should be in the password <br>";
		}
		if (uniqueInstance.specialChar != null) {
			count++;
			policy += count + ". " + uniqueInstance.specialChar + " are invalid characters <br>";
		}

		return policy;
	}

}
