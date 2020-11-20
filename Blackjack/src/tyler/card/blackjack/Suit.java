package tyler.card.blackjack;

public enum Suit {

	CLUB(1),
	SPADE(2),
	HEART(3),
	DIAMOND(4);
	
	private int value;
	Suit(int value){
		this.value = value;
	}
	public int value() {
		return value;
	}
	
}
