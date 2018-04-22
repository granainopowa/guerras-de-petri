package es.granainopowa.neural_network.neuron;

import java.util.List;

import es.granainopowa.neural_network.connector.Connector;

/**
 * @author Rafael Jiménez
 * 21 abr. 2018
 *
 */
public class HiddenNeuron extends Neuron {

	private List<Connector> inputs;

	public HiddenNeuron(List<Connector> inputs) {
		this.inputs = inputs;
	}

	@Override
	protected double getInput() {
		double result = 0;

		for (Connector hiddenConnector : inputs) {
			result += hiddenConnector.getValue();
		}

		return result;
	}

}
