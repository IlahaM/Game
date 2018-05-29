import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.graphics.GRect;

public class GoalKeeper extends GCompound {
	// constants
	private static final double EYE_WIDTH = 0.15;
	private static final double EYE_HEIGHT = 0.15;
	private static final double NOSE_WIDTH = 0.15;
	private static final double NOSE_HEIGHT = 0.10;
	private static final double MOUTH_WIDTH = 0.50;
	private static final double MOUTH_HEIGHT = 0.03;
	private static final double height = 40;
	private static final double width = 40;

	// instance variables
	private GOval head;
	private GOval leftEye, rightEye;
	private GPolygon nose;
	private GRect mouth;

	public GoalKeeper() {

		addFace();
		addBody();
	}

	private void addFace() {

		head = new GOval(width, height);
		head.setFilled(true);
		head.setFillColor(Color.YELLOW);
		leftEye = new GOval(EYE_WIDTH * width, EYE_HEIGHT * height);
		leftEye.setFilled(true);
		rightEye = new GOval(EYE_WIDTH * width, EYE_HEIGHT * height);
		rightEye.setFilled(true);
		rightEye.setFillColor(Color.BLACK);
		nose = createNosePolygon(NOSE_WIDTH * width, NOSE_HEIGHT * height);
		nose.setFilled(true);
		mouth = new GRect(MOUTH_WIDTH * width, MOUTH_HEIGHT * height);
		mouth.setFilled(true);
		mouth.setFillColor(Color.WHITE);
		add(head, 0, 0);
		add(leftEye, 0.25 * width - EYE_WIDTH * width / 2, 0.25 * height - EYE_HEIGHT * height / 2);
		add(rightEye, 0.75 * width - EYE_WIDTH * width / 2, 0.25 * height - EYE_HEIGHT * height / 2);
		add(nose, 0.50 * width, 0.50 * height);
		add(mouth, 0.50 * width - MOUTH_WIDTH * width / 2, 0.75 * height - MOUTH_HEIGHT * height / 2);
	}

	// creates a polygon as a nose of the goalkeeper
	private GPolygon createNosePolygon(double width, double height) {
		GPolygon poly = new GPolygon();
		poly.addVertex(0, -height / 2);
		poly.addVertex(width / 2, height / 2);
		poly.addVertex(-width / 2, height / 2);
		return poly;
	}

	private void addBody() {
		// body of the goalkeeper
		GRect rect = new GRect(7, 40, 26, 50);
		rect.setFilled(true);
		rect.setFillColor(Color.GRAY);
		add(rect);
		// adds the name of the goalkeeper
		GLabel label = new GLabel("BUFFON");
		add(label, 9, 60);
		label.setFont("NewTimesRoman-6");
		// adds one of goalkeeper's feet
		GRect foot1 = new GRect(13, 90, 5, 60);
		foot1.setFilled(true);
		foot1.setFillColor(Color.BLACK);
		add(foot1);
		// adds the other foot
		GRect foot2 = new GRect(22, 90, 5, 60);
		foot2.setFilled(true);
		foot2.setFillColor(Color.BLACK);
		add(foot2);
		// adds arms of the goalkeeper
		add(new GLine(7, 63, -13, 43));// middle left
		add(new GLine(7, 64, -13, 44));
		add(new GLine(7, 62, -13, 42));
		add(new GLine(33, 63, 53, 43)); // middle right
		add(new GLine(34, 63, 53, 44));
		add(new GLine(32, 63, 53, 42));
	}

}
