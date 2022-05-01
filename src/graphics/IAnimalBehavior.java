package graphics;

import java.beans.PropertyChangeSupport;
import java.util.Properties;

/**
 * The interface Animal behavior.
 * @author Nastaran Motiee - 329022727
 * @campus Ashdod
 * @version 1.0 April 20,22
 */
public interface IAnimalBehavior {


    public String getAnimalName();
    public int getSize();
    public void eatInc();
    public int getEatCount();
    public boolean getChanges ();
    public void setChanges (boolean state);
}
