package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;

class FileInitTests {
	
	public static final int NUM_ROWS = 27;
	public static final int NUM_COLUMNS = 33;
	
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
	void testRoomLabels() {
		assertEquals("Marquez", board.getRoom('M').getName() );
		assertEquals("Unused", board.getRoom('X').getName() );
		assertEquals("Elm", board.getRoom('E').getName() );
		assertEquals("Venture", board.getRoom('V').getName() );
		assertEquals("CoorsTek", board.getRoom('C').getName() );
	}


}
