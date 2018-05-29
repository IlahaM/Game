import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acmx.export.javax.swing.JButton;

public class TestProject extends GraphicsProgram implements ActionListener {

	private static final long serialVersionUID = 1L;

	// instance variables
	private GoalKeeper goalkeeper;
	private GOval ball;
	private GObject check;
	private boolean ballKicked = false;

	public void init() {

		changeBackground(); // method which changes the color of the canvas
		goalkeeper = new GoalKeeper(); // instance of Goalkeeper class
		add(goalkeeper, 184, 151);
		addPlayer(); // adds player that kicks the ball
		createDoor(); // creates a door
		ball = new GOval(680, 420, 20, 20); // creates a ball
		ball.setFilled(true);
		add(ball);
		JButton button = new JButton("Start"); // creates a Jbutton
		add(button, SOUTH);
		button.addActionListener(this);
		GButton gbutton = new GButton("Click", 60, 20); // creates a Gbutton
		gbutton.setBackgroundColor(Color.RED);
		add(gbutton, 400, 400);
		goalkeeper.sendToFront(); // makes goalkeeper the front most object
	}

	private void changeBackground() {
		double x = getWidth();
		double y = 2 * getHeight() / 3;
		// adds green part as ground
		GRect greenPart = new GRect(0, getHeight() / 3, x, y);
		greenPart.setFilled(true);
		greenPart.setFillColor(Color.GREEN);
		greenPart.setColor(Color.GREEN);
		add(greenPart);

		// adds gray part as sky
		double x2 = getWidth();
		double y2 = getHeight() / 3;
		GRect grayPart = new GRect(0, 0, x2, y2);
		grayPart.setFilled(true);
		grayPart.setFillColor(Color.lightGray);
		grayPart.setColor(Color.lightGray);
		add(grayPart);
	}

	public void run() {

		addKeyListeners();

		while (true) { // moves the ball
			pause(50);
			if (ballKicked) {
				moveBall();
				ballKicked = false;
			}
		}
	}

	// when "Start" button is clicked the ball starts moving
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			ballKicked = true;
		}
	}

	// the method that moves ball and checks for goal
	private void moveBall() {

		Random rgen = new Random();
		int x1 = 1 + rgen.nextInt(3); // random integer between [1-3]
		pause(2000); // after 2 s the button is clicked it begins

		switch (x1) {
		// if random integer is 1, ball moves to the right
		case 1:
			double deltaX = 1;
			double deltaY = (double) (420 - 217) / (680 - 374);
			while (ball.getX() != 374 && ball.getY() != 217) {
				ball.move(-deltaX, -deltaY);
				pause(3);
				if (ball != null) {
					// checks whether the object is goalkeeper or not
					check = getElementAt(ball.getX(), ball.getY());
					if (check == goalkeeper) {
						break;
					}
				}
			}
			// if goalkeeper catches the ball means you win
			if (check == goalkeeper) {
				GLabel label2 = new GLabel("You won");
				label2.setFont("Times-90");
				label2.setColor(Color.BLUE);
				add(label2, (getWidth() - label2.getWidth()) / 2, (getHeight() + label2.getAscent()) / 2);
				// if not means you lose
			} else {
				GLabel label2 = new GLabel("You lost");
				label2.setFont("Times-90");
				label2.setColor(Color.RED);
				add(label2, (getWidth() - label2.getWidth()) / 2, (getHeight() + label2.getAscent()) / 2);
			}
			break;

		// if random integer is 2, ball moves to the middle of the door
		case 2:
			double deltaX2 = 1;
			double deltaY2 = (double) (420 - 305) / (680 - 91);
			while (ball.getX() != 91 && ball.getY() != 305) {
				ball.move(-deltaX2, -deltaY2);
				pause(3);
				if (ball != null) {
					check = getElementAt(ball.getX(), ball.getY());
					if (check == goalkeeper) {
						break;
					}
				}
			}
			if (check == goalkeeper) {
				GLabel label2 = new GLabel("You won");
				label2.setFont("Times-90");
				label2.setColor(Color.BLUE);
				add(label2, (getWidth() - label2.getWidth()) / 2, (getHeight() + label2.getAscent()) / 2);
			} else {
				GLabel label2 = new GLabel("You lost");
				label2.setFont("Times-90");
				label2.setColor(Color.RED);
				add(label2, (getWidth() - label2.getWidth()) / 2, (getHeight() + label2.getAscent()) / 2);
			}
			break;

		// if random integer is 3, ball moves to the left
		case 3:
			double deltaX3 = 1;
			double deltaY3 = (double) (420 - 248) / (680 - 230);
			while (ball.getX() != 230 && ball.getY() != 248) {
				ball.move(-deltaX3, -deltaY3);
				pause(3);
				if (ball != null) {
					check = getElementAt(ball.getX(), ball.getY());
					if (check == goalkeeper) {
						break;
					}
				}
			}
			if (check == goalkeeper) {
				GLabel label2 = new GLabel("You won");
				label2.setFont("Times-90");
				label2.setColor(Color.BLUE);
				add(label2, (getWidth() - label2.getWidth()) / 2, (getHeight() + label2.getAscent()) / 2);
			} else {
				GLabel label2 = new GLabel("You lost");
				label2.setFont("Times-90");
				label2.setColor(Color.RED);
				add(label2, (getWidth() - label2.getWidth()) / 2, (getHeight() + label2.getAscent()) / 2);
			}
			break;

		}
	}

	// the method which makes the goalkeeper move by using keyboard
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			goalkeeper.move(-2, 0);
			break;
		case KeyEvent.VK_RIGHT:
			goalkeeper.move(+2, 0);
			break;
		case KeyEvent.VK_DOWN:
			goalkeeper.move(0, 2);
			break;
		case KeyEvent.VK_UP:
			goalkeeper.move(0, -2);
			break;
		}
	}

	// the method which creates and adds player who kicks the ball when games is
	// started
	private void addPlayer() {
		// head of the player
		GOval oval = new GOval(700, 290, 39, 42);
		oval.setFilled(true);
		oval.setFillColor(Color.YELLOW);
		add(oval);
		// body of the player
		GRect rect = new GRect(710, 332, 19, 50);
		rect.setFilled(true);
		rect.setFillColor(Color.RED);
		add(rect);
		// name of the player
		GLabel label = new GLabel("MESSI");
		add(label, 711, 362);
		label.setFont("NewTimesRoman-6");
		// foot of the player
		GRect rect2 = new GRect(717, 382, 7, 60);
		rect2.setFilled(true);
		rect2.setFillColor(Color.BLACK);
		add(rect2);

	}

	// creates a door with lines
	private void createDoor() {

		add(new GLine(0, 80, 330, 0));
		add(new GLine(0, 80, 65, 120));
		add(new GLine(65, 120, 395, 40));
		add(new GLine(330, 0, 395, 40));
		add(new GLine(0, 80, 18, 304));
		add(new GLine(65, 120, 83, 344));
		add(new GLine(18, 304, 83, 344));
		add(new GLine(395, 40, 420, 250));
		add(new GLine(83, 344, 420, 250));
		add(new GLine(330, 0, 333, 21));
		add(new GLine(336, 42, 339, 63));
		add(new GLine(342, 84, 345, 105));
		add(new GLine(348, 126, 351, 147));
		add(new GLine(354, 168, 357, 189));
		add(new GLine(18, 304, 50, 298));
		add(new GLine(82, 289, 114, 283));
		add(new GLine(146, 275, 178, 269));
		add(new GLine(214, 258, 242, 252));
		add(new GLine(280, 241, 312, 233));
		add(new GLine(340, 224, 360, 219));
		add(new GLine(377, 227, 397, 235));
		add(new GLine(45, 70, 112, 107));
		add(new GLine(90, 56, 159, 98));
		add(new GLine(143, 44, 203, 86));
		add(new GLine(198, 30, 250, 73));
		add(new GLine(248, 17, 295, 63));
		add(new GLine(293, 8, 337, 52));
		add(new GLine(32, 99, 357, 16));
		add(new GLine(32, 99, 46, 322));
		add(new GLine(3, 124, 70, 171));
		add(new GLine(7, 171, 72, 213));
		add(new GLine(11, 217, 75, 254));
		add(new GLine(15, 264, 79, 299));
		add(new GLine(340, 60, 402, 101));
		add(new GLine(345, 110, 408, 150));
		add(new GLine(353, 164, 415, 202));
		add(new GLine(357, 16, 385, 229));

	}

	public static void main(String[] args) {
		new TestProject().start();
	}

}
