package es.granainopowa.guerras_de_petri.gui;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import es.granainopowa.guerras_de_petri.bacteria.Bacteria;
import es.granainopowa.guerras_de_petri.bacteria.FirstTryBacteria;
import es.granainopowa.guerras_de_petri.utils.math.RandomUtils;

/**
 * @author Rafael Jiménez (16 may. 2018)
 */
public class Generation {

	private static final int EVALUATION_STEPS = 10;
	private static final int GENERATION_STEPS = 400;

	private int step = 0;
	private Point2D destinationPoint;

	private List<Bacteria> bacterias = new ArrayList<>();

	public Generation(int generationSize, Point2D destinationPoint) {
		this.destinationPoint = destinationPoint;
		for (int i = 0; i < generationSize; i++) {
			createBacteria(destinationPoint);
		}
	}

	public void resetGeneration(Point2D destinationPoint) {
		step = 0;
		this.destinationPoint = destinationPoint;
		bacterias.parallelStream().forEach(bacteria -> {
			FirstTryBacteria firstTryBacteria = (FirstTryBacteria) bacteria;
			firstTryBacteria.setDestination(destinationPoint);
			firstTryBacteria.setPosition(new Point2D.Double(0, 0));
		});
	}

	private void createBacteria(Point2D destinationPoint) {
		FirstTryBacteria bacteria = new FirstTryBacteria();
		bacteria.setPosition(new Point2D.Double(0, 0));
		bacteria.setDestination(destinationPoint);
		// bacteria.setAngle(RandomUtils.randomInt(0, 360));
		bacteria.setColor(RandomUtils.randomColor());
		this.bacterias.add(bacteria);
	}

	void drawBacterias(Graphics2D g2d, AffineTransform petriDishCenterTransform) {
		boolean hasFinishedTraining = hasFinishedTraining();

		for (Bacteria bacteria : bacterias) {
			double score = bacteria.getScore();
			if (!hasFinishedTraining) {
				bacteria.step();
				if (step != 0 && step % EVALUATION_STEPS == 0) {
					double distance = bacteria.getPosition().distance(destinationPoint);
					bacteria.setScore(score - distance);
				}
			}

			Point2D scoreLocation = petriDishCenterTransform
					.transform(new Point2D.Double(
							bacteria.getPosition().getX() - 40,
							bacteria.getPosition().getY() - 40), null);

			// Draw bacteria
			bacteria.draw(g2d, petriDishCenterTransform);
			// Draw bacteria score
			g2d.drawString(Double.toString(score), (int) scoreLocation.getX(), (int) scoreLocation.getY());
			// Draw timer
			g2d.drawString(Integer.toString(step), 10, 10);
		}
		step++;
	}

	public boolean hasFinishedTraining() {
		return step >= GENERATION_STEPS;
	}

	public void presentResults() {
		Comparator<Bacteria> scoreComparator = new Comparator<Bacteria>() {
			@Override
			public int compare(Bacteria o1, Bacteria o2) {
				double score1 = o1.getScore();
				double score2 = o2.getScore();
				if (score1 > score2) {
					return 1;
				}
				if (score1 < score2) {
					return -1;
				}
				return 0;
			}
		};

		bacterias.sort(scoreComparator);
		bacterias.get(0).setPosition(new Point2D.Double(-600, -300));
		bacterias.get(1).setPosition(new Point2D.Double(-400, -300));
		bacterias.get(2).setPosition(new Point2D.Double(-200, -300));
		bacterias.get(3).setPosition(new Point2D.Double(0, -300));
		bacterias.get(4).setPosition(new Point2D.Double(200, -300));
		bacterias.get(5).setPosition(new Point2D.Double(400, -300));
		bacterias.get(6).setPosition(new Point2D.Double(600, -300));

		int bacteriasCount = bacterias.size();
		bacterias.get(bacteriasCount - 1).setPosition(new Point2D.Double(600, 300));
		bacterias.get(bacteriasCount - 2).setPosition(new Point2D.Double(400, 300));
		bacterias.get(bacteriasCount - 3).setPosition(new Point2D.Double(200, 300));
		bacterias.get(bacteriasCount - 4).setPosition(new Point2D.Double(0, 300));
		bacterias.get(bacteriasCount - 5).setPosition(new Point2D.Double(-200, 300));
		bacterias.get(bacteriasCount - 6).setPosition(new Point2D.Double(-400, 300));
		bacterias.get(bacteriasCount - 7).setPosition(new Point2D.Double(-600, 300));

	}
}
