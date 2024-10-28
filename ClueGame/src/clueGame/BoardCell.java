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
	private boolean secretPassage = false;
	private DoorDirection doorDirection;
	private Set<BoardCell> adjList = new HashSet<>();
	
	public BoardCell(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public void addAdj(BoardCell cell) {
		adjList.add(cell);
	}
	
	//GETTERS
	
	public Set<BoardCell> getAdjList(){
		return adjList;
	}
	
	public boolean isARoom() {
		return isRoom;
	}
	
	public boolean isOccupied() {
		return occupied;
	}
	
	public boolean isDoorway() {
		return isDoorway;
	}
	
	public boolean isRoomCenter() {
		return isCenter;
	}
	
	public boolean isLabel() {
		return isLabel;
	}
	
	public boolean isSecretPassage() {
		return secretPassage;
	}
	
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	public char getChar() {
		return character;
	}
	
	public char getSecretPassage() {
		return passageChar;
	}
	
	//SETTERS
	
	public void setRoom(boolean isRoom) {
		this.isRoom = isRoom;
	}
	
	public void setOccupied(boolean isOccupied) {
		occupied = isOccupied;
	}
	
	public void setDoorway(boolean d) {
		this.isDoorway = d;
	}
	
	public void setDoorDirection(DoorDirection dir) {
		this.doorDirection = dir;
	}
	
	public void setChar(char character) {
		this.character = character;
	}
	
	public void setRoomCenter(boolean center) {
		this.isCenter = center;
	}
	
	public void setLabel(boolean label) {
		this.isLabel = label;
	}
	
	public void setSecretPassage(char c) {
		this.passageChar = c;
		this.secretPassage = true;
	}
}
