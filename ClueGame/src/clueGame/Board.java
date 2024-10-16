package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private int numRows;
	private int numColumns;
	private BoardCell[][] grid;
	private String layoutConfigFile;
	private String setupConfigFile;
	public Map<Character, Room> roomMap = new HashMap<Character, Room>();
	static Board theInstance = new Board();

	private Board() {
		super();
	}

	public static Board getInstance() {
		return theInstance;
	}

	public void initialize() {

		loadSetupConfig();
		loadLayoutConfig();

	}

	public void loadSetupConfig() {

		File setupFile = new File(setupConfigFile);
		try {
			Scanner r = new Scanner(setupFile);
			while (r.hasNextLine()) {
				String data = r.nextLine();
				String[] l = data.split(", ");
				if (l[0].equals("Room") || l[0].equals("Space")) {
					char c = l[2].charAt(0);
					String rn = l[1];

					Room room = new Room(rn);
					System.out.println(c + " " + room.getName());
					roomMap.put(Character.valueOf(c), room);
				}
			}
			r.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	
	}

	public void loadLayoutConfig() {

		// Read File 1st time to get board dimensions
		File layoutFile = new File(layoutConfigFile);
		int rowCount = 0;
		int colCount = 0;
		try {
			Scanner r = new Scanner(layoutFile);
			while (r.hasNextLine()) {

				String data = r.nextLine();
				String[] l = data.split(",");
				colCount = l.length;
				rowCount++;
			}
			r.close();

			numRows = rowCount;
			numColumns = colCount;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		grid = new BoardCell[numRows][numColumns];
		System.out.printf("%d, %d\n",numRows, numColumns);
		// Read file again to go over each cell and get the proper settings
		
		rowCount = 0;
		colCount = 0;
		
		try {
			Scanner r = new Scanner(layoutFile);
			while (r.hasNextLine()) {
				String data = r.nextLine();
				String[] l = data.split(",");
				colCount = l.length;
				for (int idx = 0; idx < colCount; idx++) {
					grid[rowCount][idx] = new BoardCell(idx, colCount);
					grid[rowCount][idx].setChar(l[idx].charAt(0));
					
					if (l[idx].length() == 2) {
						if (l[idx].charAt(1) == '#') { // if cell is label
							grid[rowCount][idx].setLabel(true);
							Room room = roomMap.get(l[idx].charAt(0));
							room.setLabelCell(grid[rowCount][idx]);
							
						} else if (l[idx].charAt(1) == '*') {
							grid[rowCount][idx].setRoomCenter(true);
							Room room = roomMap.get(l[idx].charAt(0));
							room.setCenterCell(grid[rowCount][idx]);
						
						} else if (l[idx].charAt(1) == '^') {
							grid[rowCount][idx].setDoorway(true);
							grid[rowCount][idx].setDoorDirection(DoorDirection.UP);
							
						} else if (l[idx].charAt(1) == 'v') {
							grid[rowCount][idx].setDoorway(true);
							grid[rowCount][idx].setDoorDirection(DoorDirection.DOWN);
						} else if (l[idx].charAt(1) == '<') {
							grid[rowCount][idx].setDoorway(true);
							grid[rowCount][idx].setDoorDirection(DoorDirection.LEFT);
				
						} else if (l[idx].charAt(1) == '>') {
							grid[rowCount][idx].setDoorway(true);
							grid[rowCount][idx].setDoorDirection(DoorDirection.RIGHT);
				
						} else if (l[idx].charAt(1) != '^' && l[idx].charAt(1) != 'v' && l[idx].charAt(1) != '<' && l[idx].charAt(1) != '>'){
							grid[rowCount][idx].setSecretPassage(l[idx].charAt(1));
						}
					
					}
				}
				rowCount++;
				
			}
			r.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void setConfigFiles(String csv, String txt) {
		// TODO Auto-generated method stub
		theInstance.layoutConfigFile = "data/" + csv;
		theInstance.setupConfigFile = "data/" + txt;

	}

	public Room getRoom(char c) {
		return roomMap.get(c);
	}

	public Room getRoom(BoardCell cell) {
		return roomMap.get(cell.getChar());
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public BoardCell getCell(int row, int col) {
		return this.grid[row][col];
	}

//	public static void main(String[] args) {
//		Board board;
//		board = Board.getInstance();
//		// set the file names to use my config files
//		board.setConfigFiles("ClueLayout306.csv", "ClueSetup306.txt");
//		// Initialize will load BOTH config files
//		board.initialize();
//		
//		System.out.println(board.getRoom('C').getName());
//		System.out.println("ROWS: " + board.getNumRows());
//		System.out.println("COLS: " + board.getNumColumns());
//		
//		BoardCell cell = board.getCell(3, 3);
//		
//		
//	}

}
