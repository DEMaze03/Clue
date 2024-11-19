/*
 * HumanPlayer - child class of Player that is used for the human controlled players
 * 
 * Author: Elijas Sliva
 */

package clueGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class HumanPlayer extends Player {
	private boolean turnStatus = false;
	ArrayList<BoardCell> targetArrayList;
	
	public HumanPlayer(String name, String color, int row, int col, boolean isHuman) {
		super(name, color, row, col, isHuman);
	}
	
	//selectTargets - method to select a target using the board.getTargets() and board.calcTargets() methods
		public BoardCell selectTarget(Board board, int roll) {
			board.calcTargets(board.getCell(this.getRow(),this.getCol()), roll);
			Set<BoardCell> targetList = board.getTargets();
			targetArrayList = new ArrayList<BoardCell>();
			
			//move the cells from the set to an arraylist for easier iteration and manipulation
			for (Iterator<BoardCell> it = targetList.iterator(); it.hasNext(); ) {
		        BoardCell f = it.next();
		        targetArrayList.add(f);
		    }
			
			//check if cell is a room center and if so, check if it's in the seen list. If it's not, add it to the return list
			for(BoardCell cell : targetArrayList) {
				cell.setTargetFlag(true);
			}
			return board.getCell(0, 0);
		}
		
	public void movePlayer(Board board, int row, int col) {
		board.getCell(this.getRow(),this.getCol()).setOccupied(false);
		board.getCell(row,col).setOccupied(true);
		this.setRow(row);
		this.setCol(col);
	}
		
	public ArrayList<BoardCell> getTargetList() {
		return this.targetArrayList;
	}
	
}
