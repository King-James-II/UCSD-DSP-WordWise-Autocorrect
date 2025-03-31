/**
 *
 */
package textgen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10;

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);

	}


	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}

		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));

		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}

		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {

		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

	}


	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		assertEquals("Remove: check size is correct ", 3, list1.size());
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());

		// TODO: Add more tests here
		int b = list1.remove(1);
		assertEquals("Remove: check b is correct ", 42, b);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 1, list1.size());
		try {
            list1.remove(-1); // Negative index
            fail("Expected IndexOutOfBoundsException for negative index");
        } catch (IndexOutOfBoundsException e) {
            // Test passes if exception is thrown
        }
		try {
            list1.remove(list1.size()+1); // Index out of bounds.
            fail("Expected IndexOutOfBoundsException for number larger than size");
        } catch (IndexOutOfBoundsException e) {
            // Test passes if exception is thrown
        }
	}

	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */

	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		assertEquals("Add: Check node is added to end. ", true, shortList.add("A"));
		assertEquals("Add: Check node is added to end. ", true, shortList.add("2"));
		assertEquals("AddAtIndex: Size is right after adding. ", 4, shortList.size);
	}


	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("AddAtIndex: Size is right after adding. ", 2, shortList.size());
		assertEquals("AddAtIndex: Size is right after adding. ", 3, list1.size());
		assertEquals("AddAtIndex: Size is right after adding. ", 0, emptyList.size());
		assertEquals("AddAtIndex: Size is right after adding. ", 10, longerList.size());
	}



	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		shortList.add(1, "Z");
		assertEquals("AddAtIndex: Check if node is added at index. ", "Z", shortList.get(1));
		assertEquals("AddAtIndex: Check if prev node is right. ", "A", shortList.get(0));
		assertEquals("AddAtIndex: Check if next node is right. ", "B", shortList.get(2));
		assertEquals("AddAtIndex: Size is right after adding. ", 3, shortList.size());
		shortList.add(0, "Test");
		assertEquals("AddAtIndex: Check if node is added at index. ", "Test", shortList.get(0));
		assertEquals("AddAtIndex: Check if prev node is right. ", "A", shortList.get(1));
		assertEquals("AddAtIndex: Size is right after adding. ", 4, shortList.size());
		try {
            shortList.add(-1, "O"); // Negative index
            fail("Expected IndexOutOfBoundsException for negative index");
        } catch (IndexOutOfBoundsException e) {
            // Test passes if exception is thrown
        }
		try {
            shortList.add(shortList.size()+1, "P"); // Index out of bounds.
            fail("Expected IndexOutOfBoundsException for number larger than size");
        } catch (IndexOutOfBoundsException e) {
            // Test passes if exception is thrown
        }
		try {
            shortList.add(0, null); // Adding null element
            fail("Expected NullPointerException when adding null element");
        } catch (NullPointerException e) {
            // Test passes if exception is thrown
        }
	}

	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		assertEquals("Set: Size is right after setting. ", 3, list1.size());
		assertEquals("Set: Size is right after setting. ", 2, shortList.size());
		String firstReplaced = shortList.set(0, "Z");
		int secondReplaced = list1.set(2, 99);
		assertEquals("Set: check firstReplaced element correct ", "A", firstReplaced);
		assertEquals("Set: check element 0 is correct ", "Z", shortList.get(0));
		assertEquals("Set: Size is right after setting. ", 2, shortList.size());
		assertEquals("Set: check secondReplaced element correct ", 42, secondReplaced);
		assertEquals("Set: check element 0 is correct ", (Integer)99, list1.get(2));
		assertEquals("Set: Size is right after setting. ", 3, list1.size());
		try {
            list1.set(-1, 10); // Negative index
            fail("Expected IndexOutOfBoundsException for negative index");
        } catch (IndexOutOfBoundsException e) {
            // Test passes if exception is thrown
        }
		try {
            list1.set(list1.size()+1, 5); // Index out of bounds.
            fail("Expected IndexOutOfBoundsException for number larger than size");
        } catch (IndexOutOfBoundsException e) {
            // Test passes if exception is thrown
        }
		try {
            shortList.set(0, null); // Adding null element
            fail("Expected NullPointerException when setting null element");
        } catch (NullPointerException e) {
            // Test passes if exception is thrown
        }
	}


	// TODO: Optionally add more test methods.
	@Test
	public void testtoString()
	{
	    // TODO: implement this test
		String firstReplaced = shortList.set(0, "Z");
		int secondReplaced = list1.set(2, 99);
		System.out.println(shortList.get(0));
		System.out.println(list1.get(2));
		System.out.println(shortList);
		System.out.println(list1);
	}
}
