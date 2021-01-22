package CSCI5308.GroupFormationTool.GroupFormationTest;

public class GroupFormationConfigurationTest {

	private static GroupFormationConfigurationTest uniqueInstance = null;
	private IGroupFormationAbstractFactoryTest groupFormationAbstractFactory;
	
	private GroupFormationConfigurationTest() {
		groupFormationAbstractFactory = new GroupFormationAbstractFactoryTest();
    }
	
	public static GroupFormationConfigurationTest instance() {

        if (uniqueInstance == null) {
            return new GroupFormationConfigurationTest();
        }
        return uniqueInstance;
    }
	
	public IGroupFormationAbstractFactoryTest getGroupFormationAbstractFactory() {
        return groupFormationAbstractFactory;
    }
}
