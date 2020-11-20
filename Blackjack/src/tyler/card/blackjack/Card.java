package tyler.card.blackjack;

public class Card {

	
	private Number n;
	private Suit s;
	public Card(Number n, Suit s) {
		this.n = n;
		this.s = s;
	}
	
	public Number number() {
		return n;
	}
	public Suit suit() {
		return s;
	}
	@Override
	public String toString() {
		return n + " of " + s + "s";
	}
	
}
