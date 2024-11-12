package clueGame;

import java.awt.*;

import javax.swing.*;

public class GameCardPanel extends JPanel{
	
	public GameCardPanel() {
		
		JPanel peoplePanel = new JPanel();
		peoplePanel.setSize(180, 300);
		JTextField peopleInHand = new JTextField();
		JTextField peopleSeen = new JTextField();
		peoplePanel.setLayout(new GridLayout(2,1));
		peoplePanel.add(peopleInHand, BorderLayout.CENTER);
		peoplePanel.add(peopleSeen, BorderLayout.CENTER);
		peoplePanel.setBorder(BorderFactory.createTitledBorder("Known People"));
		
		
		JPanel roomPanel = new JPanel();
		roomPanel.setSize(180, 300);
		JTextField roomInHand = new JTextField();
		JTextField roomSeen = new JTextField();
		roomPanel.setLayout(new GridLayout(2,1));
		roomPanel.add(roomInHand, BorderLayout.CENTER);
		roomPanel.add(roomSeen, BorderLayout.CENTER);
		roomPanel.setBorder(BorderFactory.createTitledBorder("Known Rooms"));
		
		JPanel weaponPanel = new JPanel();
		weaponPanel.setSize(180, 300);
		JTextField weaponInHand = new JTextField();
		JTextField weaponSeen = new JTextField();
		weaponPanel.setLayout(new GridLayout(2,1));
		weaponPanel.add(weaponInHand, BorderLayout.CENTER);
		weaponPanel.add(weaponSeen, BorderLayout.CENTER);
		weaponPanel.setBorder(BorderFactory.createTitledBorder("Known Weapons"));

		
		JLabel cardLabel = new JLabel();
		JPanel cardPanel = new JPanel();
		
		cardLabel.setText("Known Cards");		
		cardPanel.setLayout(new GridLayout(3, 1));
		cardPanel.setSize(180, 700);
		cardPanel.add(peoplePanel);
		cardPanel.add(roomPanel);
		cardPanel.add(weaponPanel);
		cardPanel.setBorder(BorderFactory.createTitledBorder("Known Cards"));
		cardPanel.setVisible(true);
		
		this.setLayout(new BorderLayout());
		this.add(cardPanel);
		
		
	}
	

	public static void main(String[] args) {
		

		GameCardPanel gamePanel = new GameCardPanel();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setContentPane(gamePanel);
		frame.setSize(180, 750);  // size the frame
		frame.setVisible(true); // make it visible
		

		
	}
	

}
