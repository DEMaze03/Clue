/*
 * BoardCell - class for an individual cell on the Clue game board.
 * Authors: Daylon Maze & Elijas Sliva
 */

package clueGame;

import java.awt.Color;
import java.awt.Graphics;
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
	private boolean isTarget = false;
	private DoorDirection doorDirection;
	private Set<BoardCell> adjList = new HashSet<>();
	
	public BoardCell(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public void addAdj(BoardCell cell) {
		adjList.add(cell);
	}
	
	//draw- method to draw each board cell using a graphics object, an x and y offset, and a size
	public void draw(Graphics g, int offsetx, int offsety, int sizex, int sizey) {
		
		if(!isTarget) {
				if(!isRoom) {
					switch(character) {
					case 'W':
						//draw walkways as yellow with black outlines
						g.setColor(Color.YELLOW);
						g.fillRect(offsetx, offsety, sizex, sizey);
						g.setColor(Color.BLACK);
						g.drawRect(offsetx, offsety, sizex, sizey);
						
					break;
					case 'X':
						//if an X cell, draw as black void
						g.setColor(Color.BLACK);
						g.fillRect(offsetx, offsety, sizex, sizey);
						g.setColor(Color.BLACK);
						g.drawRect(offsetx, offsety, sizex, sizey);
					break;
					default:
						//otherwise draw the cell as grey (room)
						g.setColor(Color.GRAY);
						g.fillRect(offsetx, offsety, sizex, sizey);
					break;
					}
				}
	
				//draw door based on doorDirection
			if (isDoorway) {
				switch (doorDirection) {
				case DoorDirection.UP:
					g.setColor(Color.GREEN);
					g.fillRect(offsetx, offsety, sizex, sizey/2);
					break;
					
				case DoorDirection.DOWN:
					g.setColor(Color.GREEN);
					g.fillRect(offsetx, offsety +(sizey/2), sizex, sizey/2);
					break;
					
				case DoorDirection.RIGHT:
					g.setColor(Color.GREEN);
					g.fillRect(offsetx + (sizex/2), offsety, sizex/2, sizey);
					break;
					
				case DoorDirection.LEFT:
					g.setColor(Color.GREEN);
					g.fillRect(offsetx, offsety, sizex/2, sizey);
					break;
					
				default:
				}
				
			}
	}else {
		g.setColor(Color.CYAN);
		g.fillRect(offsetx, offsety, sizex, sizey);
	}
		if (isOccupied()) {
			g.setColor(Color.RED);
			g.fillRect(offsetx, offsety, sizex, sizey);
		}
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
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean getTargetFlag() {
		return isTarget;
	}
	
	//SETTERS
	public void setTargetFlag(boolean flag) {
		this.isTarget = flag;
	}
	
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
