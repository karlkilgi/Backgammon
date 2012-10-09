package ee.ut.cs.sysmodel.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ee.ut.cs.sysmodel.Player;

public class GBar extends GWidget {

	private Player player;
	
	public GBar(GFrame frame, Player player) {
		this.frame = frame;
		this.player = player;
	}

	@Override
	public void setPanel(final JPanel panel) {
		this.panel = panel;
		panel.setBackground(Color.PINK);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		final GBar gBar = this;
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!frame.isFromSelected() && getNumberOfCheckers() < 1) {
					return;
				}
				frame.onWidgetClick(gBar);
				panel.setBorder(BorderFactory.createLineBorder(Color.YELLOW,2));
			}
		});
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
		return panel.getBackground();
	}

	@Override
	protected Player getPlayer() {
		return player;
	}

	@Override
	public int getPosition() {
	return player == Player.PLAYER1 ? 25 : 0;
	}
	
}
