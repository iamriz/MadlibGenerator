import java.util.Random;

/*****************************************************************************
 * Contains the token. Subject for replacement with a random word from the
 * categorized words as parsed from the JSON file.
 * 
 * @author Jufni R.
 ****************************************************************************/
public class RandomWord implements Word {
	// The token name
	private String randomWord;
	// Holds any string that is part of the token after the closing bracket.
	// (eg. for string "[verb]ing."; suffix will hold the substring "ing.")
	private String suffix = "";

	/**
	 * Constructor for the RandomWord class
	 * 
	 * @param name String token name
	 */
	public RandomWord(String name) {
		randomWord = name;
	}

	/**
	 * Returns token name without the brackets
	 * 
	 * @return String The name of the token
	 */
	@Override
	public String getWord() {
		return randomWord;
	}

	/**
	 * Setter for the string suffix
	 * 
	 * @param suffix
	 *            String holder for the suffix/any string after the closing
	 *            bracket
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * @see Maker
	 */
	@Override
	public String populate(Article article, Random r) {
		// Get the group of words that corresponds to the token name
		Group wordGroup = article.getWordGroup(getWord());

		// If token type does not exist, return to its original text from file
		StringBuilder word = new StringBuilder();
		if (wordGroup == null) {
			word.append("[" + getWord() + "]");
		} else {
			// Generate a random word from the group of words above
			word.append(wordGroup.populate(article, r));
			// Remove the white space being added in ConstantWord populate to
			// properly concatenate the suffix if there's any.
			word.deleteCharAt(word.length() - 1);
		}
		// Return the generated final word replacing the token
		return word.toString() + suffix + " ";
	}
}
