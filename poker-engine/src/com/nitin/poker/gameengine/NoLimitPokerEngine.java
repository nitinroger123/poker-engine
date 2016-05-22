package com.nitin.poker.gameengine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nitin.poker.ai.DumbPokerPlayer;
import com.nitin.poker.cards.Card;
import com.nitin.poker.cards.Deck;
import com.nitin.poker.cards.PlayerHand;
import com.nitin.poker.vo.Action;
import com.nitin.poker.vo.BetHistoryItem;

public class NoLimitPokerEngine implements PokerEngine {

	private List<PokerEnginePlayerHolder> _players;

	private Deck _deck;

	private int _dealerPosition;

	private Integer _smallBlind;
	private Integer _bigBlind;

	/**
	 * TODO: Make these configurable.
	 */
	public NoLimitPokerEngine() {
		_deck = new Deck();
		_dealerPosition = 0;
		_players = new ArrayList<>();
		_smallBlind = 1;
		_bigBlind = 2;
		_players.add(new PokerEnginePlayerHolder("1", new DumbPokerPlayer(), 200));
		_players.add(new PokerEnginePlayerHolder("2", new DumbPokerPlayer(), 200));
	}

	public void runHand() {

		_deck.shuffle();

		List<Card> communalCards = new ArrayList<>();
		List<BetHistoryItem> bettingHistory = new ArrayList<>();
		PokerPlayersPosition<PokerEnginePlayerHolder> playersPosition = new PokerPlayersPosition<PokerEnginePlayerHolder>(
				_players, _dealerPosition);
		Map<String, PlayerHand> playerHands = dealPlayers(playersPosition,
				_deck);

		PokerEnginePlayerHolder player = playersPosition.nextToAct();

		// Post small blind
		bettingHistory.add(postBlind(player, _smallBlind));
		playersPosition.playerRaised(player);

		player = playersPosition.nextToAct();

		// Post big blind
		bettingHistory.add(postBlind(playersPosition.nextToAct(), _bigBlind));
		playersPosition.playerRaised(player);

		player = playersPosition.nextToAct();

		Action action = player.get_ai().getAction(player.get_playerId(),
				playerHands.get(player.get_playerId()), bettingHistory,
				communalCards, PokerEnginePlayerHolder.getPublicInfo(_players));
		bettingHistory.add(new BetHistoryItem(player.get_playerId(), action));
		playersPosition.playerRaised(player);

		while (player != null) {
			// Get action - if action -> positionsRaise
			action = player.get_ai().getAction(player.get_playerId(),
					playerHands.get(player.get_playerId()), bettingHistory,
					communalCards, PokerEnginePlayerHolder.getPublicInfo(_players));
			bettingHistory.add(new BetHistoryItem(player.get_playerId(), action));
			player = playersPosition.nextToAct();
		}
	}

	// Helper methods

	private static BetHistoryItem postBlind(PokerEnginePlayerHolder player,
			Integer blindAmount) {
		return new BetHistoryItem(player.get_playerId(),
				Action.bet(blindAmount));
	}

	private static Map<String, PlayerHand> dealPlayers(
			PokerPlayersPosition<PokerEnginePlayerHolder> playersPosition,
			Deck deck) {
		Map<String, PlayerHand> playerHoleCards = new HashMap<>();

		// Deal all players in
		for (PokerEnginePlayerHolder player : playersPosition) {
			playerHoleCards
					.put(player.get_playerId(),
							new PlayerHand(deck.dealSingleCard(), deck
									.dealSingleCard()));

		}
		return playerHoleCards;
	}

}
