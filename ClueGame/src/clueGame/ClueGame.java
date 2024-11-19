package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ClueGame extends JFrame {
	static public Board board;
	
	public ClueGame() {
		
		
		setSize(1000, 800);
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");
		// Initialize will load BOTH config files
		board.initialize();
		
		GameControlPanel control = new GameControlPanel();
		board.roll();
		control.setTurn(board.getCurrentPlayer(), board.getRoll());
		JPanel cards = new GameCardPanel((HumanPlayer) board.getHuman());
		((GameCardPanel) cards).updatePanels();
		cards.setVisible(true);
		board.addMouseListener(new BoardClickListener());
		setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);
		add(cards, BorderLayout.EAST);
		
	}
	
	public static void main(String[] args) {
		
		JFrame game = new ClueGame();
		game.setVisible(true);
		String player = board.getHuman().getName();
		String playerCol = board.getHuman().getColor();
		JOptionPane.showMessageDialog(null, "You are " + player + " (" + playerCol + ").\nCan you find the solution before the computers do?", "Welcome To Clue", JOptionPane.INFORMATION_MESSAGE);

		
		
		
	}

}
