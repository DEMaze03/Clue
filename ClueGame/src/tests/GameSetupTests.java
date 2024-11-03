/*
 * GameSetupTests
 * Tests to verify player and card behavior
 * 
 * Authors: Daylon Maze & Elijas Sliva
 */

package tests;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
		// Test 1 human player (always PCJ) and 5 computer (all others)
		// Test 6 players total
		assertEquals("PCJ", board.returnPlayer("PCJ").getName());
		assertEquals(true, board.returnPlayer("PCJ").getIsHuman());
		assertEquals(false, board.returnPlayer("Blaster").getIsHuman());
		assertEquals(6, board.getPlayers().size());
		
	}
	
	@Test
	public void TestDeck() {
		// Test Deck size
		assertEquals(21, board.getDeck().size());
		
		//Test that a weapon is in the deck
		assertEquals(CardType.WEAPON, board.getDeck().get("Barrel of Rum").getCardType());

		//Test that a person is in the deck
		assertEquals(CardType.PERSON, board.getDeck().get("Blaster").getCardType());

		//Test that a room is in the deck
		assertEquals(CardType.ROOM, board.getDeck().get("McNeil").getCardType());
		
		//Test that no card is dealt twice
		Map<String, Player> players = board.getPlayers();
		String[] playerNames = new String[6];
		playerNames[0] = "PCJ";
		playerNames[1] = "Blaster";
		playerNames[2] = "Marvin";
		playerNames[3] = "Mines Parking";
		playerNames[4] = "Wario";
		playerNames[5] = "Waluigi";
		Set<Card> cardList = new HashSet<Card>();
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < players.get(playerNames[i]).getCards().size(); j++) {
				if (cardList.contains(players.get(playerNames[i]).getCards().get(j))) {
					assert false;
				}else {
					cardList.add(players.get(playerNames[i]).getCards().get(j));
				}
			}
		}
		assert true;
		
		
		
		
	}
	@Test
	public void TestBoard() {
		// Test Players having cards dealt to them properly (3 each in our case)
		Map<String, Player> players = board.getPlayers();
		assertEquals(3, players.get("PCJ").getCards().size());
		assertEquals(3, players.get("Blaster").getCards().size());
		assertEquals(3, players.get("Wario").getCards().size());
		assertEquals(3, players.get("Waluigi").getCards().size());
		assertEquals(3, players.get("Marvin").getCards().size());
		assertEquals(3, players.get("Mines Parking").getCards().size());
	}
	
	
}
	