/**
 * 
 */
package es.granainopowa.neural_network.base;

/**
 * @author Rafael Jiménez
 * 19 abr. 2018
 *
 */
public class Layer {

	private Neuron[] neurons;

	public Layer(int neuronCount) {
		this.neurons = new Neuron[neuronCount];
	}
}
