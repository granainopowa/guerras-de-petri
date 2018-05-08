package es.granainopowa.guerras_de_petri.utils.math;

import java.awt.Color;
import java.util.Random;

/**
 * @author Rafael Jiménez (8 may. 2018)
 */
public class RandomUtils {
	private static final Random RANDOM = new Random();

	/**
	 * @return double in the rank [0, 1)
	 */
	public static double randomDouble() {
		return RANDOM.nextDouble();
	}

	/**
	 * @return double in the rank [min, max)
	 */
	public static double randomDouble(double min, double max) {
		return min + (randomDouble() * (max - min));
	}

	/**
	 * @return int in the rank [min, max)
	 */
	public static int randomInt(int min, int max) {
		return min + RANDOM.nextInt(max - min);
	}

	public static Color randomColor() {
		return new Color(
				RANDOM.nextInt(256),
				RANDOM.nextInt(256),
				RANDOM.nextInt(256));
	}
}
