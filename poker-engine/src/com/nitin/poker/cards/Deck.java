package com.nitin.poker.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private final List<Card> _deck;
	
	public Deck() {
		_deck = new ArrayList<>();
		initialize();
	}

	private void initialize() {
		for(Suit suit: Suit.values()) {
			for(Rank rank: Rank.values()) {
				_deck.add(new Card(rank, suit));
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(_deck);
	}
	
	public List<Card> deal(int count) {
		List<Card> dealtCards = new ArrayList<>();
		for(int i = 0; i < count; ++i) {
			dealtCards.add(_deck.remove(0));
		}
		return dealtCards;
	}
	
	public Card dealSingleCard() {
		return deal(1).get(0);
	}

}
