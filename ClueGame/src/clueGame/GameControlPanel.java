package clueGame;

import javax.swing.*;

public class GameControlPanel extends JPanel {
	
	
	public GameControlPanel() {
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		GameControlPanel panel = new GameControlPanel();
		JFrame frame = new JFrame();
		JButton button = new JButton();
		frame.setContentPane(panel);
		
		frame.setSize(750, 180);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		
		button.setText("This is a button");
		button.setSize(200,  200);
		button.setVisible(true);
		frame.add(button);
		
		
		frame.setVisible(true); // make it visible
	}
}
