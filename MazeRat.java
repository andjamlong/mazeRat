package mazeRat;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Main class used to test the maze, player, and cheese.
 *
 * Allows the player to navigate the maze using keyboard input.
 *
 * @author Andrew Long
 */
public class MazeRat {

    public static void main(String[] args) {

    	
    	
        // Create the maze
        MazeCreator mazeCreator = new MazeCreator();

        // Get the maze array
        int[][] maze = mazeCreator.getMaze();

        // Get the player's starting position
        int[] startPos = mazeCreator.getStartPos();

        // Get the cheese object
        Cheese cheese = mazeCreator.getCheese();

        // Create the player
        Player player = new Player(startPos, maze);

        // Check if the cheese is reachable using BFS
        StdOut.println("Is the cheese reachable? "
                + mazeCreator.isCheeseReachable());

        // Continue playing until the cheese is found
        while (!cheese.isFound(player)) {

            // Display the maze
            displayMaze(maze, player, cheese);

            // Ask the player for movement
            StdOut.println("Move using W, A, S, D.");
            StdOut.println("Press Q to quit.");

            // Read keyboard input
            String input = StdIn.readString();

            // Convert input to lowercase
            input = input.toLowerCase();

            // Check the player's input
            if (input.equals("w")) {
                player.moveUp();
            }
            else if (input.equals("s")) {
                player.moveDown();
            }
            else if (input.equals("a")) {
                player.moveLeft();
            }
            else if (input.equals("d")) {
                player.moveRight();
            }
            else if (input.equals("q")) {
                StdOut.println("Game ended.");
                return;
            }
            else {
                StdOut.println("Invalid input.");
            }
        }

       
        displayMaze(maze, player, cheese);

        // The player has reached the cheese
        StdOut.println("Congratulations! You found the cheese!");
        
    }


    /**
     * Displays the maze in the console.
     *
     * @param maze the maze array
     * @param player the player object
     * @param cheese the cheese object
     */
    private static void displayMaze(int[][] maze, Player player, Cheese cheese) {

        // Check every row in the maze
        for (int row = 0; row < maze.length; row++) {

            // Check every column in the current row
            for (int col = 0; col < maze[row].length; col++) {

                // Display the player
                if (row == player.getRow() && col == player.getCol()) {
                    StdOut.print("P ");
                }

                // Display the cheese
                else if (row == cheese.getRow() && col == cheese.getCol()) {
                    StdOut.print("C ");
                }

                // Display a wall
                else if (maze[row][col] == 1) {
                    StdOut.print("# ");
                }

                // Display a pathway
                else {
                    StdOut.print(". ");
                }
            }

            // Move to the next line after displaying a row
            StdOut.println();
        }
        

        StdOut.println();
    }
}