package com.nitin.poker.cards;

public class Card {
	
	private final Rank _rank;
	
	private final  Suit _suit;
	
	public Card(Rank rank, Suit suit) {
		_rank = rank;
		_suit = suit;
	}

	public Rank get_rank() {
		return _rank;
	}

	public Suit get_suit() {
		return _suit;
	}
	
	@Override
	public String toString() {	
		return _rank.getName() + "_of_" + _suit.getName();
	}

}
