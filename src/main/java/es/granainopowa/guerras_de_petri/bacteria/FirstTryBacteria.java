package es.granainopowa.guerras_de_petri.bacteria;

import java.util.Arrays;
import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.appendix.input.InputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.input.RelativePositionInputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.output.OutputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.output.PawOutputAppendix;

/**
 * @author Rafael Jiménez (30 abr. 2018)
 *
 */
public class FirstTryBacteria extends Bacteria {

	public FirstTryBacteria() {
		super(createInputAppendices(),
				createHiddenLayersNeuronCount(),
				createOutputAppendices());
	}

	private static List<Integer> createHiddenLayersNeuronCount() {
		return Arrays.asList(3, 4, 3);
	}

	private static List<InputAppendix> createInputAppendices() {
		return Arrays.asList(new RelativePositionInputAppendix());
	}

	private static List<OutputAppendix> createOutputAppendices() {
		return Arrays.asList(
				new PawOutputAppendix(),
				new PawOutputAppendix(),
				new PawOutputAppendix(),
				new PawOutputAppendix());
	}
}
