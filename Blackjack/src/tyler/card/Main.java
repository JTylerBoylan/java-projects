package tyler.card;

import java.util.Scanner;

import tyler.card.blackjack.BlackJack;
import tyler.card.blackjack.Deck;

public class Main {

	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Enter [S] to Start!");
		String input = scanner.nextLine();
		while (!input.equalsIgnoreCase("s")) {
			System.out.println("Enter [S] to Start!");
			input = scanner.nextLine();
		}
		System.out.println("How many players?");
		while (!scanner.hasNextInt()) {
			System.out.println("How many players?");
			scanner.next();
		}
		int players = scanner.nextInt();
		System.out.println("How many credits?");
		while (!scanner.hasNextInt()) {
			System.out.println("How many credits?");
			scanner.next();
		}
		BlackJack bj = new BlackJack(cramp(players, 7, 1), scanner.nextInt());
		bj.start(new Deck());
	}
	
	public static int cramp(int num, int top, int min) {
		if (num > top)
			num = top;
		if (num < min)
			num = min;
		return num;
	}
	
}
