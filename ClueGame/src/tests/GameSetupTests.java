package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import clueGame.Board;

public class GameSetupTests {
	
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// singleton instance
		board = Board.getInstance();
		// set filenames
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize both config files
		board.initialize();
	}
	
	
	@Test
	public void TestPlayers() {
		// Test 1 human player and 5 computer
		// Test 6 players total
		assertEquals("Test Name", board.returnPlayer("Test Name").name);
		assertEquals(6, board.getPlayers().size());
		
	}
	
	@Test
	public static void TestDeck() {
		// Test Deck size
		// Test Weapons, people and rooms
		// Test All Cards are Dealt
		
		assertEquals(21, board.getDeck().size());
		
	}
	@Test
	public static void TestBoard() {
		// Test Players having cards dealt to them properly
		
	}
	
	
}
	