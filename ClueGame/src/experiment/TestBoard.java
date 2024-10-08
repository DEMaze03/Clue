/*
 * Simplified Cell class meant for initial testing of code
 * Author: Elijas Sliva
 */

package experiment;


import java.util.HashSet;
import java.util.Set;

public class TestBoard {	
	private Set<TestBoardCell> targetList = new HashSet<>();
	

	public TestBoard() {
		super();
	}
	
	//calcTargets - Calculates the available target spaces given the starting cell and the dice roll (or path length)
	public void calcTargets(TestBoardCell startCell, int pathlength) {
		int cellCol = startCell.col;
		int cellRow = startCell.row;
		
		targetList.add(new TestBoardCell(cellCol + pathlength, cellRow));
		targetList.add(new TestBoardCell(cellCol - pathlength, cellRow));
		targetList.add(new TestBoardCell(cellCol, cellRow + pathlength));
		targetList.add(new TestBoardCell(cellCol, cellRow - pathlength));
	}
	
	public TestBoardCell getCell(int row, int col) {
		TestBoardCell b = new TestBoardCell(row, col);
		return b;
		
	}
	
	public Set<TestBoardCell> getTargets() {
		return targetList;
		
	}
	

}
