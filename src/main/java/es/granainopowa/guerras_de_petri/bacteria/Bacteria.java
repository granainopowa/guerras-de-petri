package es.granainopowa.guerras_de_petri.bacteria;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.appendix.input.InputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.output.OutputAppendix;
import es.granainopowa.neural_network.Network;

/**
 * @author Rafael Jiménez (29 abr. 2018)
 */
public abstract class Bacteria {

	private int x;
	private int y;
	private int angle;
	private Network network;
	private List<InputAppendix> inputAppendixes;
	private List<OutputAppendix> outputAppendixes;

	protected Bacteria(
			List<Class<? extends InputAppendix>> inputAppendixClasses,
			List<Integer> hiddenLayersNeuronCount,
			List<Class<? extends OutputAppendix>> outputAppendixClasses) {
		this.network = new Network(inputAppendixes.size(), hiddenLayersNeuronCount, outputAppendixes.size());
		this.inputAppendixes = new ArrayList<>();

		for (Class<? extends InputAppendix> clazz : inputAppendixClasses) {
			try {
				Constructor<? extends InputAppendix> declaredConstructor = clazz.getDeclaredConstructor(Bacteria.class);
				this.inputAppendixes.add(declaredConstructor.newInstance(this));
			} catch (NoSuchMethodException | SecurityException e) {
				throw new IllegalStateException("Could not find a valid constructor for " + clazz.getName(), e);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				throw new IllegalStateException("Could not instantiate the " + clazz.getName(), e);
			}
		}
		this.outputAppendixes = new ArrayList<>();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void moveToPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public void step() {
		List<Double> inputs = new ArrayList<>();
		for (InputAppendix inputAppendix : inputAppendixes) {
			inputs.add(inputAppendix.getInput());
		}
		network.computeNetwork(inputs);
	}
}
