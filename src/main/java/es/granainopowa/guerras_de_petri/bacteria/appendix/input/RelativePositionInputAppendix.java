package es.granainopowa.guerras_de_petri.bacteria.appendix.input;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.Bacteria;
import es.granainopowa.guerras_de_petri.utils.DrawUtils;
import es.granainopowa.guerras_de_petri.utils.math.MathUtils;

/**
 * @author Rafael Jiménez (3 may. 2018)
 */
public class RelativePositionInputAppendix extends InputAppendix {
	private static final int INPUT_COUNT = 2;

	private Point2D destination = new Point2D.Double(0, 0);

	public RelativePositionInputAppendix(Bacteria host) {
		super(host, INPUT_COUNT);
	}

	@Override
	public void draw(Graphics2D g2d, AffineTransform petriDishCenterTransform, AffineTransform bacteriaTransform) {
		// draw an X in the destination point coordinates (relative to the center of the PetriDish)
		g2d.draw(petriDishCenterTransform.createTransformedShape(
				new Line2D.Double(
						destination.getX() - 10, destination.getY() - 10,
						destination.getX() + 10, destination.getY() + 10)));
		g2d.draw(petriDishCenterTransform.createTransformedShape(
				new Line2D.Double(
						destination.getX() + 10, destination.getY() - 10,
						destination.getX() - 10, destination.getY() + 10)));

		// draw an arrow representing bacteria Inputs
		AffineTransform inputsTransform = new AffineTransform(bacteriaTransform);
		List<Double> inputs = getInputs();
		Double inputX = inputs.get(0);
		Double inputY = inputs.get(1);

		int distanceFromCenter = 50;
		int arrowLength = 30;
		inputsTransform.translate(inputX * distanceFromCenter, inputY * distanceFromCenter);
		DrawUtils.drawArrow(g2d, inputsTransform,
				new Point2D.Double(0, 0),
				new Point2D.Double(inputX * arrowLength, inputY * arrowLength));
	}

	@Override
	public List<Double> getInputs() {
		Bacteria host = getHost();
		AffineTransform bacteriaTransform = host.getBacteriaTransform(new AffineTransform());
		Point2D transformedDestination;
		try {
			transformedDestination = bacteriaTransform.inverseTransform(destination, null);
		} catch (NoninvertibleTransformException e) {
			throw new RuntimeException(e);
		}

		double vectorModulus = MathUtils.VectorModulus(transformedDestination);

		return Arrays.asList(
				transformedDestination.getX() / vectorModulus,
				transformedDestination.getY() / vectorModulus);
	}

	public void setDestination(Point2D destination) {
		this.destination = destination;
	}

}
