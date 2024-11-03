package tests;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Player;

public class GameSetupTests {
	
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load BOTH config files
		board.initialize();
	}
	
	
	@Test
	public void TestPlayers() {
		// Test 1 human player and 5 computer
		// Test 6 players total
		assertEquals("PCJ", board.returnPlayer("PCJ").getName());
		assertEquals(true, board.returnPlayer("PCJ").getIsHuman());
		assertEquals(false, board.returnPlayer("Blaster").getIsHuman());
		assertEquals(6, board.getPlayers().size());
		
	}
	
	@Test
	public void TestDeck() {
		// Test Deck size
		// Test Weapons, people and rooms
		// Test All Cards are Dealt
		
		assertEquals(21, board.getDeck().size());
		
		Card testCard = new Card("Barrel of Rum", CardType.WEAPON);
		assertEquals(CardType.WEAPON, board.getDeck().get("Barrel of Rum").getCardType());
		testCard = new Card("Blaster", CardType.PERSON);
		assertEquals(CardType.PERSON, board.getDeck().get("Blaster").getCardType());
		testCard = new Card("McNeil", CardType.ROOM);
		assertEquals(CardType.ROOM, board.getDeck().get("McNeil").getCardType());
		
		
		
	}
	@Test
	public void TestBoard() {
		// Test Players having cards dealt to them properly
		Map<String, Player> players = board.getPlayers();
		assertEquals(3, players.get("PCJ").getCards().size());
		assertEquals(3, players.get("Blaster").getCards().size());
		assertEquals(3, players.get("Wario").getCards().size());
		assertEquals(3, players.get("Waluigi").getCards().size());
		assertEquals(3, players.get("Marvin").getCards().size());
		assertEquals(3, players.get("Mines Parking").getCards().size());
	}
	
	
}
	