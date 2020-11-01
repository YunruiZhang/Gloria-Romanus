package unsw.backend;


/**
 * interface for Observer
 */
public interface Subject {
   /**
    * attach an observer
    * @param o
    */
    public void attach(Observer o);

    /**
     * detach an observer
     * @param o
     */
    public void detach(Observer o);

    /**
     * notify all the observers
     */
    public void Notify();
    
}
