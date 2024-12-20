/*
 * Board - board class for Clue game
 * Author: Elijas Sliva & Daylon Maze
 */

package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.random.*;

import javax.swing.JPanel;

public class Board extends JPanel{
	static Board theInstance = new Board();
	static int currentPlayerIdx = 0;
	private Map<Character, Room> roomMap = new HashMap<Character, Room>();
	//private Set<BoardCell> adjList = new HashSet<BoardCell>();
	private int numRows;
	private int numColumns;
	private BoardCell[][] grid;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Set<BoardCell> targetList = new HashSet<>();
	private Set<BoardCell> visitedList = new HashSet<BoardCell>();
	private Map<String, Card> deck = new HashMap<String, Card>();
	private Map<String, Player> players = new HashMap<String, Player>();
	private ArrayList<String> playerStr = new ArrayList<String>();
	private ArrayList<String> weaponStr = new ArrayList<String>();
	private ArrayList<String> roomStr = new ArrayList<String>();
	private ArrayList<String> roomsToPick = new ArrayList<String>();
	private ArrayList<String> peopleToPick = new ArrayList<String>();
	private ArrayList<String> weaponsToPick = new ArrayList<String>();
	private Solution theAnswer = new Solution(new Card("Venture Center", CardType.ROOM), new Card("Mines Parking", CardType.PERSON), new Card("Barrel of Rum", CardType.WEAPON));
	private Player human;
	private int roll;
	
	

	private Board() {
		super();
	}

	//getInstance - getter for the instance of the game board
	public static Board getInstance() {
		return theInstance;
	}

	//initialize - method to initialize the game board by calling loadSetupConfig and loadLayoutConfig
	public void initialize() {
		deck = new HashMap<String, Card>(); 
		
		roomsToPick.clear();
		weaponsToPick.clear();
		peopleToPick.clear();
		
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
		
		deal();

	}

	//loadSetupConfig - method to load the setup configuration file for the board. Throws BadConfigFormatException if file format is bad
	public void loadSetupConfig() throws BadConfigFormatException {

		File setupFile = new File(setupConfigFile);
		try {
			Scanner reader = new Scanner(setupFile);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				String[] word = data.split(", ");
				
				if (word.length == 1 ) { // continue over empty lines
					continue;
				}
				
				if (word[0].charAt(0) == '/') { // continue over comments in file
					continue;
				}
				
				
				if (word[0].equals("Room")) {
					
					char character = word[2].charAt(0);
					String roomName = word[1];

					Room room = new Room(roomName);
					roomMap.put(Character.valueOf(character), room);
					Card roomCard = new Card(roomName, CardType.ROOM);
					deck.put(roomName, roomCard);
					roomsToPick.add(roomName);
					roomStr.add(roomName);
				} 
				
				else if (word[0].equals("Space")) {
					char character = word[2].charAt(0);
					String roomName = word[1];

					Room room = new Room(roomName);
					roomMap.put(Character.valueOf(character), room);
				}
				
				else if (word[0].equals("Player")) {
					String playerName = word[1];
					String playerColor = word[2];
					int playerRow = Integer.parseInt(word[3]);
					int playerColumn = Integer.parseInt(word[4]);
					if(word[5].equals("true")) {
						Player player = new HumanPlayer(playerName,playerColor,playerRow,playerColumn,true);
						this.human = player;
						players.put(playerName, player);
						player.setRoomOffset(players.size());
					}else {
						Player player = new ComputerPlayer(playerName,playerColor,playerRow,playerColumn,false);
						players.put(playerName, player);
						player.setRoomOffset(players.size());
					}
					playerStr.add(playerName);
					
					Card playerCard = new Card(playerName, CardType.PERSON);
					deck.put(playerName, playerCard);
					peopleToPick.add(playerName);
					
				}else if (word[0].equals("Weapon")) {
					String weaponName = word[1];
					Card weaponCard = new Card(weaponName, CardType.WEAPON);
					deck.put(weaponName, weaponCard);
					weaponsToPick.add(weaponName);
					weaponStr.add(weaponName);
				}else{
					throw new BadConfigFormatException("invalid room type");
				}
			}
			reader.close();
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
			Scanner reader = new Scanner(layoutFile);
			while (reader.hasNextLine()) {
			
				String data = reader.nextLine();
				String[] dataList = data.split(",");
			
				for (colCount = 0; colCount < numColumns; colCount++) {
					grid[rowCount][colCount] = new BoardCell(rowCount, colCount);
					
				
					try {
						grid[rowCount][colCount].setChar(dataList[colCount].charAt(0)); // if the cell is empty, this will throw something that will need to be handled
					} catch (ArrayIndexOutOfBoundsException e) {
						throw new BadConfigFormatException("Bad config file");
					}
					
					if (roomMap.containsKey(dataList[colCount].charAt(0)) != true) {
						throw new BadConfigFormatException("unknown map character");
					}
			
					
					if (dataList[colCount].length() == 2) {
						if (dataList[colCount].charAt(1) == '#') { // if cell is label
							grid[rowCount][colCount].setLabel(true);
							grid[rowCount][colCount].setRoom(true);
							Room room = roomMap.get(dataList[colCount].charAt(0));
							room.setLabelCell(grid[rowCount][colCount]);
							
						} else if (dataList[colCount].charAt(1) == '*') {
							grid[rowCount][colCount].setRoomCenter(true);
							grid[rowCount][colCount].setRoom(true);
							Room room = roomMap.get(dataList[colCount].charAt(0));
							room.setCenterCell(grid[rowCount][colCount]);
						
						} else if (dataList[colCount].charAt(1) == '^') {
							grid[rowCount][colCount].setDoorway(true);
							grid[rowCount][colCount].setDoorDirection(DoorDirection.UP);
							
						} else if (dataList[colCount].charAt(1) == 'v') {
							grid[rowCount][colCount].setDoorway(true);
							grid[rowCount][colCount].setDoorDirection(DoorDirection.DOWN);
						} else if (dataList[colCount].charAt(1) == '<') {
							grid[rowCount][colCount].setDoorway(true);
							grid[rowCount][colCount].setDoorDirection(DoorDirection.LEFT);
				
						} else if (dataList[colCount].charAt(1) == '>') {
							grid[rowCount][colCount].setDoorway(true);
							grid[rowCount][colCount].setDoorDirection(DoorDirection.RIGHT);
				
						} else if (dataList[colCount].charAt(1) != '^' && dataList[colCount].charAt(1) != 'v' && dataList[colCount].charAt(1) != '<' && dataList[colCount].charAt(1) != '>'){
							grid[rowCount][colCount].setSecretPassage(dataList[colCount].charAt(1));
						}
						
					
					}
					if (dataList[colCount].length() == 1) {
						if(dataList[colCount].charAt(0) != 'W' && dataList[colCount].charAt(0) != 'X') {
							grid[rowCount][colCount].setRoom(true);
						}
					}
				}
				rowCount++;
				
			}
			reader.close();
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
	
	
	//deal- Deal all the cards into each player's hand
	public void deal() {
		//Calculate a random room, person, and weapon for the solution class
		int solutionIndex = (int) ((Math.random() * ((roomsToPick.size()-1) - 0)) + 0);
		String solutionRoom = roomsToPick.get(solutionIndex);
		solutionIndex = (int) ((Math.random() * ((peopleToPick.size()-1) - 0)) + 0);
		String solutionPerson = peopleToPick.get(solutionIndex);
		solutionIndex = (int) ((Math.random() * ((weaponsToPick.size()-1) - 0)) + 0);
		String solutionWeapon = weaponsToPick.get(solutionIndex);
		//use the calculated room, person, and weapon for the constructor of the solution class
		theAnswer = new Solution(deck.get(solutionRoom), deck.get(solutionPerson), deck.get(solutionWeapon));
		
		//Add all the cards from the map into an arrayList to help deal out the cards
		ArrayList<Card> cards = new ArrayList<Card>();
				for (Map.Entry<String,Card> entry : deck.entrySet()) {
					//add all cards to the arrayList except for those in the solution.
					if ((entry.getKey().equals(solutionRoom) == false) && (entry.getKey().equals(solutionPerson) == false) && (entry.getKey().equals(solutionWeapon) == false)) {
				    	cards.add(deck.get(entry.getKey()));
				    	
					}
				}
				//deal out all cards evenly to all players (for each player, give them a random card from the arrayList, then remove the card, continue until all
				//cards are gone.
				for(int card = 0; card < cards.size(); card++){
					 for(Map.Entry<String,Player> entry : players.entrySet()){
						int index = (int) ((Math.random() * ((cards.size()-1) - 0)) + 0);
						players.get(entry.getKey()).updateHand(cards.get(index));
						cards.remove(index);
						if(cards.size() == 0) {
							return;
						}
					}
					 
				}
	}
	
	public boolean checkAccusation(Card room, Card person, Card weapon) {
		if (!room.equals(theAnswer.getRoom())) {
			return false;
		}
		
		if (!person.equals(theAnswer.getPerson())) {
			return false;
		}
		
		if (!weapon.equals(theAnswer.getWeapon())) {
			return false;
		}
		
		return true;
	}
	
	public Card handleSuggestion(Player suggester, Solution suggestion) {
		//do 2 loops to stop AI from spamming the same room over and over by disproving room first if possible
		for (var player : players.entrySet()) {
			
			if (player.getValue().getName().equals(suggester.getName())) {
				continue;
			}
			
			if (player.getValue().disproveSuggestion(suggestion.getRoom()) != (null)) {
				return suggestion.getRoom();
			}
		}
			for (var player : players.entrySet()) {
				
				if (player.getValue().getName().equals(suggester.getName())) {
					continue;
				}
			if (player.getValue().disproveSuggestion(suggestion.getPerson()) != (null)) {
				return suggestion.getPerson();
			}
			
			if (player.getValue().disproveSuggestion(suggestion.getWeapon()) != (null)) {
				return suggestion.getWeapon();
			}
		}
		
		return null;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		
		//calculate the height and width of each board cell
		int width = this.getWidth();
		int height = this.getHeight();
		int cellWidth = (width/numColumns);
		int cellHeight = (height/numRows);
		//check to ensure cell width and height are equal
		if (cellWidth > cellHeight) {
			cellWidth = cellHeight;
		}
		if (cellHeight > cellWidth) {
			cellHeight = cellWidth;
		}
		
		
		// draw all cells by looping through grid
		for(int col = 0; col < numColumns; col++) {
			for(int row = 0; row < numRows; row++) {
				grid[row][col].draw(g, (cellWidth*col), (cellHeight*row), cellHeight, cellWidth);
			}
		}
		
		// draw all room labels
		for(int col = 0; col < numColumns-1; col++) {
			for(int row = 0; row < numRows-1; row++) {
				if (grid[row][col].isLabel()) {
					Room room = theInstance.getRoom(grid[row][col]);
					room.draw(g, (cellWidth*col), (cellHeight*row), cellHeight, cellWidth);
				}
			}
		}
		
		// draw players
		for (Map.Entry<String, Player> player : players.entrySet()) {
			Player currPlayer = players.get(player.getKey());
			
			currPlayer.draw(g, (cellWidth*currPlayer.getCol()), (cellHeight*currPlayer.getRow()), cellHeight, cellWidth);
		}
		
		
		
		
		
	}
	
	public void roll() {
		roll = 1 + (int)(Math.random() * ((6 - 1) + 1));
	}
	
	public void resetRoomTargets(BoardCell cell) {
		char cellChar = cell.getChar();
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numColumns; col++) {
				if(grid[row][col].getChar() == cellChar || grid[row][col].getTargetFlag()) {
					grid[row][col].setTargetFlag(false);
					grid[row][col].setTargetCell(null);
				}
			}
		}
		this.repaint();
	}
	
	public int getRoll() {
		return roll;
	}
	
	
	//getTargets - return the board's targetList
	public Set<BoardCell> getTargets() {
			return targetList;
		}
	
	public Set<BoardCell> getAdjList(int row, int col) {
		return this.grid[row][col].getAdjList();
	}
	
	public ArrayList<String> getPlayerNames(){
		return playerStr;
	}

	public ArrayList<String> getWeaponNames(){
		return weaponStr;
	}
	
	public ArrayList<String> getRoomNames(){
		return roomStr;
	}
	
	//setConfigFiles - set the config .csv and .txt files
	public void setConfigFiles(String csv, String txt) {
			theInstance.layoutConfigFile = "data/" + csv;
			theInstance.setupConfigFile = "data/" + txt;

	}

	// SETTERS
	public void setRoomCellsTarget(BoardCell cell) {
		char cellChar = cell.getChar();
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numColumns; col++) {
				if(grid[row][col].getChar() == cellChar) {
					grid[row][col].setTargetFlag(true);
					grid[row][col].setTargetCell(cell);
				}
			}
		}
		this.repaint();
	}

	// GETTERS
	public Player getHuman() {
		return this.human;
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
	
	public Map<String, Card> getDeck() {
		return deck;
	}
	
	public Map<String, Player> getPlayers() {
		return players;
	}
	public Player returnPlayer(String name) {
		return players.get(name);
	}
	public Solution getSolution() {
		return theAnswer;
	}
	public Player getCurrentPlayer() {
		Player currPlayer = human;
		String playerName = playerStr.get(currentPlayerIdx % playerStr.size());
		currPlayer = players.get(playerName);
		return currPlayer;
		
	}
	

}
