package clueGame;

import java.awt.*;

import javax.swing.*;

public class GameCardPanel extends JPanel{
	private JPanel peoplePanel = new JPanel();
	private JPanel peopleInHand = new JPanel();
	private JTextField peopleSeen = new JTextField();
	private JPanel roomPanel = new JPanel();
	private JTextField roomInHand = new JTextField();
	private JTextField roomSeen = new JTextField();
	private JTextField weaponInHand = new JTextField();
	private JTextField weaponSeen = new JTextField();
	private JPanel weaponPanel = new JPanel();
	JLabel cardLabel = new JLabel();
	JPanel cardPanel = new JPanel();
	
	public GameCardPanel() {
		
		peoplePanel.setSize(180, 300);
		peopleInHand.setLayout(new GridLayout(1,1));
		peopleInHand.add(new JTextField());
		
		peoplePanel.setLayout(new GridLayout(2,1));
		peoplePanel.add(peopleInHand, BorderLayout.CENTER);
		peoplePanel.add(peopleSeen, BorderLayout.CENTER);
		peoplePanel.setBorder(BorderFactory.createTitledBorder("Known People"));
		
		
		roomPanel.setSize(180, 300);
		roomInHand.setEditable(false);
		roomPanel.setLayout(new GridLayout(2,1));
		roomPanel.add(roomInHand, BorderLayout.CENTER);
		roomPanel.add(roomSeen, BorderLayout.CENTER);
		roomPanel.setBorder(BorderFactory.createTitledBorder("Known Rooms"));
		
		weaponPanel.setSize(180, 300);
		weaponPanel.setLayout(new GridLayout(2,1));
		weaponPanel.add(weaponInHand, BorderLayout.CENTER);
		weaponPanel.add(weaponSeen, BorderLayout.CENTER);
		weaponPanel.setBorder(BorderFactory.createTitledBorder("Known Weapons"));

		
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
