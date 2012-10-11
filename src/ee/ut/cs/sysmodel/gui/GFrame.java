package ee.ut.cs.sysmodel.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ee.ut.cs.sysmodel.Game;
import ee.ut.cs.sysmodel.Player;
import ee.ut.cs.sysmodel.Point;

public class GFrame {

	private Game game;
	private List<GPoint> gPoints = new ArrayList<GPoint>();

	private GWidget moveFrom;

	private GBar player1Bar;
	private GBar player2Bar;

	private GHome player1Home;
	private GHome player2Home;
	
	private JFrame frame;
	
	private GInfo infoPanel;

	public GFrame(Game game) {
		this.game = game;
		createFrame();
	}

	private void createFrame() {
		frame = new JFrame("Backgammon");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 600, 400);
		frame.setResizable(false);
		frame.setLayout(new GridBagLayout());
		frame.setResizable(true);
		
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 20;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		frame.add(createMainPanel(), c);
		
		c.insets = new Insets(10,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 0.0;
		frame.add(createInfoPanel(), c);
		frame.setVisible(true);

		refresh();
	}
	
	private JPanel createInfoPanel() {
		JPanel panel = new JPanel();
		infoPanel = new GInfo(game);
		infoPanel.setPanel(panel);
		return panel;
	}

	public void onWidgetClick(GWidget widget) {
		if (!isFromSelected()) {
			moveFrom = widget;
			return;
		}

		if (isFromSelected() && moveFrom.getPosition() == widget.getPosition()) {
			moveFrom.refresh();
			moveFrom = null;
			return;
		}

		if (isFromSelected() && moveFrom.getPosition() != widget.getPosition()) {
			game.onMove(moveFrom.getPosition(), widget.getPosition());
			moveFrom.refresh();
			widget.refresh();
			moveFrom = null;
		}
	}

	public boolean isFromSelected() {
		return moveFrom != null;
	}

	public void refresh() {
		for (GPoint gPoint : gPoints) {
			gPoint.refresh();
		}
		player1Bar.refresh();
		player2Bar.refresh();

		player1Home.refresh();
		player2Home.refresh();
		
		infoPanel.refresh();
	}
	
	// Popups start here
	public void showChangePlayersPopup() {
		String message = game.getInActivePlayer().getName()
				+ ", your turn is over. " + game.getActivePlayer().getName()
				+ ", throw dice";
		JOptionPane.showMessageDialog(frame, message);
	}
	
	public void showBeginningPopup() {
		String message = "Game started. Throw dice to choose starting player";
		JOptionPane.showMessageDialog(frame, message);
	}
	
	public void showDiceResults(List<Integer> results) {
		String message = game.getActivePlayer().getName() + " threw " + getThrowResultsAsString(results);
		JOptionPane.showMessageDialog(frame, message);
	}
	
	public void showIllegalMovePopUp() {
		String message = "Illegal move!";
		JOptionPane.showMessageDialog(frame, message);
	}
	
	private String getThrowResultsAsString(List<Integer> results) {
	      String str = "";
	      for (Integer i : results) {
	    	  str += i.toString() + " ";
	      }
	      return str;
	}
	
	// Popups end here
	
	
	// Building graphics starts here
	
	private JPanel createMainPanel() {

		GridLayout layout = new GridLayout();
		layout.setColumns(15);
		layout.setRows(2);
		layout.setHgap(10);
		layout.setVgap(20);

		player1Bar = new GBar(this, Player.PLAYER1);
		player2Bar = new GBar(this, Player.PLAYER2);
		player1Home = new GHome(this, Player.PLAYER1);
		player2Home = new GHome(this, Player.PLAYER2);
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(layout);

		Point[] points = game.getPoints();

		for (int i = 13; i <= 18; i++) {
			createGPoint(i, points, mainPanel);
		}

		createGBar(Player.PLAYER1, mainPanel);

		for (int i = 19; i <= 24; i++) {
			createGPoint(i, points, mainPanel);
		}

		createGHome(Player.PLAYER2, mainPanel);

		for (int i = 12; i >= 7; i--) {
			createGPoint(i, points, mainPanel);
		}

		createGBar(Player.PLAYER2, mainPanel);

		for (int i = 6; i >= 1; i--) {
			createGPoint(i, points, mainPanel);
		}

		createGHome(Player.PLAYER1, mainPanel);
		
		return mainPanel;
	}
	
	private void createGHome(Player player, JPanel mainPanel) {
		JPanel panel = new JPanel();
		if (player == Player.PLAYER1) {
			player1Home.setPanel(panel);
		} else {
			player2Home.setPanel(panel);
		}
		mainPanel.add(panel);
	}
	
	private void createGBar(Player player, JPanel mainPanel) {
		JPanel panel = new JPanel();
		if (player == Player.PLAYER1) {
			player1Bar.setPanel(panel);
		} else {
			player2Bar.setPanel(panel);
		}
		mainPanel.add(panel);
	}

	private void createGPoint(int i, Point[] points, JPanel mainPanel) {
		GPoint gPoint = new GPoint(this, points[i]);
		JPanel panel = new JPanel();
		gPoint.setPanel(panel);
		mainPanel.add(panel);
		gPoints.add(gPoint);
	}
	
	// Building graphics ends here

}
