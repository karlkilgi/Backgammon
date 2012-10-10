package ee.ut.cs.sysmodel.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import ee.ut.cs.sysmodel.Player;
import ee.ut.cs.sysmodel.Point;

public class GPoint extends GWidget {

	private Point point;
	
	public GPoint(GFrame frame, Point point) {
		this.frame = frame;
		this.point = point;
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

	@Override
	public Border getBorder() {
		return BorderFactory.createEmptyBorder();
	}

	@Override
	public MouseAdapter getMouseAdapter() {
		final GPoint gPoint = this;
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!frame.isFromSelected() && getNumberOfCheckers() < 1) {
					return;
				}
				panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2));
				frame.onWidgetClick(gPoint);
			}
		};
	}
	
}
