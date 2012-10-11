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
	private JLabel dice;
	private JLabel activePlayer;
	private JLabel bet;
	private JButton doubleBetButton;
	
	public GInfo(GFrame frame) {
		this.frame = frame;
	}
	
	public void setPanel(JPanel panel) {
		this.panel = panel;
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		FlowLayout layout = new FlowLayout(FlowLayout.LEADING, 20, 0);
		panel.setLayout(layout);
		
		dice = new JLabel();
		activePlayer = new JLabel();
		bet = new JLabel();
		
		panel.add(activePlayer);
		panel.add(dice);
		panel.add(bet);
		
		doubleBetButton = new JButton("Double bet");
		doubleBetButton.addActionListener(new ActionListener() {
          
          @Override
          public void actionPerformed(ActionEvent e) {
            frame.onDoubleBet();
          }
        });
		
		panel.add(doubleBetButton);
	}
	
	public void refresh() {
		activePlayer.setText("Active player: " + frame.getPlayerInfo());
		dice.setText("Dice: " + frame.getDiceInfo());
		bet.setText("Bet : " + frame.getBetInfo());
	}
	
}
