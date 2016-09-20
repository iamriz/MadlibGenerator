import java.io.FileNotFoundException;

/*****************************************************************************
 * Entry to the Madlib generator.
 * 
 * @author Jufni R.
 ****************************************************************************/
public class MadlibGenerator {

	// Accepts the following command line parameters:
	// [0] = Path to the JSON file
	// [1] = Path to the plaintext file
	// [3] = Path to the output file
	public static void main(String[] args) throws FileNotFoundException {

		// Check if user inputs 3 command line parameters. If not, ask user to
		// input 3 file paths.
		if (args.length != 3) {
			System.out.println("Please input 3 parameters as follows:");
			System.out.println("1. Path to the JSON file");
			System.out.println("2. Path to the plaintext file");
			System.out.println("3. Path to the output file");
			return;
		}

		generate(args[0], args[1], args[2]);
		// generate("words.json", "phrases.txt", "output.txt");
	}

	/**
	 * 
	 * @param jsonFile JSON File containing the words and its corresponding types
	 * @param inputFile A plain text file containing the madlib
	 * @param outputFile
	 * @throws FileNotFoundException
	 */
	public static void generate(String jsonFile, String inputFile, String outputFile) throws FileNotFoundException {
		// Instantiate the madlib parsers passing the inputted file paths
		Parser fp = new Parser(jsonFile, inputFile, outputFile);
		// Generate output file with the result after tokenization.
		fp.writeOutputFile();
	}
}
