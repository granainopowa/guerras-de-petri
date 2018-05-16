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

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Rafael Jiménez (29 abr. 2018)
 */
public class PetriDish extends JPanel implements ActionListener {
	private static final long serialVersionUID = -6727865483084967707L;
	private static final int DISH_RADIUS = 300;
	private static final int DISH_DIAMETER = 2 * DISH_RADIUS;
	private final int DELAY = 25;

	private Timer timer;
	private Generation generation;

	public PetriDish() {
		timer = new Timer(DELAY, this);
		timer.start();

		generation = new Generation(10);
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
		generation.drawBacterias(g2d, petriDishCenterTransform);
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
