package es.granainopowa.guerras_de_petri.utils;

/**
 * @author Rafael Jiménez (6 may. 2018)
 *
 */
public class ActivationFunction {

	/**
	 * @return a double in the rank [-1, 1)
	 */
	public static double sigmoid(double x) {
		return (2 / (1 + Math.exp(-x))) - 1;
	}

	/**
	 * @return a double in the rank [-1, 1)
	 */
	public static double tanHiperbolic(double x) {
		return Math.tanh(x);
	}

}
