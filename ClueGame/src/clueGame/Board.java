package clueGame;

import java.util.Map;

public class Board {
	private BoardCell[][] grid;
	private int numRows;
	private int numColumns;
	private String layoutConfigFile;
	private String setupConfigFile;
	private Map<Character, Room> roomMap;
	static Board theInstance = new Board();
	
	private Board() {
		super();
	}
	

	public static Board getInstance() {
		return theInstance;
	}
	
	
	public void initialize() {
		
	}

	public void loadSetupConfig() {
		
	}
	
	public void loadLayoutConfig() {
		
	}


	public void setConfigFiles(String csv, String txt) {
		// TODO Auto-generated method stub
		theInstance.layoutConfigFile = csv;
		theInstance.setupConfigFile = txt;
		
	}
}
