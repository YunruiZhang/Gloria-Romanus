package unsw.backend;

abstract public class Unit {
    private int soldiers;
    private String name;
    private Province location;
    private String type;
    private double reduceEnemyDamage;
    private double soilderSpeed;
    private double reduceEnemyDamagePercent;

    public abstract void move(Province dest);
    public abstract void attact();
    public Unit(int soldiers, String name, Province location, String type){
        this.soldiers = soldiers;
        this.name = name;
        this.location = location;
        this.type = type;
        this.reduceEnemyDamage = 0;
        this.soilderSpeed = 1;
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

    public double caculateEnemyDamage(double EDamage){
        double damageLeft = (EDamage * (1-reduceEnemyDamagePercent)) - reduceEnemyDamage;
        if(damageLeft < 1){
            return 1;
        }else{
            return damageLeft;
        }
    }
}
