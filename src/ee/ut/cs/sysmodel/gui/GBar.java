package ee.ut.cs.sysmodel.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import ee.ut.cs.sysmodel.Player;

public class GBar extends GWidget {

	private Player player;
	
	public GBar(GFrame frame, Player player) {
		this.frame = frame;
		this.player = player;
	}
	
	@Override
	protected boolean isUp() {
		return player == Player.PLAYER1;
	}

	@Override
	protected int getNumberOfCheckers() {
		return player.getBar().getNumberOfCheckers();
	}

	@Override
	protected Color getBackgroundColor() {
		return Color.ORANGE;
	}

	@Override
	protected Player getPlayer() {
		return player;
	}

	@Override
	public int getPosition() {
	return isUp() ? 25 : 0;
	}

	@Override
	public Border getBorder() {
		return BorderFactory.createLineBorder(Color.BLACK);
	}

	@Override
	public MouseAdapter getMouseAdapter() {
		final GBar gBar = this;
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (frame.isFromSelected() || getNumberOfCheckers() < 1) {
					return;
				}
				panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2));
				frame.onWidgetClick(gBar);
			}
		};
	}
	
}
