package es.granainopowa.neural_network;

import java.util.List;

import es.granainopowa.neural_network.layer.HiddenLayerPOJO;

/**
 * @author Rafael Jiménez (24 abr. 2018)
 */
public class NetworkPOJO {

	private int inputCount;
	private List<HiddenLayerPOJO> hiddenLayerPOJOs;

	public int getInputCount() {
		return inputCount;
	}

	public void setInputCount(int inputCount) {
		this.inputCount = inputCount;
	}

	public List<HiddenLayerPOJO> getHiddenLayerPOJOs() {
		return hiddenLayerPOJOs;
	}

	public void setHiddenLayerPOJOs(List<HiddenLayerPOJO> hiddenLayerPOJOs) {
		this.hiddenLayerPOJOs = hiddenLayerPOJOs;
	}

}
