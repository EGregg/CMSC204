/*
 * free reign on comments and naming conventions in this file
 */


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
	 * Converts a MorseCode string into English
	 * Every letter is delimited by a '.' and every word by a '/'.
	 * @param code the morse code string
	 * @return the English translation
	 */
	public static String convertToEnglish(String code) {
		String output = "";
		String[] words = code.split("/");
		for (String word : words) {
			word = word.trim();
			String[] letters = word.split(" ");
			for (String letter : letters) {
				output += tree.fetch(letter);
			}
			output += " ";
		}
		return output.trim();
	}
	
	/**
	 * Converts a file of MorseCode into English
	 * Every letter is delimited by a '.' and every word by a '/'.
	 * @param codeFile the file that contains MorseCode
	 * @return the English translation of the file
	 * @throws FileNotFoundException thrown if file is not found
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
	
	public static void main(String[] args) {
		MorseCodeConverter test = new MorseCodeConverter();
		System.out.println(test.convertToEnglish("--. .. ...- . / -- . / -.-- --- ..- .-. / .- -. ... .-- . .-. / -.. ---"));
	}
	
}
