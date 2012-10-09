package ee.ut.cs.sysmodel;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GFrame {

	private Game game;
	private List<GPoint> gPoints = new ArrayList<GPoint>();
	
	private Point fromPoint;
	private GBar player1Bar;
	private GBar player2Bar;
	
	public GFrame(Game game) {
		this.game = game;
		buildGraphics();
	}

	private void buildGraphics() {
		
		GridLayout layout = new GridLayout();
		layout.setColumns(14);
		layout.setRows(2);
		layout.setHgap(10);
		layout.setVgap(20);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setBounds(100, 100, 600, 400);
		frame.setResizable(false);
		
		frame.setLayout(layout);
		
		Point[] points = game.getPoints();
		
		for (int i = 13; i <= 18; i++) {
			createGPoint(i, frame, points);
		}
		
		player1Bar = new GBar(this, Player.PLAYER1);
		JPanel bar1Panel = new JPanel();
		player1Bar.setPanel(bar1Panel);
		frame.add(bar1Panel);
		
		for (int i = 19; i <= 24; i++) {
			createGPoint(i, frame, points);
		}
		
		for (int i = 12; i >= 7; i--) {
			createGPoint(i, frame, points);
		}
		
		player2Bar = new GBar(this, Player.PLAYER2);
		JPanel bar2Panel = new JPanel();
		player2Bar.setPanel(bar2Panel);
		frame.add(bar2Panel);
		
		for (int i = 6; i >= 1; i--) {
			createGPoint(i, frame, points);
		}
		
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
	
	public void refresh() {
		for (GPoint gPoint : gPoints) {
			gPoint.refresh();
		}
		player1Bar.refresh();
		player2Bar.refresh();
	}

	public void onPointClick(Point point) {
		if (isFromPointSelected() && fromPoint.getPosition() != point.getPosition()) {
			game.onMove(fromPoint.getPosition(), point.getPosition());
			fromPoint = null;
		} else {
			this.fromPoint = point;
		}
	}
	
	private boolean isFromPointSelected() {
		return fromPoint != null;
	}
	
}
