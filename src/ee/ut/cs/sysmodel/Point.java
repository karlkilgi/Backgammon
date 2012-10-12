package ee.ut.cs.sysmodel;

/**
 * User: Karl Kilgi
 * Date: 10/8/12
 * Time: 9:09 PM
 */

public class Point {
    private Player player;
    private int position;
    private int numberOfCheckers;
    
    public Point(int position) {
        this.position = position;
        this.numberOfCheckers = 0;
        this.player = Player.NONE;
    }
    
    public int getNumberOfCheckers() {
        return numberOfCheckers;
    }

    public Player getPlayer() {
        return player;
    }

    public void setNumberOfCheckers(int numberOfCheckers) {
        this.numberOfCheckers = numberOfCheckers;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean canAdd(Player player) {
        return this.player == player || numberOfCheckers <= 1;
    }

    public boolean addChecker(Player player) {
        boolean sendOpponentCheckerToBar = this.player != player && this.player != Player.NONE;
        if (sendOpponentCheckerToBar) {
            numberOfCheckers = 1;
        } else {
            numberOfCheckers++;
        }
        this.player = player;
        return sendOpponentCheckerToBar;
    }

    public void removeChecker() {
        numberOfCheckers--;
        if (numberOfCheckers == 0) {
            player = Player.NONE;
        }
    }

    public int getPosition() {
        return position;
    }
}
