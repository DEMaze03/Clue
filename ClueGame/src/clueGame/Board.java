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
	private Map<Character, Room> roomMap = new HashMap<Character, Room>();
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
		
		grid = new BoardCell[numRows][numColumns];
		
		for (int row = 0; row < this.getNumRows(); row++) {
			for (int col = 0; col < this.getNumColumns(); col++) {
//				System.out.printf("(%d, %d)", row, col);
				grid[row][col] = new BoardCell(row, col);
			}
			
//			System.out.println();
		}
		

		
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
		        	 
		        	 System.out.println(c  + " " + rn);
		        	 Room room = new Room(rn);
		        	 roomMap.put(c, room);
		         }
		     }
		      r.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
	}
	
	public void loadLayoutConfig() {
		
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
		return grid[row][col];
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
