package com.nitin.poker.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import com.nitin.poker.cards.Card;
import com.nitin.poker.cards.Deck;

public class PokerTable extends JFrame implements ActionListener {

	private static final long serialVersionUID = -3501948067318133104L;
	
	private static final int NUM_PLAYERS = 2;
	
	private static final String CARD_IMG_PATH = "images/cards/thumbnail.";
	
	private JPanel _communityCardsPanel;
	
	private JPanel _player1Panel;
	
	private JPanel _player2Panel;
	
	private JPanel _buttonPanel;
	
	private JButton _dealHoleCardsButton;
	
	private JButton _dealFlopButton;
	
	private JButton _dealTurnButton;
	
	private JButton _dealRiverButton;
	
	private JButton _newGameButton;
	
	private List<List<Card>> _playersHoleCards;
	
	private List<Card> _flop;
	
	private Card _turn;
	
	private Card _river;
	
	private Deck _deck;
	
	
	public static final int WIDTH = 900;
	public static final int HEIGHT = 600;
	
	public PokerTable() throws IOException {		
		newGame();
	}
	
	private void newGame() {
		_deck = new Deck();
		_deck.shuffle();
		_playersHoleCards = new ArrayList<List<Card>>();
		_flop = new ArrayList<>();
		for(int i = 0; i< NUM_PLAYERS; ++i) {
			_playersHoleCards.add(i, new ArrayList<>());
		}		
		
		setLayout(new BorderLayout());
		setTitle("Poker Table");
		setVisible(true);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int midWidth = (int) (dim.getWidth() - WIDTH) / 2;
		int midHeight = (int) (dim.getHeight() - HEIGHT) / 2;
		setLocation(midWidth, midHeight);
		
		_communityCardsPanel = new JPanel();
		_communityCardsPanel.setBorder(BorderFactory.createTitledBorder("Community Cards"));
		
		_player1Panel = new JPanel();
		_player1Panel.setBorder(BorderFactory.createTitledBorder("Player 1"));
		
		_player2Panel = new JPanel();
		_player2Panel.setBorder(BorderFactory.createTitledBorder("Player 2"));
		
		_buttonPanel = new JPanel();
		_dealHoleCardsButton = new JButton("Deal Hole Cards");
		_dealFlopButton = new JButton("Deal Flop");
		_dealTurnButton = new JButton("Deal Turn");
		_dealRiverButton = new JButton("Deal River");
		_newGameButton = new JButton("New Game");
		_dealHoleCardsButton.addActionListener(this);
		_dealFlopButton.addActionListener(this);
		_dealTurnButton.addActionListener(this);
		_dealRiverButton.addActionListener(this);
		_newGameButton.addActionListener(this);
		
		_buttonPanel.add(_newGameButton);
		_buttonPanel.add(_dealHoleCardsButton);
		_buttonPanel.add(_dealFlopButton);
		_buttonPanel.add(_dealTurnButton);
		_buttonPanel.add(_dealRiverButton);
		
		add(_communityCardsPanel, BorderLayout.CENTER);
		add(_player1Panel, BorderLayout.WEST);
		add(_player2Panel, BorderLayout.EAST);	
		add(_buttonPanel, BorderLayout.SOUTH);
		
		_player1Panel.revalidate();
		_player1Panel.repaint();
		_player2Panel.revalidate();
		_player2Panel.repaint();
		_communityCardsPanel.revalidate();
		_communityCardsPanel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(_newGameButton)) {
			System.out.println("New Game");
			_player1Panel.removeAll();
			_player2Panel.removeAll();
			for(int i = 0; i< NUM_PLAYERS; ++i) {
				_playersHoleCards.add(i, new ArrayList<>());
			}	
			_communityCardsPanel.removeAll();
			_flop = new ArrayList<>();			
			_turn = null;			
			_river = null;
			_deck = new Deck();
			_deck.shuffle();
			
			_player1Panel.revalidate();
			_player1Panel.repaint();
			_player2Panel.revalidate();
			_player2Panel.repaint();
			_communityCardsPanel.revalidate();
			_communityCardsPanel.repaint();
		}
		
		if(e.getSource().equals(_dealHoleCardsButton)) {
			System.out.println("Deal hole cards");
			if(_playersHoleCards.get(0).size() != 0 ) {
				return;
			}
			
			
			Card card = _deck.deal(1).get(0);
			_playersHoleCards.get(0).add(card);
			addCardLabelToPanel(card, _player1Panel);
			
			
			card = _deck.deal(1).get(0);
			_playersHoleCards.get(1).add(card);
			addCardLabelToPanel(card, _player2Panel);
			
			card = _deck.deal(1).get(0);
			_playersHoleCards.get(0).add(card);
			addCardLabelToPanel(card, _player1Panel);

			card = _deck.deal(1).get(0);
			_playersHoleCards.get(1).add(card);
			addCardLabelToPanel(card, _player2Panel);
			
			_player1Panel.revalidate();
			_player1Panel.repaint();
			_player2Panel.revalidate();
			_player2Panel.repaint();
	
		}
		
		if(e.getSource().equals(_dealFlopButton)) {
			System.out.println("flop");
			if(_flop.size() != 0) {
				return;
			}
			
			_flop = _deck.deal(3);
			for(Card card: _flop) {
				addCardLabelToPanel(card, _communityCardsPanel);
			}
			_communityCardsPanel.revalidate();
			_communityCardsPanel.repaint();
		}
		
		if(e.getSource().equals(_dealTurnButton)) {
			System.out.println("turn");
			if(_turn != null) {
				return;
			}
			_turn = _deck.deal(1).get(0);
			addCardLabelToPanel(_turn, _communityCardsPanel);
			_communityCardsPanel.revalidate();
			_communityCardsPanel.repaint();
			//pack();
		}
		
		if(e.getSource().equals(_dealRiverButton)) {
			System.out.println("river");
			if(_river != null) {
				return;
			}
			_river = _deck.deal(1).get(0);
			addCardLabelToPanel(_river, _communityCardsPanel);
			_communityCardsPanel.revalidate();
			_communityCardsPanel.repaint();
		}
	}
	
	private void addCardLabelToPanel(Card card, JPanel panel)  {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(CARD_IMG_PATH + card.toString() + ".png"));
		} catch (IOException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		JLabel label = new JLabel(new ImageIcon(image));
		panel.add(label);
	}

}
