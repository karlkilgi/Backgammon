package ee.ut.cs.sysmodel;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Karl Kilgi
 * Date: 10/7/12
 * Time: 10:10 PM
 */

public class Game {

    private int betSize = 1;
    private Player activePlayer = Player.NONE;
    Point[] points = new Point[26];
    Bar player1Bar = new Bar(0, Player.PLAYER1);
    Bar player2Bar = new Bar(0, Player.PLAYER2);
    List<Move> availableMoves = new LinkedList<Move>();
    List<Integer> throwResults = new LinkedList<Integer>();
    Dice dice = new Dice();
    private List<Integer> diceMovesLeft;
    GFrame frame;
    
    Game() {
        initializeGame();
        frame = new GFrame(this);
    }

    private void initializeGame() {

        for (int i = 0; i <= 25; i++) {
            points[i] = new Point(i);
        }
        initializeCheckers();
    }

    private void initializeCheckers() {
        //For Player1
        points[24].setNumberOfCheckers(2);
        points[24].setPlayer(Player.PLAYER1);

        points[13].setNumberOfCheckers(5);
        points[13].setPlayer(Player.PLAYER1);

        points[8].setNumberOfCheckers(3);
        points[8].setPlayer(Player.PLAYER1);

        points[6].setNumberOfCheckers(5);
        points[6].setPlayer(Player.PLAYER1);

        //For Player2
        points[1].setNumberOfCheckers(2);
        points[1].setPlayer(Player.PLAYER2);

        points[12].setNumberOfCheckers(5);
        points[12].setPlayer(Player.PLAYER2);

        points[17].setNumberOfCheckers(3);
        points[17].setPlayer(Player.PLAYER2);

        points[19].setNumberOfCheckers(5);
        points[19].setPlayer(Player.PLAYER2);
    }

    public static void main(String[] args) {
        Game game = new Game();
    }

    public void onBetIncrease() {
        betSize = betSize * 2;
    }

    public void sendCheckerToBar() {

    }

    public void onMove(int fromPoint, int toPoint) {
        Move playerMove = new Move(fromPoint, toPoint);
        for (Move move : availableMoves) {
            if (move.equals(playerMove)) {
                points[playerMove.getFromPoint()].removeChecker();
                points[playerMove.getToPoint()].addChecker(activePlayer);
                setAvailableMoves(getDiceMovesLeft(playerMove.getFromPoint(), playerMove.getToPoint()));
                if (availableMoves.isEmpty()) {
                    changeActivePlayer();
                }
            }
        }
        frame.refresh();
    }

    public List<Integer> onDiceThrow() {
        throwResults = dice.throwDice();
        diceMovesLeft = throwResults;
        setAvailableMoves(throwResults);
        if (availableMoves.isEmpty()) {
            changeActivePlayer();
            return null;
        } else {
            return throwResults;
        }
    }

    private void changeActivePlayer() {
        //TODO set text about no moves left, changing player
        if (activePlayer == Player.PLAYER1) {
            activePlayer = Player.PLAYER2;
        } else {
            activePlayer = Player.PLAYER1;
        }
    }

    public void setAvailableMoves(List<Integer> throwResult) {
        availableMoves.clear();
        int toPoint;
        boolean homeGame = true;
        List<Point> populatedPoints = getActivePlayerPopulatedPoints();
        for (Point populatedPoint : populatedPoints) {
            if (activePlayer == Player.PLAYER1) {
                if (populatedPoint.getPosition() > 6) {
                    homeGame = false;
                }
            }
            if (activePlayer == Player.PLAYER2) {
                if (populatedPoint.getPosition() < 19) {
                    homeGame = false;
                }
            }
        }

        for (Point populatedPoint : populatedPoints) {
            for (int i = 0; i <= 1; i++) {
                int fromPoint = populatedPoint.getPosition();
                //Detecting if player can put the checker to home
                if (activePlayer == Player.PLAYER1) {
                    toPoint = fromPoint - throwResult.get(i);
                    if (toPoint < activePlayer.getHomePoint()) {
                        toPoint = activePlayer.getHomePoint();
                    }
                } else {
                    toPoint = fromPoint + throwResult.get(i);
                    if (toPoint > activePlayer.getHomePoint()) {
                        toPoint = activePlayer.getHomePoint();
                    }
                }
                if (points[toPoint].canAdd(activePlayer)) {
                    /*
                    TODO check that player cant put checker from position
                    1 to 0 if throw was 6 and in position 6 checker exists.
                    */
                    if (homeGame && toPoint == activePlayer.getHomePoint()) {
                        availableMoves.add(new Move(fromPoint, toPoint));
                    }
                    if (toPoint != activePlayer.getHomePoint()) {
                        availableMoves.add(new Move(fromPoint, toPoint));
                    }
                }
                //No need to populate list with same moves
                if (throwResult.size() > 2) {
                    break;
                }
            }
        }
    }

    public List<Point> getActivePlayerPopulatedPoints() {
        List<Point> populatedPoints = new LinkedList<Point>();
        for (int i = 1; i <= 24; i++) {
            if (points[i].getPlayer() == activePlayer) {
                populatedPoints.add(points[i]);
            }
        }
        return populatedPoints;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public int getBetSize() {
        return betSize;
    }

    private void setBetSize(int betSize) {
        this.betSize = betSize;
    }

    public Point[] getPoints() {
        return points;
    }

    private void setPoints(Point[] points) {
        this.points = points;
    }

    public Bar getPlayer1Bar() {
        return player1Bar;
    }

    public Bar getPlayer2Bar() {
        return player2Bar;
    }

    public List<Integer> getDiceMovesLeft(int fromPoint, int toPoint) {
        int usedMove;
        if (activePlayer == Player.PLAYER1) {
            usedMove = fromPoint - toPoint;
        } else {
            usedMove = toPoint - fromPoint;
        }
        diceMovesLeft.remove(usedMove);
        return diceMovesLeft;
    }
}
