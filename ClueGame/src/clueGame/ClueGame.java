package clueGame;

import java.awt.BorderLayout;
import javax.swing.*;

public class ClueGame extends JFrame {
	HumanPlayer player = new HumanPlayer("Waluigi", "Purple", 0, 0, true);
	
	public ClueGame() {
		
		
		setSize(800, 800);
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel control = new GameControlPanel();
		JPanel cards = new GameCardPanel(player);
		cards.setVisible(true);
		setLayout(new BorderLayout());
		add(cards);
		add(control, BorderLayout.SOUTH);
		
	}
	
	public static void main(String[] args) {

		
		JFrame game = new ClueGame();
		game.setVisible(true);
	}

}
