package proj.jtyler.cards;

public enum Suit {

	SPADES(0, "Spades"),
	HEARTS(1, "Hearts"),
	CLUBS(2, "Clubs"),
	DIAMONDS(3, "Diamonds"),
	JOKER(4, "Joker");
	
	private int id;
	private String title;
	Suit(int id, String title){
		this.id = id;
		this.title = title;
	}
	
	/*
	 * Gets suit id
	 */
	public int getID() {
		return id;
	}
	
	/*
	 * Gets suit title
	 */
	public String getTitle() {
		return title;
	}
	
	/*
	 * Gets suit from id
	 * Does not work for card id (unless its the ace of spades)
	 */
	public static Suit fromID(int i) {
		return Suit.values()[i];
	}
	
}
