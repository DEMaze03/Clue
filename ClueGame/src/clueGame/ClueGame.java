package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ClueGame extends JFrame {
	HumanPlayer player = new HumanPlayer("Waluigi", "Purple", 0, 0, true);
	Card card = new Card("McDonalds Bathroom", CardType.ROOM);
	
	public ClueGame() {
		player.updateHand(card);
		
		setSize(800, 800);
		setTitle("Clue Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel control = new GameControlPanel();
		JPanel cards = new GameCardPanel(player);
		JPanel board = new JPanel();
		board.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Board Panel:"));
		((GameCardPanel) cards).updatePanels();
		cards.setVisible(true);
		setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);
		add(cards, BorderLayout.EAST);
		
	}
	
	public static void main(String[] args) {

		
		JFrame game = new ClueGame();
		game.setVisible(true);
	}

}
