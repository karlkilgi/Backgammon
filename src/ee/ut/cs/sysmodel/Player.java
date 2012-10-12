package ee.ut.cs.sysmodel;

import java.awt.Color;

/**
 * User: Karl Kilgi
 * Date: 10/8/12
 * Time: 9:34 PM
 */
public enum Player {
    PLAYER1(Color.RED, 0, new Bar(), "Player 1"),
    PLAYER2(Color.WHITE, 25, new Bar(), "Player 2"),
    NONE;
    
    private final int homePoint;
    private final Color color;
    private Bar bar;
    private final String name;

    Player() {
        this.color = null;
        this.homePoint = -1;
        this.name = "NONE";
    }

    Player(Color color, int homePoint, Bar bar, String name) {
        this.color = color;
        this.homePoint = homePoint;
        this.bar = bar;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }
    
    public Bar getBar() {
        return bar;
    }

    public int getHomePoint() {
        return homePoint;
    }
    
    public String getName() {
    	return name;
    }


}
