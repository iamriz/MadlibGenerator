import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*****************************************************************************
 * Parser for the parameter files: JSON and plaintext. Writes the generated
 * tokenized words into the output file.
 * 
 * @author Jufni R.
 ****************************************************************************/
public class Parser {

	// Holds the parsed words from both the JSON and plain text files.
	private Article article;
	// Random object to be used later for populating a random word for the token
	private Random r;

	// Path to the JSON file
	final private String jsonFile;
	// Path to the plaintext file
	final private String inputFile;
	// Path to the output file
	final private String outputFile;

	/**
	 * Constructor for the Parser class
	 * 
	 * @param jsonFile
	 *            String Path to the JSON file
	 * @param inputFile
	 *            String Path to the plaintext file
	 * @param outputFile
	 *            String Path to the output file
	 */
	public Parser(String jsonFile, String inputFile, String outputFile) {
		// Store the file paths to the corresponding object properties
		this.jsonFile = jsonFile;
		this.inputFile = inputFile;
		this.outputFile = outputFile;

		// Instantiate the Article and Random objects
		article = new Article();
		r = new Random();

		// Read and parse the JSON file containing
		// word categories for the tokens
		parseJSONFile();

		// Read and parse the plaintext file which needs tokenization
		parseTextFile();
	}

	/**
	 * Read and parse the JSON file containing word categories for the tokens
	 */
	public void parseJSONFile() {
		// Instantiate JSONParser object to parse the JSON objects
		JSONParser parser = new JSONParser();

		try {
			// Get the JSONArray from the parsed jsonFile file
			JSONArray array = (JSONArray) parser.parse(new FileReader(jsonFile));

			// Iterate the JSONArray
			Iterator<?> keys = array.iterator();
			while (keys.hasNext()) {
				Object key = keys.next();

				// Check if content is a JSONObject
				if (key instanceof JSONObject) {
					JSONObject object = (JSONObject) key;

					// Get the type and word
					String typeName = (String) object.get("type");
					String wordValue = (String) object.get("word");

					// Add the word and its corresponding type and group them
					// and store to Article object
					article.addWordToGroup(typeName, wordValue);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File Not Found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: IOException caught.");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("ERROR: ParseException caught.");
			e.printStackTrace();
		}
	}

	/**
	 * Read and parse the plaintext file which needs tokenization
	 */
	public void parseTextFile() {
		try {
			// Instantiate the FileReader for the plaintext file
			FileReader fr = new FileReader(inputFile);
			// Instantiate the scanner that will read each line from the file
			Scanner lineScan = new Scanner(fr);
			// Create a new group containing the full article
			Group statement = new Group("article");
			// Instantiate the wordset which will hold the parsed words from the
			// plaintext file
			WordSet wordSet = new WordSet();

			// Scan the read file line by line
			while (lineScan.hasNextLine()) {
				// Get the string per line
				String nextLine = lineScan.nextLine();

				// Check if line is not empty. If not, parse word by word
				if (nextLine != null && !nextLine.isEmpty()) {
					// Instantiate the scanner for each line of string scanned
					// from the file
					Scanner scan = new Scanner(nextLine);

					// Scan per word
					while (scan.hasNext()) {
						String nextString = scan.next();
						// Parse each string read from the parsed text file,
						// check brackets, and tokenize
						tokenize(nextString, wordSet);
					}

					// Close the scanner to avoid resource leak
					scan.close();
				}
				if (lineScan.hasNextLine()) {
					// Add next line
					wordSet.addToWordList(new ConstantWord("\n"));
				}
			}

			// Add the statement containing the full article
			statement.addToWordList(wordSet);
			// Store it to Articles 'Group article' member
			article.setArticle(statement);

			// Close the line scanner to avoid resource leak
			lineScan.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File Not Found");
			e.printStackTrace();
		}
	}

	/**
	 * Write the final result to an output file
	 */
	public void writeOutputFile() {
		PrintWriter out = null;
		try {
			// Instantiate the writer object
			out = new PrintWriter(outputFile);

			// Populate the full article, replace the tokens with corresponding
			// random words, then write to file
			out.print(article.getArticle().populate(article, r));

			System.out.println("The result has been successfully generated in " + outputFile + ".");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				// Close the writer object
				out.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Parse each string read from the parsed text file. Check for tokens or
	 * those with brackets. Remove the brackets and store them as RandomWord for
	 * later replacement/population with final/constant word. If nextString has
	 * no bracket, treat it as a ConstantWord. Store them to WordSet.
	 * 
	 * @param nextString
	 *            String scanned from the parsed text file
	 * @param wordSet
	 *            WordSet holder for the words which could either be a token or
	 *            a final / constant word
	 */
	public void tokenize(final String nextString, WordSet wordSet) {
		// Check if string has bracket or if it's a token
		if (nextString.contains("[")) {
			// Check if brackets exist, then tokenize
			int closeBracketIndex = nextString.indexOf("]");
			// Remove the open and closing brackets by getting the substring
			String name = nextString.substring(1, closeBracketIndex);
			// Store tokenized words as RandomWord for later replacement
			RandomWord token = new RandomWord(name);
			// Check if there's a suffix concatenated to the token. Store it for
			// later concatenation to the generated final word.
			if ((closeBracketIndex + 1) != nextString.length()) {
				token.setSuffix(nextString.substring(closeBracketIndex + 1, nextString.length()));
			}
			// Store the RandomWord token to the word set
			wordSet.addToWordList(token);
		} else {
			// Instantiate the final word as a ConstantWord
			wordSet.addToWordList(new ConstantWord(nextString));
		}
	}
}
