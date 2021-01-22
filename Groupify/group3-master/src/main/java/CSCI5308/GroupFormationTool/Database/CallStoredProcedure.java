package CSCI5308.GroupFormationTool.Database;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CallStoredProcedure {
	private String storedProcedureName;
	private Connection connection;
	private CallableStatement statement;
	Logger logger = LoggerFactory.getLogger(CallStoredProcedure.class);

	public CallStoredProcedure(String storedProcedureName) throws SQLException {
		this.storedProcedureName = storedProcedureName;
		connection = null;
		statement = null;
		openConnection();
		createStatement();
	}

	private void createStatement() throws SQLException {
		statement = connection.prepareCall("{call " + storedProcedureName + "}");
	}

	private void openConnection() throws SQLException {
		connection = ConnectionManager.instance().getDBConnection();
	}

	public void cleanup() {
		try {
			if (null != statement) {
				statement.close();
			}
			if (null != connection) {
				if (!connection.isClosed()) {
					logger.info("Database connection is closed");
					connection.close();
				}
			}
		} catch (Exception e) {
			logger.error("Error Connecting Database",e);
		}
	}

	public void setParameter(int paramIndex, String value) throws SQLException {
		statement.setString(paramIndex, value);
	}

	public void registerOutputParameterString(int paramIndex) throws SQLException {
		statement.registerOutParameter(paramIndex, java.sql.Types.VARCHAR);
	}

	public void setParameter(int paramIndex, long value) throws SQLException {
		statement.setLong(paramIndex, value);
	}

	public void registerOutputParameterLong(int paramIndex) throws SQLException {
		statement.registerOutParameter(paramIndex, java.sql.Types.BIGINT);
	}
	
	public long getLong(int paramIndex) throws SQLException {
		return statement.getLong(paramIndex);
	}

	public void registerOutputParameterInt(int paramIndex) throws SQLException {
		statement.registerOutParameter(paramIndex, java.sql.Types.INTEGER);
	}

	public int getInteger(int paramIndex) throws SQLException {
		return statement.getInt(paramIndex);
	}
	
	public void registerOutputParameterBoolean(int paramIndex) throws SQLException {
		statement.registerOutParameter(paramIndex, java.sql.Types.BOOLEAN);
	}
	
	public boolean getBoolean(int paramIndex) throws SQLException {
		return statement.getBoolean(paramIndex);
	}

	public ResultSet executeWithResults() throws SQLException {
		if (statement.execute()) {
			return statement.getResultSet();
		}
		return null;
	}

	public void execute() throws SQLException {
		statement.execute();
	}
}
