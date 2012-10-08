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

    public void setBetSize(int betSize) {
        this.betSize = betSize;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

    public Bar getPlayer1Bar() {
        return player1Bar;
    }

    public void setPlayer1Bar(Bar player1Bar) {
        this.player1Bar = player1Bar;
    }

    public Bar getPlayer2Bar() {
        return player2Bar;
    }

    public void setPlayer2Bar(Bar player2Bar) {
        this.player2Bar = player2Bar;
    }
}
