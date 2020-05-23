package proj.jtyler.cards;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

	private Card[] cards;
	
	private int current_index = 0;
	
	/*
	 * Generates new deck (size = 52, joker excluded, & shuffled)
	 */
	public Deck() {
		cards = new Card[52];
		for (int i = 0 ; i < 52; i++) {
			cards[i] = Card.getCard(i);
		}
		shuffle();
	}
	
	/*
	 * Generates new deck (shuffled)
	 */
	public Deck(int size, boolean joker) {
		Deck deck = new Deck();
		deck = deck.subset(0, size);
		cards = deck.getCards();
	}
	
	/*
	 * Creates deck of preset cards
	 */
	public Deck(Card[] cards) {
		this.cards = cards;
	}
	
	/*
	 *  Shuffles deck and resets current index
	 */
	public void shuffle() {
		List<Card> card_list = Arrays.asList(cards);
		Collections.shuffle(card_list);
		cards = (Card[]) card_list.toArray();
		current_index = 0;
	}
	
	/*
	 * Get cards in deck
	 */
	public Card[] getCards() {
		return cards;
	}
	
	/*
	 * Get subset of the deck between start (inclusive) and end (exclusive)
	 */
	public Deck subset(int start, int end) {
		Card[] t = new Card[end-start];
		for (int i = 0; i < end-start; i++) {
			t[i] = cards[start+i];
		}
		return new Deck(t);
	}
	
	/*
	 * Get next card
	 */
	public Card next() {
		if (current_index >= cards.length)
			return null;
		return cards[current_index++];
	}
	
	public int cardsLeft() {
		return cards.length - current_index;
	}
	
}
