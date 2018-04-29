package es.granainopowa.neural_network.neuron;

import java.util.ArrayList;
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
		if (inputs == null || inputs.isEmpty()) {
			throw new IllegalArgumentException("It is not possible to create a HiddenNeuron without input connectors");
		}
		this.inputs = inputs;
	}

	public HiddenNeuronPOJO getPOJO() {
		HiddenNeuronPOJO hiddenNeuronPOJO = new HiddenNeuronPOJO();
		List<Double> inputWeights = new ArrayList<>();

		for (Connector connector : this.inputs) {
			inputWeights.add(connector.getWeight());
		}

		hiddenNeuronPOJO.setInputWeights(inputWeights);

		return hiddenNeuronPOJO;
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
