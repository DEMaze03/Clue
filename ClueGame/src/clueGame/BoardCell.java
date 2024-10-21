/*
 * BoardCell - class for an individual cell on the Clue game board.
 * Author: Daylon Maze
 */

package clueGame;

import java.util.HashSet;
import java.util.Set;

public class BoardCell {
	public int row;
	public int col;
	private char character;
	private char passageChar;
	private boolean isRoom;
	private boolean occupied;
	private boolean isCenter;
	private boolean isDoorway;
	private boolean isLabel;
	private DoorDirection doorDirection;
	private Set<BoardCell> adjList = new HashSet<>();
	
	public BoardCell(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public void addAdj(BoardCell cell) {
		adjList.add(cell);
		System.out.println("added " + cell);
	}
	
	public Set<BoardCell> getAdjList(){
		return adjList;
	}
	
	public void setRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}
	public boolean isARoom() {
		return isRoom;
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
	
	public void setDoorway(boolean d) {
		this.isDoorway = d;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	public void setDoorDirection(DoorDirection dir) {
		this.doorDirection = dir;
	}
	
	public char getChar() {
		return character;
	}
	
	public void setChar(char character) {
		this.character = character;
	}

	public boolean isRoomCenter() {
		return isCenter;
	}
	
	public void setRoomCenter(boolean center) {
		this.isCenter = center;
	}
	
	public boolean isLabel() {
		return isLabel;
	}
	
	public void setLabel(boolean label) {
		this.isLabel = label;
	}
	
	
	public char getSecretPassage() {
		return passageChar;
	}
	
	public void setSecretPassage(char c) {
		this.passageChar = c;
	}
}
