package es.granainopowa.guerras_de_petri.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import es.granainopowa.guerras_de_petri.bacteria.Bacteria;
import es.granainopowa.guerras_de_petri.bacteria.FirstTryBacteria;
import es.granainopowa.guerras_de_petri.utils.math.RandomUtils;

/**
 * @author Rafael Jiménez (29 abr. 2018)
 */
public class PetriDish extends JPanel implements ActionListener {
	private static final long serialVersionUID = -6727865483084967707L;
	private static final int DISH_RADIUS = 300;
	private static final int DISH_DIAMETER = 2 * DISH_RADIUS;
	private final int DELAY = 25;

	private static final int EVALUATION_STEPS = 10;
	private static final int GENERATION_STEPS = 400;

	private boolean finished = false;
	private int step = 0;
	private int generation = 0;
	private int generationSize = 2;

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

	List<Bacteria> bacterias = new ArrayList<>();
	private Timer timer;

	public PetriDish() {
		timer = new Timer(DELAY, this);
		timer.start();

		for (int i = 0; i < generationSize; i++) {
			createBacteria();
		}
	}

	private void nextDestinationPoint() {
		Point2D destinationPoint = destinationPoints.get(generation);
		for (Bacteria bacteria : bacterias) {
			FirstTryBacteria firstTryBacteria = (FirstTryBacteria) bacteria;
			firstTryBacteria.setDestination(destinationPoint);
			Point2D.Double center = new Point2D.Double(0, 0);
			firstTryBacteria.setPosition(center);
		}
	}

	private void createBacteria() {
		Point2D destinationPoint = destinationPoints.get(generation);

		FirstTryBacteria bacteria = new FirstTryBacteria();
		bacteria.setPosition(new Point2D.Double(0, 0));
		bacteria.setDestination(destinationPoint);
		//bacteria.setAngle(RandomUtils.randomInt(0, 360));
		bacteria.setColor(RandomUtils.randomColor());
		this.bacterias.add(bacteria);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		Dimension windowSize = getSize();
		AffineTransform petriDishCenterTransform = AffineTransform.getTranslateInstance(
				windowSize.getWidth() / 2,
				windowSize.getHeight() / 2);

		drawPetriDish(g2d, petriDishCenterTransform);
		drawBacterias(g2d, petriDishCenterTransform);
	}

	private void drawBacterias(Graphics2D g2d, AffineTransform petriDishCenterTransform) {
		boolean evaluate = step % EVALUATION_STEPS == 0;
		step++;

		for (Bacteria bacteria : bacterias) {
			if (!finished) {
				bacteria.step();
			}
			bacteria.draw(g2d, petriDishCenterTransform);

			double score = bacteria.getScore();
			Point2D position = bacteria.getPosition();
			if (evaluate && !finished) {
				double distance = position.distance(destinationPoints.get(generation));
				bacteria.setScore(score - distance);
			}
			Point2D scoreLocation = petriDishCenterTransform
					.transform(new Point2D.Double(
							position.getX() - 40,
							position.getY() - 40), null);

			// Draw bacteria score
			g2d.drawString(Double.toString(score), (int) scoreLocation.getX(), (int) scoreLocation.getY());
		}
		// Draw timer
		g2d.drawString(Integer.toString(step), 10, 10);
		if (step > GENERATION_STEPS) {
			generation++;
			step = 0;
			if (generation < destinationPoints.size()) {
				nextDestinationPoint();
			} else {
				finished = true;
				presentResults();
			}
		}
	}

	private void presentResults() {
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

	private void drawPetriDish(Graphics2D g2d, AffineTransform affineTransform) {
		Ellipse2D e = new Ellipse2D.Double(-DISH_RADIUS, -DISH_RADIUS, DISH_DIAMETER, DISH_DIAMETER);
		Ellipse2D eC = new Ellipse2D.Double(-10, -10, 20, 20);
		g2d.setStroke(new BasicStroke(1));
		g2d.setColor(Color.gray);
		g2d.draw(affineTransform.createTransformedShape(e));
		g2d.draw(affineTransform.createTransformedShape(eC));
	}

}
