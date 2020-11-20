package tyler.card.blackjack;

import java.util.Scanner;

public class BlackJack {
	
	private Hand dealer = new Hand();
	private Table game;
	private Scanner scanner = new Scanner(System.in);
	private Scanner scanner2 = new Scanner(System.in);
	private int players;
	
	public BlackJack(int players, int startBal) {
		System.out.println("Loading Blackjack game with " + players + "/7 players");
		game = new Table(players);
		this.players = players;
		System.out.println("Every player is starting out with $" + startBal);
		for (int i = 0; i < players; i++) {
			game.table()[i].setBalance(startBal);
		}
	}
	
	public void start(Deck d) {
		deal(game, d);
		bid(game);
		int play = 1;
		boolean sucks = dealer.bestValue() == 21;
		int playersLeft = 0;
		for (Hand h: game.table()) {
			if (!h.isEmpty()) {
				playersLeft++;
				if (!sucks) {
					System.out.println("PLAYER " + play);
					Card c1 = h.card(0);
					Card c2 = h.card(1);
					System.out.println("The dealer is dealt a " + dealer.card(0).number() + " of " + dealer.card(0).suit() + " and a ????? of ?????");
					System.out.print("You are dealt a " + c1.number() + " of " + c1.suit() + " and a " + c2.number() + " of " + c2.suit() + "\n");
					if (h.bestValue() == 21) {
						System.out.println("BLACKJACK!");
						h.multiplier(1.5f);
						h.chickenDinner(true);
					} else {
						System.out.println("Your top value is " + h.bestValue() + " , Enter [P] to see all possible values");
						System.out.println("Do you want to Hit [H], Split [S], Double [D], or Stay [X]");
						boolean valid = false;
						while (!valid) {
							String input = scanner.nextLine();
							System.out.println(input);
							switch (input.toLowerCase()) {
							case "h":
								hit(h,d,5,false);
								valid = true;
								break;
							case "s":
								split(h,d);
								valid = true;
								break;
							case "d":
								if (!(h.balance() < h.bid()*2)) {
									doub(h,d);
									valid = true;
								} else {
									System.out.println("You do not have enough money to do that!");
									System.out.println("Do you want to Hit [H], Split [S], Double [D], or Stay [X]");
								}
								break;
							case "p":
								System.out.print("All values : [ ");
								for (int i : h.possibleValues()) {
									System.out.print(i + " ");
								}
								System.out.print("] \n");
								System.out.println("Do you want to Hit [H], Split [S], Double [D], or Stay [X]");
								break;
							case "x":
								valid = true;
								break;
							default:
								System.out.println("Invalid entry!");
								System.out.print("You are dealt a " + c1.number() + " of " + c1.suit() + " and a " + c2.number() + " of " + c2.suit() + "\n");
								System.out.println("Do you want to Hit [H], Split [S], Double [D], or Stay [X]");
								break;	
							}
						}
					}
				} else {
					if (!(h.bestValue() == 21))
						h.multiplier(-1);
				}
			}
			play++;
		}
		if (playersLeft == 0) {
			System.out.println("No one left! Game Over");
			return;
		}
		dealerHit(d);
		System.out.println("RESULTS");
		System.out.println("PLAYER #       MULTIPLIER      COINS      BALANCE");
		int count = 1;
		for (Hand h : game.table()) {
			if (!h.isEmpty() && !h.chickenDinner() && !sucks) {
				if (!h.isSplit()) {
					if (!h.bust()) {
						if (!dealer.bust()) {
							if (dealer.bestValue() > h.bestValue()) {
								h.multiplier(-1);
							} else if (dealer.bestValue() < h.bestValue()) {
								h.multiplier(1);
							}
						} else {
							h.multiplier(1);
						}
					} else {
						h.multiplier(-1);
					}
				} else {
					h.doSplit(dealer);
				}
			}
			if (!h.isEmpty()) {
				h.setBalance((int) (h.balance() + (h.bid()*h.multiplier())));
				System.out.println("PLAYER " + count + "         x" + h.multiplier() + "         $" + (h.bid()*h.multiplier()) + "       $" + h.balance());
			}
			count++;
		}
		System.out.println("Move to the next round? [Y/N]");
		String input = scanner.nextLine();
		boolean valid = false;
		while (!valid) {
			switch (input.toLowerCase()) {
			case "y":
				valid = true;
				for (Hand h: game.table()) {
					h.reset();
				}
				dealer.reset();
				if (d.cardsLeft() > (players*5))
					start(d);
				else {
					System.out.println("Shuffling cards....");
					start(new Deck());
				}
				break;
			case "n":
				valid = true;
				System.out.println("RESULTS:");
				for (int i = 1; i <= players; i++) {
					System.out.println("PLAYER " + i + "       $" + game.table()[i-1].balance());
				}
				System.out.println("Thanks for playing!");
				break;
			}
		}
	}
	
	private void deal(Table t, Deck d) {
		for (Hand h : t.table()) {
			if (h.balance() > 0)
				h.addCard(d);
		}
		dealer.addCard(d);;
		for (Hand h : t.table()) {
			if (h.balance() > 0)
				h.addCard(d);
		}
		dealer.addCard(d);
	}
	
	private void bid(Table t) {
		int play = 1;
		for (Hand h : t.table()) {
			if (h.balance() > 0 && !h.isEmpty()) {
				System.out.println("PLAYER " + play);
				boolean valid = false;
				int bid = 0;
				while (!valid) {
					System.out.println("How much do you want to bid?");
					while (!scanner2.hasNextInt()) {
						System.out.println("How much do you want to bid?");
						scanner2.next();
					}
					bid = scanner2.nextInt();
					if (bid <= h.balance())
						valid = true;
					else {
						System.out.println("Bid too large!");
					}
				}
				h.setBid(bid);
			}
			play++;
		}
	}
	
	private void dealerHit(Deck d) {
		System.out.println("Dealer has a " + dealer.card(0).toString() + " and a " + dealer.card(1).toString());
		int count = 0;
		while (dealer.bestValue() < 16 && !dealer.bust() && count < 5) {
			dealer.addCard(d);
			System.out.println("Dealer hit and drew a " + dealer.card(count+2).toString());
			if (dealer.bestValue() == -1) {
				System.out.println("Dealer busted!");
				dealer.setBust(true);
			}
			count++;
		}
		if (!dealer.bust())
			System.out.println("Dealer stayed with a value of " + dealer.bestValue());
	}
	
	private void hit(Hand h, Deck d, int top, boolean split) {
		boolean con = true;
		int count = 0;
		if (split)
			count--;
		while (con && count < top) {
			count++;
			h.addCard(d);
			Card c = h.card(count + 1);
			System.out.println("You drew a " + c.toString());
			if (h.bestValue() == -1) {
				System.out.println("You busted!");
				h.setBust(true);
				con = false;
			} else {
				System.out.println("Your top value is " + h.bestValue());
				boolean valid = false;
				while (!valid && count < top) {
					System.out.println("Enter [H] to hit again, [X] to stay, or [P] for other values");
					String input = scanner2.nextLine();
					switch (input.toLowerCase()) {
					case "h":
						valid = true;
						break;
					case "x":
						valid = true;
						con = false;
						break;
					case "p":
						System.out.print("All values : [ ");
						for (int i : h.possibleValues()) {
							System.out.print(i + " ");
						}
						System.out.print("] \n");
						System.out.println("Do you want to Hit [H] or Stay [S]");
						scanner2.next();
						break;
					}
				}
			}
		}
	}
	
	private void split(Hand h, Deck d) {
		int count = 1;
		Hand[] hands = h.split();
		for (Hand han : hands) {
			System.out.println("HAND " + count);
			hit(han, d, 5, true);
			count++;
		}
	}
	
	private void doub(Hand h, Deck d) {
		h.setBid(h.bid()*2);
		System.out.println("Bid set to " + h.bid());
		hit(h, d, 1, false);
	}
	
}
