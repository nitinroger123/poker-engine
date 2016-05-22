package com.nitin.poker.gameengine;

public class PokerPublicPlayer {
	private final String _playerId;
	
	private final int _chipCount;
	
	public PokerPublicPlayer(String playerId, int chipCount) {
		_playerId = playerId;
		_chipCount = chipCount;
	}
	
	public int getChipCount() {
		return _chipCount;
	}
	
	public String getPlayerId() {
		return _playerId;
	}
}

