package com.nitin.poker.ai;

import java.util.List;

import com.nitin.poker.cards.Card;
import com.nitin.poker.cards.PlayerHand;
import com.nitin.poker.gameengine.PokerPublicPlayer;
import com.nitin.poker.vo.Action;
import com.nitin.poker.vo.BetHistoryItem;

public class DumbPokerPlayer implements PokerPlayer {

	

	@Override
	public String getDisplayName() {
		return "Dumb";
	}

	@Override
	public Action getAction(String playerId, PlayerHand hand,
			List<BetHistoryItem> bettingHistory, List<Card> communityCards,
			List<PokerPublicPlayer> players) {
		return Action.fold();
	}
	

}
