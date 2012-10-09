package ee.ut.cs.sysmodel.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ee.ut.cs.sysmodel.Player;


public abstract class GWidget {

	protected GFrame frame;
	protected JPanel panel;
	
	protected abstract void setPanel(JPanel panel);
	
	protected abstract int getNumberOfCheckers();
	
	protected abstract Color getBackgroundColor();
	
	protected abstract boolean isUp();
	
	protected abstract Player getPlayer();
	
	public abstract int getPosition();
	
	public void refresh() {
		panel.removeAll();
		panel.setBorder(BorderFactory.createEmptyBorder());
		int nrOfCheckers = getNumberOfCheckers();
		for (int i = 0; i < nrOfCheckers; i++) {
			JPanel checker = createChecker();
			panel.add(checker);
		}
		if (!isUp()) {
			for (int i = 0; i < 15 - nrOfCheckers; i++ ) {
				JPanel emptyPanel = createEmptyChecker();
				panel.add(emptyPanel,0);
			}
		}
	}
	
	protected JPanel createChecker() {
		JPanel checker = createEmptyChecker();
		checker.setBackground(getCheckerColor());
		checker.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.BLACK));
		return checker;
	}
	
	protected JPanel createEmptyChecker() {
		JPanel checker = new JPanel();
		checker.setBackground(getBackgroundColor());
		return checker;
	}
	
	protected Color getCheckerColor() {
		return getPlayer().getColor();
	}
	
	public static GridLayout layout;
	public static GridLayout getLayout() {
		if (layout != null) {
			return layout;
		}
		layout = new GridLayout();
		layout.setColumns(1);
		layout.setRows(15);
		return layout;
	}
	
}
