package test;
import org.junit.Test;

import game.Cell;
import game.CellInfo;
import game.Coordinate;
import game.Maze;

import static org.junit.Assert.*;

public class MazeTest {
    
    @Test
    public void testResetMap() {
        Maze maze = new Maze(5, 5);
        maze.resetMap();
        for (int i = 0; i < maze.rows; i++) {
            for (int j = 0; j < maze.cols; j++) {
                if (i == maze.monster.getCoordinate().row && j == maze.monster.getCoordinate().col) {
                    assertEquals(CellInfo.MONSTER, maze.maze[i][j].getState());
                } else {
                    assertEquals(CellInfo.EMPTY, maze.maze[i][j].getState());
                }
            }
        }
    }
    
    @Test
    public void testGenerateObstacles() {
        Maze maze = new Maze(5, 5);
        maze.resetMap();
        maze.generateObstacles();
        int obstacleCount = 0;
        for (int i = 0; i < maze.rows; i++) {
            for (int j = 0; j < maze.cols; j++) {
                if (maze.maze[i][j].getState() == CellInfo.WALL) {
                    obstacleCount++;
                }
            }
        }
        assertEquals(10, obstacleCount);
    }
    
    @Test
    public void testMoveMonster() {
        Maze maze = new Maze(5, 5);
        maze.resetMap();
        maze.monster.move(new Coordinate(2, 2));
        assertEquals(2, maze.monster.getCoordinate().row);
        assertEquals(2, maze.monster.getCoordinate().col);
    }
    
    @Test
    public void testUpdateMaze() {
        Maze maze = new Maze(5, 5);
        maze.resetMap();
        Cell[][] newMaze = new Cell[5][5];
        for (int i = 0; i < maze.rows; i++) {
            for (int j = 0; j < maze.cols; j++) {
                newMaze[i][j] = new Cell(new Coordinate(i, j), CellInfo.WALL);
            }
        }
        maze.updateMaze(newMaze);
        for (int i = 0; i < maze.rows; i++) {
            for (int j = 0; j < maze.cols; j++) {
                assertEquals(CellInfo.WALL, maze.maze[i][j].getState());
            }
        }
    }
}