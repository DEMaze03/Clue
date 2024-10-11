/*
 * Simplified Cell class meant for initial testing of code
 * Author: Elijas Sliva
 */

package experiment;


import java.util.HashSet;
import java.util.Set;

public class TestBoard {	
	final static int ROWS = 4;
	final static int COLS = 4;
	
	private TestBoardCell[][] grid = new TestBoardCell[ROWS][COLS];
	private Set<TestBoardCell> visitedList = new HashSet<>();
	private Set<TestBoardCell> targetList = new HashSet<>();
	
	public TestBoard() {
		super();
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				System.out.printf("(%d, %d)", row, col);
				grid[row][col] = new TestBoardCell(row, col);
			}
			System.out.println();
		}
		
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				
				if (row - 1 >= 0) {
					grid[row][col].addAdj(grid[row - 1][col]);
				}
				
				if (col - 1 >= 0) {
					grid[row][col].addAdj(grid[row][col - 1]);
				}
				
				if (row + 1 < ROWS) {
					grid[row][col].addAdj(grid[row + 1][col]);
				}
				
				if (col + 1 < COLS) {
					grid[row][col].addAdj(grid[row][col + 1]);
				}
				
				
			}
		}
		
	}
	
	//calcTargets - Calculates the available target spaces given the starting cell and the dice roll (or path length)
	public void calcTargets(TestBoardCell startCell, int pathlength) {	
		visitedList.add(startCell);
		findAllTargets(startCell, pathlength);
	}
	
	
	public void findAllTargets(TestBoardCell startcell, int numSteps) {
		
		Set<TestBoardCell> adjCells = startcell.getAdjList();
		
		for (TestBoardCell cell : adjCells) {
			if (visitedList.contains(cell)) {
				continue;
			}
			
			visitedList.add(cell);
			
			if (cell.isOccupied()) {
				continue;
			}
			
			if (cell.isARoom()) {
				targetList.add(cell);
				continue;
			}
			
			if (numSteps == 1) {
				targetList.add(cell);
			} else {
				findAllTargets(cell, numSteps - 1);
			}
			
			visitedList.remove(cell);
		}	
	}
	
	public TestBoardCell getCell(int row, int col) {
		return grid[row][col];

	}
	
	public Set<TestBoardCell> getTargets() {
		
		for (TestBoardCell cell: targetList) {
			System.out.printf("(%d, %d)\n", cell.row, cell.col);
		}
		
		return targetList;
		
	}
	

}
