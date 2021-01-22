package CSCI5308.GroupFormationTool.AlgorithmTest;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import CSCI5308.GroupFormationTool.Algorithm.GroupFormationAlgorithm;
import CSCI5308.GroupFormationTool.Algorithm.IGroupFormationAlgorithm;


public class GroupFormationAlgorithmTest{
	@Test
	public void generateGroup() {
		IGroupFormationAlgorithm groupFormationAlgorithm=new GroupFormationAlgorithm();
		assertNull(groupFormationAlgorithm.generateGroup());
	}

}