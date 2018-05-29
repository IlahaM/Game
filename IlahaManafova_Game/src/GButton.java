import java.awt.Color;
import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;

public class GButton extends GCompound {

	private GRect rect;
	private GLabel label;

	public GButton(String caption, int width, int height) {
		rect = new GRect(0, 0, width, height);
		rect.setFilled(true);
		add(rect);

		label = new GLabel(caption);

		add(label);

		// Set label to center of button
		label.setLocation((width - label.getWidth()) / 2, (height + label.getHeight()) / 2 - 8);
	}

	public void setCaption(String title) {
		label.setLabel(title);
		// Set label to center of button
		label.setLocation((getWidth() - label.getWidth()) / 2, (getHeight() + label.getHeight()) / 2 - 8);
	}

	public void setForgroundColor(Color color) {
		label.setColor(color);
	}

	public void setBackgroundColor(Color color) {
		rect.setFillColor(color);
	}

}
