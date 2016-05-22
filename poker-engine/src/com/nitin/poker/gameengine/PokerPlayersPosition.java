package com.nitin.poker.gameengine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PokerPlayersPosition<T> implements Iterable<T> {

	private final List<T> _players;
	
	private final List<T> _playersLeftToAct;
	
	private final Set<T> _foldedPlayers;

	public PokerPlayersPosition(List<T> players, int dealerPosition) {
		_players = playersForDealerPosition(players, dealerPosition);
		_playersLeftToAct = new ArrayList<>(_players);
		_foldedPlayers = new HashSet<>();

	}
	
	public void playerRaised(T raiser) {
		int raiserPosition = getPlayerPosition(raiser);
		List<T> nextToAct = new ArrayList<>();
		int position = nextPosition(_players, raiserPosition);
		while (position != raiserPosition) {
			T player = _players.get(position);
			if (!_foldedPlayers.contains(player)) {
				nextToAct.add(player);
			}
		}
		_playersLeftToAct.clear();
		_playersLeftToAct.addAll(nextToAct);
	}

	public T nextToAct() {
		return _playersLeftToAct.isEmpty() ? null : _playersLeftToAct.remove(0);
	}
	
	private int getPlayerPosition(T player) {
		return _players.indexOf(player);
	}

	private List<T> playersForDealerPosition(List<T> players,
			int dealerPosition) {
		List<T> positionedPlayers = new ArrayList<T>(players.size());
		int currentPosition = nextPosition(players, dealerPosition);
		while (currentPosition != dealerPosition) {
			positionedPlayers.add(players.get(currentPosition));
		}
		positionedPlayers.add(players.get(currentPosition));
		return positionedPlayers;
	}

	private int nextPosition(List<T> players, int currentPosition) {
		return (currentPosition + 1) % players.size();
	}

	@Override
	public Iterator<T> iterator() {
		return _players.iterator();
	}

}
