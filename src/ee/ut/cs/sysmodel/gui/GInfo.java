package ee.ut.cs.sysmodel.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ee.ut.cs.sysmodel.Game;

public class GInfo {

	private GFrame frame;
	private JPanel panel;
	private JLabel diceLabel;
	private JLabel activePlayerLabel;
	private JLabel betLabel;
	private JButton doubleBetButton;
	private JButton newGameButton;
	
	public GInfo(GFrame frame) {
		this.frame = frame;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		FlowLayout layout = new FlowLayout(FlowLayout.LEADING, 20, 0);
		panel.setLayout(layout);
		
		diceLabel = new JLabel();
		activePlayerLabel = new JLabel();
		betLabel = new JLabel();
		
		panel.add(activePlayerLabel);
		panel.add(diceLabel);
		panel.add(betLabel);
		
		doubleBetButton = new JButton("Double bet");
		doubleBetButton.addActionListener(new ActionListener() {
          
          @Override
          public void actionPerformed(ActionEvent e) {
            frame.onDoubleBet();
          }
        });
		
		panel.add(doubleBetButton);
		
		newGameButton = new JButton("New game");
		newGameButton.addActionListener(new ActionListener() {
          
          @Override
          public void actionPerformed(ActionEvent e) {
            frame.onNewgame();
          }
        });
		
		panel.add(newGameButton);
	}
	
	public void refresh() {
		activePlayerLabel.setText("Turn: " + frame.getPlayerInfo());
		diceLabel.setText("Dice: " + frame.getDiceInfo());
		betLabel.setText("Bet : " + frame.getBetInfo());
	}
	
}
