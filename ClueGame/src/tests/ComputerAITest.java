/*
 * ComputerAITest - tests to ensure the ComputerPlayer class selects targets and makes suggestions based on Clue rules.
 * Authors: Daylon Maze
 */

package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.Player;
import clueGame.Room;
import clueGame.Solution;

class ComputerAITest {
	
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
	public void SuggestionTests() {
		Player testPlayer = new ComputerPlayer("Test Player", "Yellow", 2, 2, true);
		
		
		//Make sure room matches current location
		Solution AISolution = testPlayer.createSuggestion(board);
		Room boardRoom = board.getRoom(board.getCell(2, 2));
		assertEquals(AISolution.getRoom().getCardName(),boardRoom.getName());
		
		
		//if only one weapon/person not seen, select only that one
			//Add all but one weapon to seen list
		testPlayer = new ComputerPlayer("Test Player", "Yellow", 2, 2, true);
		Card seenWhisk = board.getDeck().get("Clear Whiskey");
		Card seenBell = board.getDeck().get("College Bell");
		Card seenTicket = board.getDeck().get("Parking Ticket");
		Card seenGarlic = board.getDeck().get("Garlic");
		Card seenRacket = board.getDeck().get("Tennis Racket");
		testPlayer.updateSeenCard(seenRacket);
		testPlayer.updateSeenCard(seenWhisk);
		testPlayer.updateSeenCard(seenBell);
		testPlayer.updateSeenCard(seenGarlic);
		testPlayer.updateSeenCard(seenTicket);
			//Add all but one person to the seen list
		Card seenWario = board.getDeck().get("Wario");
		Card seenWaluigi = board.getDeck().get("Waluigi");
		Card seenBlaster = board.getDeck().get("Blaster");
		Card seenMarvin = board.getDeck().get("Marvin");
		Card seenParking = board.getDeck().get("Mines Parking");
		testPlayer.updateSeenCard(seenWario);
		testPlayer.updateSeenCard(seenWaluigi);
		testPlayer.updateSeenCard(seenBlaster);
		testPlayer.updateSeenCard(seenMarvin);
		testPlayer.updateSeenCard(seenParking);
		
		AISolution = testPlayer.createSuggestion(board);
		Card ExpectedSolutionPerson = new Card("PCJ", CardType.PERSON);
		Card ExpectedSolutionWeapon = new Card("Barrel of Rum", CardType.WEAPON);
			//assert equals the expected cards and the suggestion cards
		assertEquals(ExpectedSolutionPerson.getCardName(), AISolution.getPerson().getCardName());
		assertEquals(ExpectedSolutionWeapon.getCardName(), AISolution.getWeapon().getCardName());
		
		
		testPlayer = new ComputerPlayer("Test Player", "Yellow", 2, 2, true);
		//If multiple weapons/people not seen, one is randomly selected
			//Add some weapons to seen list
		testPlayer.updateSeenCard(seenGarlic);
		testPlayer.updateSeenCard(seenTicket);
			//Add some people to the seen list
		testPlayer.updateSeenCard(seenMarvin);
		testPlayer.updateSeenCard(seenParking);
		AISolution = testPlayer.createSuggestion(board);
		
		//assert false that FirstPerson and FirstWeapon equal the new suggestion
		Card previousWeapon = AISolution.getWeapon();
		Card previousPerson = AISolution.getPerson();
		int timesPersonDeviated = 0;
		int timesWeaponDeviated = 0;
		for(int i = 0; i < 1000; i++) {
			Solution newSolution =testPlayer.createSuggestion(board);
			if (newSolution.getPerson().equals(previousPerson) == false){
				timesPersonDeviated ++;
				previousPerson = newSolution.getPerson();
			}
			if (newSolution.getWeapon().equals(previousWeapon) == false){
				timesWeaponDeviated ++;
				previousWeapon = newSolution.getWeapon();
			}
			
		}
		
		if((timesPersonDeviated >0) && (timesWeaponDeviated > 0)){
			assert true;
		}else {
			assert false;
		}
		
		
	}
	
	@Test
	public void TargetTests() {
		Player testPlayer = new ComputerPlayer("Test Player", "Yellow", 6, 24, true);
		//If no rooms in list, select randomly
		BoardCell previousTarget = testPlayer.selectTarget(board, 3);
		int timesDeviated = 0;
		for(int i = 0; i < 1000; i ++) {
			BoardCell newTarget = testPlayer.selectTarget(board, 3);
			if(newTarget.equals(previousTarget) == false) {
				previousTarget = newTarget;
				timesDeviated ++;
			}
		}
		if(timesDeviated > 0) {
			assert true;
		}else {
			assert false;
		}
		
		//if unseen room in list, select it
			//Add all nearby rooms but one into seen list
			Card seenRoom = board.getDeck().get("McNeil");
		testPlayer = new ComputerPlayer("Test Player", "Yellow", 11, 7, true);
		testPlayer.updateSeenCard(seenRoom);
		BoardCell cell1 = board.getCell(17, 9);
		BoardCell cell2 = testPlayer.selectTarget(board, 4);
			//assert that the unseen room is chosen as the target
		assertEquals(cell1.getRow(),cell2.getRow());
		assertEquals(cell1.getCol(),cell2.getCol());
		
		
		//if seen room in list, randomly select
			//same as above test, but just mark nearby room as seen and make sure other spaces are target
		seenRoom = board.getDeck().get("McNeil");
		Card seenRoom2 = board.getDeck().get("Guggenheim");
		testPlayer = new ComputerPlayer("Test Player", "Yellow", 11, 7, true);
		testPlayer.updateSeenCard(seenRoom);
		testPlayer.updateSeenCard(seenRoom2);
			//assert that the unseen room is chosen as the target
		previousTarget = testPlayer.selectTarget(board, 3);
		timesDeviated = 0;
		for(int i = 0; i < 1000; i ++) {
			BoardCell newTarget = testPlayer.selectTarget(board, 3);
			if(newTarget.equals(previousTarget) == false) {
				previousTarget = newTarget;
				timesDeviated ++;
			}
		}
		if(timesDeviated > 0) {
			assert true;
		}else {
			assert false;
		}
	}
	
	
}

