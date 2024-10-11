/*
 * Simplified Cell class meant for initial testing of code
 * Author: Daylon Maze
 */

package experiment;

import java.util.HashSet;
import java.util.Set;

import clueGame.Board;

public class TestBoardCell {
	public int row;
	public int col;
	private boolean isRoom;
	private boolean isOccupied;
	private Set<TestBoardCell> adjList = new HashSet<>();
	
	public TestBoardCell(int row, int col){
		super();
		this.row = row;
		this.col = col;
	}
	
	public void addAdj(TestBoardCell cell) {
		adjList.add(cell);
	}
	
	public Set<TestBoardCell> getAdjList(){
		return adjList;
	}
	
	
	public void setRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}
	
	public boolean isARoom() {
		return isRoom;
		//Hello
	}
	
	public void setOccupied(boolean occupied) {
		isOccupied = occupied;
	}
	
	public boolean isOccupied() {
		return isOccupied;
	}
}
