package ee.ut.cs.sysmodel;

import java.awt.Color;

/**
 * User: Karl Kilgi
 * Date: 10/8/12
 * Time: 9:34 PM
 */
public enum Player {
    PLAYER1 (Color.RED), PLAYER2(Color.WHITE), NONE;
    
    private final Color color;
    
    Player() {
    	this.color = null;
    }
    
    Player(Color color) {
    	this.color = color;
    }
    
    public Color getColor() {
    	return color;
    }
    
    
}
