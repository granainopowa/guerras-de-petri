package es.granainopowa.guerras_de_petri.utils;

/**
 * @author Rafael Jiménez (6 may. 2018)
 *
 */
public class ActivationFunction {

	/**
	 * @param x
	 * @return a double in the rank [0, 1]
	 */
	public static double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	}

}
