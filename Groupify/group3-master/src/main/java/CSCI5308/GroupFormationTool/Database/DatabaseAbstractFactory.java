package CSCI5308.GroupFormationTool.Database;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseAbstractFactory implements IDatabaseAbstractFactory {

	Logger logger = LoggerFactory.getLogger(DatabaseAbstractFactory.class);

	@Override
	public CallStoredProcedure returnCallStoredProcedureInstance(String storedProcedureName) {
		CallStoredProcedure procedure = null;
		try {
			procedure = new CallStoredProcedure(storedProcedureName);
		} catch (SQLException e) {
			logger.error("Error Connecting Database",e);
			
		}
		return procedure;
	}

}
