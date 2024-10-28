/*
 * Board - board class for Clue game
 * Author: Elijas Sliva & Daylon Maze
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
		
		// Read file again to go over each cell and get the proper settings
		rowCount = 0;
		
		try {
			Scanner r = new Scanner(layoutFile);
			while (r.hasNextLine()) {
			
				String data = r.nextLine();
				String[] dataList = data.split(",");
			
				for (int col = 0; col < numColumns; col++) {
					grid[rowCount][col] = new BoardCell(rowCount, col);
					
				
					try {
						grid[rowCount][col].setChar(dataList[col].charAt(0)); // if the cell is empty, this will throw something that will need to be handled
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new BadConfigFormatException("Bad config file");
					}
					
					if (roomMap.containsKey(dataList[col].charAt(0)) != true) {
						throw new BadConfigFormatException("unknown map character");
					}
						
					
					
					if (dataList[col].length() == 2) {
						if (dataList[col].charAt(1) == '#') { // if cell is label
							grid[rowCount][col].setLabel(true);
							Room room = roomMap.get(dataList[col].charAt(0));
							room.setLabelCell(grid[rowCount][col]);
							
						} else if (dataList[col].charAt(1) == '*') {
							grid[rowCount][col].setRoomCenter(true);
							Room room = roomMap.get(dataList[col].charAt(0));
							room.setCenterCell(grid[rowCount][col]);
						
						} else if (dataList[col].charAt(1) == '^') {
							grid[rowCount][col].setDoorway(true);
							grid[rowCount][col].setDoorDirection(DoorDirection.UP);
							
						} else if (dataList[col].charAt(1) == 'v') {
							grid[rowCount][col].setDoorway(true);
							grid[rowCount][col].setDoorDirection(DoorDirection.DOWN);
						} else if (dataList[col].charAt(1) == '<') {
							grid[rowCount][col].setDoorway(true);
							grid[rowCount][col].setDoorDirection(DoorDirection.LEFT);
				
						} else if (dataList[col].charAt(1) == '>') {
							grid[rowCount][col].setDoorway(true);
							grid[rowCount][col].setDoorDirection(DoorDirection.RIGHT);
				
						} else if (dataList[col].charAt(1) != '^' && dataList[col].charAt(1) != 'v' && dataList[col].charAt(1) != '<' && dataList[col].charAt(1) != '>'){
							grid[rowCount][col].setSecretPassage(dataList[col].charAt(1));
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

	//calcTargets - method to clear tagetList and VisitedList before calling findAllTargets
	public void calcTargets(BoardCell startCell, int pathlength) {
		targetList.clear();
		visitedList.clear();
		
		visitedList.add(startCell);
		breadthFirstSearch(startCell, pathlength);
		
	}
	
	//findAllTargets - recursive method to find all targets for the given number of steps on a given starting cell
	public void breadthFirstSearch(BoardCell startcell, int numSteps) {
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
			
			if (cell.isRoomCenter()) {
				targetList.add(cell);
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
				breadthFirstSearch(cell, numSteps - 1);
			}

			visitedList.remove(cell);
		}
	}
	
	//calcAdjList - method to calculate all cell adjacencies during board setup
	public void calcAdjList() {
		//loop over rows and columns
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numColumns; col++) {
				BoardCell currentCell = theInstance.getCell(row, col);
				
				
				//if the current tile is a Walkway, connect all adjacent walkways
				if(currentCell.getChar() == 'W') { 
					if (row - 1 >= 0 && theInstance.getCell(row-1, col).getChar() == 'W' ) {
						currentCell.addAdj(grid[row - 1][col]);
					}

					if (col - 1 >= 0 && theInstance.getCell(row, col-1).getChar() == 'W') {
						currentCell.addAdj(grid[row][col-1]);
					}

					if (row + 1 < numRows  && theInstance.getCell(row+1, col).getChar() == 'W') {
						currentCell.addAdj(grid[row+1][col]);
					}

					if (col + 1 < numColumns  && theInstance.getCell(row, col+1).getChar() == 'W') {
						currentCell.addAdj(grid[row][col+1]);
					}
					//if Walkway is a doorway, find out which room it points to, and assign that room's center to the adj list
					if(currentCell.isDoorway()) {
						Room roomCell = theInstance.getRoom(theInstance.getCell(row, col-1));
						switch(currentCell.getDoorDirection()) {
						case DoorDirection.UP:
							roomCell = theInstance.getRoom(theInstance.getCell(row-1, col));
							currentCell.addAdj(roomCell.getCenterCell());
							roomCell.getCenterCell().addAdj(currentCell);
						break;
						case DoorDirection.DOWN:
							roomCell = theInstance.getRoom(theInstance.getCell(row+1, col));
							currentCell.addAdj(roomCell.getCenterCell());
							roomCell.getCenterCell().addAdj(currentCell);
						break;
						case DoorDirection.LEFT:
							roomCell = theInstance.getRoom(theInstance.getCell(row, col-1));
							currentCell.addAdj(roomCell.getCenterCell());
							roomCell.getCenterCell().addAdj(currentCell);
						break;
						case DoorDirection.RIGHT:
							roomCell = theInstance.getRoom(theInstance.getCell(row, col+1));
							currentCell.addAdj(roomCell.getCenterCell());
							roomCell.getCenterCell().addAdj(currentCell);
						break;
						case NONE:
						break;
						}
					}
				} else if (currentCell.isSecretPassage()){
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
	
	
	//getTargets - return the board's targetList
		public Set<BoardCell> getTargets() {
			return targetList;
		}
	
	public Set<BoardCell> getAdjList(int row, int col) {
		return this.grid[row][col].getAdjList();
	}

	
	//setConfigFiles - set the config .csv and .txt files
		public void setConfigFiles(String csv, String txt) {
			theInstance.layoutConfigFile = "data/" + csv;
			theInstance.setupConfigFile = "data/" + txt;

		}

		// SETTERS

		// GETTERS
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
}
