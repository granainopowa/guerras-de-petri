package es.granainopowa.neural_network.neuron;

import java.util.List;

import es.granainopowa.neural_network.connector.HiddenConnector;

/**
 * @author Rafael Jiménez
 * 21 abr. 2018
 *
 */
public class HiddenNeuron extends Neuron {

	private List<HiddenConnector> inputs;

	public HiddenNeuron(List<HiddenConnector> inputs) {
		this.inputs = inputs;
	}

	@Override
	protected double getInput() {
		double result = 0;

		for (HiddenConnector hiddenConnector : inputs) {
			result += hiddenConnector.getValue();
		}

		return result;
	}

}
