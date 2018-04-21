package es.granainopowa.neural_network.base.neuron;

/**
 * @author Rafael Jiménez
 * 21 abr. 2018
 *
 */
public class InputNeuron extends Neuron {

	private double input;

	public void setInput(double input) {
		this.input = input;
	}

	@Override
	protected double getInput() {
		return this.input;
	}
}
