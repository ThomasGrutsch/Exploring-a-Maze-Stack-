/**
 * 
 * @author Runs the maze program
 *
 */
public class Maze {
	private MazeRunnerStack path;
	private Boolean solved;
	private char[][] mazeInfo;
	private Position start;
	private Position finish;
	
	/**
	 * Main method to test maze
	 * @param args
	 */
	public static void main(String[] args) {
		char[][] maze = new char[3][3];
		maze[0][0] = 'L'; maze[0][1] = '.'; maze[0][2] = '|'; 
		maze[1][0] = '|'; maze[1][1] = '.'; maze[1][2] = '_';
		maze[2][0] = 'L'; maze[2][1] = '_'; maze[2][2] = '_';
		
//		char[][] maze = new char[2][3];
//		maze[0][0] = 'L'; maze[0][1] = '.'; maze[0][2] = '|'; 
//		maze[1][0] = 'L'; maze[1][1] = '_'; maze[1][2] = '_';
		
//		char[][] maze = new char[1][3];
//		maze[0][0] = 'L'; maze[0][1] = '_'; maze[0][2] = '_'; 
		
//		char[][] maze = new char[2][3];
//		maze[0][0] = 'L'; maze[0][1] = '_'; maze[0][2] = '.'; 
//		maze[1][0] = 'L'; maze[1][1] = '_'; maze[1][2] = '_';
		
		Maze testMaze = new Maze(maze);
		testMaze.setStart(0, 0);
		testMaze.setFinish(2, 2);
		testMaze.displayMaze();
		testMaze.solveMaze();
		testMaze.displayMaze();
		
	}
	/**
	 * Constructor that takes the mazeInfo into a char array here.
	 * @param mazeInfo
	 */
	public Maze(char[][] mazeInfo) {
		this.mazeInfo = mazeInfo;
		path = new MazeRunnerStack<Position>();
	}
	/**
	 * sets the start of the maze
	 * @param row
	 * @param col
	 */
	public void setStart(int row, int col) {
		
		start = new Position(row, col);
		
	}
	/**
	 * sets the finish of the maze.
	 * @param row
	 * @param col
	 */
	public void setFinish(int row, int col) {
		
		finish = new Position(row, col);
	}
	
	/**
	 * Displays the maze when called.
	 */
	public void displayMaze() {
		String mazeRep = "";
		if (solved != null && !solved) {
			System.out.println("No solution could be found.");
		} else if (solved != null && solved) {
			System.out.println("Solution is:");
		}
			
		
		
		if (solved == null || solved == false) {
			for (int i = 0; i < mazeInfo.length; i++) {

				if (i == 0) {
					// adds the top to the maze
					mazeRep += "+---+";
					if (mazeInfo[i].length > 1)
						for (int j = 1; j < mazeInfo[i].length; j++) {
							mazeRep += "---+";
						}
					mazeRep += System.lineSeparator();
					// adds the middle wall layer
					for (int j = 0; j < mazeInfo[i].length; j++) {
						if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '|') {
							if (i == start.row && j == start.col) {
								mazeRep += "|" + " S ";
							} else if (i == finish.row && j == finish.col) {
								mazeRep += "|" + " F ";
							} else {
								mazeRep += "|" + "   "; // 3 spaces
							}

						} else {
							if (i == start.row && j == start.col) {
								mazeRep += "  S ";
							} else if (i == finish.row && j == finish.col) {
								mazeRep += "  F ";
							} else {
								mazeRep += "    "; // 4 spaces
							}
						}
					}
					mazeRep += "|" + System.lineSeparator();
					// adds the bottom +---+ layer
					mazeRep += "+";
					for (int j = 0; j < mazeInfo[i].length; j++) {
						if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '_') {
							mazeRep += "---+";
						} else {
							mazeRep += "   +";
						}
					}
					mazeRep += System.lineSeparator();

				} else {

					// adds the middle wall layer
					for (int j = 0; j < mazeInfo[i].length; j++) {
						if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '|') {
							if (i == start.row && j == start.col) {
								mazeRep += "|" + " S ";
							} else if (i == finish.row && j == finish.col) {
								mazeRep += "|" + " F ";
							} else {
								mazeRep += "|" + "   "; // 3 spaces
							}
						} else {
							if (i == start.row && j == start.col) {
								mazeRep += "  S ";
							} else if (i == finish.row && j == finish.col) {
								mazeRep += "  F ";
							} else {
								mazeRep += "    "; // 4 spaces
							}
						}
					}
					mazeRep += "|" + System.lineSeparator();
					// adds the bottom +---+ layer
					mazeRep += "+";
					for (int j = 0; j < mazeInfo[i].length; j++) {
						if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '_') {
							mazeRep += "---+";
						} else {
							mazeRep += "   +";
						}
					}
					mazeRep += System.lineSeparator();
				}

			}
		} else { //now if solved is true
			for (int i = 0; i < mazeInfo.length; i++) {

				if (i == 0) {
					// adds the top to the maze
					mazeRep += "+---+";
					if (mazeInfo[i].length > 1)
						for (int j = 1; j < mazeInfo[i].length; j++) {
							mazeRep += "---+";
						}
					mazeRep += System.lineSeparator();
					// adds the middle wall layer
					for (int j = 0; j < mazeInfo[i].length; j++) {
						if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '|') {
							if (i == start.row && j == start.col) {
								mazeRep += "|" + " S ";
							} else if (i == finish.row && j == finish.col) {
								mazeRep += "|" + " F ";
							} else if (path.contains(new Position(i, j))) {
								mazeRep += "|" + " * ";
							} else {
								mazeRep += "|" + "   "; // 3 spaces
							}

						} else {
							if (i == start.row && j == start.col) {
								mazeRep += "  S ";
							} else if (i == finish.row && j == finish.col) {
								mazeRep += "  F ";
							} else if (path.contains(new Position(i, j))) {
								mazeRep += "  * ";								
							} else {
								mazeRep += "    "; // 4 spaces
							}
						}
					}
					mazeRep += "|" + System.lineSeparator();
					// adds the bottom +---+ layer
					mazeRep += "+";
					for (int j = 0; j < mazeInfo[i].length; j++) {
						if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '_') {
							mazeRep += "---+";
						} else {
							mazeRep += "   +";
						}
					}
					mazeRep += System.lineSeparator();

				} else {

					// adds the middle wall layer
					for (int j = 0; j < mazeInfo[i].length; j++) {
						if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '|') {
							if (i == start.row && j == start.col) {
								mazeRep += "|" + " S ";
							} else if (i == finish.row && j == finish.col) {
								mazeRep += "|" + " F ";
							} else if (path.contains(new Position(i, j))) {
								mazeRep += "|" + " * ";
							} else {
								mazeRep += "|" + "   "; // 3 spaces
							}
						} else {
							if (i == start.row && j == start.col) {
								mazeRep += "  S ";
							} else if (i == finish.row && j == finish.col) {
								mazeRep += "  F ";
							} else if (path.contains(new Position(i, j))) {
								mazeRep += "  * ";
							} else {
								mazeRep += "    "; // 4 spaces
							}
						}
					}
					mazeRep += "|" + System.lineSeparator();
					// adds the bottom +---+ layer
					mazeRep += "+";
					for (int j = 0; j < mazeInfo[i].length; j++) {
						if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '_') {
							mazeRep += "---+";
						} else {
							mazeRep += "   +";
						}
					}
					mazeRep += System.lineSeparator();
				}

			}
		}
		
		System.out.print(mazeRep);

		if (solved != null && solved) {
			String pathString = "Path is: ";
			int size = path.getSize();
			//turns the stack around
			MazeRunnerStack<Position> stack = new MazeRunnerStack<Position>();
			for (int i = 0; i < size; i++) {
				stack.push(path.pop());
			}
			
			for (int i = 0; i < size; i++) {
				Position pos = stack.pop();
				if (stack.getSize() > 0)
					pathString += "[" + pos.row + "," + pos.col + "] --> ";
				else
					pathString += "[" + pos.row + "," + pos.col + "]";

			}

			System.out.println(pathString);
		}
		
	}
	/**
	 * solves the maze with the stackADT and a orientation char
	 */
	public void solveMaze() {
		final int MAX_STEPS = mazeInfo.length * mazeInfo[0].length * 4;
		int steps = 0;
		char orientation = 'E'; //can be set to 'N'(^) 'S'(\/) 'E"(>) 'W' (<)
		path.push(start);
		
		while (steps <= MAX_STEPS) {
			
			if (orientation == 'E') {
				//going right is row + 1
				if (path.peek().row + 1 < mazeInfo.length &&
					!(mazeInfo[path.peek().row][path.peek().col] == 'L' ||
					mazeInfo[path.peek().row][path.peek().col] == '_')) {
					
					path.push(new Position(path.peek().row + 1, path.peek().col));
					orientation = 'S';
				
				//trying to go straight
				} else if (path.peek().col + 1 < mazeInfo[path.peek().row].length &&
						   (mazeInfo[path.peek().row][path.peek().col + 1] == '.' ||
						   mazeInfo[path.peek().row][path.peek().col + 1] == '_')) {
					
					path.push(new Position(path.peek().row, path.peek().col + 1));
					//orientation does not change
				
				//trying to go left 
				} else if (path.peek().row - 1 >= 0 &&
						   (mazeInfo[path.peek().row - 1][path.peek().col] == '.' ||
						   mazeInfo[path.peek().row - 1][path.peek().col] == '|')) {
							   
					orientation = 'N';
					path.push(new Position(path.peek().row - 1, path.peek().col));
							   
				} else {
					
					orientation = 'W';
					path.pop();
					
				}
				
				steps++;
				
			} else if (orientation == 'S') {
				//going right is col - 1
				if (path.peek().col - 1 > 0 &&
						!(mazeInfo[path.peek().row][path.peek().col] == 'L' ||
						mazeInfo[path.peek().row][path.peek().col] == '|')) {
					
					path.push(new Position(path.peek().row, path.peek().col - 1));
					orientation = 'W';
					
				//trying to go straight: row + 1
				} else if (path.peek().row + 1 < mazeInfo.length &&
						   !(mazeInfo[path.peek().row ][path.peek().col] == 'L' ||
						   mazeInfo[path.peek().row][path.peek().col] == '_')) {
					
					path.push(new Position(path.peek().row + 1, path.peek().col));
					//orientation does not change
					
				//trying to go left: col + 1
				} else if (path.peek().col + 1 < mazeInfo[path.peek().row].length &&
						   (mazeInfo[path.peek().row ][path.peek().col + 1] == '.' ||
						   mazeInfo[path.peek().row][path.peek().col + 1] == '_')) {
					
					path.push(new Position(path.peek().row, path.peek().col + 1));
					orientation = 'E';
					
				} else {
					
					path.pop();
					orientation = 'N';
					
				}
				
				steps++;
				
			} else if (orientation == 'W') {
				//trying to go right: row - 1
				if (path.peek().row - 1 >= 0 &&
						   (mazeInfo[path.peek().row - 1][path.peek().col] == '.' ||
						   mazeInfo[path.peek().row - 1][path.peek().col] == '|')) {
					
					path.push(new Position(path.peek().row - 1, path.peek().col));
					orientation = 'N';
				
				//trying to go straight: col - 1
				} else if (path.peek().col - 1 >= 0 &&
						   !(mazeInfo[path.peek().row][path.peek().col] == 'L' ||
						   mazeInfo[path.peek().row][path.peek().col] == '|')) {
					
					path.push(new Position(path.peek().row, path.peek().col - 1));
					
				//trying to go left: row + 1
				} else if (path.peek().row + 1 < mazeInfo.length &&
						   !(mazeInfo[path.peek().row ][path.peek().col] == 'L' ||
						   mazeInfo[path.peek().row][path.peek().col] == '_')) {
					
					path.push(new Position(path.peek().row + 1, path.peek().col));
					orientation = 'S';
					
					
				} else {
					
					path.pop();
					orientation = 'E';
					
				}
				
				steps++;
			} else { //orientation == 'N'
				
				//trying to go right: col + 1
				if (path.peek().col + 1 < mazeInfo[path.peek().row].length &&
						   (mazeInfo[path.peek().row ][path.peek().col + 1] == '.' ||
						   mazeInfo[path.peek().row][path.peek().col + 1] == '_')) {
					
					path.push(new Position(path.peek().row, path.peek().col + 1));
					orientation = 'E';
				
				//trying to go straight: row - 1 
				} else if (path.peek().row - 1 >= 0 &&
						   (mazeInfo[path.peek().row - 1][path.peek().col] == '.' ||
						   mazeInfo[path.peek().row - 1][path.peek().col] == '|')) {
					
					path.push(new Position(path.peek().row - 1, path.peek().col));
					
					
				//trying to go left: col - 1
				} else if (path.peek().col - 1 >= 0 &&
						  !(mazeInfo[path.peek().row][path.peek().col] == 'L' ||
						  mazeInfo[path.peek().row][path.peek().col] == '|')) {
					
					path.push(new Position(path.peek().row, path.peek().col - 1));
					orientation = 'W';
				
				} else {
					
					path.pop();
					orientation = 'S';
					
				}
				
				steps++;
			}
			
			if (!path.isEmpty() && path.peek().equals(finish)) {
				solved = true;
				break;
			}
			
			
		}
		
		if (solved == null) {
			solved = false;
		}
		
	}
	
	
	
}
