package es.granainopowa.guerras_de_petri.gui;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rafael Jiménez (17 may. 2018)
 */
public class Training {

	private List<Point2D> destinationPoints = Arrays.asList(
			new Point2D.Double(-200, -200),
			new Point2D.Double(-200, -100),
			new Point2D.Double(-200, 0),
			new Point2D.Double(-200, 100),
			new Point2D.Double(-200, 200),
			new Point2D.Double(-100, -200),
			new Point2D.Double(0, -200),
			new Point2D.Double(100, -200),
			new Point2D.Double(200, -200),
			new Point2D.Double(200, -100),
			new Point2D.Double(200, 0),
			new Point2D.Double(200, 100),
			new Point2D.Double(200, 200),
			new Point2D.Double(-100, 200),
			new Point2D.Double(0, 200),
			new Point2D.Double(100, 200)
			);

	private int currentDestination = 0;
	private Generation generation;

	public Training(int generationSize) {
		this.generation = new Generation(generationSize, destinationPoints.get(0));
	}

	void drawBacterias(Graphics2D g2d, AffineTransform petriDishCenterTransform) {
		generation.drawBacterias(g2d, petriDishCenterTransform);
		if (generation.hasFinishedTraining()) {
			currentDestination++;
			if (currentDestination < destinationPoints.size()) {
				Point2D nextDestinationPoint = destinationPoints.get(currentDestination);
				generation.resetGeneration(nextDestinationPoint);
			} else {
				generation.presentResults();
			}
		}
	}
}
