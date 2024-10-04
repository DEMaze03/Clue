package experiment;

import java.util.Set;

public class TestBoard {	
	private Set<TestBoardCell> targetList;
	

	public TestBoard() {
		super();
	}
	
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
