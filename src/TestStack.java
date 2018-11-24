import java.util.EmptyStackException;

/**
 * Tests if we are implementing the stacks correctly.
 * @author Thomas Grutsch
 *
 */
public class TestStack {
	/**
	 * Runs the test bench.
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (runTests()) {
			System.out.println("*************************");
			System.out.println("All tests passed!");
		} else {
			System.out.println("*************************");
			System.out.println("Test(s) failed!");
		}

	}
	/**
	 * runs all the tests.
	 * @return
	 */
	public static boolean runTests() {
		boolean test = true;
		boolean overAll = true;
		
		System.out.println("Testing Constructor.");
		test = testConst();
		if (test) 
			System.out.println("Constructor passed.");
		else {
			System.out.println("Constructor failed.");
			overAll = false;
		}
		System.out.println("Testing Push.");
		test = testPush();
		if (test) 
			System.out.println("Push passed.");
		else {
			System.out.println("Push failed.");
			overAll = false;
		}
		System.out.println("Testing Pop.");
		test = testPop();
		
		if (test) 
			System.out.println("Pop passed.");
		else {
			System.out.println("Pop failed.");
			overAll = false;
		}
		System.out.println("Testing Peek.");
		test = testPeek();
		if (test) 
			System.out.println("Peek passed.");
		else {
			System.out.println("Peek failed.");
			overAll = false;
		}
		System.out.println("Testing isEmpty.");
		test = testIsEmpty();
		if (test) 
			System.out.println("isEmpty passed.");
		else {
			System.out.println("isEmpty failed.");
			overAll = false;
		}
		System.out.println("Testing contains.");
		test = testContains();
		if (test) 
			System.out.println("contains passed.");
		else {
			System.out.println("contains failed.");
			overAll = false;
		}
		
		
		return overAll;
	}
	
	private static boolean testConst() {
		boolean test = true;
		
		MazeRunnerStack<Position> mazeObj = new MazeRunnerStack<Position>();
		
		if (!mazeObj.isEmpty()) {
			test = false;
			System.out.println("Stack was supposed to be empty, instead was false.");
		}
		
		if (mazeObj.getSize() != 0) {
			System.out.println("Stack was supposed to be empty, instead got back size: " + mazeObj.getSize());
		}
		
		return test;
	}
	
	
	
	/**
	 * test 1- checks empty array
	 * test 2- tests sizing
	 * test 3- tests sizing and pushing many objs in.
	 * test 4- tests pushing large amounts of things in.
	 * 
	 * @return
	 */
	private static boolean testPush() {
		boolean test = true;
		MazeRunnerStack<Position> mazeObj = new MazeRunnerStack<Position>();
		//test 1 
		Position nullExc = null;
		try {
			mazeObj.push(nullExc);
			System.out.println("Null was passed through, no IllArgExc thrown.");
			test = false;
		} catch (IllegalArgumentException e) {
			// do nothing
		}
		
		//test 2 -- 1
		Position p = new Position(10, 10);
		try {
			mazeObj.push(p);
			if (mazeObj.getSize() != 1) {
				System.out.println("Pushed one item in, size was not 1");
				test = false;
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Passed in a valid argument" + p +" and "
					+ "an IllegalArgExc was thrown");
			test = false;
		}
		
		//test 3
		try {
		Position p2 = new Position(10, 10);
		mazeObj.push(p2);
		if (mazeObj.getSize() != 2) {
			System.out.println("Pushed in two items, size was not 2");
			test = false;
		}
		Position p3 = new Position(0, 10);
		mazeObj.push(p3);
		if (mazeObj.getSize() != 3) {
			System.out.println("Pushed in three items, size was not 3");
			test = false;
		}
		Position p4 = new Position(10, 0);
		mazeObj.push(p4);
		Position p5 = new Position(100, 100);
		mazeObj.push(p5);
		if (mazeObj.getSize() != 5) {
			System.out.println("Pushed in five items, size was not 25");
			test = false;
		}
		Position p6 = new Position(25, 10);
		mazeObj.push(p6);
		Position p7 = new Position(19, 17);
		mazeObj.push(p7);
		if (mazeObj.getSize() != 7) {
			System.out.println("Pushed in two items, size was not 7");
			test = false;
		}
		Position p8 = new Position(-10, -10);
		mazeObj.push(p8);
		Position p9 = new Position(4, 3);
		mazeObj.push(p9);
		Position p10 = new Position(10, 10);
		mazeObj.push(p10);
		
		if (mazeObj.getSize() != 10) {
			System.out.println("Pushed in 10 items, size was not 10");
			test = false;
		}
		} catch (IllegalArgumentException e) {
			System.out.println("Passed in a valid argument and "
					+ "an IllegalArgExc was thrown");
			test = false;
		}
		//now should have a filled array, testing if 
		//anything happens when array length doubles
		try {
			Position p11 = new Position(10, 10);
			mazeObj.push(p11);
			if (mazeObj.getSize() != 11) {
				System.out.println("Pushed in 11 items, size was not 11");
				test = false;
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Passed in a valid argument and "
					+ "an IllegalArgExc was thrown");
			test = false;
		}
		
		//test 4 -- testing if implementation get implement a large number of things
		MazeRunnerStack<Position> mazeObj2 = new MazeRunnerStack<Position>();
	
		for (int i = 0; i < 150; i++) {
			Position pos = new Position(i, -10);
			mazeObj2.push(pos);
			if (!mazeObj2.peek().equals(pos)) {
				test = false;
				System.out.println("Tried to peek, and got wrong obj back.");
			}
		}
		
		for (int i = 149; i >= 0; i--) {
			Position pos = new Position(i, -10);
			if (!mazeObj2.pop().equals(pos)) {
				test = false;
				System.out.println("Tried to pop, and got wrong obj back. " + i);
			}
		}
		
		return test;
	}
	/**
	 * test 1- checks exceptions 
	 * test 2- tests popping with one obj in
	 * test 3- pops multiple objs. 
	 * @return
	 */
	private static boolean testPop() {
		boolean test = true;
		MazeRunnerStack<Position> mazeObj = new MazeRunnerStack<Position>();
		//test 1 - exceptions
		try {
			mazeObj.pop();
			System.out.println("Tried to pop an empty stack and an EmptyStackExc was not thrown.");
			test = false;
		} catch (EmptyStackException e) {
			// do nothing
		}
		
		//test 2 
		
		try {
			Position p = new Position(10, 10);
			mazeObj.push(p);
			if (!mazeObj.pop().equals(p)) {
				System.out.print("Popped position is not the same as the one put in.(1)");
				test = false;
			}
			//killing two birds with one stone but we are assuming the first pop will work fine.
			mazeObj.pop();
			System.out.println("Tried to pop an empty stack and an EmptyStackExc was not thrown.");
			test = false;
		} catch (EmptyStackException e) {
			//do nothing
		} catch (IllegalArgumentException e) {
			System.out.println("Passed in a valid argument and "
					+ "an IllegalArgExc was thrown");
			test = false;
		}
		
		MazeRunnerStack<Position> mazeObj2 = new MazeRunnerStack<Position>();
		// test 3 - exceptions
		try {
			Position p = new Position(10, 10);
			Position p2 = new Position(5, 5);
			Position p3 = new Position(15, 15);
			mazeObj2.push(p);
			mazeObj2.push(p2);
			mazeObj2.push(p3);
			
			if (!mazeObj2.pop().equals(p3)) {
				System.out.println("Popped position is not the same as the one put in.(2)");
				test = false;
			}
			
			if (!mazeObj2.pop().equals(p2)) {
				System.out.println("Popped position is not the same as the one put in.(3)");
				test = false;
			}
			
			if (!mazeObj2.pop().equals(p)) {
				System.out.println("Popped position is not the same as the one put in.(4)");
				test = false;
			}
			
			mazeObj.pop();
			System.out.println("Tried to pop an empty stack and an EmptyStackExc was not thrown.");
			test = false;
		} catch (EmptyStackException e) {
			// do nothing
		}
		
		
		return test;
	}
	
	/**
	 * test 1- peeks empty stack
	 * test 2- peeks a nonempty stack
	 * @return
	 */
	private static boolean testPeek() {
		boolean test = true;
		//test 1 -- peeking an empty stack
		try {
			MazeRunnerStack<Position> mazeObj = new MazeRunnerStack<Position>();
			mazeObj.peek();
			test = false;
			System.out.println("Tried to peek an empty stack and no Exception was thrown.");
		} catch (EmptyStackException e) {
			//do nothing
		}
		
		//test 2 -- peeking something when one then 2 things pushed in.
		try {
			MazeRunnerStack<Position> mazeObj2 = new MazeRunnerStack<Position>();
			Position p = new Position(10,10);
			mazeObj2.push(p);
			if (!mazeObj2.peek().equals(p)) {
				test = false;
				System.out.print("Peeked a stack with one obj and didn't return the correct obj.");
			}
			Position p2 = new Position(2,10);
			mazeObj2.push(p2);
			if (!mazeObj2.peek().equals(p2)) {
				test = false;
				System.out.print("Peeked a stack with one obj and didn't return the correct obj.");
			}
		} catch (EmptyStackException e) {
			System.out.println("Tried to peeking a stack and an Exception was thrown.");
		}
		
		
		return test;
	}
	/**
	 * test 1- checks empty stack
	 * test 2- checks a nonempty stack
	 * @return
	 */
	private static boolean testIsEmpty() {
		boolean test = true;
		//test 1 -- returning true
		MazeRunnerStack<Position> mazeObj = new MazeRunnerStack<Position>();
		if (mazeObj.isEmpty() != true) {
			test = false;
			System.out.println("isEmpty returned false instead of true.");
		}
		
		Position p = new Position(0,0);
		mazeObj.push(p);
		//test 2 -- returning false
		if (mazeObj.isEmpty() != false) {
			test = false;
			System.out.println("isEmpty returned true instead of false.");
		}
		
		return test;
	}
	/**
	 * test 1- checks empty stack
	 * test 2-3- checks known positions
	 * test 4- checks unknown position
	 * @return
	 */
	private static boolean testContains() {
		boolean test = true;
		MazeRunnerStack<Position> mazeObj = new MazeRunnerStack<Position>();
		//test 1
		if (mazeObj.contains(new Position(10, 10))) {
			test = false;
			System.out.println("Contained an empty list and got back true.");
		}
		
		for (int i = 0; i < 10; i++) {
			Position pos = new Position(i, -10);
			mazeObj.push(pos);
		}
		//test 2 
		if (!mazeObj.contains(new Position(0, -10))) {
			test = false;
			System.out.println("Contained a known position and did not return true.");
		}
		//test 3
		if (!mazeObj.contains(new Position(5, -10))) {
			test = false;
			System.out.println("Contained a known position and did not return true.");
		}
		//test 4
		if (mazeObj.contains(new Position(0, 0))) {
			test = false;
			System.out.println("Contained an unknown position and got back true.");
		}
		
		return test;
	}
	
}
