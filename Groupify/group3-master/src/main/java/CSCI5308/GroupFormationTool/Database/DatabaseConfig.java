package CSCI5308.GroupFormationTool.Database;

public class DatabaseConfig {

	private static DatabaseConfig uniqueInstance = null;
	private IDatabaseAbstractFactory databaseAbstractFactory;
	private IDatabaseConfiguration databaseConfiguration;
	
	private DatabaseConfig() {
		databaseAbstractFactory = new DatabaseAbstractFactory();
		databaseConfiguration = new DefaultDatabaseConfiguration();
	}
	
	public static DatabaseConfig instance() {
		// Using lazy initialization, this is the one and only place that the System
		// object will be instantiated.
		if (null == uniqueInstance) {
			uniqueInstance = new DatabaseConfig();
		}
		return uniqueInstance;
	}
	
	public IDatabaseAbstractFactory getDatabaseAbstractFactory() {
        return databaseAbstractFactory;
    }

    public void setDatabaseAbstractFactory(IDatabaseAbstractFactory databaseAbstractFactory) {
        this.databaseAbstractFactory = databaseAbstractFactory;
    }
    
    public IDatabaseConfiguration getDatabaseConfiguration() {
		return databaseConfiguration;
	}

	public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
	}
}
