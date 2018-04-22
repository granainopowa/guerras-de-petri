package es.granainopowa.neural_network.connector;

import es.granainopowa.neural_network.neuron.Neuron;

/**
 * Connection between neurons
 *
 * @author Rafael Jiménez
 * 18 abr. 2018
 *
 */
public class HiddenConnector extends Connector {
	private Neuron inputNeuron;

	/**
	 * Sets a random weight in the interval (-1, 1)
	 */
	public HiddenConnector(Neuron inputNeuron) {
		super();
		this.inputNeuron = inputNeuron;
	}

	@Override
	protected double getConnectorInput() {
		return inputNeuron.getOutput();
	}

}
