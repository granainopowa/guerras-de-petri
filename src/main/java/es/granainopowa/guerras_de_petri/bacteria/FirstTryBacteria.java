package es.granainopowa.guerras_de_petri.bacteria;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.appendix.input.InputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.input.RelativePositionInputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.output.OutputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.output.PawOutputAppendix;

/**
 * This is a first try for testing purposes
 *
 * @author Rafael Jiménez (30 abr. 2018)
 */
public class FirstTryBacteria extends Bacteria {

	RelativePositionInputAppendix input;
	
	public FirstTryBacteria() {
		init(createInputAppendices(), Arrays.asList(3, 4, 3), createOutputAppendices());
	}

	public void setDestination(Point2D destination) {
		input.setDestination(destination);
	}
	
	private List<InputAppendix> createInputAppendices() {
		input = new RelativePositionInputAppendix(this);
		return Arrays.asList(input);
	}

	private List<OutputAppendix> createOutputAppendices() {
		return Arrays.asList(
				new PawOutputAppendix(this, 45),
				new PawOutputAppendix(this, -45),
				new PawOutputAppendix(this, 135),
				new PawOutputAppendix(this, -135));
	}
}
