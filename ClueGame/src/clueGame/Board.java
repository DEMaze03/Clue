package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private BoardCell[][] grid;
	private int numRows;
	private int numColumns;
	private String layoutConfigFile;
	private static String setupConfigFile;
	private static Map<Character, Room> roomMap = new HashMap<Character, Room>();
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

	public static void loadSetupConfig() {
		
		File setupFile = new File(setupConfigFile);
		try {
			Scanner r = new Scanner(setupFile);
		      while (r.hasNextLine()) {
		         String data = r.nextLine();
		         String[] l = data.split(", ");
		         if (l[0].equals("Room")) {
		        	 char c = l[2].charAt(0);
		        	 String rn = l[1];
		        	 Room room = new Room(rn);
		        	 roomMap.put(c, room);
		         }
		         
		     }
		      
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
	
	public int getNumRows() {
		return numRows;
	}


	public int getNumColumns() {
		return numColumns;
	}


	public static void main(String[] args) {
		Board board;
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout306.csv", "ClueSetup306.txt");
		// Initialize will load BOTH config files
		board.initialize();
		
		System.out.println(board.getRoom('C').getName());
		System.out.println("ROWS: " + board.getNumRows());
		System.out.println("COLS: " + board.getNumColumns());
		
		
	}


}
