package tests;

import static org.junit.Assert.*;

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
	public static void TestBoard() {
		
	}
	}
	
}
