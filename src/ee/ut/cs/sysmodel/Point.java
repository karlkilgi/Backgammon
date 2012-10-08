package ee.ut.cs.sysmodel;

/**
 * User: Karl Kilgi
 * Date: 10/8/12
 * Time: 9:09 PM
 */

public class Point {
    int position;
    int numberOfCheckers;
    Player player;

    Point(int position) {
        this.position = position;
        numberOfCheckers = 0;
        player = Player.NONE;
    }

    public boolean canAdd(Player player) {
        return this.player == player || numberOfCheckers <= 1;
    }

    public void addChecker(Player player) {
        this.player = player;
        numberOfCheckers++;
    }

    public void removeChecker() {
        numberOfCheckers--;
        if (numberOfCheckers == 0) {
            player = Player.NONE;
        }
    }
}
