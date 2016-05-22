package com.nitin.poker.gameengine;

import java.util.ArrayList;
import java.util.List;

import com.nitin.poker.ai.PokerPlayer;

public class PokerEnginePlayerHolder {
	
	private final String _playerId;
	
	private final PokerPlayer _ai;
	
	private int _chipCount;

	public PokerEnginePlayerHolder(String playerId, PokerPlayer ai,
			int chipCount) {
		super();
		_playerId = playerId;
		_ai = ai;
		_chipCount = chipCount;
	}

	public String get_playerId() {
		return _playerId;
	}

	public PokerPlayer get_ai() {
		return _ai;
	}

	public int getChipCount() {
		return _chipCount;
	}
	
	public void setChipCount(int chipCount) {
		_chipCount = chipCount;
	}
	
	public PokerPublicPlayer publicInfo() {
		return new PokerPublicPlayer(_playerId, _chipCount);
	}
	
	public static List<PokerPublicPlayer> getPublicInfo(List<PokerEnginePlayerHolder> players) {
		List<PokerPublicPlayer> publicPlayers = new ArrayList<PokerPublicPlayer>();
		for(PokerEnginePlayerHolder player: players) {
			publicPlayers.add(new PokerPublicPlayer(player.get_playerId(),player.getChipCount()));
		}
		return publicPlayers;
	}
	
	
}


