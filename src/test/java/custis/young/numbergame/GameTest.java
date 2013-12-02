package custis.young.numbergame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {

    private final Game game = new DichotomyGame();
//    private final Game game = new GameImpl1();
//    private final Game game = new GameImpl2();
//    private final Game game = new GameImpl3();
//    private final Game game = new GameImpl4();
//    private final Game game = new GameImpl5();

    @Test
    public void basic() {
        final SimpleUser user = new SimpleUser(1_000_000);
        final int answer = game.play(user);
        assertEquals(user.getTheNumber(), answer);
    }

    @Test
    public void cornerCaseZero() {
        final SimpleUser user = new SimpleUser(1_000_000, 0);
        final int answer = game.play(user);
        assertEquals(user.getTheNumber(), answer);
    }

    @Test
    public void cornerCaseMaxValue() {
        final SimpleUser user = new SimpleUser(Integer.MAX_VALUE, Integer.MAX_VALUE);
        final int answer = game.play(user);
        assertEquals(user.getTheNumber(), answer);
    }

    @Test
    public void usersFraud() {
        final User user = new SimpleUser(1_000_000, -1);
        final int answer = game.play(user);
        assertTrue(answer < 0);
    }

    @Test
    public void repeatableUse() {
        for (int i = 0; i < 2; i++) {
            final SimpleUser user = new SimpleUser(1_000_000);
            final int answer = game.play(user);
            assertEquals(user.getTheNumber(), answer);
        }
    }

    @Test
    public void annoyingFactor() {
        final SimpleUser user = new SimpleUser(1_000_000);
        final int answer = game.play(user);
        assertTrue("answer = " + answer + "; askCount = " + user.getAskCount(), user.getAskCount() - (answer + 1) < 20);
    }
}
