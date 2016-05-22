package com.nitin.poker.vo;

public class BetHistoryItem {

	private final String _playerId;
	
	private final Action _action;

	public BetHistoryItem(String _playerId, Action _action) {
		super();
		this._playerId = _playerId;
		this._action = _action;
	}

	public String get_playerId() {
		return _playerId;
	}

	public Action get_action() {
		return _action;
	}
	
	
}
