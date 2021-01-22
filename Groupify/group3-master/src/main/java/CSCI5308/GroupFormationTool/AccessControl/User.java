package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.ISecurityAbstractFactory;
import CSCI5308.GroupFormationTool.Security.IUserPasswordRelationship;
import CSCI5308.GroupFormationTool.Security.IUserPasswordRelationshipPersistence;
import CSCI5308.GroupFormationTool.Security.PasswordPolicy;
import CSCI5308.GroupFormationTool.Security.SecurityConfig;

public class User {

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

	private long id;
	private String password;
	private String bannerID;
	private String firstName;
	private String lastName;
	private String email;
	private IUserPasswordRelationship userPasswordObj;

	public User() {
		setDefaults();
	}

	public User(long id, IUserPersistence persistence) {
		setDefaults();
		persistence.loadUserByID(id, this);
	}

	public User(String bannerID, IUserPersistence persistence) {
		setDefaults();
		persistence.loadUserByBannerID(bannerID, this);
	}

	public void setDefaults() {
		ISecurityAbstractFactory securityAbstractFactory = SecurityConfig.instance().getSecurityAbstractFactory();
		id = -1;
		password = "";
		bannerID = "";
		firstName = "";
		lastName = "";
		email = "";
		userPasswordObj = securityAbstractFactory.returnUserPasswordRelationship();
	}

	public void setID(long id) {
		this.id = id;
	}

	public long getID() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setBannerID(String bannerID) {
		this.bannerID = bannerID;
	}

	public String getBannerID() {
		return bannerID;
	}

	public String getBanner() {
		return bannerID;
	}

	public void setFirstName(String name) {
		firstName = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String name) {
		lastName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public static boolean isPasswordValid(String password, PasswordPolicy uniqueInstance) {

		Pattern pattern;
		Matcher matcher;

		int upperCaseCount = 0;
		int lowerCaseCount = 0;
		int specialCharCount = 0;

		pattern = Pattern.compile("[A-Z]");
		matcher = pattern.matcher(password);
		while (matcher.find()) {
			upperCaseCount++;
		}

		pattern = Pattern.compile("[a-z]");
		matcher = pattern.matcher(password);
		while (matcher.find()) {
			lowerCaseCount++;
		}

		pattern = Pattern.compile("[^A-Za-z0-9]");
		matcher = pattern.matcher(password);
		while (matcher.find()) {
			specialCharCount++;
		}

		if (-1 != uniqueInstance.getMinLength() && password.length() < uniqueInstance.getMinLength()) {
			return false;
		}
		if (-1 != uniqueInstance.getMaxLength() && password.length() > uniqueInstance.getMaxLength()) {
			return false;
		}
		if (-1 != uniqueInstance.getMinUpperCase() && upperCaseCount < uniqueInstance.getMinUpperCase()) {
			return false;
		}
		if (-1 != uniqueInstance.getMinLowerCase() && lowerCaseCount < uniqueInstance.getMinLowerCase()) {
			return false;
		}
		if (-1 != uniqueInstance.getMinSpecialChar() && specialCharCount < uniqueInstance.getMinSpecialChar()) {
			return false;
		}
		if (uniqueInstance.getSpecialChar() != null) {
			for (int i = 0; i < uniqueInstance.getSpecialChar().length(); i++) {
				if (password.contains(String.valueOf(uniqueInstance.getSpecialChar().charAt(i)))) {
					return false;
				}

			}

		}
		return true;

	}

	public boolean isUserPasswordHistoryValid(String upassword, IUserPasswordRelationshipPersistence userPasswordDBObj,
			int historyLimit) {
		List<String> passwords = userPasswordObj.getUserPasswords(this, userPasswordDBObj, historyLimit);
		IPasswordEncryption passwordEncryption = SecurityConfig.instance().getPasswordEncryption();
		for (String password : passwords) {
			if (passwordEncryption.matches(upassword, password)) {
				return false;
			}
		}
		return true;
	}

	public boolean isValidUser() {
		return id != -1;
	}

	public boolean savePassword(IUserPasswordRelationshipPersistence userPasswordRelationShipDB) {
		return userPasswordObj.savePassword(this, userPasswordRelationShipDB);
	}

	public boolean createUser(IUserPersistence userDB, IPasswordEncryption passwordEncryption,
			UserNotification notification) {
		String rawPassword = randomPassword();
		
		this.password = passwordEncryption.encryptPassword(this.password);
		boolean success = userDB.createUser(this);
		if (success && (null != notification)) {
			System.out.println("password is " + rawPassword);
			notification.sendUserLoginCredentials(this, rawPassword);
		}
		return success;
	}

	public boolean updateUser(IUserPersistence userDB) {
		return userDB.updateUser(this);
	}

	private static boolean isStringNullOrEmpty(String s) {
		if (null == s) {
			return true;
		}
		return s.isEmpty();
	}

	public static boolean isBannerIDValid(String bannerID) {
		return !isStringNullOrEmpty(bannerID);
	}

	public static boolean isFirstNameValid(String name) {
		return !isStringNullOrEmpty(name);
	}

	public static boolean isLastNameValid(String name) {
		return !isStringNullOrEmpty(name);
	}

	public static boolean isEmailValid(String email) {
		if (isStringNullOrEmpty(email)) {
			return false;
		}

		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public String randomPassword() {
		IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
		int length = 8;
		String symbol = "-/.^&*_!@%=+>)";
		String cap_letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String small_letter = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";

		String finalString = cap_letter + small_letter + numbers + symbol;

		Random random = userAbstractFactory.returnRandomInstance();

		char[] password = userAbstractFactory.returnCharacterInstance(length);

		for (int i = 0; i < length; i++) {
			password[i] = finalString.charAt(random.nextInt(finalString.length()));

		}

		return password.toString();
	}

	public void loadUserByBannerID(IUserPersistence userDB) {
		userDB.loadUserByBannerID(this.getBanner(),this);
	}

}