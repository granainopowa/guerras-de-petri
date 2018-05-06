package es.granainopowa.guerras_de_petri.utils;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * @author Rafael Jiménez (3 may. 2018)
 *
 */
public class DrawUtils {

	public static void drawArrow(Graphics2D g2d, AffineTransform bacteriaTransform, Point2D origin, Point2D head) {
		Line2D line = new Line2D.Double(origin, head);
		Line2D arrowLine = new Line2D.Double(head, new Point2D.Double(head.getX() + 5, head.getY() + 10));
		Line2D arrowLine2 = new Line2D.Double(head, new Point2D.Double(head.getX() - 5, head.getY() + 10));
		g2d.draw(bacteriaTransform.createTransformedShape(line));
		g2d.draw(bacteriaTransform.createTransformedShape(arrowLine));
		g2d.draw(bacteriaTransform.createTransformedShape(arrowLine2));
	}

}
