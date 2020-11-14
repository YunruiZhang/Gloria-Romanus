package unsw.backend;

/**
 * interface for composite design patten
 */
public interface GoalComponent {
    public boolean checkMeet(Player player);
    public String getType();
}
