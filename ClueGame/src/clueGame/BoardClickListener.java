/*
 * BoardClickListener - action listener for the when you click on the board. Implements human player Movement
 * Authors: Daylon Maze & Elijas Sliva
 */


package clueGame;

import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class BoardClickListener implements MouseListener {
	Board board = Board.getInstance();
	@Override
	public void mouseClicked(MouseEvent e) {
		HumanPlayer player = (HumanPlayer) board.getHuman();
		if(player.getTurnStatus() == false) {
			int x = e.getX();
	        int y = e.getY();
	        int cellWidth = (board.getWidth()/board.getNumColumns());
			int cellHeight = (board.getHeight()/board.getNumRows());
			//check to ensure cell width and height are equal
			if (cellWidth > cellHeight) {
				cellWidth = cellHeight;
			}
			if (cellHeight > cellWidth) {
				cellHeight = cellWidth;
			}
	        int row = y / cellHeight;
	        int col = x / cellWidth;
	        if(row < board.getNumRows() && col < board.getNumColumns()) {
		        if(board.getCell(row, col).getTargetFlag()) {
		        	player.movePlayer(board, board.getCell(row, col).getTargetCell().getRow(), board.getCell(row, col).getTargetCell().getCol());
		        	player.setTurnStatus(true);
		        	board.resetRoomTargets(board.getCell(row, col));
		        	for(BoardCell cell : player.getTargetList()) {
		        		cell.setTargetFlag(false);
		        		board.repaint();
		        	}
		        	// have suggestion here if player is in a room;
		        	if (board.getCell(player.getRow(), player.getCol()).isARoom()) {
		        		JDialog suggestion = new GameSuggestionPanel(board);
		        		suggestion.setVisible(true);
		        	}
		        }else {
		        	JOptionPane.showMessageDialog(null, "Oops! That's not a valid cell!", "A Message From Within...", JOptionPane.INFORMATION_MESSAGE);
		        }	
	        }
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
