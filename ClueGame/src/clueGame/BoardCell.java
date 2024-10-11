package clueGame;

import java.util.HashSet;
import java.util.Set;

import experiment.TestBoardCell;

public class BoardCell {
	public int row;
	public int col;
	private char character;
	private boolean isRoom;
	private boolean occupied;
	private boolean isDoorway;
	private DoorDirection doorDirection;
	private Set<BoardCell> adjList = new HashSet<>();
	
	public BoardCell(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public void addAdj(BoardCell cell) {
		adjList.add(cell);
	}
	
	public Set<BoardCell> getAdjList(){
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
	
	

	public boolean isDoorway() {
		return isDoorway;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getChar() {
		return character;
	}

	public boolean isRoomCenter() {
		
		return false;
	}
}
