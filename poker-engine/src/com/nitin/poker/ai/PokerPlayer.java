package com.nitin.poker.ai;

import java.util.List;

import com.nitin.poker.cards.Card;
import com.nitin.poker.cards.PlayerHand;
import com.nitin.poker.gameengine.PokerPublicPlayer;
import com.nitin.poker.vo.Action;
import com.nitin.poker.vo.BetHistoryItem;

public interface PokerPlayer {

	/**
	 * 
	 * @param playerId - Unique id assigned by the game engine
	 * @param hand - the players hole cards
	 * @param bettingHistory - An ordered list of action history
	 * @param communityCards - the community cards seen till now
	 * @param players - A sorted list of playerIds by position. Dealer is last position.
	 * @return
	 */
	public Action getAction(String playerId, PlayerHand hand,
			List<BetHistoryItem> bettingHistory, List<Card> communityCards,
			List<PokerPublicPlayer> players);
	
	public String getDisplayName();
}
