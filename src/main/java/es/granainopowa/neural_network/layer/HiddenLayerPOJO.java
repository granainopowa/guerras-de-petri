package es.granainopowa.neural_network.layer;

import java.util.List;

import es.granainopowa.neural_network.neuron.HiddenNeuronPOJO;

/**
 * @author Rafael Jiménez
 * 24 abr. 2018
 *
 */
public class HiddenLayerPOJO {

	private List<HiddenNeuronPOJO> neuronPOJOs;

	public List<HiddenNeuronPOJO> getNeuronPOJOs() {
		return neuronPOJOs;
	}

	public void setNeurons(List<HiddenNeuronPOJO> neuronPOJOs) {
		this.neuronPOJOs = neuronPOJOs;
	}

}
