package tyler.card.blackjack;

public enum Number {

	ACE(1,11),
	TWO(2,2),
	THREE(3,3),
	FOUR(4,4),
	FIVE(5,5),
	SIX(6,6),
	SEVEN(7,7),
	EIGHT(8,8),
	NINE(9,9),
	TEN(10,10),
	JACK(11,10),
	QUEEN(12,10),
	KING(13,10);
	
	private int value;
	private int card;
	Number(int card, int value){
		this.value = value;
		this.card = card;
	}
	public int value() {
		return value;
	}
	public int cardValue() {
		return card;
	}
	
}
