package es.granainopowa.guerras_de_petri.bacteria;

import java.util.List;

import es.granainopowa.neural_network.Network;

/**
 * @author Rafael Jiménez
 * 29 abr. 2018
 *
 */
public abstract class Bacteria {

	private int x;
	private int y;
	private Network network;

	protected Bacteria(int inputCount, List<Integer> hiddenLayersNeuronCount, int outputCount) {
		this.network = new Network(inputCount, hiddenLayersNeuronCount, outputCount);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	protected List<Double> getOutputs(List<Double> inputs) {
		network.computeNetwork(inputs);
		return network.getNetworkOutputs();
	}
}
