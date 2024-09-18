import java.util.Scanner;
/**
 *	The game of Pig.
 *	human vs computer game where each takes a turn rolling the die and
 *	can score if it's greater than 1 and skip if it's less
 * 
 *	@author	Audrey Yin
 *	@since	September 13, 2024
 */
public class PigGame {
	int userScore = 0;		//the user's score
	int computerScore = 0;	//the computer's score
	//vars for counting each the scores for statistics
	double zero = 0, twenty = 0, twentyOne = 0, twentyTwo = 0;	
	double  twentyThree = 0, twentyFour = 0, twentyFive = 0;
	
	/** main
	 * calls other methods
	 * @param 	String[] args
	 * @return 	N/A
	 */
	public static void main(String[] args) {
		PigGame pg = new PigGame();
		pg.printIntroduction();
		pg.chooseAMode();
	}
	
	/** printIntroduction
	 * prints the instructions for the game
	 * @param 	none
	 * @return 	N/A
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
							+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
							+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
							+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
							+ "turn goes to other player");
		System.out.println("\n");
	}
	/** chooseAMode
	 * prompts for either the game or statistics to be chosen and runs that mode
	 * @param 	none
	 * @return 	N/A
	 */
	public void chooseAMode() {
		char c = ' ';
		while (c!='p' && c!='s') {
			c = Prompt.getChar("Play game or Statistics (p or s)");
	}
		if (c=='p') 
			gamePlay();
		else
			statistics();
		
	}
	
	/** gamePlay
	 * the main game loop for the game
	 * @param 	none
	 * @return 	N/A
	 */
	public void gamePlay() {
		int count = 0;
		while (userScore<100 && computerScore<100) {
			if (count%2==0)
				userTurn();
			else
				computerTurn();
			count++;
		}
		if (userScore>=100) {
			System.out.println("Congratulations!!! YOU WON!!!\n");
			System.out.println("Thank you for playing the pig game!!");
	}
		else {
			System.out.println("Oh no you lost!!!\n");
		}
	}
	/** userTurn
	 * what is run when it is the users turn
	 * @param 	none
	 * @return 	N/A
	 */
	public void userTurn() {
		Dice dc = new Dice();
		int roll = 0;
		int turnScore = 0;
		System.out.println("**** USER Turn ***\n");
		System.out.println("Your turn score: " + turnScore);
		System.out.println("Your total score: " + userScore + "\n");
		char c = 'r';

		while (roll!=1 && c=='r') {
			c=' ';
			while (c!='r' && c!='h') {
				c = Prompt.getChar("(r)oll or (h)old");
				if (c=='x')
					System.exit(1);
			}
			if (c=='r') {
			System.out.println("\nYou ROLL\n");
			roll = dc.roll();
			dc.printDice();
			if (roll==1) {
				System.out.println("You LOSE your turn.");
				System.out.println("Your total score: " + userScore + "\n");
			}
			else {
				turnScore+=roll;
				System.out.println("Your turn score: " + turnScore);
				System.out.println("Your total score: " + userScore + "\n");
			}
		}
			else {
			System.out.println("\nYou HOLD\n");
			userScore+=turnScore;
			System.out.println("Your total score: " + userScore + "\n");
			}
		}
		
	}
	/** computerTurn
	 * what is run when it is the computers turn
	 * @param 	none
	 * @return 	N/A
	 */
	public void computerTurn() {
		Scanner in = new Scanner(System.in);
		Dice dc = new Dice();
		int roll = 0;
		int turnScore = 0;
		System.out.println("**** COMPUTER'S Turn ***\n");
		System.out.println("Computer's turn score: " + turnScore);
		System.out.println("Computer's total score: " + computerScore + "\n");
		while (turnScore<20) {
			System.out.print("Press enter for computer's turn -> ");
			String str = in.nextLine();
			System.out.println("\nComputer will ROLL\n");
			roll = dc.roll();
			dc.printDice();
			if (roll==1) {
				System.out.println("Computer LOSES its turn.");
				System.out.println("Computer's total score: " + computerScore + "\n");
				turnScore = 20;
			}
			else {
				turnScore+=roll;
				System.out.println("Computer's turn score: " + turnScore);
				System.out.println("Computer's total score: " + computerScore + "\n");
			}
		}
		if (roll!=1) {
			System.out.print("Press enter for computer's turn -> ");
			String str = in.nextLine();
			System.out.println("\nComputer will HOLD\n");
			computerScore+=turnScore;
			System.out.println("Computer's total score: " + computerScore + "\n");
		}

	}
	
	/** statistics
	 * calculates the statistics with probability for each turn and prints it
	 * @param 	none
	 * @return 	N/A
	 */
	public void statistics() {
		System.out.println("Run statistical analysis - \"Hold at 20\"");
		int turns = Prompt.getInt("Number of turns (1000 - 1000000)", 1000, 1000000);
		for (int i = 0; i < turns; i++) 
			statsBotTurn();
		System.out.println("\n\tEstimated");
		System.out.println("Score\tProbability\n");
		System.out.println("0\t" + zero/turns);
		System.out.println("20\t" + twenty/turns);
		System.out.println("21\t" + twentyOne/turns);
		System.out.println("22\t" + twentyTwo/turns);
		System.out.println("23\t" + twentyThree/turns);
		System.out.println("24\t" + twentyFour/turns);
		System.out.println("25\t" + twentyFive/turns);
	}
	
	/** statsBotTurn
	 * each turn for when the game is simulated
	 * @param 	none
	 * @return 	N/A
	 */
	public void statsBotTurn() {
		Dice dc = new Dice();
		int roll = 0;
		int turnScore = 0;
		while (turnScore<20) {
			roll = dc.roll();
			if (roll==1) {
				turnScore = 20;
			}
			else {
				turnScore+=roll;
			}
		}
		if (roll!=1) {
			if (turnScore == 20) 
				twenty++;
			else if (turnScore == 21) 
				twentyOne++;
			else if (turnScore == 22) 
				twentyTwo++;
			else if (turnScore == 23) 
				twentyThree++;
			else if (turnScore == 24) 
				twentyFour++;
			else if (turnScore == 25) 
				twentyFive++;
	}
	else {
		zero++;
	}
	}
}
