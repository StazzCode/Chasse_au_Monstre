package test;
import org.junit.Test;

import game.model.Cell;
import game.model.CellInfo;
import game.model.Coordinate;
import game.model.Maze;

import static org.junit.Assert.*;

public class MazeTest {
    
    @Test
    public void testResetMaze() {
        Maze maze = new Maze(5, 5);
        maze.resetMaze();
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                if (i == maze.getMonster().getCoordinate().getRow() && j == maze.getMonster().getCoordinate().getColumn()) {
                    assertEquals(CellInfo.MONSTER, maze.getMaze()[i][j].getState());
                } else {
                    assertEquals(CellInfo.EMPTY, maze.getMaze()[i][j].getState());
                }
            }
        }
    }
    
    @Test
    public void testGenerateObstacles() {
        Maze maze = new Maze(10, 10);
        maze.resetMaze();
        maze.generateObstacles();
        int obstacleCount = 0;
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                if (maze.getMaze()[j][i].getState() == CellInfo.WALL) {
                    obstacleCount++;
                }
            }
        }
        assertEquals(8, obstacleCount);
    }
    
    @Test
    public void testMoveMonster() {
        Maze maze = new Maze(5, 5);
        maze.resetMaze();
        maze.getMonster().move(new Coordinate(2, 2));
        assertEquals(0, maze.getMonster().getCoordinate().getRow());
        assertEquals(0, maze.getMonster().getCoordinate().getColumn());
    }

    @Test
    public void testUpdateMaze() {
        Maze maze = new Maze(5, 5);
        maze.resetMaze();
        Cell[][] newMaze = new Cell[5][5];
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                newMaze[i][j] = new Cell(new Coordinate(i, j), CellInfo.WALL);
            }
        }
        maze.updateMaze(newMaze);
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                assertEquals(CellInfo.WALL, maze.getMaze()[i][j].getState());
            }
        }
    }
}