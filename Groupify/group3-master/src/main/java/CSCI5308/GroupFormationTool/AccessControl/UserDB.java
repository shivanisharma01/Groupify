package CSCI5308.GroupFormationTool.AccessControl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Database.DatabaseConfig;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;

public class UserDB implements IUserPersistence {

	Logger logger = LoggerFactory.getLogger(UserDB.class);

	public void loadUserByID(long id, User user) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spLoadUser(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String password = results.getString(3);
					String firstName = results.getString(4);
					String lastName = results.getString(5);
					String email = results.getString(6);
					user.setID(userID);
					user.setBannerID(bannerID);
					user.setPassword(password);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setEmail(email);
				}
				logger.info("Loaded user deatils for user "+user.getFirstName()+" from database");
			}
		} catch (SQLException e) {
			logger.error("Error loading user deatils for user id "+id+" from database");
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
	}

	public void loadUserByBannerID(String bannerID, User user) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();
		long userID = -1;
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spFindUserByBannerID(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			if (null != results) {
				while (results.next()) {
					userID = results.getLong(1);
				}
				logger.info("Loaded user deatils for user "+bannerID+" from database");
			}
		} catch (SQLException e) {
			logger.error("Error loading user deatils using Banner ID: "+bannerID+" from database");
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		// If we found the ID load the full details.
		if (userID > -1) {
			loadUserByID(userID, user);
		}
	}

	public boolean createUser(User user) {
		CallStoredProcedure proc = null;
		IDatabaseAbstractFactory databaseAbstractFactory = DatabaseConfig.instance().getDatabaseAbstractFactory();
		try {
			proc = databaseAbstractFactory.returnCallStoredProcedureInstance("spCreateUser(?, ?, ?, ?, ?, ?)");
			proc.setParameter(1, user.getBannerID());
			proc.setParameter(2, user.getPassword());
			proc.setParameter(3, user.getFirstName());
			proc.setParameter(4, user.getLastName());
			proc.setParameter(5, user.getEmail());
			proc.registerOutputParameterLong(6);
			proc.execute();
		} catch (SQLException e) {
			logger.error("Error creating user account for "+user.getBannerID());
			return false;
		} finally {
			if (null != proc) {
				proc.cleanup();
			}
		}
		logger.info("Created user account for "+user.getBannerID());
		return true;
	}

	public boolean updateUser(User user) {
		return false;
	}
}
