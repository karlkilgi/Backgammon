package ee.ut.cs.sysmodel;

/**
 * User: Karl Kilgi
 * Date: 10/8/12
 * Time: 9:47 PM
 */

public class Bar {
    int numberOfCheckers;
    Player player;

    Bar(int numberOfCheckers, Player player) {
        this.numberOfCheckers = numberOfCheckers;
        this.player = player;
    }

    public boolean isEmpty() {
        return numberOfCheckers == 0;
    }
}
