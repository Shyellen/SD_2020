package GUI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class RoundedButton3 extends JButton {
	public RoundedButton3() {
		super();
		decorate();
	}
	
	public RoundedButton3(String text) {
		super(text);
		decorate();
	}
	
	public RoundedButton3(Action action) {
		super(action);
		decorate();
	}
	public RoundedButton3(Icon icon) {
		super(icon);
		decorate();
	}
	public RoundedButton3(String text, Icon icon) {
		super(text, icon);
		decorate();
	}
	
	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();
		
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if (getModel().isArmed()) {
			graphics.setColor(new Color(104, 152, 55));
		}
		else if (getModel().isRollover()) {
			graphics.setColor(new Color(174, 212, 136));
		}
		else if (!getModel().isEnabled()) {
			graphics.setColor(new Color(169, 168, 171));
		}
		else {
			graphics.setColor(new Color(138, 193, 83));
		}
		graphics.fillRoundRect(0, 0, width, height, 10, 10);
		
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
		
		int textX = (width - stringBounds.width) / 2;
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
		
		graphics.setColor(getForeground());
		graphics.setFont(getFont());
		graphics.drawString(getText(), textX, textY);
		graphics.dispose();
		
		super.paintComponent(g);
	}
}