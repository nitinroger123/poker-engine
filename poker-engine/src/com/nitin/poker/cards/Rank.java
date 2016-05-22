package com.nitin.poker.cards;

public enum Rank {
	
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13),
	ACE(14);
	
	private int _rankValue;
	
	private Rank(int rankValue) {
		_rankValue = rankValue;
	}
	
	public int getRankValue() {
		return _rankValue;
	}
	
	public String getName() {
		if(_rankValue == 14) {
			return "ace";
		}
		if(_rankValue == 13) {
			return "king";
		}
		if(_rankValue == 12) {
			return "queen";
		}
		if(_rankValue == 11) {
			return "jack";
		}
		return String.valueOf(_rankValue);
	}

}
