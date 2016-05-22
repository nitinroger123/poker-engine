package com.nitin.poker.vo;

public class Action {
	
	private final int _betAmount;
	
	private final boolean _isFold;
	
	private Action(int betAmount, boolean isFold) {
		_betAmount = betAmount;
		_isFold = isFold;
	}
	
	public static Action bet(int amount) {
		return new Action(amount, false);
	}
	
	public static Action fold() {
		return new Action(0, true);
	}

	public int get_betAmount() {
		return _betAmount;
	}

	public boolean is_isFold() {
		return _isFold;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _betAmount;
		result = prime * result + (_isFold ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Action other = (Action) obj;
		if (_betAmount != other._betAmount)
			return false;
		if (_isFold != other._isFold)
			return false;
		return true;
	}
	
	
	
	
	
}
