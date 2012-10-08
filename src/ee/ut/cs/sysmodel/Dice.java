package ee.ut.cs.sysmodel;

import java.util.Arrays;
import java.util.Random;

/**
 * User: Karl Kilgi
 * Date: 10/8/12
 * Time: 8:28 PM
 */

public class Dice {

    public int[] throwDice() {
        int[] diceResult;
        Random dice = new Random();
        int firstDiceResult = dice.nextInt(6) + 1;
        int secondDiceResult = dice.nextInt(6) + 1;

        if (firstDiceResult != secondDiceResult) {
            diceResult = new int[2];
            diceResult[0] = firstDiceResult;
            diceResult[1] = secondDiceResult;
        } else {
            diceResult = new int[4];
            Arrays.fill(diceResult, firstDiceResult);
        }
        return diceResult;
    }
}
