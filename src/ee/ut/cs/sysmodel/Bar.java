package ee.ut.cs.sysmodel;

/**
 * User: Karl Kilgi
 * Date: 10/8/12
 * Time: 9:47 PM
 */

public class Bar {
    int numberOfCheckers;
    Player player;

    Bar() {
        this.numberOfCheckers = 0;
    }

    public boolean isEmpty() {
        return numberOfCheckers == 0;
    }

    public void addChecker() {
        numberOfCheckers++;
    }

    public void removeChecker() {
        numberOfCheckers--;
    }
}
