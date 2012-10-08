package ee.ut.cs.sysmodel;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * User: Karl Kilgi
 * Date: 10/8/12
 * Time: 8:28 PM
 */

public class Dice {

    public List<Integer> throwDice() {
        List<Integer> diceResult = new LinkedList<Integer>();
        Random dice = new Random();
        int firstDiceResult = dice.nextInt(6) + 1;
        int secondDiceResult = dice.nextInt(6) + 1;

        if (firstDiceResult != secondDiceResult) {
            diceResult.add(firstDiceResult);
            diceResult.add(secondDiceResult);
        } else {
            for (int i = 0; i <= 3; i++)
                diceResult.add(firstDiceResult);
        }
        return diceResult;
    }
}
