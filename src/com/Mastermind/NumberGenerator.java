package com.Mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NumberGenerator {
	int min = 1;
	int max = 6;
	private Scanner scanner = new Scanner(System.in);

	// important variables
	ArrayList<Integer> theNumber;
	List<String> splitGuess;
	ArrayList<Integer> arrayOfNumbers;

	
	// constructor
	public NumberGenerator() {
		theNumber = new ArrayList<>();
		splitGuess = new ArrayList<>();
		arrayOfNumbers = new ArrayList<>();
	}

	
	// method for creating random 4 digit number
	public ArrayList<Integer> createRandomNumber() { 
		Random random = new Random(); 
		while (theNumber.size() < 4) {
			int randomInt = random.nextInt(max - min + 1) + min;
			if (theNumber.contains(randomInt)) {// if there is a duplicate value, make a new random number
			} else {// populating the arrayList
				theNumber.add(randomInt);
			}
		}
		return theNumber;
	}


	public ArrayList<Integer> usersNumberGuess() {
		System.out.println("Please enter your guess:");
		String numberGuess = "";
		boolean validGuess = false;
		while (!validGuess) { // do while guess is invalid
			numberGuess = scanner.nextLine();

			if (numberGuess.length() < 4) {
				System.out.println("Not enough characters, try again.\n");

			} else if (!isValidNum(numberGuess)) {
				System.out.println("Try again.\n");

			} else { // if the guess is valid, move on
				validGuess = true;
			}
		}
		splitGuess = Arrays.asList(numberGuess.split("|"));
		// taking the string arrayList and making it an int arrayList
		// so it can be compared to the random number
		for (int i = 0; i < splitGuess.size(); i++) {
			String numberString = splitGuess.get(i);
			int number = Integer.parseInt(numberString);
			arrayOfNumbers.add(number);
		}
		// the users guess as an arrayList of Integers
		return arrayOfNumbers;
	}

	
	public boolean isValidNum(String userGuess) {

		ArrayList<Integer> isThisValid = new ArrayList<Integer>();
		List<String> validNumCheck = Arrays.asList(userGuess.split("|"));

		for (int i = 0; i < validNumCheck.size(); i++) {
			String numberString = validNumCheck.get(i);
			// checking to make sure the array contains numbers only
			try {
				int stringToInt = Integer.parseInt(numberString);
			} catch (NumberFormatException ex) {
				System.out.println("Guess contains at least one letter.");
				userGuess = null;
				return false;
			}

			int number = Integer.parseInt(numberString);
			isThisValid.add(number);
		}
		// take all the numbers and check if they're in specifications
		for (int i = 0; i < isThisValid.size(); i++) {
			if (isThisValid.get(i) > 6 || isThisValid.get(i) < 1) {
				userGuess = null;
				return false;
			}
		}
		return true;
	}

	
	public String showUserNum() {
		String userGuessToString = arrayOfNumbers.toString().replace(",", "");
		return userGuessToString;
	}
	
	
	public String showRandomNumber() {
		String numToString = theNumber.toString().replace(",", "");
		return numToString;
	}

} // final bracket
