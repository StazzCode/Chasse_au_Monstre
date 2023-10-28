package game;

import utils.Subject;

public abstract class Player extends Subject {

    protected Coordinate coordinate;
    
    public Coordinate getCoordinate() {
    	return this.coordinate;
    }

    /*
    public boolean canPlay(){

    }

    public void play(){

    }

    public void update(ICellEvent);


     */
}
