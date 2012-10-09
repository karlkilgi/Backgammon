package ee.ut.cs.sysmodel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GBar {

	private GFrame frame;
	private Player player;
	private JPanel panel;
	
	public GBar(GFrame frame, Player player) {
		this.frame = frame;
		this.player = player;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
		panel.setBackground(Color.PINK);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void refresh() {

	}
	
	private boolean isUp() {
		return false;
	}
	
}
