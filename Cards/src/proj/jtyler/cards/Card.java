package proj.jtyler.cards;

import java.util.Random;

public class Card {

	private Random random;
	
	private Number number;
	private Suit suit;
	
	/*
	 * Generates random card (joker excluded)
	 */
	public Card() {
		random = new Random();
		number = Number.values()[random.nextInt(Number.values().length-1)];
		suit = Suit.values()[random.nextInt(Suit.values().length-1)];
	}
	
	/*
	 * Generates random card
	 * True: includes joker
	 * False: excludes joker
	 */
	public Card(boolean joker) {
		random = new Random();
		int size = joker ? Number.values().length : Number.values().length-1;
		number = Number.values()[random.nextInt(size)];
		suit = Suit.values()[random.nextInt(Suit.values().length-1)];
		if (number == Number.JOKER)
			suit = Suit.JOKER;
	}
	
	/*
	 * Generates specific card of number and suit
	 */
	public Card(Number number, Suit suit) {
		this.number = number;
		this.suit = suit;
	}
	
	/*
	 * Get card number
	 */
	public Number getNumber() {
		return number;
	}
	
	/*
	 * Get card suit
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/*
	 * Gets card id
	 * In order by suit then by number from Ace to King
	 * Spades: 0 to 12
	 * Hearts: 13 to 25
	 * Clubs: 26 to 38
	 * Diamonds: 39 to 51
	 * Joker: 65
	 */
	public int getID() {
		return suit.getID()*13 + number.getID();
	}
	
	/*
	 * Get card from ID
	 */
	public static Card getCard(int id) {
		int suit_id = id/13;
		int number_id = id%13;
		return new Card(Number.fromID(number_id),Suit.fromID(suit_id));
	}
	
	/*
	 * Tests if two cards are equal in number and suit
	 */
	public boolean equals(Card card) {
		return card.getNumber() == number && card.getSuit() == suit;
	}
	
	public String toString() {
		if (number == Number.JOKER)
			return "Joker";
		else
			return number.getTitle() + " of " + suit.getTitle();
	}
}
