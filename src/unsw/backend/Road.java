package unsw.backend;

public class Road extends Infrastructure{
    static private int buildTime = 1;
    static private String type = "Road";
    static private int maxUpgrade = 4;
    public Road(Province province) {
        super(buildTime, maxUpgrade, type, province);
    }
    
    public int crossPoints() {
        int temp = 0;
        switch(super.getLevel()) {
            case 1:
                temp = 4;
                break;
            case 2:
                temp = 3;
                break;
            case 3:
                temp = 2;
                break;
            case 4:
                temp = 1;
                break;
        }
        return temp;
    }

    public String getType() {
        return type;
    }
}
