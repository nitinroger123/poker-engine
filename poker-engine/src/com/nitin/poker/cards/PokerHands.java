package com.nitin.poker.cards;

public enum PokerHands {
	
	ROYAL_FLUSH(1),
	STRAIGHT_FLUSH(2),
	QUADS(3),
	FULL_HOUSE(4),
	FLUSH(5),
	STRAIGHT(6),
	TRIPS(7),
	TWO_PAIR(8),
	PAIR(9),
	HIGH_CARD(10);
	
	private int _rank;
	
	private PokerHands(int rank) {
		_rank = rank;
	}
	
	public int winner(PokerHands other) {
		
		if(this._rank < other._rank) {
			return 1;
		}
		
		if(this._rank > other._rank) {
			return -1;
		}
		
		return 0;
	}

}
