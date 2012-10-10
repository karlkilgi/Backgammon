package ee.ut.cs.sysmodel.gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ee.ut.cs.sysmodel.Game;

public class GInfo {

	private Game game;
	private JPanel panel;
	private JLabel dice;
	private JLabel activePlayer;
	
	public GInfo(Game game) {
		this.game = game;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		FlowLayout layout = new FlowLayout(FlowLayout.LEADING, 20, 0);
		panel.setLayout(layout);
		
		dice = new JLabel();
		activePlayer = new JLabel();
		panel.add(dice);
		panel.add(activePlayer);
	}
	
	public void refresh() {
		dice.setText("Dice: " + "loldno");
		if (game != null) {
			activePlayer.setText("Active player: " + game.getActivePlayer().getName());
		}
	}
	
}
