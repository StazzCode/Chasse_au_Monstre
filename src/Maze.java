import java.util.Random;

public class Maze {
    Integer[][] maze;
    int rows, cols;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        maze = new Integer[rows][cols];
    }

    public void updateMaze(int rows, int cols) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.maze[i][j] = maze[i][j];
            }
        }
    }

    public void resetMap() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                maze[i][j] = null;
            }
        }
    }

    public void generateObstacles() {
        int obstacles = 10;
        for (int i = 0; i < obstacles; i++) {
            int x = new Random().nextInt(rows);
            int y = new Random().nextInt(cols);
            maze[x][y] = 0;
        }
    }

    // affichage plateau
    public void displayMaze() {
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < cols; j++) {
                System.out.print(maze[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Maze maze = new Maze(10, 10);
        maze.generateObstacles();
        maze.displayMaze();
        maze.resetMap();
        maze.displayMaze();
    }

    public void updateLastAppearance() {
        //
    }
}
