/*
 * computerPlayer - child class of Player that is used for all CPU controlled players
 * 
 * Author: Elijas Sliva
 */

package clueGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(String name, String color, int row, int col, boolean isHuman) {
		super(name, color, row, col, isHuman);
	}
	
	public Solution createSuggestion(Card room, Card person, Card weapon) {
		Solution sol = new Solution(room, person, weapon);
		return sol;
	}
	
	public BoardCell selectTarget(Board board, int roll) {
		board.calcTargets(board.getCell(this.getRow(),this.getCol()), roll);
		Set<BoardCell> targetList = board.getTargets();
		ArrayList<BoardCell> targetArrayList = new ArrayList<BoardCell>();
		ArrayList<BoardCell> returnList = new ArrayList<BoardCell>();
		
		//move the cells from the set to an arraylist for easier iteration and manipulation
		for (Iterator<BoardCell> it = targetList.iterator(); it.hasNext(); ) {
	        BoardCell f = it.next();
	        targetArrayList.add(f);
	    }
		
		//check if cell is a room and if so, check if it's in the seen list. If it's not, add it to the return list
		for(BoardCell cell : targetArrayList) {
			if (cell.isRoomCenter()) {
				for(Card card : getSeenCards()) {
					String cellName = board.getRoom(cell).getName();
					String cardName = card.getCardName();
					if (cardName.equals(cellName) == false) {
						returnList.add(cell);
					}
				}
			}
		}
		if(returnList.size() > 0) {
			//return random element from returnList
			int solutionIndex = (int) ((Math.random() * ((returnList.size()-1) - 0)) + 0);
			return returnList.get(solutionIndex);
		}else {
			//return random element from targetArrayList
			int solutionIndex = (int) ((Math.random() * ((targetArrayList.size()-1) - 0)) + 0);
			return targetArrayList.get(solutionIndex);
		}
	}
}
