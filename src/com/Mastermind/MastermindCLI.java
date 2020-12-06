package com.Mastermind;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MastermindCLI {

	// frequently used variables
	private Scanner scanner = new Scanner(System.in);
	private NumberGenerator numGen = new NumberGenerator();

	// important elements
	List<String> splitGuess = new ArrayList<>();
	ArrayList<Integer> arrayOfNumbers = new ArrayList<>();
	ArrayList<Integer> randomNumber = new ArrayList<>();
	ArrayList<String> plusMinus = new ArrayList<>();

	// method to run application
	public void run() {
		System.out.println("                                            Welcome to Gabby's \n"
				+ " __    __     ______     ______     ______   ______     ______     __    __     __     __   __     _____    \r\n" + 
				  "/\\ \"-./  \\   /\\  __ \\   /\\  ___\\   /\\__  _\\ /\\  ___\\   /\\  == \\   /\\ \"-./  \\   /\\ \\   /\\ \"-.\\ \\   /\\  __-.  \r\n" + 
				  "\\ \\ \\-./\\ \\  \\ \\  __ \\  \\ \\___  \\  \\/_/\\ \\/ \\ \\  __\\   \\ \\  __<   \\ \\ \\-./\\ \\  \\ \\ \\  \\ \\ \\-.  \\  \\ \\ \\/\\ \\ \r\n" + 
				  " \\ \\_\\ \\ \\_\\  \\ \\_\\ \\_\\  \\/\\_____\\    \\ \\_\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_\\ \\ \\_\\  \\ \\_\\  \\ \\_\\\\\"\\_\\  \\ \\____- \r\n" + 
				  "  \\/_/  \\/_/   \\/_/\\/_/   \\/_____/     \\/_/   \\/_____/   \\/_/ /_/   \\/_/  \\/_/   \\/_/   \\/_/ \\/_/   \\/____/ \r\n" + 
				  "                                                                                                            "
				+ "\n"
				+ "\n"
				+ "\n");
		menu();
	}

	// main menu method
	private void menu() {
		System.out.println("Please type in the number of the option you want to select: \n" + "1 - Start new game \n"
				+ "2 - How to play \n" + "3 - Exit Mastermind \n");
			 
 
	    String menuOption = scanner.nextLine();
	    int menuChoice = 0;
	    
	    try {
	    	menuChoice = Integer.parseInt(menuOption);
	    } catch (NumberFormatException e) {
	    	System.out.println("Please choose a number between 1 and 3.");
	    }
		
		if (menuChoice == 1) {
			runGame();
		}
		if (menuChoice == 2) {
			displayDirections();
		}
		if (menuChoice == 3) {
			exitProgram();
		} else if (menuChoice != 1 && menuChoice != 2 && menuChoice != 3) {
			System.out.println("That isn't a valid option. Try again.\n");
			menu();
		}
	}

	private void runGame() {
		int turnCounter = 0; // starting turn counter
		boolean gameWon = false; 

		
		randomNumber = numGen.createRandomNumber(); //assigning the random number
		System.out.println("The computer has picked a random number. \n");

		while (turnCounter < 10 && !gameWon) {
			ArrayList<Integer> theUserGuess = numGen.usersNumberGuess(); 
			gameWon = evaluateGuess(theUserGuess, randomNumber);
			turnCounter++;
			System.out.println("\n"
							 + "Turn " + turnCounter + " is complete.\n"
							 + "You have "+ (10-turnCounter) + " turns remaining.\n \n");
			theUserGuess.clear();
		}

		if (turnCounter == 10) {
			loser();
		}

	}

	private void displayDirections() {
		System.out.println("\n"
				+ ""
				+ " _  _ ____ _  _   ___ ____   ___  _    ____ _ _\r\n"  
				+ " |--| [__] |/\\|    |  [__]   |--' |___ |--|  Y "
				+ "\n"
				+ "\n" + "\n"
				+ "In this game, you as the user will be playing againt the computer. \n"
				+ "The computer will randomly select a four digit number \n"
				+ "with each digit being between 1 and 6. (example: 1234)\n" + "\n"
				+ "Your job is to figure out that number in 10 turns or less. \n"
				+ "Each round, you will be asked to guess the number. \n" + "\n"
				+ "If you guessed a number correctly and that number is in the right spot, \n"
				+ "a plus (+) sign will be displayed.\n \n"
				+ "If you guessed a number correctly, but it is not in the right spot within \n"
				+ "the four digit number, a minus (-) sign will be displayed. \n \n"
				+ "If nothing is displayed, it means it is both not in the four \n" + "digit number at all.\n \n"
				+ "The plus and minus signs will not display underneith the number that is\n"
				+ "correct. It is up to you as the user to figure out which \n"
				+ "numbers coordinate to which sign and change your guess accordingly. \n" + "\n"
				+ "If you don't correctly guess the number within your 10 turns, you lose. \n"
				+ "Guess correctly in that same amount of turns and you're the winner!\n" + "\n"  
				+ "\n"
				+ ""
				+ " _  _ ____ _  _ ____   ____ _  _ __ _ /\r\n"  
				+ " |--| |--|  \\/  |===   |--- |__| | \\|. \n"
				+ "\n");
		menu();
	}

	private void winner() {
		System.out.println("*****You've Won!*****\n" + "\n" + "Your guess: " + numGen.showUserNum() + "\n"
				+ "Random number: " + numGen.showRandomNumber());
		run();
	}

	private void loser() {
		System.out.println(
				"***** Sorry, You Lost :( ***** \n" + "\n" + "The random number was: " + numGen.showRandomNumber());
		run();
	}

	private void exitProgram() {
		System.out.println("Bye, thanks for playing!");

		System.exit(0);
	}

	private boolean evaluateGuess(ArrayList<Integer> usersGuess, ArrayList<Integer> theNumber) { 
		
		for (int i = 0; i < usersGuess.size(); i++) {
			// if the guess matches the random number, fill plusMinus with +, declare winner
			if (usersGuess.get(i) == theNumber.get(i)) { // checks first for correct num/correct spot
															
				plusMinus.add("+");
			}
		}
		if (theNumber.get(0) == usersGuess.get(1) || theNumber.get(0) == usersGuess.get(2)
				|| theNumber.get(0) == usersGuess.get(3)) {
			plusMinus.add("-");
		}
		if (theNumber.get(1) == usersGuess.get(0) || theNumber.get(1) == usersGuess.get(2)
				|| theNumber.get(1) == usersGuess.get(3)) {
			plusMinus.add("-");
		}
		if (theNumber.get(2) == usersGuess.get(0) || theNumber.get(2) == usersGuess.get(1)
				|| theNumber.get(2) == usersGuess.get(3)) {
			plusMinus.add("-");
		}
		if (theNumber.get(3) == usersGuess.get(0) || theNumber.get(3) == usersGuess.get(1)
				|| theNumber.get(3) == usersGuess.get(2)) {
			plusMinus.add("-");
		}

		if (usersGuess.equals(theNumber)) {
			winner();
		} else {
		}

		System.out.println("\n" + usersGuess + "\n" + plusMinus);
		plusMinus.clear();
		return false;

	}
}// end bracket