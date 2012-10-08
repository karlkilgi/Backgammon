package ee.ut.cs.sysmodel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
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
		int nrOfCheckers = point.getNumberOfCheckers();
		for (int i = 0; i < nrOfCheckers; i++) {
			JPanel checker = createChecker();
			panel.add(checker);
		}
		if (!isUp()) {
			for (int i = 0; i < 16 - nrOfCheckers; i++ ) {
				JPanel emptyPanel = createEmptyChecker();
				panel.add(emptyPanel,0);
			}
		}
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
		panel.setBackground(getBackGroundColor());
		GridLayout layout = new GridLayout();
		layout.setColumns(1);
		layout.setRows(16);
		panel.setLayout(layout);
		
		for (int i = 0; i < 16; i++) {
			panel.add(createEmptyChecker());
		}

		panel.setVisible(true);
		refresh();
	}
	
	private JPanel createEmptyChecker() {
		JPanel checker = new JPanel();
		checker.setBackground(getBackGroundColor());
		return checker;
	}
	
	private JPanel createChecker() {
		JPanel checker = new JPanel();
		checker.setBackground(point.getPlayer().getColor());
		checker.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.BLACK));
		return checker;
	}
	
	private boolean isUp() {
		return point.getPosition() > 12;
	}
	
	private Color getBackGroundColor() {
		return point.getPosition() % 2 == 0 ? Color.LIGHT_GRAY : Color.GRAY;
	}
	
}
