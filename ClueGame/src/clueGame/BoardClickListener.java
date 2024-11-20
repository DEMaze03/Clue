package clueGame;

import java.awt.event.MouseEvent;
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
	        int row = y / cellHeight;
	        int col = x / cellWidth;
	        if(row < board.getNumRows() && col < board.getNumColumns()) {
		        if(board.getCell(row, col).getTargetFlag()) {
		        	player.movePlayer(board, row, col);
		        	player.setTurnStatus(true);
		        	for(BoardCell cell : player.getTargetList()) {
		        		cell.setTargetFlag(false);
		        		board.repaint();
		        	}
		        }else {
		        	JOptionPane.showMessageDialog(null, "Oops! That's not a valid cell!"+ row +","+col, "A Message From Within..."+ board.getCell(row, col).getTargetFlag(), JOptionPane.INFORMATION_MESSAGE);
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
