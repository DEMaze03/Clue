package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;
import clueGame.Room;

class FileInitTests {
	
	public static final int NUM_ROWS = 27;
	public static final int NUM_COLUMNS = 33;
	
	private static Board board;
	
	@BeforeAll
	public static void setUp() {
		// singleton instance
		board = Board.getInstance();
		// set filenames
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize both config files
		board.initialize();
	}

	@Test
	void testRoomLabels() {
		assertEquals("Marquez", board.getRoom('M').getName() );
		assertEquals("Unused", board.getRoom('X').getName() );
		assertEquals("Elm", board.getRoom('E').getName() );
		assertEquals("Venture", board.getRoom('V').getName() );
		assertEquals("CoorsTek", board.getRoom('C').getName() );
	}
	
	@Test
	void testBoardDimensions() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	
	@Test
	void testDoorDirections() {
		BoardCell cell = board.getCell(9, 6);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.LEFT, cell.getDoorDirection());
		
		cell = board.getCell(23, 8);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.RIGHT, cell.getDoorDirection());
		
		cell = board.getCell(14, 27);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.DOWN, cell.getDoorDirection());
		
		cell = board.getCell(13, 2);
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.UP, cell.getDoorDirection());
	}
	
	@Test
	void testNumberofDoors() {
		int doorCount = 0;
		for (int rows = 0; rows < board.getNumRows(); rows++ ) {
			for (int cols = 0; cols < board.getNumColumns(); cols++) {
				if (board.getCell(rows, cols).isDoorway()) {
					doorCount++;
				}
			}
		}
		Assert.assertEquals(11, doorCount);
	}
	
	@Test
	void testCellInitial() {
		//Test W tile
		BoardCell cell = board.getCell(7, 8);
		Room room = board.getRoom( cell ) ;
		assertTrue( room != null );
		assertEquals( room.getName(), "Walkway" ) ;
		assertFalse( cell.isRoomCenter() );
		assertFalse( cell.isLabel() );
		
		//Test X tiles
		cell = board.getCell(7, 15);
		room = board.getRoom(cell);
		assertTrue( room != null );
		assertEquals( room.getName(), "Unused" ) ;
		assertFalse( cell.isRoomCenter() );
		assertFalse( cell.isLabel() );
		
		//Test G tile
		cell = board.getCell(14, 9);
		room = board.getRoom(cell);
		assertTrue( room != null );
		assertEquals( room.getName(), "Guggenheim" ) ;
		assertFalse( cell.isRoomCenter() );
		assertFalse( cell.isLabel() );
		
		//Test Upway door tile
		cell = board.getCell(22, 13);
		room = board.getRoom(cell);
		assertTrue( room != null );
		assertEquals( room.getName(), "Doorway" ) ;
		assertFalse( cell.isRoomCenter() );
		assertFalse( cell.isLabel() );
		assertTrue(cell.isDoorway());
		assertEquals(DoorDirection.UP, cell.getDoorDirection());
		
	}
	


}
