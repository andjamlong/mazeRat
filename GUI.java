package mazeRat;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class GUI 
{
    JFrame window = new JFrame("Maze Rat");
    private int[][] maze;
    private int cellSize;

    Color WALL = Color.black;
    Color PATH = Color.white;

    public GUI (int[][] maze, int cellSize, Player player, Cheese cheese)
    {
        window.setSize(800, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

    }

    private class MazePanel extends JPanel
    {
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            for (int row = 0; row < maze.length; row++)
            {
                for (int column = 0; column < maze[row].length; column++)
                {
                    if (maze[row][column] == 0)
                    {

                    }

                    else if (maze[row][column] == 1)
                    {

                    }

                }
            }


        }
    }

    public static void main(String[] args)
    {
        MazeCreator mazeCreator = new MazeCreator();
        int[][] maze = mazeCreator.getMaze();

        int cellSize = 50;

        int[] startPosition = {1, 1};

        Player player = new Player(startPosition, maze);

        // Example goal coordinates; adjust to your Cheese constructor
        Cheese cheese = new Cheese(1, 3);

        GUI gui = new GUI(maze, cellSize, player, cheese);
    }
}