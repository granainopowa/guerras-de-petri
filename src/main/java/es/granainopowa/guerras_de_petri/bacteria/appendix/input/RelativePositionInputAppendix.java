package es.granainopowa.guerras_de_petri.bacteria.appendix.input;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.Bacteria;
import es.granainopowa.guerras_de_petri.utils.ActivationFunction;

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
		Ellipse2D e = new Ellipse2D.Double(destination.getX() - 5, destination.getY() - 5, 10, 10);
		g2d.draw(petriDishCenterTransform.createTransformedShape(e));
	}

	@Override
	public List<Double> getInputs() {
		Point2D bacteriaPosition = getHost().getPosition();
		double InputX = destination.getX() - bacteriaPosition.getX();
		double InputY = destination.getY() - bacteriaPosition.getY();

		return Arrays.asList(
				ActivationFunction.sigmoid(InputX),
				ActivationFunction.sigmoid(InputY));
	}

	public void setDestination(Point2D destination) {
		this.destination = destination;
	}

}
