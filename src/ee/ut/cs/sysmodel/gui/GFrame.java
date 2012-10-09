package ee.ut.cs.sysmodel.gui;

import java.awt.GridLayout;
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

	public GFrame(Game game) {
		this.game = game;
		player1Bar = new GBar(this, Player.PLAYER1);
		player2Bar = new GBar(this, Player.PLAYER2);
		player1Home = new GHome(this, Player.PLAYER1);
		player2Home = new GHome(this, Player.PLAYER2);
		buildGraphics();
	}

	public void showChangePlayersPopup() {
		String message = game.getInActivePlayer().getName()
				+ ", your turn is over. " + game.getActivePlayer().getName()
				+ ", throw dice";
		JOptionPane.showMessageDialog(frame, message);
		game.onDiceThrow();
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

	private void buildGraphics() {

		GridLayout layout = new GridLayout();
		layout.setColumns(15);
		layout.setRows(2);
		layout.setHgap(10);
		layout.setVgap(20);

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 600, 400);
		frame.setResizable(false);

		frame.setLayout(layout);

		Point[] points = game.getPoints();

		for (int i = 13; i <= 18; i++) {
			createGPoint(i, frame, points);
		}

		JPanel bar1Panel = new JPanel();
		player1Bar.setPanel(bar1Panel);
		frame.add(bar1Panel);

		for (int i = 19; i <= 24; i++) {
			createGPoint(i, frame, points);
		}

		JPanel home1Panel = new JPanel();
		player1Home.setPanel(home1Panel);
		frame.add(home1Panel);

		for (int i = 12; i >= 7; i--) {
			createGPoint(i, frame, points);
		}

		JPanel bar2Panel = new JPanel();
		player2Bar.setPanel(bar2Panel);
		frame.add(bar2Panel);

		for (int i = 6; i >= 1; i--) {
			createGPoint(i, frame, points);
		}

		JPanel home2Panel = new JPanel();
		player2Home.setPanel(home2Panel);
		frame.add(home2Panel);

		refresh();
		frame.setVisible(true);
	}

	private void createGPoint(int i, JFrame frame, Point[] points) {
		GPoint gPoint = new GPoint(this, points[i]);
		JPanel panel = new JPanel();
		gPoint.setPanel(panel);
		frame.add(panel);
		gPoints.add(gPoint);
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
			return;
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
	}

}
