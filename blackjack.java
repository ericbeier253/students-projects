package programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class GlobalScanner {
    public static Scanner scanner = new Scanner(System.in);
} //end class GlobalScanner


class Player{
	private double bankroll;
	public double bet;
	private ArrayList<Card> hand;
	public int playerId;
	public boolean blackjackFlag = false;
	public int handCount;
	
	public Player(double bankroll, int playerId) {
		this.bankroll = bankroll;
		this.bet = 0;
		this.playerId = playerId;
		this.handCount = 0;
		hand = new ArrayList<>();		
	}
	
	public int getHandCount(){
		

		
		
		return handCount;
	} //end method getHandCount;
	
	public void newHand() {
		hand = new ArrayList<>();
		blackjackFlag = false;
	} //end method newHand
	
	public void setBet() {
	    double bet;
	    do {
	        System.out.print("Enter your bet: ");
	        bet = GlobalScanner.scanner.nextDouble();

	        if (bet > this.bankroll || bet == 0) {
	            System.out.println("Invalid bet.");
	        } else {
	            this.bet = bet;
	            break;
	        }
	    } while (true);
	} //end method setBet
	
	public void win() {
		if (this.blackjackFlag == true) {
			this.bankroll += 1.5*this.bet;
		} else {
		this.bankroll += this.bet;
		}
	} //end method win
	
	public void lose() {
		this.bankroll -= this.bet;
	} //end method lose
	
	
	public double getBankroll() {
		return this.bankroll;
	} //end method getBankroll
	
	public void dealCard(Deck deck) {
		
		this.hand.add(deck.dealCard());
		
		this.handCount = 0;
		int aceCount = 0;
		
		for (Card card : this.hand) {
			switch (card.getRank()) {
			case "2":
				this.handCount += 2;
				//System.out.println("adding 2");
				break;
			case "3":
				this.handCount += 3;
				//System.out.println("adding 3");
				break;
			case "4":
				this.handCount += 4;
				//System.out.println("adding 4");
				break;
			case "5":
				this.handCount += 5;
				//System.out.println("adding 5");
				break;
			case "6":
				this.handCount += 6;
				//System.out.println("adding 6");
				break;
			case "7":
				this.handCount += 7;
				//System.out.println("adding 7");
				break;
			case "8":
				this.handCount += 8;
				//System.out.println("adding 8");
				break;
			case "9":
				this.handCount += 9;
				//System.out.println("adding 9");
				break;
			case "10":
				this.handCount += 10;
				//System.out.println("adding 10");
				break;
			case "J":
				this.handCount += 10;
				//System.out.println("adding 10");
				break;
			case "Q":
				this.handCount += 10;
				//System.out.println("adding 10");
				break;
			case "K":
				this.handCount += 10;
				//System.out.println("adding 10");
				break;
			case "A":
				this.handCount += 11;
				//System.out.println("adding 11");
				aceCount += 1;
				break;
			}
			
		}
		
		while (this.handCount > 21 && aceCount > 0) {
			this.handCount -= 10;
			aceCount -= 1;
		}
	} //end method dealCard
	
	public void printHand() {
		
		for (Card card : this.hand) {
			System.out.print(card.toString());
			System.out.print(" ");			
		}
		
		System.out.println();
	} //end method printHand
	
	
}//end class Player


class Card {
	private String suit;
	private String rank;
	
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public String getRank() {
		return rank;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public String toString() {
		return rank+suit;		
	}
	
}//end class Card

class Deck {
	private ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<>();
		initializeDeck();
		shuffle();
	}//end Deck
	
	public Card dealCard() {
		
		if (deck.isEmpty()) {
			initializeDeck();
			shuffle();
		}
		
		Card topCard = deck.remove(0);
		return topCard;
	}
	
	
	
	private void initializeDeck() {
		String[] suits = {"♠","♥","♣","♦"};
		String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		
		for (String suit: suits) {
			for (String rank : ranks) {
				deck.add(new Card(suit,rank));			
			}
		}
	} //end initializeDeck
	
	private void shuffle() {
		Collections.shuffle(deck); 
	}//end shuffle
		
}//end class Deck



public class Blackjack {
	

	

	public static void main(String[] args) {
		
		
		//initialize game
		
		System.out.println(".\n..\n...\nWelcome to Blackjack.\n...\n..\n.\n");
		
		System.out.println("How many players?");
		
		int numPlayers = GlobalScanner.scanner.nextInt();
		
		ArrayList<Player> players;
		players = new ArrayList<>();
		
		double getBankroll;
		 
		for (int i=0; i<numPlayers; i++) {
			System.out.printf("Enter starting bankroll for player %d\n", i+1);
			getBankroll = GlobalScanner.scanner.nextDouble();
			players.add(new Player(getBankroll, i+1));		
		}
		
		Player dealer = new Player(0,0);
		
		int bankruptCount = 0;
		boolean bankruptFlag = false;
		
		
		String playAgain = "y";		
		while (playAgain.equals("y")) {
			
			Deck deck = new Deck();
			bankruptCount = 0;
			
			dealer.newHand();
			dealer.dealCard(deck);
				
			for (Player player : players) {
				
				player.newHand();
				
				if (player.getBankroll() == 0) {
					System.out.printf("Player %d is bankrupt\n", player.playerId);
					player.bet = 0;
					bankruptCount += 1;
					
					if (bankruptCount == numPlayers) {
						System.out.println("All players are bankrupt. The house always wins.");
						bankruptFlag = true;
						break;
					}
									
					continue;
					
				} else {
					System.out.printf("Player %d:\n", player.playerId);
					
					System.out.printf("Current Bankroll: %.2f\n", player.getBankroll());
					player.setBet();
					
					dealer.printHand();
					System.out.printf("Dealer shows: %d\n", dealer.handCount);
					
					
					player.dealCard(deck);
					player.dealCard(deck);
					player.printHand();
					System.out.printf("Player has: %d\n", player.handCount);
					 
					if (player.handCount == 21) {
						System.out.println("Blackjack!");
						player.blackjackFlag = true;
						continue;
					}
					
					
					
					GlobalScanner.scanner.nextLine(); //random scanner buffer to fix program input
					

					while(player.handCount < 21) {
						
						System.out.print("Take a hit (y/n)? ");
						String takeHit = GlobalScanner.scanner.nextLine();
						
						if (takeHit.equals("y")) {
							player.dealCard(deck);
							player.printHand();
							System.out.printf("Player has: %d\n", player.handCount);
							
							if (player.getHandCount() > 21) {
								System.out.println("Player busts.");
								break;
							}
						} else {
							System.out.println("Player stands.");
							break;
						}					
					}				
				}			
			} //end player for loop
			
			if (bankruptFlag == true) {
				break;
			}
			
			//start dealer play
			System.out.println("Dealer play:");
			dealer.dealCard(deck);
			dealer.printHand();
			System.out.printf("Dealer has: %d\n", dealer.handCount);
			
			if (dealer.getHandCount() == 21) {
				System.out.println("Blackjack!");
				dealer.blackjackFlag = true;		
			}
			while (dealer.getHandCount()<17) {
				dealer.dealCard(deck);
				
				if(dealer.handCount > 21) {
					dealer.printHand();
					System.out.println("Dealer busts!");
					break;
				}
				
				dealer.printHand();
				System.out.printf("Dealer has: %d\n", dealer.handCount);				
			}
			
			
			//process winners
			for (Player player : players) {
				
				if(player.blackjackFlag == true && dealer.blackjackFlag == true) {
					//blackjack standoff
					continue;
				}
				else if (dealer.blackjackFlag == true) {
					//dealer has blackjack
					player.lose();
					continue;
				}
				else if (player.handCount > 21) {
					//player busted
					player.lose();
					continue;
				}
				else if (dealer.handCount > 21) {
					//dealer busted
					player.win();
					continue;
				}
				else if (dealer.handCount == player.handCount) {
					//tie
					continue;
				}
				else if (player.handCount > dealer.handCount) {
					//player wins
					player.win();
					continue;
				}
				else if (player.handCount < dealer.handCount) {
					//player loses
					player.lose();
					continue;
				}
			}

			
			
		System.out.print("Play Again? (y/n)? ");
		playAgain = GlobalScanner.scanner.nextLine();	

			
						
		} //end gameplay while loop
		
		
				

	} //end Main

} //end class Blackjack
