package proj.jtyler.cards;

public class Main {

	public static void main(String[] args) {
		Deck d = new Deck();
		while (d.cardsLeft() > 0) {
			System.out.println(d.next().toString());
		}
	}
	
}
