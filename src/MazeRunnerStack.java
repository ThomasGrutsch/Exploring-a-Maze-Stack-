import java.util.EmptyStackException;

/**
 * The actual implementation of the stack.
 * @author Thomas Grutsch
 *
 */
public class MazeRunnerStack<E> implements StackADT<Position> {
	
	Position[] stack;
	private int size;
	private Position head;
	public MazeRunnerStack() {
		stack = new Position[10];
		size = 0;
	}
	
	@Override
	public void push(Position item) {
		if (item == null)
			throw new IllegalArgumentException();
		
		if (stack[0] == null) {
			
			stack[0] = item;
			size++;
			
		} else {
			if (size == stack.length) {
				//making of a new array makes this easier to do.
				Position[] newArray = new Position[stack.length * 2];
				for(int i = 0; i < size; i++) {
					newArray[i + 1] = stack[i];
					
				}
				//make stack the new array
				stack = newArray;
				//set item to 0 and increment size
				stack[0] = item;
				size++;
			} else {
				Position[] newArray = new Position[stack.length];
				for (int i = 0; i < size; i++) {
					newArray[i+1] = stack[i];
				}
				stack = newArray;
				stack[0] = item;
				size++;
			}
			
			
		}
		
	}

	@Override
	public Position pop() throws EmptyStackException {
		
		if (stack[0] == null) {
			throw new EmptyStackException();
		} else {
			//gets returned
			Position tempPos = stack[0];
			
			for (int i = 1; i <= size; i++) {
				if (i != size) {
					stack[i - 1] = stack[i];
				} else {
					stack[size - 1] = null;
				}
			}
			
			size--;
			
			//incase if size goes to 0.
			if (size == 0) {
				stack = new Position[10];
			}
			return tempPos;
		}
	}

	@Override
	public Position peek() throws EmptyStackException {
		
		if ( stack[0] != null) {
			return stack[0];
		} else {
			throw new EmptyStackException();
		}
		
	}

	@Override
	public boolean isEmpty() {
		return stack[0] == null;	
	}
	
	//Reports whether the Position p can be found within the stack
	public boolean contains(Position p) { 
		boolean contained = false;
		if (!isEmpty()) {
			for (int i = 0; i < size; i++) {
				if (p.equals(stack[i]))
					contained = true;
			}
			return contained;
		} else 
			return contained;
	}
	
	public int getSize() {
		return size;
	}
	

}

/**
 * Position class that creates the positions.
 * @author Thomas Grutsch
 *
 */
    class Position {
	int col;
	int row;
	Position(int row, int col) {
		this.col = col;
		this.row = row;
	}
	boolean equals(Position other) {
		return this.col==other.col && this.row==other.row;
	}
}





