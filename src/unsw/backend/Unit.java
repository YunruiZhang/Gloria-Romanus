package unsw.backend;

abstract public class Unit {
    private int soldiers;
    private String name;
    private Province location;
    private String type;
    public abstract void move(Province dest);
    public abstract void attact();
    public Unit(int soldiers, String name, Province location, String type){
        this.soldiers = soldiers;
        this.name = name;
        this.location = location;
        this.type = type;
    }
    public String getName(){
        return name;
    }
    public int get_soliders(){
        return soldiers;
    }
    public void soliders_die(int num){
        soldiers -= num;
    }
}
