package unsw.backend;
import java.util.ArrayList;

public class Turn implements Subject {
    private int turn;
    private ArrayList <Observer> observers;

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
    public void incTurn(){
        this.turn += 1;
    }
    public void Notify(){
        for(Observer temp: observers){
            temp.update(turn);
        }
    }
}
