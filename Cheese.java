package mazeRat;


/**
 * Represents the cheese that the player (mouse) is trying to reach. 
 * Stores the cheese's location in the maze and checks whether or
 * not the player has reached it. 
 * 
 * This commit is just a test.
 * 
 * @author Andrew Long
 */

public class Cheese {
	
	//The position of the cheese within the maze
	private int row;
	private int col;
	
	/*
	 * Create a cheese object at the specified location
	 * 
	 * @param row is the cheese's row within the maze
	 * @param col is the cheese's column within the maze
	 */
	public Cheese(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * @return the row of the cheese
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the column of the cheese
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Checks if the player has reached the cheese.
	 * 
	 * @param player - the player's location is being compared
	 * to the cheese location
	 * @return true if the player is at the cheese's location
	 */
	public boolean isFound(Player player) {
		return player.getRow() == row && player.getCol() == col;
	}

}
