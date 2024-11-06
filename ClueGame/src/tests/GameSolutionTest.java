/*
 * GameSolutionTests - tests to ensure suggestion and accusation handling works well.
 * Authors: Daylon Maze & Elijas Sliva
 */

package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import clueGame.*;

class GameSolutionTest {

	private static Board board;
		
	private static Card parkingTicketCard;
	private static Card minesParkingCard;
	private static Card tennisRacketCard;
	private static Card ventureCard;
	@BeforeAll
	static void setUp() {
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load BOTH config files
		board.initialize();
	
		parkingTicketCard = new Card("Parking Ticket", CardType.WEAPON);
		minesParkingCard = new Card("Mines Parking", CardType.PERSON);
		tennisRacketCard = new Card("Tennis Racket", CardType.WEAPON);
		ventureCard = new Card("Venture Center", CardType.ROOM);
		
	}
	
	@Test
	void testAccusation() {
		//make sure the correct accusation returns true
		Solution solution = board.getSolution();
		assertTrue(board.checkAccusation(solution.getRoom(), solution.getPerson(), solution.getWeapon()));
		
		//create a new person, if it happens to be the same person in the solution, pick something else
		Card newPerson = board.getDeck().get("PCJ");
		if(newPerson.equals(solution.getPerson())) {
			newPerson = board.getDeck().get("Blaster");
		}
		
		//create a new room, if it happens to be the same person in the solution, pick something else
		Card newRoom = board.getDeck().get("McNeil");
		if(newPerson.equals(solution.getRoom())) {
			newRoom = board.getDeck().get("Venture Center");
		}
		
		//create a new weapon, if it happens to be the same person in the solution, pick something else
		Card newWeapon = board.getDeck().get("Garlic");
		if(newPerson.equals(solution.getWeapon())) {
			newWeapon = board.getDeck().get("Parking Ticket");
		}
		
		//make sure if the wrong accusation is made, it returns false
		assertFalse(board.checkAccusation(newRoom, solution.getPerson(), newWeapon));
		assertFalse(board.checkAccusation(solution.getRoom(), newPerson, solution.getWeapon()));
		assertFalse(board.checkAccusation(solution.getRoom(), solution.getPerson(), newWeapon));
	}
	
	@Test
	void testDisprove() {
		// test one matching card
		
		Player testPlayer = new HumanPlayer("Test Player", "Yellow", 0, 0, true);
		ArrayList<Card> cards = new ArrayList<Card>();
		Card exCard = new Card("Venture Center", CardType.ROOM);
		
		testPlayer.updateHand(exCard);
		assertTrue(exCard.equals(testPlayer.disproveSuggestion(exCard)));
		
		// test no matching card
		Card nullCard = new Card("Marquez", CardType.ROOM);
		assertEquals(null, (board.returnPlayer("PCJ").disproveSuggestion(nullCard)));
		
		// add multiple test card
		testPlayer.updateHand(new Card("Parking Ticket", CardType.WEAPON));
		testPlayer.updateHand(new Card("Mines Parking", CardType.PERSON));
		assertTrue(exCard.equals(testPlayer.disproveSuggestion(exCard)));

	}
	
	@Test
	void testSuggestion() {
		
		Solution suggestion = new Solution(ventureCard, new Card("Blaster", CardType.PERSON), new Card("Clear Whiskey", CardType.WEAPON));
		
		board.returnPlayer("PCJ").updateHand(parkingTicketCard);
		board.returnPlayer("Marvin").updateHand(tennisRacketCard);
		board.returnPlayer("Wario").updateHand(minesParkingCard);
		
		
		assertEquals(null, board.handleSuggestion(board.returnPlayer("PCJ"), suggestion));
		board.returnPlayer("PCJ").updateHand(ventureCard);
		assertEquals(null, board.handleSuggestion(board.returnPlayer("PCJ"), suggestion));
		board.returnPlayer("PCJ").updateHand(ventureCard);
		board.returnPlayer("PCJ").updateHand(parkingTicketCard);
		assertEquals(ventureCard, board.handleSuggestion(board.returnPlayer("Marvin"), suggestion));
		assertEquals(ventureCard, board.handleSuggestion(board.returnPlayer("Wario"), suggestion));
		
	}
	
	

}
