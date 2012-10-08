package ee.ut.cs.sysmodel;

/**
 * User: Karl Kilgi
 * Date: 10/7/12
 * Time: 10:10 PM
 */

public class Game {

    private int betSize = 1;
    private Player activePlayer = Player.NONE;
    Point[] points = new Point[24];
    Bar player1Bar = new Bar(0, Player.PLAYER1);
    Bar player2Bar = new Bar(0, Player.PLAYER2);

    Game() {
        initializeGame();
    }

    private void initializeGame() {

        int positionNumber = 1;
        for (Point point : points) {
            point = new Point(positionNumber);
            positionNumber++;
        }
        initializeCheckers();
    }

    private void initializeCheckers() {
        //For Player1
        points[23].setNumberOfCheckers(2);
        points[23].setPlayer(Player.PLAYER1);

        points[12].setNumberOfCheckers(5);
        points[12].setPlayer(Player.PLAYER1);

        points[7].setNumberOfCheckers(3);
        points[7].setPlayer(Player.PLAYER1);

        points[5].setNumberOfCheckers(5);
        points[5].setPlayer(Player.PLAYER1);

        //For Player2
        points[0].setNumberOfCheckers(2);
        points[0].setPlayer(Player.PLAYER2);

        points[7].setNumberOfCheckers(5);
        points[7].setPlayer(Player.PLAYER2);

        points[16].setNumberOfCheckers(3);
        points[16].setPlayer(Player.PLAYER2);

        points[5].setNumberOfCheckers(18);
        points[5].setPlayer(Player.PLAYER2);
    }

    public static void main(String[] args) {
        Game game = new Game();
    }

    public void onBetIncrease() {
        betSize = betSize * 2;
    }

    public void sendCheckerToBar() {

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
}
