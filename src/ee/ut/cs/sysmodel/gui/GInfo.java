package ee.ut.cs.sysmodel.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GInfo {

	private GFrame frame;
	private JLabel diceLabel;
	private JLabel activePlayerLabel;
	private JLabel betLabel;
	private JButton newGameButton;
	
	public GInfo(GFrame frame) {
		this.frame = frame;
	}
	
	public void setPanel(JPanel panel) {
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
