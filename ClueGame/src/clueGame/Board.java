package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	private BoardCell[][] grid;
	private String layoutConfigFile;
	private String setupConfigFile;
	public Map<Character, Room> roomMap = new HashMap<Character, Room>();
	public Set<BoardCell> targetList = new HashSet<BoardCell>();
	public Set<BoardCell> adjList = new HashSet<BoardCell>();
	static Board theInstance = new Board();

	private Board() {
		super();
	}

	public static Board getInstance() {
		return theInstance;
	}

	public void initialize() {
		
		try {
			loadSetupConfig();
		} catch (BadConfigFormatException e) {
			System.out.println(e);
			e.getStackTrace();
		}
			
		try {
			loadLayoutConfig();
		} catch (BadConfigFormatException e) {
			System.out.println(e);
			e.getStackTrace();
		}

	}

	public void loadSetupConfig() throws BadConfigFormatException {

		File setupFile = new File(setupConfigFile);
		try {
			Scanner r = new Scanner(setupFile);
			while (r.hasNextLine()) {
				String data = r.nextLine();
				String[] l = data.split(", ");
				
				if (l.length == 1 ) { // continue over empty lines
					continue;
				}
				
				if (l[0].charAt(0) == '/') { // continue over comments in file
					continue;
				}
				
				
				if (l[0].equals("Room") || l[0].equals("Space")) {
					char c = l[2].charAt(0);
					String rn = l[1];

					Room room = new Room(rn);
					System.out.println(c + " " + room.getName());
					roomMap.put(Character.valueOf(c), room);
				} else {
					throw new BadConfigFormatException("invalid room type");
				}
			}
			r.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	
	}

	public void loadLayoutConfig() throws BadConfigFormatException{

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
		
		try {
			Scanner r = new Scanner(layoutFile);
			while (r.hasNextLine()) {
			
				String data = r.nextLine();
				String[] l = data.split(",");
				

				System.out.println(colCount);
				for (int idx = 0; idx < numColumns; idx++) {
					grid[rowCount][idx] = new BoardCell(idx, colCount);
//					System.out.printf("(%d, %d) %s \n",rowCount, idx, l[idx]);
					
				
					try {
						grid[rowCount][idx].setChar(l[idx].charAt(0)); // if the cell is empty, this will throw something that will need to be handled
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new BadConfigFormatException("Bad config file");
					}
					
					if (roomMap.containsKey(l[idx].charAt(0)) != true) {
						throw new BadConfigFormatException("unknown map character");
					}
						
					
					
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

	public void calcTargets(BoardCell cell, int i) {
		// TODO Auto-generated method stub
		
	}

	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return targetList;
	}

	public Set<BoardCell> getAdjList(int i, int j) {
		// TODO Auto-generated method stub
		return adjList;
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
