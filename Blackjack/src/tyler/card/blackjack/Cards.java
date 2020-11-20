package tyler.card.blackjack;

public class Cards {

	public static Number card(int value) {
		for (Number n : Number.values())
			if (n.cardValue() == value)
				return n;
		return null;
		
	}
	
	public static Suit suit(int value) {
		for (Suit s : Suit.values())
			if (s.value() == value)
				return s;
		return null;
	}
	
}
