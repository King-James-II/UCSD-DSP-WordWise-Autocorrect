package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}


	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		if (!wordList.isEmpty()) {
			return;
		}
		String[] words = sourceText.split("[\\s]+"); // Split by whitespace
	    starter = words[0]; // Set the starter to the first word
	    String prevWord = starter;
	    for (int i = 1; i < words.length; i++) {
	    	 String word = words[i];

	         // Check if prevWord is already a node in the list
	         ListNode node = findNode(prevWord);
	         if (node != null) {
	        	 //if "prevWord" is a node in the list add "w" as a nextWord to the "prevWord" node
	        	 node.addNextWord(word);
	         }
	         // else
	         else {
	        	 //add a node to the list with "prevWord" as the node's word add "w" as a nextWord to the "prevWord" node
	             node = new ListNode(prevWord);
	             wordList.add(node);
	        	 node.addNextWord(word);
	         }
	         // set "prevWord" = "w"
	         prevWord = word;
	    }
	    // add starter to be a next word for the last word in the source text.
	    ListNode lastNode = findNode(prevWord);
	    if (lastNode == null) {
	        lastNode = new ListNode(prevWord);
	        wordList.add(lastNode);
	    }
	    lastNode.addNextWord(starter);
	}

	/**
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords){
	    // TODO: Implement this method
		//		set "currWord" to be the starter word
		if (wordList.isEmpty() || numWords == 0) {
			return "";
		}
		String currWord = starter;
		//		set "output" to be ""
		StringBuilder output = new StringBuilder ();
		//		add "currWord" to output
		output = output.append(currWord);
		//		while you need more words
		for (int i = 1; i < numWords; i ++) {
			//			   find the "node" corresponding to "currWord" in the list
			ListNode currNode = findNode(currWord);
			//			   select a random word "w" from the "wordList" for "node"
			String word = currNode.getRandomNextWord(rnGenerator);
			//			   add "w" to the "output"
			output = output.append(" " + word);
			//			   set "currWord" to be "w"
			currWord = word;
			//			   increment number of words added to the list

		}
		return output.toString();
	}


	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		starter = "";
		train(sourceText);
	}

	// TODO: Add any private helper methods you need here.
	private ListNode findNode(String word) {
	    for (ListNode node : wordList) {
	        if (node.getWord().equals(word)) {
	            return node;
	        }
	    }
	    return null;
	}

	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from
	    // the MarkovTextGeneratorLoL class
		//		select a random word from the "wordList" for "node"
		int index = generator.nextInt(nextWords.size());
	    return nextWords.get(index);
	}

	@Override
	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}


}


