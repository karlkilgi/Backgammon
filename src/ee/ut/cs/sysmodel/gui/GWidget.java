package ee.ut.cs.sysmodel.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import ee.ut.cs.sysmodel.Player;


public abstract class GWidget {

	protected GFrame frame;
	protected JPanel panel;
	
	protected abstract int getNumberOfCheckers();
	
	protected abstract Color getBackgroundColor();
	
	protected abstract boolean isUp();
	
	protected abstract Player getPlayer();
	
	public abstract int getPosition();
	
	public abstract Border getBorder();
	
	// Mouse listener which handles clicks on the widget
	// and decides whether to notice the frame
	public abstract MouseAdapter getMouseAdapter();
	
	public void setPanel(final JPanel panel) {
		this.panel = panel;
		panel.setBackground(getBackgroundColor());
		panel.setBorder(getBorder());
		panel.addMouseListener(getMouseAdapter());
		panel.setLayout(getLayout());
	}
	
	public void refresh() {
		panel.removeAll();
		panel.setBorder(getBorder());
		int nrOfCheckers = getNumberOfCheckers();
		for (int i = 0; i < nrOfCheckers; i++) {
			JPanel checker = createChecker();
			panel.add(checker);
		}
		// If widget is not upper, we need to add "invisible" checkers
		// to top of the widget to push real checkers downwards
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
	
	// Creates "invisible" checkers
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
