package tests;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import experiment.TestBoard;
import experiment.TestBoardCell;

public class BoardTestsExp {
	TestBoard board;
	
	@BeforeEach
	public void setUp() {
		//board should create adj list
		board = new TestBoard();
	}
	
	/*
	 * test adjacencies for deveral different locations
	 * Test corners, centers, and edges
	 */
	
	@Test
	public void testAdjacency() {
		//Top left (0,0)
		TestBoardCell cell = board.getCell(0,0);
		Set<TestBoardCell> testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(1,0)));
		Assert.assertTrue(testList.contains(board.getCell(0,1)));
		Assert.assertEquals(2,  testList.size());
		
		//Top Right (0,3)
		cell = board.getCell(0,3);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(1,3)));
		Assert.assertTrue(testList.contains(board.getCell(0,2)));
		Assert.assertEquals(2,  testList.size());
		
		//Bottom Left (3,0)
		cell = board.getCell(3,0);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(3,1)));
		Assert.assertTrue(testList.contains(board.getCell(2,0)));
		Assert.assertEquals(2,  testList.size());
		
		//Bottom Right (3,3)
		cell = board.getCell(3,3);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(3,2)));
		Assert.assertTrue(testList.contains(board.getCell(2,3)));
		Assert.assertEquals(2,  testList.size());
		
		//Left Edge (1,0)
		cell = board.getCell(1,0);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(0,0)));
		Assert.assertTrue(testList.contains(board.getCell(1,1)));
		Assert.assertTrue(testList.contains(board.getCell(2,0)));
		Assert.assertEquals(3,  testList.size());
		
		//Right Edge (1,3)
		cell = board.getCell(1,3);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(2,3)));
		Assert.assertTrue(testList.contains(board.getCell(0,3)));
		Assert.assertTrue(testList.contains(board.getCell(2,1)));
		Assert.assertEquals(3,  testList.size());
		
		//Middle(1,2)
		cell = board.getCell(1,2);
		testList = cell.getAdjList();
		Assert.assertTrue(testList.contains(board.getCell(0,2)));
		Assert.assertTrue(testList.contains(board.getCell(2,2)));
		Assert.assertTrue(testList.contains(board.getCell(1,1)));
		Assert.assertTrue(testList.contains(board.getCell(1,3)));
		Assert.assertEquals(4,  testList.size());
		
	}
	
	/*
	 * Test targets with several rolls and start locations
	 */
	@Test
	public void testTargetsNormal() {
		TestBoardCell cell = board.getCell(0,0);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(6,  targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3,0)));
		Assert.assertTrue(targets.contains(board.getCell(2,1)));
		Assert.assertTrue(targets.contains(board.getCell(0,1)));
		Assert.assertTrue(targets.contains(board.getCell(1,2)));
		Assert.assertTrue(targets.contains(board.getCell(0,3)));
		Assert.assertTrue(targets.contains(board.getCell(1,0)));
	}
	
	@Test
	public void testTargetMixed() {
		//set up occupied cells
		board.getCell(0,2).setOccupied(true);
		board.getCell(1,2).setRoom(true);
		TestBoardCell cell = board.getCell(0,3);
		board.calcTargets(cell, 3);
		Set<TestBoardCell> targets = board.getTargets();
		Assert.assertEquals(3,  targets.size());
		Assert.assertTrue(targets.contains(board.getCell(1,2)));
		Assert.assertTrue(targets.contains(board.getCell(2,2)));
		Assert.assertTrue(targets.contains(board.getCell(3,3)));
	}
	
	@Test
	public void testCalcTarget() {
		Set<TestBoardCell> testArr = new HashSet<>();
		testArr.add(new TestBoardCell(0, 4));
		testArr.add(new TestBoardCell(0, 2));
		testArr.add(new TestBoardCell(1, 3));
		testArr.add(new TestBoardCell(-1, 3));
		TestBoardCell cell = board.getCell(0, 3);
		board.calcTargets(cell, 1);
		Set<TestBoardCell> targetArr = board.getTargets();	
		Assert.assertEquals(targetArr, testArr);
		
	}
}
