import java.util.Random;

/*****************************************************************************
 * Contains the fixed words (do not change)
 * 
 * @author Jufni R.
 ****************************************************************************/
public class ConstantWord implements Word {

	// Fixed word (do not change)
	String constant;

	/**
	 * Constructor for the ConstantWord class
	 * 
	 * @param
	 */
	public ConstantWord(String word) {
		constant = word;
	}

	/**
	 * Returns the constant/fixed word
	 * 
	 * @return String The final word
	 */
	@Override
	public String getWord() {
		return constant;
	}

	/**
	 * @see Maker
	 */
	@Override
	public String populate(Article article, Random r) {
		String word = getWord();
		// Returns the final word. 
		// If it's not an empty line (\n), do not concatenate a white space
		return word + ((word != "\n") ? " " : "");
	}
}
