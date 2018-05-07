package es.granainopowa.guerras_de_petri.utils.math;

import java.awt.geom.Point2D;

/**
 * @author Rafael Jiménez (7 may. 2018)
 *
 */
public class MathUtils {

	public static double VectorModulus(Point2D vector) {
		double x2 = Math.pow(vector.getX(), 2);
		double y2 = Math.pow(vector.getY(), 2);
		return Math.sqrt(x2 + y2);
	}

}
