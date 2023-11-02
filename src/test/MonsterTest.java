package test;

import game.*;
import org.junit.Test;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class MonsterTest {

    private Monster monster;
    private Maze maze = new Maze(5,5);

    @BeforeEach
    public void setup(){
        maze.resetMaze();
    }

    @Test
    public void monsterCannotMoveLeft(){
        int col = 0;
        int row = 1;
        maze.getMaze()[col][row] = new Cell(new Coordinate(col,row), CellInfo.WALL);
        monster = new Monster(maze);
        monster.setCoordinate(new Coordinate(col+1, row));
        assertFalse(monster.canMove(new Coordinate(col,row)));
    }

    @Test
    public void monsterCanMoveLeft(){
        int col = 0;
        int row = 1;
        maze.getMaze()[col][row] = new Cell(new Coordinate(col,row), CellInfo.EMPTY);
        monster = new Monster(maze);
        monster.setCoordinate(new Coordinate(col+1, row));
        assertTrue(monster.canMove(new Coordinate(col,row)));
    }

    @Test
    public void monsterCannotMoveRight(){
        int col = 2;
        int row = 1;
        maze.getMaze()[col][row] = new Cell(new Coordinate(col,row), CellInfo.WALL);
        monster = new Monster(maze);
        monster.setCoordinate(new Coordinate(col-1, row));
        assertFalse(monster.canMove(new Coordinate(col,row)));
    }

    @Test
    public void monsterCanMoveRight(){
        int col = 2;
        int row = 1;
        maze.getMaze()[col][row] = new Cell(new Coordinate(col,row), CellInfo.EMPTY);
        monster = new Monster(maze);
        monster.setCoordinate(new Coordinate(col-1, row));
        assertTrue(monster.canMove(new Coordinate(col,row)));
    }

    @Test
    public void monsterCannotMoveUp(){
        int col = 1;
        int row = 0;
        maze.getMaze()[col][row] = new Cell(new Coordinate(col,row), CellInfo.WALL);
        monster = new Monster(maze);
        monster.setCoordinate(new Coordinate(col, row+1));
        assertFalse(monster.canMove(new Coordinate(col,row)));
    }

    @Test
    public void monsterCanMoveUp(){
        int col = 1;
        int row = 0;
        maze.getMaze()[col][row] = new Cell(new Coordinate(col,row), CellInfo.EMPTY);
        monster = new Monster(maze);
        monster.setCoordinate(new Coordinate(col, row+1));
        assertTrue(monster.canMove(new Coordinate(col,row)));
    }

    @Test
    public void monsterCannotMoveDown(){
        int col = 1;
        int row = 2;
        maze.getMaze()[col][row] = new Cell(new Coordinate(col,row), CellInfo.WALL);
        monster = new Monster(maze);
        monster.setCoordinate(new Coordinate(col, row-1));
        assertFalse(monster.canMove(new Coordinate(col,row)));
    }

    @Test
    public void monsterCanMoveDown(){
        int col = 1;
        int row = 2;
        maze.getMaze()[col][row] = new Cell(new Coordinate(col,row), CellInfo.EMPTY);
        monster = new Monster(maze);
        monster.setCoordinate(new Coordinate(col, row-1));
        assertTrue(monster.canMove(new Coordinate(col,row)));
    }

    @Test
    public void monsterCannotMoveDiagonally(){
        maze.resetMaze();
        monster = new Monster(maze);
        monster.setCoordinate(new Coordinate(1, 1));
        assertFalse(monster.canMove(new Coordinate(0,0)));
        assertFalse(monster.canMove(new Coordinate(2,0)));
        assertFalse(monster.canMove(new Coordinate(0,2)));
        assertFalse(monster.canMove(new Coordinate(2,2)));
    }
}
