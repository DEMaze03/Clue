package experiment;

import java.util.Set;

public class TestBoardCell {
	private int row;
	private int col;
	private boolean isRoom;
	private boolean occupied;
	private Set<TestBoardCell> adjList;
	
	TestBoardCell(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	void addAdj(TestBoardCell cell) {
		adjList.add(cell);
	}
	
	Set<TestBoardCell> getAdjList(){
		return adjList;
	}
	
	void setRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}
	boolean isARoom() {
		return isRoom;
		//Hello
	}
	
	void setOccupied(boolean isOccupied) {
		occupied = isOccupied;
	}
	boolean isOccupied() {
		return occupied;
	}
}
