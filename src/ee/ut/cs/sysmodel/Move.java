package ee.ut.cs.sysmodel;

/**
 * User: Karl Kilgi
 * Date: 10/8/12
 * Time: 10:01 PM
 */

public class Move {
    private int fromPoint;
    private int toPoint;

    public Move(int fromPoint, int toPoint) {
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
    }
    
    public int getFromPoint() {
        return fromPoint;
    }

    public int getToPoint() {
        return toPoint;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Move) {
            Move move = (Move) object;
            return (this.fromPoint == move.fromPoint) && (this.toPoint == move.toPoint);
        } else {
            return false;
        }
    }
}
