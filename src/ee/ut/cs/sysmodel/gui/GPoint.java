package ee.ut.cs.sysmodel.gui;

import javax.swing.*;

import ee.ut.cs.sysmodel.Player;
import ee.ut.cs.sysmodel.Point;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GPoint extends GWidget {

	private Point point;
	
	public GPoint(GFrame frame, Point point) {
		this.frame = frame;
		this.point = point;
	}

	@Override
	public void setPanel(final JPanel panel) {
		this.panel = panel;
		panel.setBackground(getBackgroundColor());
		final GPoint gPoint = this;
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!frame.isFromSelected() && getNumberOfCheckers() < 1) {
					return;
				}
				frame.onWidgetClick(gPoint);
				panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2));
			}
		});
		panel.setLayout(getLayout());
		panel.setVisible(true);
	}
	
	@Override
	protected boolean isUp() {
		return point.getPosition() > 12;
	}
	
	@Override
	protected Color getBackgroundColor() {
		return point.getPosition() % 2 == 0 ? Color.LIGHT_GRAY : Color.GRAY;
	}

	@Override
	protected int getNumberOfCheckers() {
		return point.getNumberOfCheckers();
	}

	@Override
	protected Player getPlayer() {
		return point.getPlayer();
	}

	@Override
	public int getPosition() {
		return point.getPosition();
	}
	
}
