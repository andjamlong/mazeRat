package mazeRat;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;


/**
 * Creates the maze, established player's starting position within
 * the maze, and places the cheese in a reachable location. 
 * 
 * @author Andrew Long
 */
public class MazeCreator {
	
	//maze is a 2d array 0 = path, 1 = wall
	private int[][] maze;
	//Players starting position, ind 0 = row, ind 1 = col
	private int[] startPos;

	//create the cheese
	private Cheese cheese;
	
	/**
	 * Creates the maze, places the player and cheese
	 */
	public MazeCreator() {
		
		//create the maze
		createMaze(8);
		
		//set the player location
		setStartPosition();
		
		//place the cheese
		placeCheese();
	}
	
	/**
	 * Creates a randomly generated maze using depth-first search.
	 *
	 * 0 represents a pathway
	 * 1 represents a wall
	 */
	private void createMaze(int n) {

	    // Create the maze array
	    maze = new int[2 *n + 1][2 * n + 1];

	    // Fill the maze with walls
	    for (int row = 0; row < maze.length; row++) {
	        for (int col = 0; col < maze[row].length; col++) {
	            maze[row][col] = 1;
	        }
	    }

	    // Track visited cells
	    boolean[][] visited = new boolean[n][n];

	    // Stack stores the cells used for backtracking
	    Stack<int[]> stack = new Stack<>();

	    // Start at the first cell
	    int row = 0;
	    int col = 0;

	    // Convert cell coordinates to maze coordinates
	    int mazeRow = 2 * row + 1;
	    int mazeCol = 2 * col + 1;

	    // Open the starting cell
	    maze[mazeRow][mazeCol] = 0;

	    // Mark the starting cell as visited
	    visited[row][col] = true;

	    // Push the starting cell onto the stack
	    stack.push(new int[] {row, col});

	    // Continue until the stack is empty
	    while (!stack.isEmpty()) {

	        // Get the current cell
	        int[] current = stack.peek();

	        row = current[0];
	        col = current[1];

	        // Store possible directions
	        int[] directions = new int[4];

	        // Count valid directions
	        int count = 0;

	        // Check above
	        if (row > 0 && !visited[row - 1][col]) {
	            directions[count] = 0;
	            count++;
	        }

	        // Check below
	        if (row < n - 1 && !visited[row + 1][col]) {
	            directions[count] = 1;
	            count++;
	        }

	        // Check left
	        if (col > 0 && !visited[row][col - 1]) {
	            directions[count] = 2;
	            count++;
	        }

	        // Check right
	        if (col < n - 1 && !visited[row][col + 1]) {
	            directions[count] = 3;
	            count++;
	        }

	        // If there are no available directions
	        if (count == 0) {

	            // Backtrack
	            stack.pop();
	        }

	        else {

	            // Randomly choose an available direction
	            int direction = directions[StdRandom.uniformInt(count)];

	            // Store the next cell
	            int nextRow = row;
	            int nextCol = col;

	            // Move up
	            if (direction == 0) {
	                nextRow--;
	            }

	            // Move down
	            else if (direction == 1) {
	                nextRow++;
	            }

	            // Move left
	            else if (direction == 2) {
	                nextCol--;
	            }

	            // Move right
	            else if (direction == 3) {
	                nextCol++;
	            }

	            // Convert current cell to maze coordinates
	            int currentMazeRow = 2 * row + 1;
	            int currentMazeCol = 2 * col + 1;

	            // Convert next cell to maze coordinates
	            int nextMazeRow = 2 * nextRow + 1;
	            int nextMazeCol = 2 * nextCol + 1;

	            // Find the wall between the cells
	            int wallRow = (currentMazeRow + nextMazeRow) / 2;
	            int wallCol = (currentMazeCol + nextMazeCol) / 2;

	            // Remove the wall
	            maze[wallRow][wallCol] = 0;

	            // Open the next cell
	            maze[nextMazeRow][nextMazeCol] = 0;

	            // Mark the next cell as visited
	            visited[nextRow][nextCol] = true;

	            // Push the next cell onto the stack
	            stack.push(new int[] {nextRow, nextCol});
	        }
	    }
	}

	
	/**
	 * sets the players starting position
	 */
	private void setStartPosition() {
		startPos = new int[] {1,1};
	}
	
	/**
	 * Randomly places the cheese in an open pathway.
	 *
	 * The cheese cannot be placed on a wall or on the
	 * player's starting position.
	 */
	private void placeCheese() {

	    // Store the randomly selected location
	    int cheeseRow;
	    int cheeseCol;

	    // Continue searching until a valid location is found
	    do {

	        // Randomly select a row
	        cheeseRow = StdRandom.uniformInt(maze.length);

	        // Randomly select a column
	        cheeseCol = StdRandom.uniformInt(maze[0].length);

	    } while (

	        // Check if the location is a wall
	        maze[cheeseRow][cheeseCol] == 1

	        // Check if the location is the player's starting position
	        || (cheeseRow == startPos[0]
	            && cheeseCol == startPos[1])
	    );

	    // Create the Cheese object at the selected location
	    cheese = new Cheese(cheeseRow, cheeseCol);
	}
	
	 /**
     * Creates a graph representing the maze.
     * Each pathway is a vertex.
     * Edges connect adjacent pathways.
     *
     * @return the graph representing the maze
     */
    private Graph createGraph() {

        // Create one vertex for every array location
        Graph graph = new Graph(maze.length * maze[0].length);

        // Check every location in the maze
        for (int row = 0; row < maze.length; row++) {

            for (int col = 0; col < maze[row].length; col++) {

                // Only create edges from pathways
                if (maze[row][col] == 0) {

                    // Check the location to the right
                    if (col + 1 < maze[row].length
                            && maze[row][col + 1] == 0) {

                        graph.addEdge(
                                convertToVertex(row, col),
                                convertToVertex(row, col + 1));
                    }

                    // Check the location below
                    if (row + 1 < maze.length
                            && maze[row + 1][col] == 0) {

                        graph.addEdge(
                                convertToVertex(row, col),
                                convertToVertex(row + 1, col));
                    }
                }
            }
        }

        return graph;
    }

    /**
     * Converts a row and column into a graph vertex.
     *
     * @param row the row in the maze
     * @param col the column in the maze
     * @return the corresponding graph vertex
     */
    private int convertToVertex(int row, int col) {

        return row * maze[0].length + col;
    }

    /**
     * Returns the maze.
     *
     * @return the 2D maze array
     */
    public int[][] getMaze() {

        return maze;
    }

    /**
     * Returns the player's starting position.
     *
     * @return the starting position
     */
    public int[] getStartPos() {
        return startPos;
    }

    /**
     * Returns the cheese object.
     *
     * @return the cheese
     */
    public Cheese getCheese() {

        return cheese;
    }

    /**
     * Checks whether the cheese can be reached from the player's starting position.
     *
     * @return true if a path exists to the cheese
     */
    public boolean isCheeseReachable() {
        Graph graph = createGraph();

        // Convert the player's starting position to a graph vertex
        int startVertex = convertToVertex(startPos[0], startPos[1]);

        // Create BFS starting at the player's position
        BreadthFirstPaths paths = new BreadthFirstPaths(graph, startVertex);

        // Convert the cheese location to a graph vertex
        int cheeseVertex = convertToVertex(cheese.getRow(), cheese.getCol());

        // Check whether BFS can reach the cheese
        return paths.hasPathTo(cheeseVertex);
    }	
}
