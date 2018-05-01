package es.granainopowa.guerras_de_petri.bacteria;

import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.appendix.input.InputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.output.OutputAppendix;

/**
 * @author Rafael Jiménez (30 abr. 2018)
 *
 */
public class FirstTryBacteria extends Bacteria {

	/**
	 * @param inputAppendixes
	 * @param hiddenLayersNeuronCount
	 * @param outputAppendixes
	 */
	protected FirstTryBacteria(List<InputAppendix> inputAppendixes, List<Integer> hiddenLayersNeuronCount,
			List<OutputAppendix> outputAppendixes) {
		super(inputAppendixes, hiddenLayersNeuronCount, outputAppendixes);
		// TODO Auto-generated constructor stub
	}

}
