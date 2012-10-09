package ee.ut.cs.sysmodel;

import java.awt.Color;

/**
 * User: Karl Kilgi
 * Date: 10/8/12
 * Time: 9:34 PM
 */
public enum Player {
    PLAYER1(Color.RED, 0, new Bar()), PLAYER2(Color.WHITE, 25, new Bar()), NONE;

    private final Color color;
    private Bar bar;

    public Bar getBar() {
        return bar;
    }

    public int getHomePoint() {
        return homePoint;
    }

    private final int homePoint;

    Player() {
        this.color = null;
        this.homePoint = 0;
    }

    Player(Color color, int homePoint, Bar bar) {
        this.color = color;
        this.homePoint = homePoint;
        this.bar = bar;
    }

    public Color getColor() {
        return color;
    }


}
