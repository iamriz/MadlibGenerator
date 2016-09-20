import java.util.ArrayList;
import java.util.Random;

/*****************************************************************************
 * Groups the set of words under the same type which could either be
 * "noun|verb|adjective|number|adverb|place|person" or "article" as label for
 * the whole writeup parsed from the plain text file.
 * 
 * The WordSet is in ArrayList form so we could play with a random number in
 * selecting word for the tokens.
 * 
 * @author Jufni R.
 ****************************************************************************/
public class Group implements Maker {

	// Could be "noun|verb|adjective|number|adverb|place|person" or
	// "article" as label for the whole writeup parsed from the plain text file.
	String typeName;

	// Contains the words which belong to the same type "typeName"
	ArrayList<WordSet> words;

	/**
	 * Constructor for the Group class
	 */
	public Group(String s) {
		typeName = s;
		words = new ArrayList<WordSet>();
	}

	/**
	 * Add word set to WordSet ArrayList
	 * 
	 * @param word
	 *            WordSet word
	 */
	public void addToWordList(WordSet word) {
		words.add(word);
	}

	/**
	 * Gets the type name
	 * 
	 * @return typeName The type name
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @see Maker
	 */
	public String populate(Article article, Random r) {
		// Get a random number that's within the size of the WordSet array.
		int randomI = r.nextInt(words.size());
		// Randomly select a word for a particular token 
		String s = words.get(randomI).populate(article, r);
		// Return the generated random word
		return s;
	}
}