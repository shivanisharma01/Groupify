package CSCI5308.GroupFormationTool.Security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Database.DatabaseConfig;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;

public class UserPasswordRelationshipDB implements IUserPasswordRelationshipPersistence {
	Logger logger = LoggerFactory.getLogger(UserPasswordRelationshipDB.class);

	@Override
	public boolean savePassword(User user) {
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		CallStoredProcedure proc = null;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spSavePassword(?)");
			proc.setParameter(1, user.getBannerID());
			proc.execute();
		} catch (SQLException e) {
			logger.error("Error Connecting Database",e);
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return true;
	}

	@Override
	public List<String> getUserPasswords(User user, int historyLimit) {
		ISecurityAbstractFactory securityAbstractFactory = SecurityConfig.instance().getSecurityAbstractFactory();
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();

		List<String> passwords = securityAbstractFactory.returnStringListInstance();
		CallStoredProcedure proc = null;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spLoadUserPasswords(?,?)");
			proc.setParameter(1, user.getID());
			proc.setParameter(2, historyLimit);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					passwords.add(results.getString("password"));
				}
			}
		} catch (SQLException e) {
			logger.error("Error Connecting Database",e);
			return null;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		return passwords;
	}

}
