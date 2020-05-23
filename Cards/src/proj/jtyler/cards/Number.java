package proj.jtyler.cards;

public enum Number {

	ACE(0, "Ace"),
	TWO(1, "Two"),
	THREE(2, "Three"),
	FOUR(3, "Four"),
	FIVE(4, "Five"),
	SIX(5, "Six"),
	SEVEN(6, "Seven"),
	EIGHT(7, "Eight"),
	NINE(8, "Nine"),
	TEN(9, "Ten"),
	JACK(10, "Jack"),
	QUEEN(11, "Queen"),
	KING(12, "King"),
	JOKER(13, "Joker");
	
	private int id;
	private String title;
	Number(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	/*
	 * Get number id
	 */
	public int getID() {
		return id;
	}
	
	/*
	 * Get number title
	 */
	public String getTitle() {
		return title;
	}
	
	/*
	 * Get number from id
	 * Number id only, does not work for card id (unless spades)
	 */
	public static Number fromID(int i) {
		return Number.values()[i];
	}
	
}
