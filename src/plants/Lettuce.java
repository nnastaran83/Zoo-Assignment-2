package plants;

import graphics.ZooPanel;
import utilities.MessageUtility;

import java.util.logging.Level;

/**
 * @author baroh
 *
 */
public class Lettuce extends Plant {
	public Lettuce(ZooPanel pan){
		super(pan);
	}
	public Lettuce() {
		MessageUtility.logConstractor("Lettuce", "Lettuce");
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
