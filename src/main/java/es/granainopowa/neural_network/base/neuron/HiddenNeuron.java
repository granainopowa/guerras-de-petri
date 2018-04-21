package es.granainopowa.neural_network.base.neuron;

import es.granainopowa.neural_network.connector.HiddenConnector;

/**
 * @author Rafael Jiménez
 * 21 abr. 2018
 *
 */
public class HiddenNeuron extends Neuron {

	private HiddenConnector[] inputs;

	public HiddenNeuron(HiddenConnector[] inputs) {
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
