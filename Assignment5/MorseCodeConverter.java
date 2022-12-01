/*
 * Author: Edward Gregg
 * Class: CMSC 204
 * Project: Assignment 5
 * Due Date: Nov 30, 2022
 * 
 */


//It's working, don't touch


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class MorseCodeConverter {
	private static MorseCodeTree tree = new MorseCodeTree();;
	
	/**
	 * Constructs a MorseCodeConverter object and builds a MorseCodeTree
	 */
	public MorseCodeConverter() {
		tree = new MorseCodeTree();
	}
	
	/**
	 * Prints all the elements of the MorseCodeTree in LNR order 
	 * Uses the toArrayList() method of MorseCodeTree to test if built correctly
	 * @return the data in the tree in the correct order separated by a space
	 */
	public static String printTree() {
		String output = "";
		for (String s : tree.toArrayList()) {
			output += s + " ";
		}
		return output.trim();
	}
	/**
	 * Every word seperated by a "/".
	 * @param codeFile is the name of the file with the code in it
	 * @return the English translation of the file
	 * @throws FileNotFoundException file not found
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		String output = "";
		Scanner sc = new Scanner(codeFile);
		while (sc.hasNextLine()) {
			String code = sc.nextLine();
			output += convertToEnglish(code);
		}
		return output;
	}
		
	/**
	 * Converts a MorseCode string into English
	 * Every letter is delimited by a '.' and every word by a "/".
	 * Example: ".- .-.. .-.. / .... ..- -- .- -."
	 * 
	 * @param code the morse code
	 * @return the English translation
	 * 
	 * this part was found on geeksforgeeks
	 */
	public static String convertToEnglish(String code) {
		String output = "";
		
		String[] sentence = code.split("/");
		
		for (String word : sentence) {
			
			word = word.trim();
			
			String[] letters = word.split(" ");
			
			for (String letter : letters) {
				
				output += tree.fetch(letter);
			}
			output += " ";
		}
		return output.trim();
	}
}
