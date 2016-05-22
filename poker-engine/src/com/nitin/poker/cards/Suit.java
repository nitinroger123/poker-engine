package com.nitin.poker.cards;

public enum Suit {
	
	SPADE("spades"),
	CLUB("clubs"),
	HEART("hearts"),
	DIAMOND("diamonds");

	private final String _name;
	
	private Suit(String name) {
		_name = name;
	}
	
	public String getName() {
		return _name;
	}
}
