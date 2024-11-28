/*
 * computerPlayer - child class of Player that is used for all CPU controlled players
 * 
 * Author: Elijas Sliva & Daylon Maze
 */

package clueGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

public class ComputerPlayer extends Player {
	private Solution prevSuggestion;
	
	public ComputerPlayer(String name, String color, int row, int col, boolean isHuman) {
		super(name, color, row, col, isHuman);
	}
	
	//createSuggestion - method to create a random suggestion based on which cards the AI has/has not seen
	public Solution createSuggestion(Board board) {
		Card room = new Card(board.getRoom(board.getCell(this.getRow(),this.getCol())).getName(), CardType.ROOM);
		
		//Since the room in the suggestion will always be in the room the AI is in, we only need to separate and store weapons and people to suggest
		ArrayList<Card> weaponCardsNotSeen = new ArrayList<Card>();
		ArrayList<Card> personCardsNotSeen = new ArrayList<Card>();
		
		//loop over the deck in the board, ignoring room cards, and add all weapons/people not in seen list to separate ArrayLists
		for (Map.Entry<String,Card> deckCard : board.getDeck().entrySet()) {
			if(deckCard.getValue().getCardType() == CardType.ROOM){
				continue;
			}
			//if seen list is empty, just add every non-room card
			if (this.getSeenCards().size() == 0){
				if (deckCard.getValue().getCardType() == CardType.PERSON){
					personCardsNotSeen.add(deckCard.getValue());
				}
				if (deckCard.getValue().getCardType() == CardType.WEAPON){
					weaponCardsNotSeen.add(deckCard.getValue());
				}
			}
			
			//if current deck card is in seen list, continue, otherwise, add the deck card to the correct list
			boolean cont = false;
			for(Card c : this.getSeenCards()) {
				if (c.equals(deckCard.getValue())){
					cont = true;
				}
			}
				if (cont){
					continue;
				}else {
					if (deckCard.getValue().getCardType() == CardType.PERSON){
						personCardsNotSeen.add(deckCard.getValue());
					}
					if (deckCard.getValue().getCardType() == CardType.WEAPON){
						weaponCardsNotSeen.add(deckCard.getValue());
					}
				}
				
		}
		//generate a random index to pick from the ArrayLists (if only one item, it will always be picked)
		int personIndex = (int) ((Math.random() * ((personCardsNotSeen.size()-1) - 0)) + 0);
		int weaponIndex = (int) ((Math.random() * ((weaponCardsNotSeen.size()-1) - 0)) + 0);
		//create a new solution object and return it
		Solution solution = new Solution(room, personCardsNotSeen.get(personIndex), weaponCardsNotSeen.get(weaponIndex));
		Board.getInstance().getCell(Board.getInstance().getPlayers().get(solution.getPerson().getCardName()).getRow(),Board.getInstance().getPlayers().get(solution.getPerson().getCardName()).getCol()).setOccupied(false);
		Board.getInstance().getPlayers().get(solution.getPerson().getCardName()).setRow(this.getRow());
		Board.getInstance().getPlayers().get(solution.getPerson().getCardName()).setCol(this.getCol());
		
		try {
			((GameControlPanel) ClueGame.control).setGuess(solution.getPerson().getCardName(), room.getCardName(), solution.getWeapon().getCardName(), this.getColorObject());
		}catch(Exception e) {
			
		}
		return solution;
	}
	
	//selectTargets - method to select a target using the board.getTargets() and board.calcTargets() methods
	public BoardCell selectTarget(Board board, int roll) {
		//if the CPU had a previous suggestion that wasn't disproven, make the CPU make an accusation
		if (board.getCurrentPlayer().getAccStatus()) {
			if (board.checkAccusation(prevSuggestion.getRoom(), prevSuggestion.getPerson(), prevSuggestion.getWeapon())) {
				JOptionPane.showMessageDialog(null, "Oops! You Lose! "+this.getName()+" won!", "A Message From Within...", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}else {
				JOptionPane.showMessageDialog(null, "CPU was wrong. proposed solution was: \n" + prevSuggestion.getPerson().getCardName()+" in "+ prevSuggestion.getRoom().getCardName()+ " with the "+prevSuggestion.getWeapon().getCardName(),"A Message From Within...", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		board.calcTargets(board.getCell(this.getRow(),this.getCol()), roll);
		Set<BoardCell> targetList = board.getTargets();
		ArrayList<BoardCell> targetArrayList = new ArrayList<BoardCell>();
		ArrayList<BoardCell> returnList = new ArrayList<BoardCell>();
		
		//move the cells from the set to an arraylist for easier iteration and manipulation
		for (Iterator<BoardCell> it = targetList.iterator(); it.hasNext(); ) {
	        BoardCell f = it.next();
	        targetArrayList.add(f);
	    }
		
		//check if cell is a room center and if so, check if it's in the seen list. If it's not, add it to the return list
		for(BoardCell cell : targetArrayList) {
			if (cell.isRoomCenter()) {
				boolean contains = false;
				for(Card cards : this.getSeenCards()) {
					if((board.getDeck().get(board.getRoom(cell).getName()).getCardName().equals(cards.getCardName()))) {
						contains = true;
					}
				}
				if(!contains) {
					returnList.add(cell);
				}
			
			}
		}
		
		board.getCell(this.getRow(),this.getCol()).setOccupied(false);
		
		if(returnList.size() > 0) {
			//return random element from returnList
			int solutionIndex = (int) ((Math.random() * ((returnList.size()-1) - 0)) + 0);
			returnList.get(solutionIndex).setOccupied(true);
			this.setRow(returnList.get(solutionIndex).getRow());
			this.setCol(returnList.get(solutionIndex).getCol());
			if(Board.getInstance().getCell(this.getRow(), this.getCol()).isRoomCenter()) {
				Solution suggestion = this.createSuggestion(Board.getInstance());
				//check if the suggestion can be disproven and if not, set the accusation flag
				if(Board.getInstance().handleSuggestion(this, suggestion)==null) {
					try {
						((GameControlPanel) ClueGame.control).setGuessResult("Unable to be disproven!!!", Color.WHITE);
					}catch(Exception e) {
						
					}
					Player plr = board.getCurrentPlayer();
					if (!plr.getIsHuman() && (this.getCards().contains(suggestion.getRoom()) == false) && (this.getCards().contains(suggestion.getWeapon()) == false) && (this.getCards().contains(suggestion.getPerson()) == false)) {
						plr.setAccStatus(true);
						prevSuggestion = suggestion;
					}
					
				}else {
					try {
						((GameControlPanel) ClueGame.control).setGuessResult("Suggestion Disproven!", Board.getInstance().handleSuggestion(this, suggestion).getOwner().getColorObject());
					}catch(Exception e) {
						
					}
					this.updateSeenCard(Board.getInstance().handleSuggestion(this, suggestion));
				}
			}
			return returnList.get(solutionIndex);
		}else {
			//if target list is not empty choose a random element from it, otherwise just return the current cell
			if(targetArrayList.size()>0) {
			//return random element from targetArrayList
			int solutionIndex = (int) ((Math.random() * ((targetArrayList.size()-1) - 0)) + 0);
			targetArrayList.get(solutionIndex).setOccupied(true);
			this.setRow(targetArrayList.get(solutionIndex).getRow());
			this.setCol(targetArrayList.get(solutionIndex).getCol());
			if(Board.getInstance().getCell(this.getRow(), this.getCol()).isRoomCenter()) {
				this.createSuggestion(Board.getInstance());
			}
			return targetArrayList.get(solutionIndex);
			}else {
				return Board.getInstance().getCell(this.getRow(), this.getCol());
			}
		}
	}
}
