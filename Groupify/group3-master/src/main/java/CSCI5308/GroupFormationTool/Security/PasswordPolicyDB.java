package CSCI5308.GroupFormationTool.Security;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Database.DatabaseConfig;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;

public class PasswordPolicyDB implements IPasswordPolicyPersistence {

	Logger logger = LoggerFactory.getLogger(UserPasswordRelationshipDB.class);
	@Override
	public void loadPasswordPolicy(PasswordPolicy passwordPolicyObj) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spLoadPasswordPolicy()");
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					int minLength = results.getInt("MinLength");
					int maxLength = results.getInt("MaxLength");
					int minUpperCase = results.getInt("MinUpperCase");
					int minLowerCase = results.getInt("MinLowerCase");
					int minSpecialChar = results.getInt("MinSpecialChar");
					String specialChar = results.getString("InvalidChar");
					int passwordHistory = results.getInt("PasswordHistory");
					System.out.println("Min special" + minSpecialChar);
					passwordPolicyObj.setMinLength(minLength);
					passwordPolicyObj.setMaxLength(maxLength);
					passwordPolicyObj.setMinUpperCase(minUpperCase);
					passwordPolicyObj.setMinLowerCase(minLowerCase);
					passwordPolicyObj.setMinSpecialChar(minSpecialChar);
					passwordPolicyObj.setSpecialChar(specialChar);
					passwordPolicyObj.setPasswordHistory(passwordHistory);
				}
			}
		} catch (SQLException e) {
			
			logger.error("Error Connecting Database",e);
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}

	}
}
