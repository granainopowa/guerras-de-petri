package es.granainopowa.guerras_de_petri.utils.math;

import java.awt.geom.Point2D;

/**
 * @author Rafael Jiménez (7 may. 2018)
 *
 */
public class MathUtils {
	public static double VectorModulus(Point2D vector) {
		return Math.hypot(vector.getX(), vector.getY());
	}

}
