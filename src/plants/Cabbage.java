package plants;

import graphics.ZooPanel;
import utilities.MessageUtility;

/**
 * @author baroh
 *
 */
public class Cabbage extends Plant {

	public Cabbage(ZooPanel pan){
		super(pan);
	}
	public Cabbage() {
		MessageUtility.logConstractor("Cabbage", "Cabbage");
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
