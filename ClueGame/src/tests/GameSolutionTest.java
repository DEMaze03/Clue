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
		Solution solution = board.getSolution();
		assertTrue(board.checkAccusation(solution.getRoom(), solution.getPerson(), solution.getWeapon()));
		
		assertFalse(board.checkAccusation(solution.getRoom(), new Card("Blaster", CardType.PERSON), solution.getWeapon()));
		assertFalse(board.checkAccusation(new Card("Venture Center", CardType.ROOM), solution.getPerson(), new Card("Parking Ticket", CardType.WEAPON)));
		assertFalse(board.checkAccusation(solution.getRoom(), new Card("Blaster", CardType.PERSON), solution.getWeapon()));
	}
	
	@Test
	void testDisprove() {
		// test one matching card
		Card exCard = new Card("Venture Center", CardType.ROOM);
		assertTrue(exCard.equals(board.returnPlayer("PCJ").disproveSuggestion(exCard)));
		
		// test no matching card
		Card nullCard = new Card("Marquez", CardType.ROOM);
		assertEquals(null, (board.returnPlayer("PCJ").disproveSuggestion(nullCard)));
		
		// add multiple test card
	}
	
	@Test
	void testsuggestion() {
		
	}
	
	

}
