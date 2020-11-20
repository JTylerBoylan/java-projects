package tyler.card.chance;

import tyler.card.blackjack.Card;
import tyler.card.blackjack.Deck;
import tyler.card.blackjack.Hand;
import tyler.card.blackjack.Suit;
import tyler.card.blackjack.Number;

public class Chance {
	
	
	public static double chanceWin(Hand hand) {
		Deck deck = new Deck(13,false);
		double totalscore = 0;
		double score = 0;
		for (Card c : deck.deck()) {
			Hand h = hand;
			h.add(c);
			if (h.bestValue() != -1 && h.size() < 5)
				if (h.bestValue() > 16)
					score++;
				else
					score = chanceWin(h);
			totalscore++;
		}
		System.out.println(score/totalscore);
		return score/totalscore;
	}
	
	public static void main(String[] args) {
		Hand h = new Hand();
		h.add(new Card(Number.ACE,Suit.CLUB));
		System.out.print(chanceWin(h));
	}
	
	
}
