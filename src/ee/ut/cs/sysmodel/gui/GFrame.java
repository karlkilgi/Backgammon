package ee.ut.cs.sysmodel.gui;

import java.awt.Color;

import javax.swing.BorderFactory;

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

    // Is starting position of a move selected
    public boolean isFromSelected() {
        return moveFrom != null;
    }

    // Repaint the whole frame
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

	// EVENT HANDLERS start here
	
	// Handles events from points, bars and homes
	public void onWidgetClick(GWidget widget) {
	    // If starting position of a move has not been set yet
	    // then register the widget as one
		if (!isFromSelected()) {
			moveFrom = widget;
			return;
		}

		// If widget's starting position is the same as registered starting position
		// then unregister the starting position (unselect the widget)
		if (isFromSelected() && moveFrom.getPosition() == widget.getPosition()) {
			moveFrom.refresh();
			moveFrom = null;
			return;
		}

		// If we have both starting and ending position of a move
		// then send the move to THE GAME
		if (isFromSelected() && moveFrom.getPosition() != widget.getPosition()) {
			game.onMove(moveFrom.getPosition(), widget.getPosition());
			moveFrom.refresh();
			widget.refresh();
			moveFrom = null;
		}
	}
	
    public void onDoubleBet() {
      game.increaseBet();
      infoPanel.refresh();
    }
    
    public void onNewgame() {
      String message = "Are you sure you want to cancel the game and start a new one?";
      Object[] options = {"yea wadeva", "naw bro"};
      int answer = JOptionPane.showOptionDialog(frame,
          message,
          "Start a new game",
          JOptionPane.OK_CANCEL_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);
      if (answer == 0) {
        // TODO game.startNewGame();
      }
    }
    
    // EVENT HANDLERS end here
	
	// POP UPS start here
	
    public void showStartingPlayerPopup(List<Integer> throwresults) {
      String message = Player.PLAYER1.getName() + " threw " + throwresults.get(0) + "\n";
      message += Player.PLAYER2.getName() + " threw " + throwresults.get(1) + "\n\n";
      message += game.getActivePlayer().getName() + " starts!";
      showMessagePopup(message, "OK", JOptionPane.INFORMATION_MESSAGE);
    }
    
	public void showChangePlayersPopup() {
		String message = game.getInActivePlayer().getName()
				+ ", your turn is over. " + game.getActivePlayer().getName()
				+ ", it's your move";
		
	    Object[] options = {"Throw", "Double bets and throw"};
	      int answer = JOptionPane.showOptionDialog(frame,
	          message,
	          "Doubling the bet",
	          JOptionPane.DEFAULT_OPTION,
	          JOptionPane.QUESTION_MESSAGE,
	          null,
	          options,
	          options[0]);
	      if (answer == 0 || showAcceptDoublingPopup()) {
	        infoPanel.refresh();
	      }
	}
	
	public void showBeginningPopup() {
		String message = "Game started. Throw dice to choose starting player";
		showMessagePopup(message, "Throw", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showDiceResultsPopup(List<Integer> results) {
		String message = game.getActivePlayer().getName() + " threw " + getIntegerListAsString(results);
		showMessagePopup(message, "OK", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showIllegalMovePopUp() {
		String message = "Illegal move!";
		showMessagePopup(message, "OK", JOptionPane.WARNING_MESSAGE);
	}
	
	private boolean showAcceptDoublingPopup() {
	  String message = game.getInActivePlayer().getName() + ", do you accept doubling the bet?";
	  Object[] options = {"Yes", "No, I'll resign"};
      int answer = JOptionPane.showOptionDialog(frame,
          message,
          "Doubling the bet",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          options,
          options[0]);
      if (answer == 0) {
        game.increaseBet();
        infoPanel.refresh();
        return true;
      } else {
        game.onWin();
        return false;
      }
	}
	
	private void showMessagePopup(String message, String buttonText, int messageType) {
	  Object[] options = {buttonText};
	     JOptionPane.showOptionDialog(frame,
	            message,
	            "",
	            JOptionPane.OK_OPTION,
	            messageType,
	            null,
	            options,
	            options[0]);
	}
	
	// POP UPS end here
	
	
	// CREATING GRAPHICS starts here (here be dragons)
	
	// Creates the main window and adds the main panel and the info panel to it
    private void createFrame() {
	        frame = new JFrame("Backgammon");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setBounds(100, 100, 600, 400);
	        frame.setLayout(new GridBagLayout());
	        frame.setResizable(false);
	        
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
	    
	// Creates the main panel and adds points, bars and homes to it
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
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
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
	
	private void createGPoint(int i, Point[] points, JPanel mainPanel) {
	        GPoint gPoint = new GPoint(this, points[i]);
	        JPanel panel = new JPanel();
	        gPoint.setPanel(panel);
	        mainPanel.add(panel);
	        gPoints.add(gPoint);
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
	
    private JPanel createInfoPanel() {
      JPanel panel = new JPanel();
      infoPanel = new GInfo(this);
      infoPanel.setPanel(panel);
      return panel;
    }

	// CREATING GRAPHICS ends here
    
    // GETTERS FOR INFO PANEL start here
    
    public String getDiceInfo() {
      return getIntegerListAsString(game.getDiceMovesLeft());
    }
    
    public String getPlayerInfo() {
      return game.getActivePlayer().getName();
    }
    
    public String getBetInfo() {
      return Integer.toString(game.getBetSize());
    }
    
    public String getAdditionalInfo() {
      return "TODO";
    }
    
    // GETTERS FOR INFO PANEL start here
    
    private String getIntegerListAsString(List<Integer> list) {
      if (list == null || list.isEmpty()) {
        return "none";
      }
      String str = "";
      for (Integer i : list) {
          str += i.toString() + " ";
      }
      return str;
    }

}
