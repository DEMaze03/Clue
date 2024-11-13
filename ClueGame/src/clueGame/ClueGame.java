package clueGame;

import java.awt.BorderLayout;
import javax.swing.*;

public class ClueGame extends JFrame {
	private static Board board;
	HumanPlayer player = new HumanPlayer("Waluigi", "Purple", 0, 0, true);
	
	public ClueGame() {
		setSize(800, 800);
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		JPanel control = new GameControlPanel();
		JPanel cards = new GameCardPanel(player);
		cards.setVisible(true);
		setLayout(new BorderLayout());
		add(cards);
		setContentPane(cards);
//		add(control, BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args) {
		board = Board.getInstance();
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		board.initialize();
		
		JFrame game = new ClueGame();
		game.setVisible(true);
	}

}
