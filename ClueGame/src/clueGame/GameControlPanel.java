/*
 * GameControlPanel - class to create a JPanel that houses the control panel for the game. Includes setters for game clarity
 * Authors: Daylon Maze & Elijas Sliva
 */

package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameControlPanel extends JPanel {
	JLabel turnLabel;
	JTextField turnTextField;
	JPanel turnPanel;
	JTextField guess;
	JLabel rollLabel;
	JTextField rollTextField;
	JPanel rollPanel;
	JLabel guessLabel;
	JPanel guessPanel;
	JTextField guessResult;
	JLabel guessResultLabel;
	JPanel guessResultPanel;
	JPanel lowerGuessResult;
	
	public GameControlPanel() {

		turnLabel = new JLabel("Who's Turn?");
		turnLabel.setVisible(true);
		turnTextField = new JTextField();
		turnTextField.setEditable(false);
		
		turnPanel = new JPanel();
		turnPanel.setLayout(new GridLayout(2, 2));
		turnPanel.add(turnLabel, BorderLayout.NORTH);
		turnPanel.add(turnTextField, BorderLayout.CENTER);
		
		rollLabel = new JLabel("Roll: ");
		rollLabel.setVisible(true);
		rollTextField = new JTextField();
		rollTextField.setEditable(false);
		rollPanel = new JPanel();
		rollPanel.setLayout(new GridLayout(1, 2));
		rollPanel.add(rollLabel, BorderLayout.NORTH);
		rollPanel.add(rollTextField, BorderLayout.CENTER);
		
		JButton button3 = new JButton();
		button3.setText("Make  Accusation");
		button3.setSize(200,  200);
		button3.setVisible(true);
		button3.addActionListener(new ActionListenerAccusation());
		
		
		JButton button4 = new JButton();
		button4.setText("NEXT");
		button4.setSize(200,  200);
		button4.setVisible(true);
		button4.addActionListener(new ActionListenerNext(this));
	

		this.setLayout(new GridLayout(2, 1));
		JPanel twinPane = new JPanel();
		twinPane.setLayout(new GridLayout(1,4));
		
		twinPane.add(turnPanel);
		twinPane.add(rollPanel);
		twinPane.add(button3);
		twinPane.add(button4);
		
		guess = new JTextField();
		guess.setEditable(false);
		guessLabel = new JLabel("Guess: ");
		guessPanel = new JPanel(new GridLayout(1,0));
		guessPanel.add(guessLabel, BorderLayout.NORTH);
		guessPanel.add(guess, BorderLayout.CENTER);
		
		guessResult = new JTextField();
		guessResult.setEditable(false);
		guessResultLabel = new JLabel("Guess Result: ");
		guessResultPanel = new JPanel(new GridLayout(1,0));
		guessResultPanel.add(guessResultLabel, BorderLayout.NORTH);
		guessResultPanel.add(guessResult, BorderLayout.CENTER);
		
		
		lowerGuessResult = new JPanel();
		lowerGuessResult.setLayout(new GridLayout(1,2));
		lowerGuessResult.add(guessPanel);
		lowerGuessResult.add(guessResultPanel);
		
		this.add(twinPane, BorderLayout.NORTH);
		this.add(lowerGuessResult, BorderLayout.EAST);
		
	}

	
	//setters
	public void setGuess(String person, String room, String weapon, Color color) {
		guess.setText(person + ", " + room + ", " + weapon);
		guess.setBackground(color);
	}
	
	public void setGuessResult(String resultText, Color color) {
		this.guessResult.setText(resultText);
		this.guessResult.setBackground(color);
	}
	
	public void setTurn(Player player, int roll) {
		this.rollTextField.setText(Integer.toString(roll));
		this.turnTextField.setText(player.getName());
		this.turnTextField.setBackground(player.getColorObject());
		Board board = Board.getInstance();
		if (board.getHuman() == player) {
			player.setTurnStatus(false);
		}
		player.selectTarget(board, roll);
	}
	
	
	
	public static void main(String[] args) {
		GameControlPanel gamePanel = new GameControlPanel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setContentPane(gamePanel);
		frame.setSize(750, 180);  // size the frame
		frame.setVisible(true); // make it visible
		
		// test filling in the data
				//gamePanel.setTurn(new ComputerPlayer( "Waluigi", "Purple", 0, 0, false), 5);
				gamePanel.setGuess( "Luigi", "Ghost House", "Blue Shell", Color.GREEN);
				gamePanel.setGuessResult( "So you have nothing?", Color.RED);
	}
}
