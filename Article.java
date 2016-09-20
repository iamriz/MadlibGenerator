import java.util.ArrayList;
import java.util.Random;

/*****************************************************************************
 * Holds the parsed words from both the JSON and plain text files.
 * 
 * @author Jufni R.
 ****************************************************************************/
public class Article implements Maker {

	// Holds the text from the plain text file
	private Group article;

	// Holds the group of words according to type as parsed from the JSON file
	private ArrayList<Group> wordGroup;

	/**
	 * Constructor for the Article class
	 */
	public Article() {
		// Instantiate an emptly list for the wordGroup ArrayList
		wordGroup = new ArrayList<Group>();
	}

	/**
	 * Setter for the full Article from the parsed plain text file
	 * 
	 * @param article
	 *            Holds all the words from the parsed plain text file
	 */
	public void setArticle(Group article) {
		this.article = article;
	}

	/**
	 * Returns the full article
	 * 
	 * @return article Group of words holding up the full article from the plain
	 *         text file
	 */
	public Group getArticle() {
		return article;
	}

	/**
	 * Add word to its corresponding group of words of the same type
	 * 
	 * @param typeName
	 *            The type where the word belongs
	 * @param word
	 *            The word which would be stored as a Constant word under its
	 *            corresponding type
	 */
	public void addWordToGroup(String typeName, String word) {

		// Get the word group if it already exists
		Group type = getWordGroup(typeName);

		// If type does not exist yet, add a new Group
		if (type == null) {
			type = new Group(typeName);
			wordGroup.add(type);
		}

		// Instantiate a word set containing an ArrayList of words
		WordSet wordSet = new WordSet();

		// Add the word to its corresponding type Group
		wordSet.addToWordList(new ConstantWord(word));
		type.addToWordList(wordSet);
	}

	/**
	 * Match the token name to the position of the stored token type
	 * 
	 * @param token
	 *            The name of the token type
	 * @return group The group of words under a specific token type
	 */
	public Group getWordGroup(String token) {

		// Get the number of existing supported types
		int size = wordGroup.size();
		for (int index = 0; index < size; index++) {

			Group group = wordGroup.get(index);

			// Get the Group object which matched the parameter token's type
			if (token.equals(group.getTypeName())) {

				// Return the matched type Group object
				return group;
			}
		}

		// Return null if type does not exist or is not supported
		return null;
	}

	/**
	 * @see Maker
	 */
	@Override
	public String populate(Article article, Random r) {
		return "" + article.populate(article, r);
	}
}