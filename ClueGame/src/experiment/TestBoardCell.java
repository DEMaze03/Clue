package experiment;

import java.util.Set;

public class TestBoardCell {
	public int row;
	public int col;
	private boolean isRoom;
	private boolean occupied;
	private Set<TestBoardCell> adjList;
	
	TestBoardCell(int row, int col){
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
	
	public void setOccupied(boolean isOccupied) {
		occupied = isOccupied;
	}
	public boolean isOccupied() {
		return occupied;
	}
}
