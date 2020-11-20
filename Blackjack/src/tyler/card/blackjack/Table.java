package tyler.card.blackjack;

public class Table {

	private Hand[] table;
	
	public Table(int size) {
		table = new Hand[size];
		for (int i = 0; i < size; i++) {
			table[i] = new Hand();
		}
	}
	
	public Hand getHand(int player) {
		return table[player-1];
	}
	
	public Hand[] table() {
		return table;
	}
	
}
