package es.granainopowa.guerras_de_petri.utils;

import java.util.ArrayList;
import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.appendix.BacteriaAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.input.InputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.output.OutputAppendix;

/**
 * @author Rafael Jiménez (6 may. 2018)
 *
 */
public class AppendixUtils {

	public static int getNeuronConnectionsCount(List<? extends BacteriaAppendix> appendices) {
		int neuronCount = 0;
		for (BacteriaAppendix appendix : appendices) {
			neuronCount += appendix.getNeuronConnectionsCount();
		}

		return neuronCount;
	}

	/**
	 * Gets all inputs from all InputAppendices in the list
	 */
	public static List<Double> getAllInputs(List<InputAppendix> inputAppendices) {
		List<Double> inputs = new ArrayList<>();
		for (InputAppendix inputAppendix : inputAppendices) {
			inputs.addAll(inputAppendix.getInputs());
		}

		return inputs;
	}

	/**
	 * Goes over every OutputAppendix in the list and uses the corresponding
	 * networkOutputs to make it react
	 */
	public static void makeAllReactToNetworkOutputs(List<OutputAppendix> outputAppendices,
			List<Double> allNnetworkOutputs) {
		int i = 0;
		for (OutputAppendix outputAppendix : outputAppendices) {
			int neededOutputs = outputAppendix.getNeuronConnectionsCount();
			List<Double> appendixCorrespondingOutputs = allNnetworkOutputs.subList(i, i + neededOutputs);
			i += neededOutputs;
			outputAppendix.reactToNetworkOutputs(appendixCorrespondingOutputs);
		}
	}
}
