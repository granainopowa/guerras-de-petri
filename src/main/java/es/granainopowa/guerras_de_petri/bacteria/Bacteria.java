package es.granainopowa.guerras_de_petri.bacteria;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.appendix.input.InputAppendix;
import es.granainopowa.guerras_de_petri.bacteria.appendix.output.OutputAppendix;
import es.granainopowa.guerras_de_petri.utils.AppendixUtils;
import es.granainopowa.guerras_de_petri.utils.DrawUtils;
import es.granainopowa.neural_network.Network;

/**
 * <h1>Base for Bacteria</h1>
 *
 * @author Rafael Jiménez (29 abr. 2018)
 */
public abstract class Bacteria {
	private static final int DEFAULT_RADIUS = 25;
	private static final BasicStroke DEFAULT_STROKE = new BasicStroke(3);

	// Network properties
	private Network network;
	private List<InputAppendix> inputAppendices;
	private List<OutputAppendix> outputAppendices;

	// Physical properties
	private Point2D position = new Point2D.Double(0, 0);
	private int angle;
	private Color color;
	private int radius = DEFAULT_RADIUS;

	/**
	 * Initialization of the Bacteria. This should be called in the constructor
	 */
	protected void init(List<InputAppendix> inputAppendices,
			List<Integer> hiddenLayersNeuronCount,
			List<OutputAppendix> outputAppendices) {
		this.inputAppendices = inputAppendices;
		this.outputAppendices = outputAppendices;

		int inputCount = AppendixUtils.getNeuronConnectionsCount(inputAppendices);
		int outputCount = AppendixUtils.getNeuronConnectionsCount(outputAppendices);

		this.network = new Network(inputCount, hiddenLayersNeuronCount, outputCount);
	}

	/**
	 * Obtains the inputs of the Bacteria, computes them and performs a reaction for
	 * every OutputAppendix
	 */
	public void step() {
		List<Double> inputs = AppendixUtils.getAllInputs(inputAppendices);
		network.computeNetwork(inputs);

		List<Double> allNetworkOutputs = network.getNetworkOutputs();
		AppendixUtils.makeAllReactToNetworkOutputs(outputAppendices, allNetworkOutputs);
	}

	/**
	 * Draws the Bacteria using the provided Grapics.
	 *
	 * @param g2d
	 *            Graphics used to draw the Bacteria
	 * @param petriDishCenterTransform
	 *            transformation required for the Bacteria to move its origin to be
	 *            relative to the PetriDish
	 */
	public final void draw(Graphics2D g2d, AffineTransform petriDishCenterTransform) {
		AffineTransform bacteriaTransform = getBacteriaTransform(petriDishCenterTransform);

		if (color != null) {
			g2d.setColor(color);
		}
		g2d.setStroke(DEFAULT_STROKE);

		// Draw the body of the Bacteria
		Ellipse2D e = new Ellipse2D.Double(-radius, -radius, 2 * radius, 2 * radius);
		g2d.draw(bacteriaTransform.createTransformedShape(e));

		// Draw the Appendices
		for (InputAppendix inputAppendix : inputAppendices) {
			inputAppendix.draw(g2d, petriDishCenterTransform, bacteriaTransform);
		}
		for (OutputAppendix outputAppendix : outputAppendices) {
			outputAppendix.draw(g2d, petriDishCenterTransform, bacteriaTransform);
		}

		// Draw an arrow that points to the Bacteria angle direction
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.black);
		Point2D center = new Point2D.Double(0, 0);
		Point2D arrowHead = new Point2D.Double(0, -radius);
		DrawUtils.drawArrow(g2d, bacteriaTransform, center, arrowHead);
	}

	/* GETTERS */

	/**
	 * Gets an AffineTransform to move the origin of the Bacteria for drawing into
	 * the PetriDish
	 *
	 * @param affineTransform
	 *            PetriDish based AffineTransform
	 */
	public AffineTransform getBacteriaTransform(AffineTransform affineTransform) {
		AffineTransform bacteriaTransform = new AffineTransform(affineTransform);
		bacteriaTransform.translate(position.getX(), position.getY());
		bacteriaTransform.rotate(getAngleInRadians());

		return bacteriaTransform;
	}

	public Point2D getPosition() {
		return position;
	}

	/**
	 * @return the angle (in degrees) of the bacteria
	 */
	public int getAngle() {
		return angle;
	}

	/**
	 * @return the angle (radians) of the bacteria
	 */
	public double getAngleInRadians() {
		return Math.toRadians(angle);
	}

	public Color getColor() {
		return color;
	}

	public int getRadius() {
		return radius;
	}

	/* SETTERS */

	public void setPosition(Point2D position) {
		this.position = position;
	}

	/**
	 * @param angle
	 *            in degrees
	 */
	public void setAngle(int angle) {
		this.angle = angle;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
