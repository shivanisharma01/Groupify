package CSCI5308.GroupFormationTool.GroupFormationTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.CoursesTest.CourseConfigurationTest;
import CSCI5308.GroupFormationTool.CoursesTest.ICourseAbstractFactoryTest;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationPolicy;

public class GroupFormationPolicyTest {
	
	@Test
	public void getGroupSize() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setGroupSize(1);
		assertTrue(groupPolicyObj.getGroupSize() == 1);		
	}

	@Test
	public void setGroupSize() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setGroupSize(1);
		assertTrue(groupPolicyObj.getGroupSize() == 1);
	}

	@Test
	public void getQuestionID() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setQuestionID(1);
		assertTrue(groupPolicyObj.getQuestionID() == 1);
	}

	@Test
	public void setQuestionID() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setQuestionID(1);
		assertTrue(groupPolicyObj.getQuestionID() == 1);
	}

	@Test
	public void getQuestionType() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setQuestionType(1);
		assertTrue(groupPolicyObj.getQuestionType() == 1);
	}

	@Test
	public void setQuestionType() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setQuestionType(1);
		assertTrue(groupPolicyObj.getQuestionType() == 1);
	}

	@Test
	public void getCourseID() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setCourseID((long) 1);
		assertTrue(groupPolicyObj.getCourseID() == (long) 1);
	}

	@Test
	public void setCourseID() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setCourseID((long) 1);
		assertTrue(groupPolicyObj.getCourseID() == (long) 1);
	}

	@Test
	public void getGroupBy() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setGroupBy("SIMILAR");
		assertTrue(groupPolicyObj.getGroupBy().equals("SIMILAR"));
	}

	@Test
	public void setGroupBy() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setGroupBy("SIMILAR");
		assertTrue(groupPolicyObj.getGroupBy().equals("SIMILAR"));
	}

	@Test
	public void getCompareValue() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setCompareValue(1);
		assertTrue(groupPolicyObj.getCompareValue() == 1);
	}

	@Test
	public void setCompareValue() {
		IGroupFormationAbstractFactoryTest groupFormationAbstractFactoryTest = GroupFormationConfigurationTest.instance().getGroupFormationAbstractFactory();
		GroupFormationPolicy groupPolicyObj = groupFormationAbstractFactoryTest.returnGroupFormationPolicyInstance();
		groupPolicyObj.setCompareValue(1);
		assertTrue(groupPolicyObj.getCompareValue() == 1);
	}

}
