import java.util.Random;

/*****************************************************************************
 * Generates random words to replace the tokens in the parsed Article from the
 * inputted text file
 * 
 * @author Jufni R.
 ****************************************************************************/
public interface Maker {

	/**
	 * Populates the resulting word/article after tokenization
	 * 
	 * @param article
	 *            Article containing the needed words to populate the tokenized madlib
	 * @param r
	 *            Random object
	 * @return String the generated text after tokenization
	 */
	public String populate(Article article, Random r);
}
