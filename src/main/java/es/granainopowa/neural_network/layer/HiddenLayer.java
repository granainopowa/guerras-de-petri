package es.granainopowa.neural_network.layer;

import es.granainopowa.neural_network.base.neuron.HiddenNeuron;
import es.granainopowa.neural_network.base.neuron.Neuron;
import es.granainopowa.neural_network.connector.HiddenConnector;

/**
 * @author Rafael Jiménez
 * 21 abr. 2018
 *
 */
public class HiddenLayer extends Layer {

	public HiddenLayer(int neuronCount, Layer previousLayer) {
		super(getNeurons(neuronCount, previousLayer));
	}

	private static Neuron[] getNeurons(int neuronCount, Layer previousLayer) {
		Neuron[] neurons = new Neuron[neuronCount];

		for (int i = 0; i < neuronCount; i++) {
			Neuron[] previousNeurons = previousLayer.neurons;
			HiddenConnector[] hiddenConnectors = new HiddenConnector[previousNeurons.length];
			for (int j = 0; j < previousNeurons.length; j++) {
				Neuron previousNeuron = previousNeurons[j];
				hiddenConnectors[j] = new HiddenConnector(previousNeuron);
			}
			neurons[i] = new HiddenNeuron(hiddenConnectors);
		}

		return neurons;
	}
}
