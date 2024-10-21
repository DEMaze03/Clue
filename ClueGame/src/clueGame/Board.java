/*
 * Board - board class for Clue game
 * Author: Elijas Sliva
 */

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
	private Set<BoardCell> targetList = new HashSet<>();
	private Set<BoardCell> visitedList = new HashSet<BoardCell>();
	public Set<BoardCell> adjList = new HashSet<BoardCell>();
	static Board theInstance = new Board();

	private Board() {
		super();
	}

	//getInstance - getter for the instance of the game board
	public static Board getInstance() {
		return theInstance;
	}

	//initialize - method to initialize the game board by calling loadSetupConfig and loadLayoutConfig
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
		calcAdjList();

	}

	//loadSetupConfig - method to load the setup configuration file for the board. Throws BadConfigFormatException if file format is bad
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

	//loadLayoutConfig - method to load the layout configuration file for the board. Throws BadConfigFormatException if file format is bad
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
					grid[rowCount][idx] = new BoardCell(rowCount, idx);
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

	//setConfigFiles - set the config .csv and .txt files
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

	public void calcTargets(BoardCell startCell, int pathlength) {
		// TODO Auto-generated method stub
		targetList.clear();
		visitedList.clear();
		
		visitedList.add(startCell);
		findAllTargets(startCell, pathlength);
		
	}
	
	public void findAllTargets(BoardCell startcell, int numSteps) {
		Set<BoardCell> adjCells = startcell.getAdjList();
		for (BoardCell cell : adjCells) {
			if (visitedList.contains(cell)) {
				continue;
			}

			if (cell.isOccupied()) {
				if (cell.isRoomCenter()) {
					targetList.add(cell);
				}
				continue;
			}

			visitedList.add(cell);
			if (cell.isARoom()) {
				targetList.add(cell);
				continue;
			}

			if (numSteps == 1) {
				targetList.add(cell);
			} else {
				findAllTargets(cell, numSteps - 1);
			}

			visitedList.remove(cell);
		}
	}

	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return targetList;
	}
	
	public void calcAdjList() {
		//loop over rows and columns
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				BoardCell currentCell = theInstance.getCell(i, j);
				
				
				//if the current tile is a Walkway, connect all adjacent walkways
				if(currentCell.getChar() == 'W') { 
					if (i - 1 >= 0 && theInstance.getCell(i-1, j).getChar() == 'W') {
						currentCell.addAdj(grid[i - 1][j]);
					}

					if (j - 1 >= 0 && theInstance.getCell(i, j-1).getChar() == 'W') {
						currentCell.addAdj(grid[i][j-1]);
					}

					if (i + 1 < numRows  && theInstance.getCell(i+1, j).getChar() == 'W') {
						currentCell.addAdj(grid[i+1][j]);
					}

					if (j + 1 < numColumns  && theInstance.getCell(i, j+1).getChar() == 'W') {
						currentCell.addAdj(grid[i][j+1]);
					}
					//if Walkway is a doorway, find out which room it points to, and assign that room's center to the adj list
					if(currentCell.isDoorway()) {
						Room roomCell = theInstance.getRoom(theInstance.getCell(i, j-1));
						switch(currentCell.getDoorDirection()) {
						case DoorDirection.UP:
							roomCell = theInstance.getRoom(theInstance.getCell(i-1, j));
							currentCell.addAdj(roomCell.getCenterCell());
							roomCell.getCenterCell().addAdj(currentCell);
						break;
						case DoorDirection.DOWN:
							roomCell = theInstance.getRoom(theInstance.getCell(i+1, j));
							currentCell.addAdj(roomCell.getCenterCell());
							roomCell.getCenterCell().addAdj(currentCell);
						break;
						case DoorDirection.LEFT:
							roomCell = theInstance.getRoom(theInstance.getCell(i, j-1));
							currentCell.addAdj(roomCell.getCenterCell());
							roomCell.getCenterCell().addAdj(currentCell);
						break;
						case DoorDirection.RIGHT:
							roomCell = theInstance.getRoom(theInstance.getCell(i, j+1));
							currentCell.addAdj(roomCell.getCenterCell());
							roomCell.getCenterCell().addAdj(currentCell);
						break;
						case NONE:
						break;
						}
					}
				}else if (currentCell.isSecretPassage()){
					Room room = roomMap.get(currentCell.getChar());
					Room passageRoom = roomMap.get(currentCell.getSecretPassage());
					BoardCell roomCenter = room.getCenterCell();
					BoardCell passageCenter = passageRoom.getCenterCell();
					roomCenter.addAdj(passageCenter);
					passageCenter.addAdj(roomCenter);
				}
			}
		}
	}

	public Set<BoardCell> getAdjList(int row, int col) {
		
		return this.grid[row][col].getAdjList();
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
