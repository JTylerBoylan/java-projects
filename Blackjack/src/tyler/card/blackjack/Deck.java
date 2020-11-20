package tyler.card.blackjack;


public class Deck {

	
	private int size;
	private boolean repeat;
	private Card[] deck;
	int top = 0;
	public Deck(int size, boolean repeat, boolean shuffled) {
		this.size = size;
		this.repeat = repeat;
		generate(shuffled);
	}
	public Deck(int size, boolean shuffled) {
		this.size = size;
		this.repeat = false;
		generate(shuffled);
	}
	public Deck(boolean repeat) {
		this.size = 52;
		this.repeat = repeat;
		generate(true);
	}
	public Deck() {
		this.size = 52;
		this.repeat = false;
		generate(true);
	}
	
	public Card[] deck() {
		return deck;
	}
	
	public int cardsLeft() {
		return size - top;
	}
	
	private void generate(boolean shuffle) {
		deck = new Card[size];
		int count = 0;
		int suit = 1;
		int num = 1;
		while (count < size) {
			int cardN;
			int cardS;
			if (shuffle) {
			    cardN = (int)((Math.random() * 13) + 1);
			    cardS = (int)((Math.random() * 4) + 1);
			} else {
				cardN = num;
				cardS = suit;
				if (num == 13) {
					num = 1;
					suit++;
				}
				
			}
			Card nC = new Card(Cards.card(cardN),Cards.suit(cardS));
			if (!repeat) {
				if (!duplicate(nC)) {
					deck[count] = nC;
					count++;
				}
			} else {
				deck[count] = nC;
				count++;
			}
		}
	}
	
	public boolean duplicate(Card card) {
		for (Card c : deck)
			if (c != null)
				if (card.number() == c.number() && card.suit() == c.suit())
					return true;
		return false;
	}
	
	public void shuffle() {
		generate(true);
	}
	
	public Card grab() {
		top++;
		if (top >= size) {
			System.out.println("Out of card! Auto-shuffling!");
			shuffle();
			top = 0;
		}
		return deck[top-1];
	}
	
}
