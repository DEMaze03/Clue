package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;

class BoardAdjTargetTest {

private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();
	}
	
	//Test room and door adjacencies
	//Light orange on the planning spreadsheet
	@Test
	public void testAdjacencies() {
		//test a couple of different rooms.
				// Elm, which has 2 doors and a secret passage
				Set<BoardCell> testList = board.getAdjList(23, 2);
				assertEquals(3, testList.size());
				assertTrue(testList.contains(board.getCell(6, 30)));
				assertTrue(testList.contains(board.getCell(21, 2)));
				assertTrue(testList.contains(board.getCell(23, 6)));
				
				// Coorstek, 2 doors
				testList = board.getAdjList(24, 14);
				assertEquals(2, testList.size());
				assertTrue(testList.contains(board.getCell(23, 19)));
				assertTrue(testList.contains(board.getCell(23, 8)));
				
				// Doorway to Labriola, room center, and adjacent walkways
				testList = board.getAdjList(2, 7);
				assertEquals(3, testList.size());
				assertTrue(testList.contains(board.getCell(25, 30)));
				assertTrue(testList.contains(board.getCell(3, 7)));
				assertTrue(testList.contains(board.getCell(2, 8)));
	}
	
	// Test a variety of walkway scenarios
		// These tests are Dark Orange on the planning spreadsheet
		@Test
		public void testAdjacencyWalkways() {
			//walkway at the bottom of the board, sandwiched between two X tiles, only one adjacency
			Set<BoardCell> testList = board.getAdjList(27, 23);
			assertEquals(1, testList.size());
			assertTrue(testList.contains(board.getCell(26, 23)));
			
			//Walkway in the middle of the board, 4 adjacencies
			testList = board.getAdjList(8, 24);
			assertEquals(4, testList.size());
			assertTrue(testList.contains(board.getCell(8, 23)));
			assertTrue(testList.contains(board.getCell(8, 25)));
			assertTrue(testList.contains(board.getCell(7, 24)));
			assertTrue(testList.contains(board.getCell(9, 24)));
			
		}
		
		// Tests out of room center, 1, 3 and 4
		// These are LIGHT BLUE on the planning spreadsheet
		@Test
		public void testTargetsInMarquez() {
			// test a roll of 1
			board.calcTargets(board.getCell(25, 30), 1);
			Set<BoardCell> targets= board.getTargets();
			assertEquals(2, targets.size());
			assertTrue(targets.contains(board.getCell(2, 2)));
			assertTrue(targets.contains(board.getCell(22, 31)));	
			
			// test a roll of 3
			board.calcTargets(board.getCell(25, 30), 3);
			targets= board.getTargets();
			assertEquals(7, targets.size());
			assertTrue(targets.contains(board.getCell(22, 29)));
			assertTrue(targets.contains(board.getCell(2, 8)));	
			assertTrue(targets.contains(board.getCell(3, 7)));
			assertTrue(targets.contains(board.getCell(21, 30)));	
		}
		@Test
		public void testTargetsInDoorway() {
			// test a roll of 1
			board.calcTargets(board.getCell(19, 2), 1);
			Set<BoardCell> targets= board.getTargets();
			assertEquals(4, targets.size());
			assertTrue(targets.contains(board.getCell(19, 1)));
			assertTrue(targets.contains(board.getCell(19, 3)));	
			assertTrue(targets.contains(board.getCell(23, 2)));
			
			// test a roll of 3
			board.calcTargets(board.getCell(19, 2), 3);
			targets= board.getTargets();
			assertEquals(7, targets.size());
			assertTrue(targets.contains(board.getCell(5, 28)));
			assertTrue(targets.contains(board.getCell(18, 4)));	
			assertTrue(targets.contains(board.getCell(17, 1)));
			assertTrue(targets.contains(board.getCell(19, 5)));	
		}
		
		@Test
		// test to make sure occupied locations do not cause problems
		//Red on the planning spreadsheet
		public void testTargetsOccupied() {
			// test a roll of 1 blocked on 2 sides
			board.getCell(24, 6).setOccupied(true);
			board.getCell(24, 8).setOccupied(true);
			board.calcTargets(board.getCell(24, 7), 1);
			board.getCell(24, 6).setOccupied(false);
			board.getCell(24, 8).setOccupied(false);
			Set<BoardCell> targets = board.getTargets();
			assertEquals(2, targets.size());
			assertTrue(targets.contains(board.getCell(23, 7)));
			assertTrue(targets.contains(board.getCell(25, 7)));
			assertFalse( targets.contains( board.getCell(24, 6))) ;
			assertFalse( targets.contains( board.getCell(24, 8))) ;
		
			// we want to make sure we can get into a room, even if flagged as occupied
			board.getCell(6, 30).setOccupied(true);
			board.calcTargets(board.getCell(5, 28), 1);
			board.getCell(6, 30).setOccupied(false);
			targets= board.getTargets();
			assertEquals(4, targets.size());
			assertTrue(targets.contains(board.getCell(6, 30)));	
			assertTrue(targets.contains(board.getCell(4, 28)));	
			assertTrue(targets.contains(board.getCell(6, 28)));	
			
			// check leaving a room with a blocked doorway
			board.getCell(22, 31).setOccupied(true);
			board.calcTargets(board.getCell(12, 20), 1);
			board.getCell(22, 31).setOccupied(false);
			targets= board.getTargets();
			assertEquals(1, targets.size());
			assertTrue(targets.contains(board.getCell(2, 2)));

		}

}
