package clueGame;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public Room(String name) {
		this.name = name;
	}
	
	// getters
	public BoardCell getLabelCell() {
		return labelCell;
	}
	
	public BoardCell getCenterCell() {
		return centerCell;
	}
	
	public String getName() {
		return name;
	}
	
	// setters
	public void setCenterCell(BoardCell cell) {
		this.centerCell = cell;
	}
	
	public void setLabelCell(BoardCell cell) {
		this.labelCell = cell;
	}
	

}
