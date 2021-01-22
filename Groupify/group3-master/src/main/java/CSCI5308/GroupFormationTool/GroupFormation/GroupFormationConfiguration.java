package CSCI5308.GroupFormationTool.GroupFormation;

public class GroupFormationConfiguration {

	private static GroupFormationConfiguration uniqueInstance = null;
	private IGroupFormationAbstractFactory groupFormationAbstractFactory;
	private IGroupFormationPolicyPersistence groupFormationPolicyObj;
	private IGroupFormationService groupFormationServiceObj;


	public static GroupFormationConfiguration instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new GroupFormationConfiguration();
		}
		return uniqueInstance;
	}

	private GroupFormationConfiguration() {

		groupFormationAbstractFactory = new GroupFormationAbstractFactory();
		groupFormationPolicyObj = new GroupFormationPolicyDB();
		groupFormationServiceObj = new GroupFormationService();

	}
	
	public IGroupFormationAbstractFactory getGroupFormationAbstractFactory() {
        return groupFormationAbstractFactory;
    }

    public void setGroupFormationAbstractFactory(IGroupFormationAbstractFactory groupFormationAbstractFactory) {
        this.groupFormationAbstractFactory = groupFormationAbstractFactory;
    }
    
    public IGroupFormationService getGroupFormationServiceObj() {
		return groupFormationServiceObj;
	}

	public void setGroupFormationServiceObj(IGroupFormationService groupFormationServiceObj) {
		this.groupFormationServiceObj = groupFormationServiceObj;
	}

	public IGroupFormationPolicyPersistence getGroupFormationPolicyObj() {
		return groupFormationPolicyObj;
	}

	public void setGroupFormationPolicyObj(IGroupFormationPolicyPersistence groupFormationPolicyObj) {
		this.groupFormationPolicyObj = groupFormationPolicyObj;
	}
}
