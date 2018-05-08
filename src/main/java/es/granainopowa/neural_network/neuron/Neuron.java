package es.granainopowa.neural_network.neuron;

import es.granainopowa.guerras_de_petri.utils.ActivationFunction;

/**
 * Neuron
 *
 * @author Rafael Jiménez (18 abr. 2018)
 */
public abstract class Neuron {

	private double output;

	protected abstract double getInput();

	public double getOutput() {
		return output;
	}

	public final void computeOutput() {
		//this.output = ActivationFunction.sigmoid(getInput());
		this.output = ActivationFunction.tanHiperbolic(getInput());
	}

}
