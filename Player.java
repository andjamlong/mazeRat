package mazeRat;

public class Player 
{
	private int[] position = new int[2];
	private int[][] maze;
	
	/** 
	 * Represents the Player character in the maze. stores the players position 
	 * in the maze and allows the player to move the mouse throughout it.
	 * 
	 * @param startPos : array with two positions. position 0 is the row, position 1 is the column
	 * @param maze : the maze the player will be placed in
	 */
	public Player (int[] startPos, int[][] maze)
	{
		//checks if the given maze is valid
		if (maze == null || maze.length == 0 || maze[0].length == 0)
		{
			throw new IllegalArgumentException("this is not a valid maze!");
		}
		
		for (int i = 0; i < maze.length; i++)
		{
			int length = maze[0].length;
			
			if (maze[i] == null || maze[i].length != length)
			{
				throw new IllegalArgumentException("this is not a valid maze!");
			}
		}
		
		//tests if the starting position has the correct number of elements
		if (startPos.length != 2)
		{
			throw new IllegalArgumentException("this is not a valid start position!");
		}
		
		//checks if the position is negative
		if (startPos == null || startPos[0] < 0 || startPos[1] < 0)
		{
			throw new IllegalArgumentException("this is not a valid start position!");
		}
		
		//checks if the start position is inside the maze
		if (startPos[0] >= maze.length || startPos[1] >= maze[0].length)
		{
			throw new IllegalArgumentException("this is not a valid start position!");
		}
		
		this.maze = maze;
		//initializes the players starting position
		
		
		for (int i = 0; i < startPos.length; i++)
		{
			position[i] = startPos[i];
		}
		
	}
	
	/**
	 * Checks whether a position the player is trying to move to is valid or not
	 * 
	 * @param position : the position to check is valid
	 * @return : if the position is valid or not
	 */
	private boolean canMove(int[] position)
	{
		if(position[0] < 0 || position[0] >= maze.length || position[1] < 0 || position[1] >= maze[0].length)
		{
			return false;
		}
		
		else if (maze[position[0]][position[1]] == 1)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * moves the player up in the maze
	 */
	public void moveUp()
	{
		int[] tempPos = {position[0] - 1, position[1]};
		if (canMove(tempPos))
		{
			position[0] -= 1;
		}
	}
	
	/**
	 * moves the player down in the maze
	 */
	public void moveDown()
	{
		int[] tempPos = {position[0] + 1, position[1]};
		if (canMove(tempPos))
		{
			position[0] += 1;
		}
	}
	
	/**
	 * moves the player left in the maze
	 */
	public void moveLeft()
	{
		int[] tempPos = {position[0], position[1] - 1};
		if (canMove(tempPos))
		{
			position[1] -= 1;
		}
	}
	
	/**
	 * moves the player right in the maze
	 */
	public void moveRight()
	{
		int[] tempPos = {position[0], position[1] + 1};
		if (canMove(tempPos))
		{
			position[1] += 1;
		}
	}
	
	/**
	 * Returns the player's current row.
	 * 
	 * @return the player's row
	 */
	public int getRow()
	{
	    return position[0];
	}

	/**
	 * Returns the player's current column.
	 * 
	 * @return the player's column
	 */
	public int getCol()
	{
	    return position[1];
	}

}