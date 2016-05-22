package com.nitin.poker.cards;

public class PlayerHand {
	
	private final Card _holdCard1;
	
	private final Card _holeCard2;

	public PlayerHand(Card holdCard1, Card holeCard2) {
		super();
		_holdCard1 = holdCard1;
		_holeCard2 = holeCard2;
	}
	
	public Card getFirstHoleCard() {
		return _holdCard1;
	}

	public Card getSecondHoleCard() {
		return _holeCard2;
	}
}
