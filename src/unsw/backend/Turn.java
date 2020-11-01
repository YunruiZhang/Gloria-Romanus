package unsw.backend;
import java.util.ArrayList;

/**
 * class turn the controler of the observer turn
 */
public class Turn implements Subject {
    private int turn;
    private ArrayList <Observer> observers;

    /**
     * constructor
     */
    public Turn(){
        this.turn = 0;
        this.observers = new ArrayList<Observer>();
    }
    public void attach(Observer o){
        observers.add(o);
    }
    public void detach(Observer o){
        observers.remove(o);
    }

    /**
     * inc the turn and notify all the observers
     */
    public void incTurn(){
        this.turn += 1;
        this.Notify();
    }
    public void Notify(){
        for(Observer temp: observers){
            temp.update(turn);
        }
    }
}
