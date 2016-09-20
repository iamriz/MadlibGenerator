import java.util.ArrayList;
import java.util.Random;

/*****************************************************************************
 * Holds the words in its sequence as read from the parsed plain text file.
 * 
 * @author Jufni R.
 ****************************************************************************/
public class WordSet implements Maker {
	// Holds the words in sequence needed for the final output.
	ArrayList<Word> wordsInSequence;

	/**
	 * Constructor for the WordSet class
	 */
	public WordSet() {
		wordsInSequence = new ArrayList<Word>();
	}

	/**
	 * Add the words to its corresponding word. Used in grouping the words in
	 * its corresponding type and in putting the parsed plaintext words in
	 * sequence
	 * 
	 * @param word
	 *            Either a ConstantWord or a RandomWord; Always a ConstantWord
	 *            when parsing from a JSON file
	 */
	public void addToWordList(Word word) {
		wordsInSequence.add(word);
	}

	/**
	 * Returns the group of words in sequence or according to type
	 * 
	 * @return ArrayList<Word> Group of words
	 */
	public ArrayList<Word> getWordsInSequence() {
		return wordsInSequence;
	}

	/**
	 * @see Maker
	 */
	@Override
	public String populate(Article article, Random r) {
		String statement = "";
		int length = wordsInSequence.size();
		// Populate the words in sequence
		for (int i = 0; i < length; i++) {
			statement = statement + wordsInSequence.get(i).populate(article, r);
		}
		// Return the final word/words
		return statement;
	}
}
