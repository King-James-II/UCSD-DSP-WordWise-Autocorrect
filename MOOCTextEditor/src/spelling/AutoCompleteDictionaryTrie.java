package spelling;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;


    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}


	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the
	 * string to all lower case before you insert it.
	 *
	 * This method adds a word by creating and linking the necessary trie nodes
	 * into the trie, as described outlined in the videos for this week. It
	 * should appropriately use existing nodes in the trie, only creating new
	 * nodes when necessary. E.g. If the word "no" is already in the trie,
	 * then adding the word "now" would add only one additional node
	 * (for the 'w').
	 *
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	@Override
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		if (word == null || word.isEmpty()) {
		        return false; // Ignore invalid inputs.
		}
		TrieNode currNode = root;
		boolean isNewWord = false;
		for (char currChar : word.toLowerCase().toCharArray()) {
			if (currNode.getValidNextCharacters().contains(currChar) ) {
				currNode = currNode.getChild(currChar);
			}
			else {
				currNode = currNode.insert(currChar);
			}
		}
	    if (!currNode.endsWord() ) {
	    	currNode.setEndsWord(true);
	    	size++;
	    	return true;
	    }
	    return false;
	}

	/**
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	@Override
	public int size()
	{
	    return size;
	}


	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s)
	{
	    // TODO: Implement this method
		TrieNode currNode = root;
		for (char currChar : s.toLowerCase().toCharArray()) {
			if (currNode.getValidNextCharacters().contains(currChar)) {
				currNode = currNode.getChild(currChar);
			}
			else {
				return false;
			}
		}
		if (currNode.endsWord()) {
			return true;
		}
		return false;
	}

	/**
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions
     * of the prefix string. All legal completions must be valid words in the
     * dictionary. If the prefix itself is a valid word, it is included
     * in the list of returned words.
     *
     * The list of completions must contain
     * all of the shortest completions, but when there are ties, it may break
     * them in any order. For example, if there the prefix string is "ste" and
     * only the words "step", "stem", "stew", "steer" and "steep" are in the
     * dictionary, when the user asks for 4 completions, the list must include
     * "step", "stem" and "stew", but may include either the word
     * "steer" or "steep".
     *
     * If this string prefix is not in the trie, it returns an empty list.
     *
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions)
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
 		TrieNode currNode = root;
 		List<String> completions = new LinkedList<String>();
 		for (char currChar : prefix.toLowerCase().toCharArray()) {
			if (currNode.getValidNextCharacters().contains(currChar)) {
				currNode = currNode.getChild(currChar);
			}
			else
			{
				return completions;
			}
 		}
 		if (currNode.endsWord()) {
 			completions.add(currNode.getText());
 		}

 		Queue<TrieNode> nodeQueue = new LinkedList<TrieNode>();
 		List<Character> children = new LinkedList<Character>(currNode.getValidNextCharacters());

 		for (int i = 0; i < children.size(); i++) {
 			char currChar = children.get(i);
 			nodeQueue.add(currNode.getChild(currChar));
 		}
 		while (!nodeQueue.isEmpty() && completions.size() < numCompletions) {
 			TrieNode firstNode = nodeQueue.poll();
 			if (firstNode.endsWord()) {
 				completions.add(firstNode.getText());
 			}

 			List<Character> childNodes = new LinkedList<Character>(firstNode.getValidNextCharacters());
 			for (int i = 0; i < childNodes.size(); i++) {
 				char c = childNodes.get(i);
 				nodeQueue.add(firstNode.getChild(c));
 			}
 		}
 		return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}

 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) {
			return;
		}

 		System.out.println(curr.getText());

 		TrieNode next = null;

 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}



}