package clueGame;

public class Room {
	private String name;
	private BoardCell centerCell;
	private BoardCell labelCell;
	
	public Room(String name) {
		this.name = name;
	}
	
	public BoardCell getCenterCell() {
		return centerCell;
	}
	
	public void setCenterCell(BoardCell cell) {
		this.centerCell = cell;
	}
	
	public BoardCell getLabelCell() {
		return labelCell;
	}
	
	public void setLabelCell(BoardCell cell) {
		this.labelCell = cell;
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
