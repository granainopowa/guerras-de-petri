package es.granainopowa.guerras_de_petri.bacteria;

import java.util.ArrayList;
import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.appendix.input.InputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.output.OutputAppendix;
import es.granainopowa.neural_network.Network;

/**
 * @author Rafael Jiménez (29 abr. 2018)
 */
public abstract class Bacteria {

	private int x;
	private int y;
	private int angle;
	private Network network;
	private List<InputAppendix> inputAppendices;
	private List<OutputAppendix> outputAppendices;

	protected Bacteria(
			List<InputAppendix> inputAppendices,
			List<Integer> hiddenLayersNeuronCount,
			List<OutputAppendix> outputAppendices) {
		this.network = new Network(inputAppendices.size(), hiddenLayersNeuronCount, outputAppendices.size());
		this.inputAppendices = inputAppendices;
		this.outputAppendices = outputAppendices;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void moveToPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public void step() {
		List<Double> inputs = new ArrayList<>();
		for (InputAppendix inputAppendix : inputAppendices) {
			inputs.add(inputAppendix.getInput(this));
		}
		network.computeNetwork(inputs);

		List<Double> networkOutputs = network.getNetworkOutputs();
		for (int i = 0; i < networkOutputs.size(); i++) {
			outputAppendices.get(i).react(this, networkOutputs.get(i));
		}
	}
}
