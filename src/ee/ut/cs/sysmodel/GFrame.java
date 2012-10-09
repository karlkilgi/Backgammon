package ee.ut.cs.sysmodel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GFrame {

	private Game game;
	private List<GPoint> gPoints = new ArrayList<GPoint>();
	
	private Point fromPoint;
	
	public GFrame(Game game) {
		this.game = game;
		buildGraphics();
	}

	private void buildGraphics() {
		
		GridLayout layout = new GridLayout();
		layout.setColumns(12);
		layout.setRows(2);
		layout.setHgap(10);
		layout.setVgap(20);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setBounds(100, 100, 600, 400);
		frame.setBackground(Color.BLACK);
		frame.setResizable(false);
		
		frame.setLayout(layout);
		
		Point[] points = game.getPoints();
		
		for (int i = 12; i < 24; i++) {
			createGPoint(i, frame, points);
		}
		
		for (int i = 11; i >= 0; i--) {
			createGPoint(i, frame, points);
		}
		
		frame.setVisible(true);
	}
	
	private void createGPoint(int i, JFrame frame, Point[] points) {
		GPoint gPoint = new GPoint(this, points[i + 1]);
		JPanel panel = new JPanel();
		gPoint.setPanel(panel);
		frame.add(panel);
		gPoints.add(gPoint);
	}
	
	public void refresh() {
		for (GPoint gPoint : gPoints) {
			gPoint.refresh();
		}
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
