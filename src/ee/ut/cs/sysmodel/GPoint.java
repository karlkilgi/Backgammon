package ee.ut.cs.sysmodel;

import java.awt.GridLayout;
import javax.swing.JPanel;

public class GPoint {

	private GFrame frame;
	private Point point;
	private JPanel panel;
	
	public GPoint(GFrame frame, Point point) {
		this.frame = frame;
		this.point = point;
	}
	
	public int getPosition() {
		return point.getPosition();
	}

	public void refresh() {
		panel.removeAll();
		for (int i = 0; i < point.getNumberOfCheckers(); i++) {
			JPanel checker = new JPanel();
			checker.setBackground(point.getPlayer().getColor());
			checker.setVisible(true);
			panel.add(checker);
		}
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
		GridLayout layout = new GridLayout();
		layout.setColumns(1);
		layout.setRows(15);
		panel.setLayout(layout);
		panel.setVisible(true);
	}
	
}
