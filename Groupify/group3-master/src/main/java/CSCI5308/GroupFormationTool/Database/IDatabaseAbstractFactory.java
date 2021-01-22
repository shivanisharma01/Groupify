package CSCI5308.GroupFormationTool.Database;

public interface IDatabaseAbstractFactory {
	CallStoredProcedure returnCallStoredProcedureInstance(String storedProcedureName);
}
