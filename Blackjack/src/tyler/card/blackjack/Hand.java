package tyler.card.blackjack;

import java.util.ArrayList;

public class Hand {

	private int balance = 0;
	private int bid = 0;
	private boolean bust = false;
	private ArrayList<Card> hand;
	private boolean split = false;
	private Hand[] splitHand;
	private float multiplier = 0;
	private boolean chicken = false;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public int size() {
		return hand.size();
	}
	
	public void multiplier(float value) {
		multiplier += value;
	}
	
	public float multiplier() {
		return multiplier;
	}
	
	public void addCard(Deck d) {
		hand.add(d.grab());
	}
	
	public void clear() {
		hand.clear();
	}
	
	public void add(Card c) {
		hand.add(c);
	}
	
	public Card card(int i) {
		return hand.get(i);
	}
	
	public void setBid(int i) {
		bid = i;
	}
	
	public int bid() {
		return bid;
	} 
	
	public int bestValue() {
		int top = -1;
		for (int i : possibleValues())
			if (i > top && i < 22)
				top = i;
		return top;
	}
	
	public int[] possibleValues() {
		int value = 0;
		int aces = 0;
		for (Card c : hand) {
			value += c.number().value();
			if (c.number() == Number.ACE)
				aces++;
		}
		int[] values = new int[aces + 1];
		values[aces] = value;
		while (aces > 0) {
			aces--;
			values[aces] = value-10;
			value -= 10;
		}
		return values;
		
	}
	
	public boolean chickenDinner() {
		return chicken;
	}
	public void chickenDinner(boolean b) {
		chicken = b;
	}
	
	public int balance() {
		return balance;
	}
	
	public void setBalance(int i) {
		balance = i;
	}
	
	public boolean isEmpty() {
		if (hand.size() < 1)
			return true;
		return false;
	}
	
	public void setBust(boolean b) {
		bust = b;
	}
	
	public boolean bust() {
		return bust;
	}
	
	public Hand[] split(){
		Hand card1 = new Hand();
		Hand card2 = new Hand();
		card1.add(hand.get(0));
		card2.add(hand.get(1));
		Hand[] hands = {card1, card2};
		split = true;
		splitHand = hands;
		return hands;
	}
	
	public boolean isSplit() {
		return split;
	}
	
	public void saveSplit(Hand[] hands) {
		splitHand = hands;
	}
	
	public void reset() {
		bid = 0;
		bust = false;
		hand = new ArrayList<Card>();
		split = false;
		multiplier = 0;
		chicken = false;
	}
	
	public void doSplit(Hand dealer) {
		for (Hand h : splitHand) {
			if (!dealer.bust()) {
				if (h.bestValue() > dealer.bestValue())
					multiplier(1);
				if (h.bestValue() < dealer.bestValue()) {
					multiplier(-1);
				}
			} else {
				if (h.bust())
					multiplier(-1);
				else
					multiplier(1);
			}
		}
	}
	
}
