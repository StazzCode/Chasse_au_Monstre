package game;

import java.util.ArrayList;

public class Hunter extends Player{

    protected Maze maze;
    private ArrayList<Coordinate> hitsList;

    public Hunter(Maze m){
    	this.attach(m);
        this.maze = m;
        this.hitsList = new ArrayList<>();
    }
    
    public void hit(Coordinate c) {
    	hitsList.add(c);
    	notifyObservers();
    }
    
    public ArrayList<Coordinate> getHitsList(){
    	return this.hitsList;
    }
    
}
