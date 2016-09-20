/*****************************************************************************
 * Allows random and constant words to be grouped under the same "type" for the
 * array list in WordSet
 * 
 * @author Jufni R.
 ****************************************************************************/
public interface Word extends Maker {
	/**
	 * Gets the String word after tokenization (without the brackets for
	 * RandomWord type)
	 * 
	 * @return String word
	 */
	public String getWord();
}
