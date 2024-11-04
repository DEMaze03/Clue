package tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Solution;

class GameSolutionTest {

	private static Board board;
	
	@BeforeAll
	static void setUp() {
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load BOTH config files
		board.initialize();
	}
	
	@Test
	void testAccusation() {
		Solution solution = new Solution(new Card("Venture Center", CardType.ROOM), new Card("Mines Parking", CardType.PERSON), new Card("Barrel of Rum", CardType.WEAPON));
		assertTrue(board.checkAccusation(solution.getRoom(), solution.getPerson(), solution.getWeapon()));
	}
	
	

}
