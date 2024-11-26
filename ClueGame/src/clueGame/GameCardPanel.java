/*
 * GameCardPanel - class to create a JPanel that displays the current card information about the game. Includes setters for game clarity
 * Authors: Daylon Maze & Elijas Sliva
 */

package clueGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class GameCardPanel extends JPanel {
	private Player player;
	private JPanel peoplePanel;
	private JPanel peopleHand;
	private JPanel peopleSeen;
	private JPanel roomPanel;
	private JPanel roomHand;
	private JPanel roomSeen;
	private JPanel weaponPanel;
	private JPanel weaponHand;
	private JPanel weaponSeen;
	private JPanel twinPane;
	private int roomHandRows = 1;
	private int peopleHandRows = 1;
	private int weaponsHandRows = 1;
	private int roomSeenRows = 1;
	private int peopleSeenRows = 1;
	private int weaponsSeenRows = 1;
	
	
	// default constructor 
	public GameCardPanel(HumanPlayer player) {
		
		setPreferredSize(new Dimension(180,750));
		// create known card panel
		this.player = player;
		this.setLayout(new GridLayout(1,0));
		twinPane = new JPanel();
		twinPane.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Known Cards"));
		twinPane.setLayout(new GridLayout(3,0));
		
		
		// create known people category
		peoplePanel = new JPanel();
		peoplePanel.setLayout(new GridLayout(4,0));
		peoplePanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Known People:"));
		JLabel pSeen = new JLabel("Seen:");
		JLabel pInHand = new JLabel("In Hand:");
		twinPane.add(peoplePanel);
		peoplePanel.add(pInHand);
		peopleHand = new JPanel();
		peoplePanel.add(peopleHand);
		peoplePanel.add(pSeen);
		peopleSeen = new JPanel();
		peoplePanel.add(peopleSeen);
		
		// create known room category
		roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(4,0));
		roomPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Known Rooms:"));
		JLabel rSeen = new JLabel("Seen:");
		JLabel rInHand = new JLabel("In Hand:");
		twinPane.add(roomPanel);
		roomPanel.add(rInHand);
		roomHand = new JPanel();
		roomPanel.add(roomHand);
		roomPanel.add(rSeen);
		roomSeen = new JPanel();
		roomPanel.add(roomSeen);
		
		// create known weapon category
		weaponPanel = new JPanel();
		weaponPanel.setLayout(new GridLayout(4,0));
		weaponPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Known Weapons:"));
		JLabel wSeen = new JLabel("Seen:");
		JLabel wInHand = new JLabel("In Hand:");
		twinPane.add(weaponPanel);
		weaponPanel.add(wInHand);
		weaponHand = new JPanel();
		weaponPanel.add(weaponHand);
		weaponPanel.add(wSeen);
		weaponSeen = new JPanel();
		weaponPanel.add(weaponSeen);
		
		// for every card in the player hand, add it to the correct category.
		addHand();
	}
	
	// refresh panel function- removes every element, updates them with the current information, and re-adds them
	public void updatePanels() {
		// remove all cards
		roomSeen.removeAll();
		roomHand.removeAll();
		peopleSeen.removeAll();
		peopleHand.removeAll();
		weaponSeen.removeAll();
		weaponHand.removeAll();
		
		//add the seen cards and the cards in-hand to their respective panels.
		addSeen();
		addHand();
		
		//re-add each panel to the twin panel, then add the twin panel to the card panel
		twinPane.add(peoplePanel, BorderLayout.NORTH);
		twinPane.add(roomPanel, BorderLayout.NORTH);
		twinPane.add(weaponPanel, BorderLayout.NORTH);	
		this.add( twinPane, BorderLayout.NORTH );
		
	}
	
	public void addHand() {
		
		//reset rows for the in-hand cards
		roomHandRows = 0;
		peopleHandRows = 0;
		weaponsHandRows = 0;
		
		//update the number of rows needed for the cards in-hand panels
		for(Card handCard : player.getCards()) {
			switch(handCard.getCardType()) {
			case CardType.ROOM:
				roomHandRows++;
			break;
			case CardType.PERSON:
				peopleHandRows++;
			break;
			case CardType.WEAPON:
				weaponsHandRows++;
			break;
			}
		}
		
		for(Card card : player.getCards()) {
			
			switch(card.getCardType()) {
			case CardType.ROOM:
				roomHand.setLayout(new GridLayout(roomHandRows,0));
				JTextField roomField = new JTextField(card.getCardName());
				roomField.setEditable(false);
				roomField.setBackground(card.getOwner().getColorObject());
				roomHand.add(roomField);
				
			break;
			case CardType.PERSON:
				peopleHand.setLayout(new GridLayout(peopleHandRows,0));
				JTextField personField = new JTextField(card.getCardName());
				personField.setEditable(false);
				personField.setBackground(card.getOwner().getColorObject());
				peopleHand.add(personField);
			break;
			case CardType.WEAPON:
				weaponHand.setLayout(new GridLayout(weaponsHandRows,0));
				JTextField weaponField = new JTextField(card.getCardName());
				weaponField.setEditable(false);
				weaponField.setBackground(card.getOwner().getColorObject());
				weaponHand.add(weaponField);
			break;
			}
	}
	}
	
	public void addSeen() {
		
		//reset the rows for the seen cards
				roomSeenRows = 0;
				peopleSeenRows = 0;
				weaponsSeenRows = 0;
		
		//update the number of rows needed for the cards seen panels
				for(Card card : player.getSeenCards()) {
					switch(card.getCardType()) {
					case CardType.ROOM:
						roomSeenRows++;
					break;
					case CardType.PERSON:
						peopleSeenRows++;
					break;
					case CardType.WEAPON:
						weaponsSeenRows++;
					break;
					}
				}
		
		for(Card card : player.getSeenCards()) {
			if((player.getCards().contains(card)) == false){
				switch(card.getCardType()) {
				case CardType.ROOM:
					roomSeen.setLayout(new GridLayout(roomSeenRows,0));
					JTextField roomField = new JTextField(card.getCardName(),15);
					
					roomField.setEditable(false);
					roomField.setBackground(card.getOwner().getColorObject());
					roomSeen.add(roomField, BorderLayout.NORTH);
					
				break;
				case CardType.PERSON:
					peopleSeen.setLayout(new GridLayout(peopleSeenRows,0));
					JTextField personField = new JTextField(card.getCardName(),15);
					personField.setEditable(false);
					personField.setBackground(card.getOwner().getColorObject());
					peopleSeen.add(personField, BorderLayout.NORTH);
				break;
				case CardType.WEAPON:
					weaponSeen.setLayout(new GridLayout(weaponsSeenRows,0));
					JTextField weaponField = new JTextField(card.getCardName(),15);
					weaponField.setEditable(false);
					weaponField.setBackground(card.getOwner().getColorObject());
					weaponSeen.add(weaponField, BorderLayout.NORTH);
				break;
				}
			}
		}
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		
		HumanPlayer player = new HumanPlayer("Waluigi", "Purple", 0, 0, true);
		Player player2 = new ComputerPlayer("Wario", "Yellow", 1,1,false);
		
		Player player3 = new ComputerPlayer("Craig","Red",2,2,false);
		Card seenCard2 = new Card("Wendys Chili", CardType.WEAPON);
		Card seenCard3 = new Card("Waluigi", CardType.PERSON);
		player3.updateHand(seenCard2);
		player3.updateHand(seenCard3);
		
		Card card = new Card("McDonalds Bathroom", CardType.ROOM);
		Card seenCard = new Card("Big Mac", CardType.WEAPON);
		GameCardPanel cardPanel = new GameCardPanel(player);
		player.updateHand(card);
		player2.updateHand(seenCard);
		player.updateSeenCard(seenCard);
		
		cardPanel.updatePanels();
		player.updateSeenCard(seenCard2);
		player.updateSeenCard(seenCard3);
		cardPanel.updatePanels();
		frame.setContentPane(cardPanel);
		frame.setSize(180, 750);  // size the frame
		frame.setVisible(true); // make it visible
		
		
		//cardPanel.updatePanels();
		
		
		
		
	}
}
