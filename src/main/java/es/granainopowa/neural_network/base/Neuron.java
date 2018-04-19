package es.granainopowa.neural_network.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Neuron
 *
 * @author Rafael Jiménez
 * 18 abr. 2018
 *
 */
public class Neuron {

	private List<Connector> inputs = new ArrayList<>();
	private List<Connector> outputs = new ArrayList<>();

	public void addInputConnection(Connector connection) {
		inputs.add(connection);
	}

	public void addOutputConnection(Connector connection) {
		outputs.add(connection);
	}

	public double getOutput() {
		double result = 0;

		for (Connector connector : inputs) {
			// calculate output
			//result += connector.getValue();
		}

		return sigmoid(result);
	}

	private double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	}
}
