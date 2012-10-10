package ee.ut.cs.sysmodel.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import ee.ut.cs.sysmodel.Player;

public class GHome extends GWidget {

	private Player player;

	public GHome(GFrame frame, Player player) {
		this.frame = frame;
		this.player = player;
	}

	@Override
	protected int getNumberOfCheckers() {
		return 0;
	}

	@Override
	protected Color getBackgroundColor() {
		return Color.PINK;
	}

	@Override
	protected boolean isUp() {
		return player == Player.PLAYER2;
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
		return BorderFactory.createBevelBorder(BevelBorder.LOWERED);
	}

	@Override
	public MouseAdapter getMouseAdapter() {
		final GHome w = this;
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!frame.isFromSelected()) {
					return;
				}
				frame.onWidgetClick(w);
			}
		};
	}

}
