package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class GameControlPanel extends JPanel {
	
	
	public GameControlPanel() {
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		GameControlPanel gamePanel = new GameControlPanel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setContentPane(gamePanel);
		
//		JButton button1 = new JButton();
//		button1.setText("button1");
//		button1.setSize(200,  200);
//		button1.setVisible(true);
		
		JLabel turnLabel = new JLabel("Who's Turn?");
		turnLabel.setVisible(true);
		JTextField turnTextField = new JTextField();
		
		JPanel turnPanel = new JPanel();
		turnPanel.setLayout(new GridLayout(2, 2));
		turnPanel.add(turnLabel, BorderLayout.NORTH);
		turnPanel.add(turnTextField, BorderLayout.CENTER);
		
		
//		JButton button2 = new JButton();
//		button2.setText("button2");
//		button2.setSize(200,  200);
//		button2.setVisible(true);
		
		JLabel rollLabel = new JLabel("Roll: ");
		rollLabel.setVisible(true);
		JTextField rollTextField = new JTextField();
		JPanel rollPanel = new JPanel();
		rollPanel.setLayout(new GridLayout(1, 2));
		rollPanel.add(rollLabel, BorderLayout.NORTH);
		rollPanel.add(rollTextField, BorderLayout.CENTER);
		
		JButton button3 = new JButton();
		button3.setText("make  accusation");
		button3.setSize(200,  200);
		button3.setVisible(true);
		
		JButton button4 = new JButton();
		button4.setText("NEXT");
		button4.setSize(200,  200);
		button4.setVisible(true);
	

		frame.setLayout(new GridLayout(2, 1));
		JPanel twinPane = new JPanel();
		twinPane.setLayout(new GridLayout(1,4));
		
		twinPane.add(turnPanel);
		twinPane.add(rollPanel);
		twinPane.add(button3);
		twinPane.add(button4);
		
		////
		JTextField guess = new JTextField();
		JLabel guessLabel = new JLabel("Make a guess");
		JPanel guessPanel = new JPanel(new GridLayout(1,0));
		guessPanel.add(guessLabel, BorderLayout.NORTH);
		guessPanel.add(guess, BorderLayout.CENTER);
		
		JTextField guessResult = new JTextField();
		JLabel guessResultLabel = new JLabel("Guess Result: ");
		JPanel guessResultPanel = new JPanel(new GridLayout(1,0));
		guessResultPanel.add(guessResultLabel, BorderLayout.NORTH);
		guessResultPanel.add(guessResult, BorderLayout.CENTER);
		
		
		JPanel lowerGuessResult = new JPanel();
		lowerGuessResult.setLayout(new GridLayout(1,2));
		lowerGuessResult.add(guessPanel);
		lowerGuessResult.add(guessResultPanel);
		
		frame.add(twinPane, BorderLayout.NORTH);
		frame.add(lowerGuessResult, BorderLayout.EAST);
		


//		twinPane.setLayout(new GridLayout(1, 2));
		
		frame.setSize(750, 180);  // size the frame
		
//		frame.add(button);
		
		
		frame.setVisible(true); // make it visible
	}
}